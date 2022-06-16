package com.fullness.ec.form;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
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
