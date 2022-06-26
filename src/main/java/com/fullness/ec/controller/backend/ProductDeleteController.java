package com.fullness.ec.controller.backend;

import com.fullness.ec.entity.Product;
import com.fullness.ec.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/product-delete")
public class ProductDeleteController {

    private static final String TEMPLATE_DIR = "backend/product-delete";
    @Autowired private ProductService productService;

    @GetMapping("{no}")
    public String confirm(@PathVariable Integer no, Model model){
        Product product = productService.findById(no);
        model.addAttribute("product",product);
        return TEMPLATE_DIR + "/confirm";
    }

    @PostMapping("/execute")
    public String execute(@RequestParam Integer id, @RequestParam String name, RedirectAttributes redirectAttributes){
        productService.delete(id);
        redirectAttributes.addFlashAttribute("name", name);
        return "redirect:complete";
    }

    @GetMapping("/complete")
    public String complete(@ModelAttribute("name") String name){
        return TEMPLATE_DIR + "/complete";
    }

    @GetMapping("/cancel")
    public String cancel(){
        return "redirect:/admin/product";
    }
}
