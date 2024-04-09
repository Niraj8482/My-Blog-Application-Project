package com.blogapp01.payload;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class CommentDto {

    private long id;
    private String name;
    private String message;


}
