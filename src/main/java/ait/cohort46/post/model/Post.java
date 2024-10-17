package ait.cohort46.post.model;

import ait.cohort46.post.dto.CommentDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Document(collection = "posts")
public class Post {
    @Id
    private String id;
    @Setter
    private String title;
    @Setter
    private String content;
    @Setter
    private String author;
    private LocalDateTime dateCreated;
    @Setter
    private List<String> tags;
    private Integer likes;
    private List<String> comments;

    public Post(String title, String content, String author, List<String> tags) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.dateCreated = LocalDateTime.now();
        this.tags = tags;
        this.comments = new ArrayList<>();
        this.likes = 0;
    }
}
