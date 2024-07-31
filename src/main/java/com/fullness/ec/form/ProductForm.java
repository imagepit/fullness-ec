package com.fullness.ec.form;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;

@Data
public class ProductForm implements Serializable {
    private Integer id;
    @NotNull
    @Size(min = 5, max = 20)
    private String name;
    @Min(1)
    @Max(999999999)
    @NotNull
    private Integer price;
    @Min(1)
    @Max(1000000)
    @NotNull
    private Integer stock;
    @NotNull
    private Integer category;
    @NotNull
    private MultipartFile image;
    public boolean isEmpty(){
        return (name == null && price == null && stock == null && category == null && image == null);
    }
}
