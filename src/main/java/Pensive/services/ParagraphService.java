package Pensive.services;

import Pensive.domainobjects.Paragraph;
import Pensive.repositories.ParagraphRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParagraphService {
    private ParagraphRepository paragraphRepository;

    @Autowired
    public ParagraphService(ParagraphRepository paragraphRepository) {
        this.paragraphRepository = paragraphRepository;
    }

    public Paragraph getParagraph(String paragraphId) {
        return paragraphRepository.findOne(paragraphId);
    }

    public void deleteParagraph(String paragraphId) {
        paragraphRepository.delete(paragraphId);
    }
}
