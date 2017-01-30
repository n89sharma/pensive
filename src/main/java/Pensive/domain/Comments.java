package Pensive.domain;

import Pensive.db.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Comments{
    private String parentId;
    private List<Comment> comments;
}