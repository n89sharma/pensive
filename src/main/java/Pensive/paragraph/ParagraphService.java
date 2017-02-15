package Pensive.paragraph;

import Pensive.prototype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class ParagraphService extends Service<Paragraph, ParagraphValidator, ParagraphRepository> {

    @Autowired
    public ParagraphService(ParagraphRepository paragraphRepository, ParagraphValidator paragraphValidator) {
        super(paragraphRepository, paragraphValidator);
    }

}
