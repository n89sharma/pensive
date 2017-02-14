package Pensive.services;

import Pensive.domainobjects.Paragraph;
import Pensive.repositories.ParagraphRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParagraphService extends PensiveAppService<Paragraph, String, ParagraphRepository> {
    private ParagraphRepository paragraphRepository;

    @Autowired
    public ParagraphService(ParagraphRepository paragraphRepository) {
        super(paragraphRepository);
    }

}
