package ait.cohort46.security;

import ait.cohort46.accounting.dao.UserAccountRepository;
import ait.cohort46.accounting.model.UserAccount;
import ait.cohort46.post.dao.PostRepository;
import ait.cohort46.post.model.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class CustomWebSecurity {
    private final PostRepository postRepository;
    private final UserAccountRepository userRepository;

    public boolean checkPostAuthor(String postId, String username) {
        Post post = postRepository.findById(postId).orElse(null);
        return post != null && post.getAuthor().equals(username);
    }

    public boolean checkPasswordDate(String username) {
        UserAccount user = userRepository.findById(username).orElse(null);
        return user != null && user.getPasswordLastChanged().plusDays(60).isAfter(LocalDate.now());
    }
}