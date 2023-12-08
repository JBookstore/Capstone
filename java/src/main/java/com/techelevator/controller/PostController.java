package com.techelevator.controller;


import com.techelevator.dao.PostDao;
import com.techelevator.model.Post;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
public class PostController {
    private final PostDao postDao;

    public PostController(PostDao postDao) {
        this.postDao = postDao;
    }
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/posts/user/{id}")
    public List<Post> getPostByUserId(@PathVariable int id) {
        List<Post> post = postDao.getPostByUserId(id);
        if (post == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post Not Found");
        } else {
            return postDao.getPostByUserId(id);
        }
    }
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/posts", method = RequestMethod.POST)
    public Post create(@RequestBody @Valid Post post) {
        if (post == null) {
            throw new ResponseStatusException(HttpStatus.CREATED, "Post Created");
        } else {
            return postDao.createPost(post);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/posts/{id}", method = RequestMethod.GET)
    public Post getPostById(@PathVariable int id) {
        Post post = postDao.getPostById(id);
        if (post == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post Not Found");
        } else {
            return postDao.getPostById(id);
        }
    }
}
