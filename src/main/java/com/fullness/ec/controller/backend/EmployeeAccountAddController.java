package com.fullness.ec.controller.backend;

import com.fullness.ec.entity.EmployeeAccount;
import com.fullness.ec.form.EmployeeAccountForm;
import com.fullness.ec.helper.EmployeeAccountHelper;
import com.fullness.ec.service.EmployeeAccountService;
import com.fullness.ec.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

/**
 * 担当者アカウント登録コントローラー
 */
@SessionAttributes("employeeAccountForm")
@RequestMapping("/admin/emp-add")
@Controller
public class EmployeeAccountAddController {

    /** テンプレートディレクトリパス */
    private static final String TEMPLATE_DIR = "backend/emp-add";

    /** 担当者アカウント登録関連ヘルパー */
    @Autowired
    private EmployeeAccountHelper helper;

    /** 担当者アカウントサービス */
    @Autowired
    private EmployeeAccountService employeeAccountService;

    /** 社員サービス */
    @Autowired
    private EmployeeService employeeService;

    /**
     * フォーム初期化処理
     * @return 担当者アカウント登録用フォーム
     */
    @ModelAttribute("employeeAccountForm")
    public EmployeeAccountForm setupForm(){
        return new EmployeeAccountForm();
    }

    /**
     * 
     * @param model
     * @return
     */
    @GetMapping
    public String form(Model model){
        model.addAttribute("employees",employeeService.findAll());
        return TEMPLATE_DIR + "/form";
    }

    @PostMapping("/confirm")
    public String confirm(@Validated @ModelAttribute("employeeAccountForm") EmployeeAccountForm form, BindingResult result, Model model){
        if(result.hasErrors()) return "backend/emp-add/form";
        model.addAttribute("employee",employeeService.findById(form.getEmpName()));
        return TEMPLATE_DIR + "/confirm";
    }

    @PostMapping("/complete")
    public String complete(
            @ModelAttribute("employeeAccountForm") EmployeeAccountForm form,
            Model model,
            SessionStatus sessionStatus
    ){
        if(form.isEmpty()) throw new RuntimeException("フォームデータがありません");
        EmployeeAccount employeeAccount = helper.convertEmployeeAccount(form);
        employeeAccount.setEmployee(employeeService.findById(form.getEmpName()));
        employeeAccountService.insert(employeeAccount);
        sessionStatus.setComplete();
        model.addAttribute("employeeAccount",employeeAccount);
        return TEMPLATE_DIR + "/complete";
    }

    @GetMapping("/cancel")
    public String cancel(SessionStatus sessionStatus){
        sessionStatus.setComplete();
        return "redirect:/admin";
    }

}
