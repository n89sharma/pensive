package Pensive.domainobjects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@Document(collection = "paragraphs")
public class Paragraph {
    @Id
    private String id;
    private String text;
    private String book;
    private String chapter;
    private String section;
    private List<String> noteIds;
}
