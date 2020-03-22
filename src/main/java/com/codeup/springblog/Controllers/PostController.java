package com.codeup.springblog.Controllers;

import com.codeup.springblog.Models.Post;
import com.codeup.springblog.Repository.PostRepository;
import com.codeup.springblog.Repository.UserRepository;
import com.codeup.springblog.Services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



@Controller
public class PostController {

    private PostRepository postDao;
    private final UserRepository userDao;

    @Autowired
    private EmailService emailService;

    public PostController(PostRepository postDao, UserRepository userDao ){
        this.postDao = postDao;
        this.userDao = userDao;
    }

    @GetMapping("/posts")
    public String getPosts(Model model){
        model.addAttribute("posts", postDao.findAll());
        return "posts/index";
    }


    @GetMapping("/posts/{id}")
    public String getPost(@PathVariable long id, Model model){
//        Post post1 = new Post( id, "Europa's First Post", "Remote Learning Today!");
//        model.addAttribute("title", post1.getTitle());
//        model.addAttribute("body", post1.getBody());
        model.addAttribute("post", postDao.getOne(id));
        return "posts/show";
    }

    @GetMapping("/posts/create")
    public String getCreatePostForm(){
    Post newPost = new Post();
    newPost.setTitle("New Post");
    newPost.setBody("This is the newly saved post");
    newPost.setUser(userDao.getOne(1L));
    postDao.save(newPost);
    String emailSubject = "This is the email subject";
    String emailBody = " This is the body of the email";
    emailService.prepareandsend(newPost, emailSubject, emailBody);
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String createPost(@ModelAttribute Post post){
        post.setUser(userDao.getOne(1l));
        postDao.save(post);
        return "redirect:/posts";
    }


    @RequestMapping(path="/posts", method=RequestMethod.DELETE)
    public String delete(@PathVariable long id){
        postDao.deleteById(id);
        return "redirect:posts";
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