package Pensive.controllers;

import Pensive.domainobjects.Paragraph;
import Pensive.domainobjects.aggregated.Paragraphs;
import Pensive.repositories.ParagraphRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ParagraphController extends PensiveAppController{

    @Autowired
    private ParagraphRepository paragraphRepository;

    @RequestMapping(value="/paragraphs", method = RequestMethod.GET)
    public Paragraphs getParagraphs(@RequestParam("book") String book){
        Paragraphs paragraphs = new Paragraphs();
        paragraphs.setParagraphs(paragraphRepository.findByBook(book));
        return paragraphs;
    }

    @RequestMapping(value="/paragraphs", method = RequestMethod.POST)
    public Paragraphs saveParagraph(@RequestBody Paragraph paragraph){
        Paragraphs paragraphs = new Paragraphs();
        paragraphs.addParagraph(paragraphRepository.save(paragraph));
        return paragraphs;
    }
}
