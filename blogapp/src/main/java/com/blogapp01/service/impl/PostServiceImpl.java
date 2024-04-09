package com.blogapp01.service.impl;

import com.blogapp01.entity.Post;
import com.blogapp01.exception.ResourceNotFound;
import com.blogapp01.payload.AllPostDto;
import com.blogapp01.payload.PostDto;
import com.blogapp01.repository.PostRepository;
import com.blogapp01.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    private ModelMapper modelMapper;

    public PostServiceImpl(PostRepository postRepository, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
/*
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
*/

        Post post = mapToEntity(postDto);

        Post save = postRepository.save(post);

/*
        postDto.setId(save.getId());

        PostDto dto = new PostDto();
        dto.setId(save.getId());
        dto.setTitle(save.getTitle());
        dto.setDescription(save.getDescription());
        dto.setContent(save.getContent());
*/


        PostDto dto = mapToDto(save);

        return dto;
    }



    Post mapToEntity(PostDto postDto){
//        Post post = new Post();
//        post.setTitle(postDto.getTitle());
//        post.setDescription(postDto.getDescription());
//        post.setContent(postDto.getContent());

        Post post = modelMapper.map(postDto, Post.class);
        return post;
    }

    PostDto mapToDto(Post post){
//        PostDto dto = new PostDto();
//        dto.setId(post.getId());
//        dto.setTitle(post.getTitle());
//        dto.setDescription(post.getDescription());
//        dto.setContent(post.getContent());

        PostDto dto = modelMapper.map(post, PostDto.class);
        return dto;
    }


    @Override
    public boolean deleteRec(long id) {
        if (postRepository.existsById(id)) {
            postRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public PostDto getPostById(long id){
        Post post = postRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Post not found with id : " + id)
        );
        return mapToDto(post);
    }

    @Override
    public AllPostDto fetchAllPost(int pageNo, int pageSize, String sortBy, String sortDir) {

//        List<Post> post = postRepository.findAll();

        Sort sort = sortDir.equalsIgnoreCase(Sort.DEFAULT_DIRECTION.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo,pageSize, sort);
        Page<Post> all = postRepository.findAll(pageable);
        List<Post> post = all.getContent();
        List<PostDto> postDtos = post.stream().map(e -> this.mapToDto(e)).collect(Collectors.toList());

        AllPostDto allPost = new AllPostDto();
        allPost.setPostDto(postDtos);
        allPost.setTotalPages(all.getTotalPages());
        allPost.setTotalElements(all.getTotalElements());
        allPost.setFirstPage(all.isFirst());
        allPost.setLastPage(all.isLast());
        allPost.setPageNumber(all.getNumber());
        return allPost;

    }
}
