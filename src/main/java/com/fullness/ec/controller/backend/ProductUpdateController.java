package com.fullness.ec.controller.backend;

import com.fullness.ec.entity.Product;
import com.fullness.ec.entity.ProductCategory;
import com.fullness.ec.form.ProductForm;
import com.fullness.ec.form.ProductUpdateValidator;
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

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/admin/product-update")
@SessionAttributes({"productUpdateForm","categories","originalImageUrl","newImageBytes","product"})
public class ProductUpdateController {

    private static final String TEMPLATE_DIR = "backend/product-update";
    @Autowired private ProductCategoryService productCategoryService;
    @Autowired private ProductService productService;
    @Autowired private ProductUpdateValidator productUpdateValidator;
    @ModelAttribute("productUpdateForm")
    public ProductForm setupForm(){
        return new ProductForm();
    }

    @InitBinder("productUpdateForm")
    public void initBinder(WebDataBinder binder){
        binder.addValidators(productUpdateValidator);
    }

    @GetMapping("{no}")
    public String form(@PathVariable Integer no, Model model, @ModelAttribute("productUpdateForm") ProductForm form) throws IOException {
        if(form.isEmpty()){
            List<ProductCategory> categories = productCategoryService.findAll();
            Product product = productService.findById(no);
            model.addAttribute("productUpdateForm", ProductHelper.convertForm(product));
            model.addAttribute("originalImageUrl", product.getImageUrl());
            model.addAttribute("categories", categories);
            model.addAttribute("product", product);
        }
        return TEMPLATE_DIR + "/form";
    }

    @PostMapping("/confirm")
    public String confirm(@Validated @ModelAttribute("productUpdateForm") ProductForm form, BindingResult result, Model model) throws IOException {
        if(result.hasErrors()) return "backend/product-update/form";
        model.addAttribute("category",productCategoryService.findById(form.getCategory()));
        if(isImageUpdated(form)){
            model.addAttribute("uploadImage",ProductHelper.createBase64ImageString(form.getImage()));
            model.addAttribute("newImageBytes",form.getImage().getBytes());
        } else {
            model.addAttribute("uploadImage","/img/"+model.getAttribute("originalImageUrl"));
        }
        return TEMPLATE_DIR + "/confirm";
    }

    @PostMapping("/complete")
    public String complete(@ModelAttribute("productUpdateForm") ProductForm form, @ModelAttribute("product") Product product, SessionStatus sessionStatus, Model model) throws Exception {
        if(form.isEmpty()) throw new RuntimeException("フォームのデータがありません");
        Product updateProduct = ProductHelper.convertEntity(form, productCategoryService.findById(form.getCategory()), product.getId());
        updateProduct.setImageUrl(getImageUrl(form,model));
        productService.update(updateProduct);
        sessionStatus.setComplete();
        model.addAttribute("p",updateProduct);
        return TEMPLATE_DIR + "/complete";
    }

    @GetMapping("/cancel")
    public String cancel(SessionStatus sessionStatus){
        sessionStatus.setComplete();
        return "redirect:/admin/product";
    }

    /**
     * フォームとモデルから画像URLを取得する<br>
     * （修正画面で画像を更新している場合）Base64エンコードされた画像を使ってサーバに画像を保存したファイル名を返す<br>
     * （修正画面で画像を更新していない場合）元の商品の画像のファイル名を返す
     * @param form 商品修正用フォームオブジェクト
     * @param model SpringMVCモデルオブジェクト
     * @return 画像のファイル名
     * @throws Exception ファイルアップロード時の例外
     */
    private String getImageUrl(ProductForm form, Model model) throws Exception {
        if(isImageUpdated(form)){
            return ProductHelper.uploadFile(form.getImage().getOriginalFilename(), (byte[])model.getAttribute("newImageBytes"));
        } else {
            return model.getAttribute("originalImageUrl").toString();
        }
    }

    /**
     * 画像を更新したかどうかをチェックする
     * フォームで画像用ファイルが入力されたかどうかで判断
     * @param form フォーム
     * @return true:アップロード済み、false:未アップロード
     */
    private boolean isImageUpdated(ProductForm form){
        return !(form.getImage().getOriginalFilename().equals(""));
    }
}
