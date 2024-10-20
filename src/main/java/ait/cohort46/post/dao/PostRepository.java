package ait.cohort46.post.dao;

import ait.cohort46.post.dto.PostDto;
import ait.cohort46.post.model.Post;
import org.modelmapper.ModelMapper;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;


public interface PostRepository extends MongoRepository<Post, String> {
    Iterable<PostDto> findByAuthorIgnoreCase(String author);

    Stream<PostDto> findByTagsInIgnoreCase(List<String> tags);

    Stream<PostDto> findByDateCreatedBetween(LocalDate from, LocalDate to);
}
