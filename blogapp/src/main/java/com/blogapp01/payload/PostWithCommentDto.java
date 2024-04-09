package com.blogapp01.payload;

import com.blogapp01.entity.Post;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
public class PostWithCommentDto {

    private PostDto post;
    private List<CommentDto> commentDtos;

}
