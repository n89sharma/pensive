package raml;

import Pensive.author.Author;
import Pensive.book.Book;
import Pensive.book.BookInfo;
import Pensive.comment.Comment;
import Pensive.definitions.DefinitionBundle;
import Pensive.note.Note;
import Pensive.note.NoteType;
import Pensive.paragraph.Paragraph;
import Pensive.user.User;
import Pensive.user.UserInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jsonSchema.JsonSchemaGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RamlComponents {

    ObjectMapper objectMapper = new ObjectMapper();
    JsonSchemaGenerator jsonSchemaGenerator = new JsonSchemaGenerator(objectMapper);

    @Test
    public void generateRamlComponents() throws JsonProcessingException, ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        BookInfo bookInfo = new BookInfo(1, "Cosmos", "Introduction");
        UserInfo userInfo = new UserInfo(1, "sharmani");

        DomainFileList domainFileList = new DomainFileList();
        domainFileList.add(Author.class, Arrays.asList(new Author("Carl", "Sagan"), new Author("Orson", "Card")));
        domainFileList.add(Book.class, Arrays.asList(
                new Book("Cosmos", "Introduction"),
                new Book("Ender's game", "Introduction")));
        domainFileList.add(Comment.class, Arrays.asList(
                new Comment(
                        1,
                        "this is a comment",
                        dateFormat.parse("20170101"),
                        dateFormat.parse("20170101"),
                        bookInfo,
                        userInfo,
                        null),
                new Comment(
                        2,
                        "this is also a comment",
                        dateFormat.parse("20170101"),
                        dateFormat.parse("20170101"),
                        bookInfo,
                        userInfo,
                        null)));
        domainFileList.add(DefinitionBundle.class, Arrays.asList(new DefinitionBundle("a definition")));
        domainFileList.add(Note.class, Arrays.asList(
                new Note(
                        "",
                        "Don't you believe it",
                        "Confirm this.",
                        false,
                        dateFormat.parse("20170101"),
                        dateFormat.parse("20170101"),
                        NoteType.QUESTION,
                        userInfo,
                        bookInfo,
                        null),
                new Note(
                        "",
                        "Interesting",
                        "Good point.",
                        false,
                        dateFormat.parse("20170101"),
                        dateFormat.parse("20170101"),
                        NoteType.GENERAL_NOTE,
                        userInfo,
                        bookInfo,
                        null)));
        domainFileList.add(Paragraph.class, Arrays.asList(
                new Paragraph("this is a paragraph.", bookInfo),
                new Paragraph("this is also a paragraph.", bookInfo)));
        domainFileList.add(User.class, Arrays.asList(
                new User("sharmani", "Nikhil Sharma", null),
                new User("khawajah", "Hassan Khawaja", null)));

        domainFileList.getDomainFileInfos()
                .entrySet()
                .forEach(s -> generateFiles(
                        s.getValue().fileName,
                        s.getValue().jsonSchema,
                        s.getValue().jsonItemExample,
                        s.getValue().jsonCollectionExample));

    }

    private void generateFiles(
            String fileName,
            String jsonSchema,
            String jsonItemExample,
            String jsonCollectionExample) {
        try {
            String schemaFilePath = "/Users/nik/Documents/development/pensive/raml/schemas/" + fileName + ".schema";
            String itemExampleFilePath = "/Users/nik/Documents/development/pensive/raml/examples/" + fileName + ".json";
            String collectionExampleFilePath = "/Users/nik/Documents/development/pensive/raml/examples/" + fileName +
                    "s.json";
            Files.write(Paths.get(schemaFilePath), jsonSchema.getBytes(), StandardOpenOption.CREATE);
            Files.write(Paths.get(itemExampleFilePath), jsonItemExample.getBytes(), StandardOpenOption.CREATE);
            Files.write(Paths.get(
                    collectionExampleFilePath),
                    jsonCollectionExample.getBytes(),
                    StandardOpenOption.CREATE);
        } catch (IOException ioException) {
            System.out.println(ioException);
        }
    }

    @NoArgsConstructor
    private class DomainFileList {
        @Getter
        Map<Class, DomainFileInfo> domainFileInfos = new HashMap<>();

        public void add(Class domainClass, List<Object> examples) throws JsonProcessingException {
            domainFileInfos.put(domainClass, new DomainFileInfo(
                    domainClass.getSimpleName(),
                    objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(
                            jsonSchemaGenerator.generateSchema(domainClass)),
                    objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(examples.get(0)),
                    objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(examples)));
        }
    }

    @AllArgsConstructor
    private class DomainFileInfo {
        private String fileName;
        private String jsonSchema;
        private String jsonItemExample;
        private String jsonCollectionExample;
    }
}
