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
import java.util.HashMap;
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
        domainFileList.add(Author.class, new Author("Carl", "Sagan"));
        domainFileList.add(Book.class, new Book("Cosmos", "Introduction"));
        domainFileList.add(Comment.class, new Comment(
                1,
                "this is a comment",
                dateFormat.parse("20170101"),
                dateFormat.parse("20170101"),
                bookInfo,
                userInfo,
                null));
        domainFileList.add(DefinitionBundle.class, new DefinitionBundle("a definition"));
        domainFileList.add(Note.class, new Note(
                "",
                "Don't you believe it",
                "Confirm this.",
                false,
                dateFormat.parse("20170101"),
                dateFormat.parse("20170101"),
                NoteType.QUESTION,
                userInfo,
                bookInfo,
                null));
        domainFileList.add(Paragraph.class, new Paragraph("this is a paragraph.", bookInfo));
        domainFileList.add(User.class, new User("sharmani", "Nikhil Sharma", null));


        domainFileList.getDomainFileInfos()
                .entrySet()
                .forEach(s -> generateFiles(s.getValue().fileName, s.getValue().jsonSchema, s.getValue().jsonExample));

    }

    private void generateFiles(String fileName, String jsonSchema, String jsonExample) {
        try {
            String schemaFilePath = "/Users/nik/Documents/development/pensive/raml/schemas/" + fileName + ".schema";
            String exampleFilePath = "/Users/nik/Documents/development/pensive/raml/examples/" + fileName + ".json";
            Files.write(Paths.get(schemaFilePath), jsonSchema.getBytes(), StandardOpenOption.CREATE);
            Files.write(Paths.get(exampleFilePath), jsonExample.getBytes(), StandardOpenOption.CREATE);
        } catch (IOException ioException) {
            System.out.println(ioException);
        }
    }

    @NoArgsConstructor
    private class DomainFileList {
        @Getter
        Map<Class, DomainFileInfo> domainFileInfos = new HashMap<>();

        public void add(Class domainClass, Object example) throws JsonProcessingException {
            domainFileInfos.put(domainClass, new DomainFileInfo(
                    domainClass.getSimpleName(),
                    objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(
                            jsonSchemaGenerator.generateSchema(domainClass)),
                    objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(example)));
        }
    }

    @AllArgsConstructor
    private class DomainFileInfo {
        private String fileName;
        private String jsonSchema;
        private String jsonExample;
    }
}
