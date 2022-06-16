package com.fullness.ec.controller.backend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

//@SessionAttributes(
//        {
//                "employeeAccountForm",
//                "categories",
//                "imageBytes",
//                "productCategoryAddForm",
//                "productUpdateForm",
//                "originalImageUrl",
//                "newImageBytes",
//                "product"
//        }
//)
@RequestMapping("/admin")
@Controller
public class MenuController {
    @GetMapping
    public String menu(
            //SessionStatus sessionStatus
    ){
        // sessionStatus.setComplete();
        return "backend/menu";
    }
}
