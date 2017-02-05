package Pensive.domainobjects;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class Paragraphs {
    private List<Paragraph> paragraphs;

    public Paragraphs(){
        this.paragraphs = new ArrayList<Paragraph>();
    }
    public void addParagraph(Paragraph paragraph){
        paragraphs.add(paragraph);
    }
}
