package com.blogapp01.payload;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
public class AllPostDto {
    private List<PostDto> postDto;
    private int totalPages;
    private long totalElements;
    private int pageNumber;
    private boolean firstPage;
    private boolean lastPage;
}
