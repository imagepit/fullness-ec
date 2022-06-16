package com.fullness.ec.controller.backend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/admin")
@Controller
public class LoginController {
    @GetMapping("/login")
    public String login(){
        return "backend/login";
    }
}
