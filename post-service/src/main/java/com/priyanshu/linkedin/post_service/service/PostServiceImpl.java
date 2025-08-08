package com.priyanshu.linkedin.post_service.service;

import com.priyanshu.linkedin.post_service.dto.PostCreateRequestDto;
import com.priyanshu.linkedin.post_service.dto.PostDto;
import com.priyanshu.linkedin.post_service.entities.Post;
import com.priyanshu.linkedin.post_service.exception.ResourceNotFoundException;
import com.priyanshu.linkedin.post_service.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    @Override
    public PostDto createPost(PostCreateRequestDto postDto, Long userId) {
        Post post = modelMapper.map(postDto, Post.class);
        post.setUserId(userId);

        Post savedPost = postRepository.save(post);
        return modelMapper.map(savedPost, PostDto.class);

    }

    @Override
    public PostDto getPostById(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() ->
                new ResourceNotFoundException("Post not found with Id " + postId));

        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public List<PostDto> getAllPostsOfUser(Long userId) {
        List<Post> posts = postRepository.findByUserId(userId);

        return posts.stream().map((post) ->
                modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());
    }
}
