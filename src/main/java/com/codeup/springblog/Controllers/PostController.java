package com.codeup.springblog.Controllers;

import com.codeup.springblog.Models.Post;
import com.codeup.springblog.Models.User;
import com.codeup.springblog.Repository.PostRepository;
import com.codeup.springblog.Repository.UserRepository;
import com.codeup.springblog.Services.EmailService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;


@Controller
public class PostController {

    private PostRepository postDao;
    private UserRepository userDao;
    private EmailService emailService;

    public PostController(PostRepository postDao, UserRepository userDao, EmailService emailService){
        this.postDao = postDao;
        this.userDao = userDao;
        this.emailService = emailService;
    }

    @GetMapping("/posts")
    public String getPosts(Model model){
        model.addAttribute("posts", postDao.findAll());
        return "posts/index";
    }


    @GetMapping("/posts/{id}")
    public String getPost(@PathVariable long id, Model model, Principal principal){
        String userName = "";
        if (principal != null) {
            userName = principal.getName();
        }
        model.addAttribute("userName", userName);
        model.addAttribute("post",postDao.getOne(id));
        return "posts/show";
    }

    @GetMapping("/posts/create")
    public String getCreatePostForm(Model view){
        view.addAttribute("action", "create");
        view.addAttribute("post", new Post());
        return "posts/create";
    }


    @PostMapping("/posts/create")
    public String getCreatePostForm(@ModelAttribute Post post){
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        post.setUser(loggedInUser);
        postDao.save(post);
        emailService.prepareandsend(post, "Post Created!", "We created your post: " + post.getTitle());
        return "redirect:/posts";
    }

    @PostMapping("/posts/{id}/delete")
    public String delete(@PathVariable long id){
       User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
       if (loggedInUser.getId() == postDao.getOne(id).getUser().getId())
       postDao.deleteById(id);
        return "redirect:/posts";
    }

    @GetMapping("/posts/{id}/edit")
    public String editForm(@PathVariable long id, Model model) {
        Post postToEdit = postDao.getOne(id);
        model.addAttribute("post", postToEdit);
        return "posts/edit";
    }

    @PostMapping("/posts/{id}/edit")
    public String updatePost(@PathVariable long id, @ModelAttribute Post post) {
        Post p = postDao.getOne(id);
        p.setTitle(post.getTitle());
        p.setBody(post.getBody());
        postDao.save(p);
        return "redirect:/posts";
    }


}