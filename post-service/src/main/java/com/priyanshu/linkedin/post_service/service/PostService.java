package com.priyanshu.linkedin.post_service.service;

import com.priyanshu.linkedin.post_service.dto.PostCreateRequestDto;
import com.priyanshu.linkedin.post_service.dto.PostDto;
import com.priyanshu.linkedin.post_service.entities.Post;

import java.util.List;

public interface PostService {

    PostDto createPost(PostCreateRequestDto postDto, Long userId);

    PostDto getPostById(Long postId);

    List<PostDto> getAllPostsOfUser(Long userId);
}
