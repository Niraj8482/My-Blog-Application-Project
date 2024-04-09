package com.blogapp01.service;

import com.blogapp01.payload.AllPostDto;
import com.blogapp01.payload.PostDto;

import java.util.List;

public interface PostService {
    public PostDto createPost(PostDto postDto);

    boolean deleteRec(long id);

    AllPostDto fetchAllPost(int pageNo, int pageSize, String sortBy, String sortDir);

    public PostDto getPostById(long id);
}
