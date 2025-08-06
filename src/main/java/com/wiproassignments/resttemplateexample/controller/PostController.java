package com.wiproassignments.resttemplateexample.controller;

import com.wiproassignments.resttemplateexample.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final String BASE_URL = "https://jsonplaceholder.typicode.com/posts";

    @Autowired
    private RestTemplate restTemplate;

    // GET all posts
    @GetMapping
    public ResponseEntity<Post[]> getAllPosts() {
        return restTemplate.getForEntity(BASE_URL, Post[].class);
    }

    // GET single post
    @GetMapping("/{id}")
    public ResponseEntity<Post> getPost(@PathVariable Long id) {
        return restTemplate.getForEntity(BASE_URL + "/" + id, Post.class);
    }

    // CREATE post
    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        return restTemplate.postForEntity(BASE_URL, post, Post.class);
    }

    // UPDATE post
    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePost(@PathVariable Long id, @RequestBody Post post) {
        restTemplate.put(BASE_URL + "/" + id, post);
        return ResponseEntity.noContent().build();
    }

    // DELETE post
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        restTemplate.delete(BASE_URL + "/" + id);
        return ResponseEntity.noContent().build();
    }
}
