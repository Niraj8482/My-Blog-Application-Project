package com.blogapp01.payload;

import lombok.Data;

@Data
public class Signup {

    private String name;
    private String username;
    private String email;
    private String password;
}
