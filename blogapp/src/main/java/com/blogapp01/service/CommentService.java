package com.blogapp01.service;

import com.blogapp01.payload.CommentDto;
import com.blogapp01.payload.PostWithCommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(CommentDto commentDto, long postId);
    PostWithCommentDto getAllCommentsByPostId(long id);
}
