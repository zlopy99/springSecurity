package com.SECURITY.securityTake2.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class TemplateController {

//    @PostMapping("login")
//    public void getLoginView(){
//
//    }

    @GetMapping("courses")
    public String getCourses(){
        return "courses";
    }
}
