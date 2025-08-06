package com.wiproassignments.resttemplateexample.service;

import com.wiproassignments.resttemplateexample.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class PostService {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/posts";

    @Autowired
    private RestTemplate restTemplate;

    // GET all posts
    public List<Post> getAllPosts() {
        ResponseEntity<Post[]> response = restTemplate.getForEntity(BASE_URL, Post[].class);
        return Arrays.asList(response.getBody());
    }

    // GET single post by ID
    public Post getPostById(Long id) {
        return restTemplate.getForObject(BASE_URL + "/" + id, Post.class);
    }

    // POST create a new post
    public Post createPost(Post post) {
        return restTemplate.postForObject(BASE_URL, post, Post.class);
    }

    // PUT update a post
    public void updatePost(Long id, Post post) {
        restTemplate.put(BASE_URL + "/" + id, post);
    }

    // DELETE a post
    public void deletePost(Long id) {
        restTemplate.delete(BASE_URL + "/" + id);
    }
}
