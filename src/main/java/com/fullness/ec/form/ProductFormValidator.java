package com.fullness.ec.form;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.fullness.ec.entity.Product;
import com.fullness.ec.repository.ProductRepository;

@Component
public class ProductFormValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return ProductForm.class.isAssignableFrom(clazz);
    }

    @Autowired protected ProductRepository productRepository;

    @Override
    public void validate(Object target, Errors errors) {
        ProductForm form = (ProductForm)target;
        // 商品名の重複チェック
        if(isDuplicateName(form)){
            errors.rejectValue("name","ProductForm.nameError.duplicate");
        }
        if(form.getCategory() == null || form.getImage() == null || form.getPrice() == null) return;
        // 文房具なら30円〜5000円以内
        if(form.getCategory() == 1){
            if(form.getPrice() < 30 || form.getPrice() > 5000){
                errors.rejectValue("price","ProductForm.priceError.stationeryPriceRange");
            }
        }
        // 雑貨なら100円〜10000円
        if(form.getCategory() == 2){
            if(form.getPrice() < 100 || form.getPrice() > 10000){
                errors.rejectValue("price","ProductForm.priceError.zakkaPriceRange");
            }
        }
        // バソコン周辺機器なら300円〜30000円
        if(form.getCategory() == 3){
            if(form.getPrice() < 300 || form.getPrice() > 30000){
                errors.rejectValue("price","ProductForm.priceError.pcPriceRange");
            }
        }
        // 画像のチェック
        if(!isUpdateImage(form)){
            errors.rejectValue("image","ProductForm.imageFileError.empty");
        }
        if(!isImageFile(form)){
            errors.rejectValue("image","ProductForm.imageFileError.contentTypeNotImage");
        }
    }
    /**
     * 商品名が重複しているかどうかを判定する
     * @param name
     * @return
     */
    protected boolean isDuplicateName(ProductForm form){
        Product product = productRepository.findByName(form.getName());
        if(product == null) return false;
        return product.getName() != form.getName();
    }
    /**
     * 画像の更新有無を判定する
     */
    protected boolean isUpdateImage(ProductForm form){
        return form.getImage().getSize() > 0;
    }
    /**
     * ファイル形式が画像かどうかを判定する
     */
    protected boolean isImageFile(ProductForm form){
        return form.getImage().getSize() > 0 && form.getImage().getContentType().startsWith("image");
    }
}
