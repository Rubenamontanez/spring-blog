package com.codeup.springblog.Controllers;

import com.codeup.springblog.Models.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String getPost(@PathVariable int id, Model model){
        Post post1 = new Post((long) id, "Europa's First Post", "Remote Learning Today!");
        model.addAttribute("title", post1.getTitle());
        model.addAttribute("body", post1.getBody());
        return "posts/show";
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

    @RequestMapping(path="/post", method=RequestMethod.DELETE)
    @ResponseBody
    public String delete(){
        return "Delete!";
    }



}