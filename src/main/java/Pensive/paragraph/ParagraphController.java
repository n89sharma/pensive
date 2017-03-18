package Pensive.paragraph;

import Pensive.prototype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/paragraphs")
public class ParagraphController extends Controller<
        Paragraph,
        ParagraphValidator,
        ParagraphRepository,
        ParagraphService> {

    @Autowired
    public ParagraphController(ParagraphService paragraphService) {
        super(paragraphService);
    }
}
