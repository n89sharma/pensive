package Pensive.paragraph;

import Pensive.prototype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/paragraphs")
public class ParagraphController extends Controller {

    private ParagraphService paragraphService;

    @Autowired
    public ParagraphController(ParagraphService paragraphService) {
        this.paragraphService = paragraphService;
    }

    @RequestMapping(value = "/{paragraphId}", method = RequestMethod.GET)
    public Paragraph getParagraph(@PathVariable String paragraphId) {
        return paragraphService.readDomainObject(paragraphId);
    }

    @RequestMapping(value = "/{paragraphId}", method = RequestMethod.DELETE)
    public void deleteParagraph(@PathVariable String paragraphId) {
        paragraphService.deleteDomainObject(paragraphId);
    }
}
