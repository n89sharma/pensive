package Pensive.domainobjects.aggregated;

import Pensive.domainobjects.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comments{
    private String parentId;
    private List<Comment> comments;
}