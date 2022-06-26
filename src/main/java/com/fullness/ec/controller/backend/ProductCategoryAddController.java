package com.fullness.ec.controller.backend;

import com.fullness.ec.entity.ProductCategory;
import com.fullness.ec.form.ProductCategoryForm;
import com.fullness.ec.service.ProductCategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@RequestMapping("/admin/product-category-add")
@SessionAttributes("productCategoryAddForm")
public class ProductCategoryAddController {

    private static final String TEMPLATE_DIR = "backend/product-category-add";
    @Autowired private ProductCategoryService service;

    @ModelAttribute("productCategoryAddForm")
    public ProductCategoryForm setupForm(){
        return new ProductCategoryForm();
    }

    @GetMapping
    public String form(Model model){
        return TEMPLATE_DIR + "/form";
    }

    @PostMapping("/confirm")
    public String confirm(@Validated @ModelAttribute("productCategoryAddForm") ProductCategoryForm form, BindingResult result){
        if(result.hasErrors()) return TEMPLATE_DIR + "/form";
        return TEMPLATE_DIR + "/confirm";
    }

    @PostMapping("/complete")
    public String complete(@ModelAttribute("productCategoryAddForm") ProductCategoryForm form, SessionStatus sessionStatus, Model model){
        if(form.isEmpty()) throw new RuntimeException();
        ModelMapper modelMapper = new ModelMapper();
        ProductCategory category = modelMapper.map(form, ProductCategory.class);
        service.insert(category);
        sessionStatus.setComplete();
        model.addAttribute("category",category);
        return TEMPLATE_DIR + "/complete";
    }

    @GetMapping("/cancel")
    public String cancel(SessionStatus sessionStatus){
        sessionStatus.setComplete();
        return "redirect:/admin/product";
    }

}
