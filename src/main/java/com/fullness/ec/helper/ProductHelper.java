package com.fullness.ec.helper;

import com.fullness.ec.entity.Product;
import com.fullness.ec.entity.ProductCategory;
import com.fullness.ec.entity.ProductStock;
import com.fullness.ec.form.ProductForm;
import java.util.Base64;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * 商品ヘルパークラス<br>
 * 商品関連のフォーム・エンティティを変換などを行うヘルパークラス
 */
public class ProductHelper {

    /**
     * 商品関連画像フォーム、選択したカテゴリ、商品IDから商品エンティティに変換
     * @param form 商品関連フォーム
     * @param category 選択された商品カテゴリ
     * @param id 商品ID
     * @return 商品エンティティ
     */
    public static Product convertEntity(ProductForm form, ProductCategory category, Integer id){
        Product product = new Product();
        product.setId(id);
        product.setName(form.getName());
        product.setPrice(form.getPrice());
        product.setImageUrl(form.getImage().getOriginalFilename());
        product.setProductCategory(category);
        ProductStock productStock = new ProductStock();
        productStock.setStock(form.getStock());
        productStock.setProductId(id);
        product.setStock(productStock);
        return product;
    }

    /**
     * 商品エンティティから商品関連画面のフォームに変換
     * @param product 商品データ
     * @return 商品関連画面（追加、編集）のフォーム
     */
    public static ProductForm convertForm(Product product){
        System.out.println("prduct:"+product);
        ProductForm form = new ProductForm();
        form.setId(product.getId());
        form.setName(product.getName());
        form.setPrice(product.getPrice());
        form.setCategory(product.getProductCategory().getId());
        form.setStock(product.getStock().getStock());
        return form;
    }

    /**
     * フォームからファイルを保存するメソッド
     * @param fileName ファイル名前
     * @param data ファイルのバイトデータ
     * @return ファイル名
     * @throws Exception ファイル保存関連の例外
     */
    public static String uploadFile(String fileName,byte[] data) throws Exception {
        System.out.println("uploadFile:"+fileName+" byte:"+data.length);
        String uuidFileName = UUID.randomUUID().toString() + "_" + fileName;
        System.out.println("uuidFileName:"+uuidFileName);
        // ファイルパスは相対パスで指定
        String filePath = new File("src/main/webapp/img" + File.separator + uuidFileName).getAbsolutePath();
        System.out.println("filpath:"+filePath);
        // もしfilePathが「/src/main/webapp/img」で始まっている場合、先頭の「/」を削除
        if (filePath.startsWith("/")) {
            filePath = filePath.substring(1);
        }
        FileOutputStream fos = new FileOutputStream(filePath);
        fos.write(data);
        fos.close();
        return uuidFileName;
    }

    /**
     * フォームから取得したMultipartfileからBase64エンコードした画像バイトデータ文字列を生成
     * @param file フォームから入力されたMultipartfile
     * @return Base64エンコードした画像バイトデータ文字列
     * @throws IOException ファイルIO関連の例外
     */
    public static String createBase64ImageString(MultipartFile file) throws IOException {
        StringBuffer data = new StringBuffer();
        String base64 = new String(Base64.getEncoder().encodeToString(file.getBytes()));
        data.append("data:image/jpeg;base64,");
        data.append(base64);
        return data.toString();
    }
}
