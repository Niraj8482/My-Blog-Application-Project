package com.blogapp01.payload;

import javax.validation.constraints.*;
import lombok.Data;

@Data
public class PostDto {

    private long id;
    @NotEmpty
    @Size(min = 3, message = "Title should be atleast 3 characters")
    private String title;
    @NotEmpty
    private String description;
    private String content;

    @Email
    private String email;

//    @Size(min = 10, max = 10, message = "Mobile number should be exactly 10 digits")
//    private String mobile;

    @Min(value = 1000000000L, message = "Mobile number should not less than 10 digits")
    @Max(value = 9999999999L, message = "Mobile number should not exceed than 10 digits")
    private long mobile;
}
