package com.codeup.springblog.Controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class HomeController {
    @GetMapping("/home/{name}")
//    In order to pass data to our views, we will need to define a controller
//    method that accepts a model. A Model in this context is what Spring refers to a view as.
    public String hello(@PathVariable String name, Model model){
        model.addAttribute("name", name);
        return "home";
    }
    @GetMapping("/join")
    public String showJoinForm(){
        return "join";
    }
    @PostMapping("/join")
        public String joinCohort(@RequestParam(name = "cohort") String cohort, Model model){
        model.addAttribute("cohort", "Welcome to " + cohort + "!");
        return "join";
    }

}
