package com.fullness.ec.selenide;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

public class MenuPage {
  // h2タグのテキストを取得する
  private final SelenideElement title = Selenide.$("h2");
  public MenuPage shouldHaveTitle() {
    title.shouldHave(com.codeborne.selenide.Condition.text("メニュー"));
    return this;
  }
}
