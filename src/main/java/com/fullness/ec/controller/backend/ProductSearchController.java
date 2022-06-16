package com.fullness.ec.controller.backend;

import com.fullness.ec.service.ProductCategoryService;
import com.fullness.ec.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

//@SessionAttributes(
//        {
//            "employeeAccountForm",
//            "productAddForm",
//            "categories",
//            "imageBytes",
//            "productCategoryAddForm",
//            "productUpdateForm",
//            "originalImageUrl",
//            "newImageBytes",
//            "product"
//        }
//)
@RequestMapping("/admin/product")
@Controller
public class ProductSearchController {
    @Autowired
    private ProductService productService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping
    public String list(
            Model model,
            //SessionStatus sessionStatus,
            @PageableDefault(page=0,size=3) Pageable pageable
    ){
        //sessionStatus.setComplete();
        model.addAttribute("categories",productCategoryService.findAll());
        model.addAttribute("products",productService.findPage(pageable));
        model.addAttribute("pageUrl","/admin/product?");
        model.addAttribute("next",pageable.getPageNumber()+2);
        model.addAttribute("prev",pageable.getPageNumber());
        return "backend/product/list";
    }

    @GetMapping("search")
    public String search(
            @RequestParam("category") String category,
            Model model,
            @PageableDefault(page=0,size=3) Pageable pageable
    ){
        model.addAttribute("category", category);
        model.addAttribute("categories",productCategoryService.findAll());
        model.addAttribute("products",productService.findPageByCategoryId(Integer.parseInt(category),pageable));
        model.addAttribute("pageUrl","/admin/product/search?category="+category+"&");
        model.addAttribute("next",pageable.getPageNumber()+2);
        model.addAttribute("prev",pageable.getPageNumber());
        return "backend/product/list";
    }
}
