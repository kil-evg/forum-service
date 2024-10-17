package ait.cohort46.post.dao;

import ait.cohort46.post.model.Post;
import org.springframework.data.repository.CrudRepository;


public interface PostRepository extends CrudRepository<Post, String> {
}
