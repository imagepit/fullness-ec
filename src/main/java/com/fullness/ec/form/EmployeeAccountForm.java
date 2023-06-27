package com.fullness.ec.form;

import lombok.Data;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.io.Serializable;

@Data
public class EmployeeAccountForm implements Serializable {
    private Integer empName;
    @NotNull
    @Size(min = 5, max = 20)
    @Pattern(regexp = "[a-zA-Z0-9]*")
    private String userName;
    @NotNull
    @Size(min = 4, max = 10)
    @Pattern(regexp = "[a-zA-Z0-9]*")
    private String password;

    public boolean isEmpty(){
        return (empName == null && userName == null && password == null);
    }
}
