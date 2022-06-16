package com.fullness.ec.controller.frontend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
@Controller
public class TopController {
    @GetMapping
    public String top(){
        return "top";
    }
}
