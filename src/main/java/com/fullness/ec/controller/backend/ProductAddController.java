package com.fullness.ec.controller.backend;

import com.fullness.ec.entity.Product;
import com.fullness.ec.entity.ProductCategory;
import com.fullness.ec.form.ProductForm;
import com.fullness.ec.form.ProductFormValidator;
import com.fullness.ec.helper.ProductHelper;
import com.fullness.ec.service.ProductCategoryService;
import com.fullness.ec.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@SessionAttributes({"productAddForm","categories","imageBytes"})
@RequestMapping("/admin/product-add")
@Controller
public class ProductAddController {

    private static final String TEMPLATE_DIR = "backend/product-add";

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductFormValidator validator;

    @ModelAttribute("productAddForm")
    public ProductForm setupForm(){
        return new ProductForm();
    }

    @InitBinder("productAddForm")
    public void bind(WebDataBinder webDataBinder){
        webDataBinder.addValidators(validator);
    }

    @GetMapping
    public String form(Model model){
        model.addAttribute("categories", productCategoryService.findAll());
        return TEMPLATE_DIR + "/form";
    }

    @PostMapping("/confirm")
    public String confirm(
            @Validated @ModelAttribute("productAddForm") ProductForm form,
            BindingResult result,
            Model model) throws Exception {
        if(result.hasErrors()) return "backend/product-add/form";
        model.addAttribute("category",getCategory((List<ProductCategory>)model.getAttribute("categories"),form.getCategory()));
        model.addAttribute("uploadImage",ProductHelper.createBase64ImageString(form.getImage()));
        model.addAttribute("imageBytes",form.getImage().getBytes());
        return TEMPLATE_DIR + "/confirm";
    }

    @PostMapping("/complete")
    public String complete(
            @ModelAttribute("productAddForm") ProductForm form,
            @ModelAttribute("imageBytes") byte[] data,
            SessionStatus sessionStatus,
            Model model
    ) throws Exception {
        if(form.isEmpty()) throw new RuntimeException("フォームのデータがありません");
        String fileName = ProductHelper.uploadFile(form.getImage().getOriginalFilename(),data);
        Product product = ProductHelper.convertEntity(form,productCategoryService.findById(form.getCategory()),null);
        product.setImageUrl(fileName);
        productService.insert(product);
        sessionStatus.setComplete();
        model.addAttribute("p",product);
        return TEMPLATE_DIR + "/complete";
    }

    @GetMapping("/cancel")
    public String cancel(
            //@ModelAttribute("productAddForm") ProductForm form,
            SessionStatus sessionStatus
            //,RedirectAttributes redirectAttributes
    ){
        sessionStatus.setComplete();
        // redirectAttributes.addFlashAttribute("productAddForm", form);
        return "redirect:/admin/product";
    }

    private ProductCategory getCategory(List<ProductCategory> categories, int categoryId){
        for(ProductCategory pc : categories){
            if(pc.getId() == categoryId){ return pc; }
        }
        return null;
    }
}
