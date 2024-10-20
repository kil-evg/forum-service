package ait.cohort46.post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NewPostDto {
    private String title;
    private Set<String> tags;
    private String content;
}
