package com.fullness.ec.form;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
