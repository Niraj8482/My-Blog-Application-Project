package com.blogapp01.service.impl;

import com.blogapp01.entity.Comment;
import com.blogapp01.entity.Post;
import com.blogapp01.exception.ResourceNotFound;
import com.blogapp01.payload.CommentDto;
import com.blogapp01.payload.PostDto;
import com.blogapp01.payload.PostWithCommentDto;
import com.blogapp01.repository.CommentRepository;
import com.blogapp01.repository.PostRepository;
import com.blogapp01.service.CommentService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {
    
    private CommentRepository commentRepository;
    private ModelMapper modelMapper;
    private PostRepository postRepository;
    
    @Override
    public CommentDto createComment(CommentDto commentDto, long postId) {
        Optional<Post> byId = postRepository.findById(postId);
        Post post = byId.get();
        Comment comment = mapToEntity(commentDto);
        comment.setPost(post);                  // at this point foreign key will be populated
        Comment savedComment = commentRepository.save(comment);
        CommentDto dto = mapToDto(savedComment);
        return dto;
    }

    @Override
    public PostWithCommentDto getAllCommentsByPostId(long id){

        Post post = postRepository.findById(id).get();

        PostDto dto = new PostDto();
        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setContent(post.getContent());
        dto.setDescription(post.getDescription());

        List<Comment> comments = commentRepository.findByPostId(id);
        List<CommentDto> dtos = comments.stream().map(this::mapToDto).collect(Collectors.toList());
        PostWithCommentDto postWithCommentDto = new PostWithCommentDto();

        postWithCommentDto.setCommentDtos(dtos);
        postWithCommentDto.setPost(dto);
        return postWithCommentDto;
    }

    
    Comment mapToEntity(CommentDto dto){
        Comment map = modelMapper.map(dto, Comment.class);
        return map;
    }

    CommentDto mapToDto(Comment comment){
        return modelMapper.map(comment, CommentDto.class);
    }
}
