package Pensive.wikipedia;

import Pensive.prototype.DomainObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Article extends DomainObject {

    private String keyword;
    private String wikipediaUrl;
    private List<String> imageUrls;
    private String summary;
    private List<String> relatedKeywords;
}
