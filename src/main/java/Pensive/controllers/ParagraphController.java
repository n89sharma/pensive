package Pensive.controllers;

import Pensive.domainobjects.Comments;
import Pensive.domainobjects.Paragraph;
import Pensive.domainobjects.Paragraphs;
import Pensive.repositories.ParagraphsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ParagraphController extends PensiveAppController{

    @Autowired
    private ParagraphsRepository paragraphsRepository;

    @RequestMapping(value="/paragraphs", method = RequestMethod.GET)
    public Paragraphs getParagraphs(@RequestParam("book") String book){
        Paragraphs paragraphs = new Paragraphs();
        paragraphs.setParagraphs(paragraphsRepository.findByBook(book));
        return paragraphs;
    }

    @RequestMapping(value="/paragraphs", method = RequestMethod.POST)
    public Paragraphs saveParagraph(@RequestBody Paragraph paragraph){
        Paragraphs paragraphs = new Paragraphs();
        paragraphs.addParagraph(paragraphsRepository.save(paragraph));
        return paragraphs;
    }
}
