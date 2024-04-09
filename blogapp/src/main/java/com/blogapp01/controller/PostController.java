package com.blogapp01.controller;

import com.blogapp01.payload.AllPostDto;
import com.blogapp01.payload.PostDto;
import com.blogapp01.service.PostService;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }


    // http://localhost:8080/api/posts          ---->>          Creating a Post
    @PostMapping
    public ResponseEntity<?> createPost(@Valid @RequestBody PostDto postDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity<>(bindingResult.getFieldError().getDefaultMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

        PostDto dto = postService.createPost(postDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    // http://localhost:8080/api/posts/4        ---->>           Deleting a Post
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable long id){

        boolean b = postService.deleteRec(id);
        if(b){
            return new ResponseEntity<>("Post deleted successfully", HttpStatus.OK);
        }

        return new ResponseEntity<>("Please enter valid Id", HttpStatus.INTERNAL_SERVER_ERROR);

    }

    // http://localhost:8080/api/posts?pageNo=1&pageSize=3&sortBy=id&sortDir=asc        --->    GetAllData
    @GetMapping
    public ResponseEntity<?>fetchAllPost(
            @RequestParam(name = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(name = "pageSize", defaultValue = "2", required = false) int pageSize,
            @RequestParam(name = "sortBy", defaultValue = "id", required = false) String sortBy,
            @RequestParam(name = "sortDir", defaultValue = "asc", required = false) String sortDir
    ){
        AllPostDto allPostDto = postService.fetchAllPost(pageNo,pageSize,sortBy,sortDir);

        if(allPostDto.getPostDto().isEmpty()){
            return new ResponseEntity<>("No record is present", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(allPostDto, HttpStatus.OK);
    }

//    http://localhost:8080/api/posts/1             ---->>      Reading a data by id
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable long id){
        PostDto dto = postService.getPostById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

}
