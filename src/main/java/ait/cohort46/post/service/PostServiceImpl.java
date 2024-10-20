package ait.cohort46.post.service;

import ait.cohort46.post.dao.PostRepository;
import ait.cohort46.post.dto.NewCommentDto;
import ait.cohort46.post.dto.NewPostDto;
import ait.cohort46.post.dto.PostDto;
import ait.cohort46.post.model.Comment;
import ait.cohort46.post.model.Post;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import ait.cohort46.post.dto.exceptions.PostNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final ModelMapper modelMapper;


    @Override
    public PostDto addNewPost(String author, NewPostDto newPostDto) {
        Post post = modelMapper.map(newPostDto, Post.class);
        //Post post = new Post(newPostDto.getTitle(), newPostDto.getContent(),author, newPostDto.getTags());
        post.setAuthor(author);
        post = postRepository.save(post);
        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public PostDto findPostById(String id) {
        Post post = postRepository.findById(id).orElseThrow(PostNotFoundException::new);
        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public PostDto removePost(String id) {
        Post post = postRepository.findById(id).orElseThrow(PostNotFoundException::new);
        //postRepository.deleteById(id);
        postRepository.delete(post);
        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public PostDto updatePost(String id, NewPostDto newPostDto) {
        Post post = postRepository.findById(id).orElseThrow(PostNotFoundException::new);
        if (newPostDto.getTitle() != null) {
            post.setTitle(newPostDto.getTitle());
        }
        if (newPostDto.getContent() != null) {
            post.setContent(newPostDto.getContent());
        }
        if (newPostDto.getTags() != null) {
            post.getTags().addAll(newPostDto.getTags());
        }
        postRepository.save(post);
        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public PostDto addComment(String id, String author, NewCommentDto newCommentDto) {
        Post post = postRepository.findById(id).orElseThrow(PostNotFoundException::new);
        Comment comment = modelMapper.map(newCommentDto, Comment.class);
        comment.setUser(author);
        post.addComment(comment);
        postRepository.save(post);
        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public void addLike(String id) {
        Post post = postRepository.findById(id).orElseThrow(PostNotFoundException::new);
        post.addLike();
        postRepository.save(post);
    }

    @Override
    public Iterable<PostDto> findPostsByAuthor(String author) {
        return postRepository.findByAuthorIgnoreCase(author);
    }

    @Override
    public Iterable<PostDto> findPostsByTags(List<String> tags) {
        return postRepository.findByTagsInIgnoreCase(tags)
                .map(post -> modelMapper.map(post, PostDto.class))
                .toList();
    }

    @Override
    public Iterable<PostDto> findPostsByPeriod(LocalDate dateFrom, LocalDate dateTo) {
        return postRepository.findByDateCreatedBetween(dateFrom, dateTo)
                .map(post -> modelMapper.map(post, PostDto.class))
                .toList();
    }
}
