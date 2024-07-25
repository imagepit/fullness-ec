package com.fullness.ec.selenide;

import com.codeborne.selenide.Selenide;

public class LoginPage {
  public LoginPage open(String url) {
    Selenide.open(url);
    return this;
  }
  public LoginPage login(String userName, String password) {
    Selenide.$("input[name='username']").setValue(userName);
    Selenide.$("input[name='password']").setValue(password);
    Selenide.$("button[type=submit]").click();
    return this;
  }
}
