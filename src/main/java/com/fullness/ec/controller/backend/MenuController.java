package com.fullness.ec.controller.backend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("admin")
public class MenuController {
    @GetMapping
    public String menu(){
        return "backend/menu";
    }
    @GetMapping("sample")
    public String sample() {
        throw new RuntimeException("バックエンドでエラーが発生しました。システム管理者に連絡してください!!!!!");
    }
    
}
