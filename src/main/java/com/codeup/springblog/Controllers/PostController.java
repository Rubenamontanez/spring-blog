package com.codeup.springblog.Controllers;

import com.codeup.springblog.Models.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
public class PostController {

    @GetMapping("/posts")
    public String getPosts(Model model){
        ArrayList<Post> postList = new ArrayList<>();
        postList.add(new Post((long) 2, "Second Post", "askdfhkashdfkjahsdf"));
        postList.add(new Post((long) 3, "Third Post", "some more text..."));

        model.addAttribute("posts", postList);
        return "posts/index";
    }


    @GetMapping("/posts/{id}")
    @ResponseBody
    public String getPost(@PathVariable int id){
        return "view an individual post" + id;
    }

    @GetMapping("/posts/create")
    @ResponseBody
    public String getCreatePostForm(){
        return "view the form for creating a post";
    }

    @PostMapping("/posts/create")
    @ResponseBody
    public String createPost(){
        return "create a post";
    }




}