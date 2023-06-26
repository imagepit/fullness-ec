package com.fullness.ec.form;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
public class ProductForm implements Serializable {
    private Integer id;
    @NotNull
    @Size(min = 5, max = 20)
    private String name;
    @NotNull
    private int price;

    @Min(1)
    @Max(999999999)
    @NotNull
    private int stock;
    @NotNull
    private Integer category;
    @NotNull
    private MultipartFile image;
    public boolean isEmpty(){
        return (name == null && price == 0 && stock == 0 && category == null && image == null);
    }
}
