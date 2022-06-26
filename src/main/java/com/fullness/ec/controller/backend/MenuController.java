package com.fullness.ec.controller.backend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class MenuController {
    @GetMapping
    public String menu(){
        return "backend/menu";
    }
}
