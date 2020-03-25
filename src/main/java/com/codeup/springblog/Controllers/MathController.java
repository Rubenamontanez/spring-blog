package com.codeup.springblog.Controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class MathController {

//    You can use the request map when yout not getting a string
    @RequestMapping(path = "/add/{a}/and/{b}", method = RequestMethod.GET)
    @ResponseBody
    public Integer add(@PathVariable int a, @PathVariable int b ){
        return a + b;
    }

    @GetMapping("/subtract/{a}/and/{b}")
    @ResponseBody
    public Integer subtract(@PathVariable int a, @PathVariable int b ){
        return a + b;
    }
    @GetMapping("/multiply/{a}/and/{b}")
    @ResponseBody
    public String multiply(@PathVariable int a,@PathVariable int b){
        return ""+(a*b);
    }

    @GetMapping("/divide/{a}/by/{b}")
    @ResponseBody
    public double divide(@PathVariable double a,@PathVariable double b){
        return (a/b);
    }


}
