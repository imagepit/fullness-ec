package com.fullness.ec.form;

import lombok.Data;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;

@Data
public class ProductCategoryForm implements Serializable {
    @Size(min = 2, max = 20)
    @NotNull
    private String name;
    public boolean isEmpty(){
        return (name == null);
    }
}
