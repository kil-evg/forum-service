package ait.cohort46.post.controller;

import java.time.LocalDate;
import java.util.List;

import ait.cohort46.post.dto.NewCommentDto;
import ait.cohort46.post.dto.NewPostDto;
import ait.cohort46.post.dto.PostDto;
import ait.cohort46.post.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/forum")
public class PostController {

	private final PostService postService;

	@PostMapping("/post/{author}")
	public PostDto addNewPost(@PathVariable String author, @RequestBody NewPostDto newPostDto) {
		return postService.addNewPost(author, newPostDto);
	}

	@GetMapping("/post/{id}")
	public PostDto findPostById(@PathVariable String id) {
		return postService.findPostById(id);
	}

	@DeleteMapping("/post/{id}")
	public PostDto removePost(@PathVariable String id) {
		return postService.removePost(id);
	}

	@PatchMapping("/post/{id}")
	public PostDto updatePost(@PathVariable String id, @RequestBody NewPostDto newPostDto) {
		return postService.updatePost(id, newPostDto);
	}

	@PatchMapping("/post/{id}/comment/{author}")
	public PostDto addComment(@PathVariable String id, @PathVariable String author, @RequestBody NewCommentDto newCommentDto) {
		return postService.addComment(id, author, newCommentDto);
	}

	@PatchMapping("/post/{id}/like")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void addLike(@PathVariable String id) {
		postService.addLike(id);
	}

	@GetMapping("/posts/author/{author}")
	public Iterable<PostDto> findPostsByAuthor(@PathVariable String author) {
		return postService.findPostsByAuthor(author);
	}

	@GetMapping("/posts/tags")
	public Iterable<PostDto> findPostsByTags(@RequestParam List<String> values) {
		return postService.findPostsByTags(values);
	}

	@GetMapping("/posts/period")
	public Iterable<PostDto> findPostsByPeriod(@RequestParam LocalDate dateFrom, @RequestParam LocalDate dateTo) {
		return postService.findPostsByPeriod(dateFrom, dateTo);
	}

}
