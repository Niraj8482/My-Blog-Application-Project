package com.blogapp01.controller;

import com.blogapp01.payload.CommentDto;
import com.blogapp01.payload.PostWithCommentDto;
import com.blogapp01.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/comments")
@AllArgsConstructor
public class CommentController {

    private CommentService commentService;

    @PostMapping("/{postId}")
    public ResponseEntity<CommentDto> createComment(
           @RequestBody CommentDto commentDto,
           @PathVariable long postId){

        CommentDto comment = commentService.createComment(commentDto, postId);
        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<PostWithCommentDto> getAllComments(@RequestParam long niraj){
        PostWithCommentDto allCommentsByPostId = commentService.getAllCommentsByPostId(niraj);
        return new ResponseEntity<>(allCommentsByPostId, HttpStatus.OK);
    }
}