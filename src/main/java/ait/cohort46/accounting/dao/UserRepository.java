package ait.cohort46.accounting.dao;

import ait.cohort46.accounting.dto.UserDto;
import ait.cohort46.accounting.model.User;
import ait.cohort46.post.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

}
