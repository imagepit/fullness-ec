package com.fullness.ec.form;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fullness.ec.entity.Product;
import com.fullness.ec.repository.ProductRepository;

@Component
public class ProductUpdateValidator extends ProductFormValidator {
  @Autowired private ProductRepository productRepository;
  @Override
  public boolean isDuplicateName(ProductForm form) {
    // idから商品名を取得
    String name = productRepository.findById(form.getId()).getName();
    // 商品名が変更されていない場合は重複チェックを行わない
    if(name.equals(form.getName())) return false;
    // 商品名が重複しているかどうかを判定
    if(productRepository.findByName(form.getName()) != null) return true;
    return false;
  }
  @Override
  protected boolean isUpdateImage(ProductForm form) {
    return true;
  }
  @Override
  protected boolean isImageFile(ProductForm form) {
    if(form.getImage().isEmpty()) return true; // 更新の場合はファイルが空でもOK
    return super.isImageFile(form); // ファイルを更新した場合は親クラスのチェックを行う
  }
}
