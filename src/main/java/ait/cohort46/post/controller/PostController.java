package ait.cohort46.post.controller;

import ait.cohort46.post.dto.NewCommentDto;
import ait.cohort46.post.dto.NewPostDto;
import ait.cohort46.post.dto.PostDto;
import ait.cohort46.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping("/forum/post/{author}")
    public PostDto addNewPost(@PathVariable String author, @RequestBody NewPostDto newPostDto) {
        return postService.addNewPost(author, newPostDto);
    }

    @GetMapping("/forum/post/{postId}")
    public PostDto findPostById(@PathVariable String postId) {
        return postService.findPostById(postId);
    }

    @DeleteMapping("/forum/post/{postId}")
    public PostDto removePost(@PathVariable String postId) {
        return postService.removePost(postId);
    }

    @PatchMapping("/forum/post/{postId}")
    public PostDto updatePost(@PathVariable String postId, @RequestBody NewPostDto newPostDto) {
        return postService.updatePost(postId, newPostDto);
    }

    @PatchMapping("/forum/post/{postId}/comment/{commenter}")
    public PostDto addComment(@PathVariable String postId, @PathVariable String commenter, @RequestBody NewCommentDto newCommentDto) {
        return postService.addComment(postId, commenter, newCommentDto);
    }

    @PatchMapping("/forum/post/{postId}/like")
    public void addLike(@PathVariable String postId) {
        postService.addLike(postId);
    }

    @GetMapping("8080/forum/posts/author/{user}")
    public Iterable<PostDto> findPostsByAuthor(@PathVariable String user) {
        return postService.findPostsByAuthor(user);
    }

    @GetMapping("/forum/posts/tags")
    public Iterable<PostDto> findPostsByTags(@RequestParam List<String> tags) {
        return postService.findPostsByTags(tags);
    }

    @GetMapping("/forum/posts/period")
    public Iterable<PostDto> findPostsByPeriod(@RequestParam LocalDate dateFrom, @RequestParam LocalDate dateTo) {
        return postService.findPostsByPeriod(dateFrom, dateTo);
    }
}
