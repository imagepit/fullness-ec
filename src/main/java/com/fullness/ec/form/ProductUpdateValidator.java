package com.fullness.ec.form;

import org.springframework.stereotype.Component;

@Component
public class ProductUpdateValidator extends ProductFormValidator {
  @Override
  public boolean isDuplicateName(ProductForm form) {
    String name = productRepository.findById(form.getId()).getName(); // idから商品名を取得
    if(name.equals(form.getName())) return false; // 商品名が変更されていない場合は重複チェックを行わない
    if(productRepository.findByName(form.getName()) != null) return true; // 変更した商品名が重複しているかどうかを判定
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
