package com.fullness.ec.selenide;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.junit5.TextReportExtension;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

@ExtendWith(TextReportExtension.class)
@Epic("Selenide + Allureのテスト")
public class ScreenshotTest {
    @BeforeAll
    public static void setUp() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));
        WebDriverRunner.addListener(new AbstractWebDriverEventListener() {
            @Override
            //要素をクリックする直前の処理
            public void beforeClickOn(WebElement element, WebDriver driver){
                screenshot();
                System.out.println("beforeClickOn:" + driver.getCurrentUrl());
            }
            @Override
            //要素をクリックした直後の処理
            public void afterClickOn(WebElement element, WebDriver driver){
                screenshot();
                System.out.println("afterClickOn:" + driver.getCurrentUrl());
            }
            @Attachment(type = "image/png")
            // スクリーンショットを取得
            public byte[] screenshot() {
                return Selenide.screenshot(OutputType.BYTES);
            }
        });
    }

    @Test
    @Description("Selenide + Allureのテストの説明")
    @Story("Selenide + Allureのテストのストーリー")
    @Step("Googleで検索して表示させる")
    public void Google検索するテスト() {
        open("https://www.google.co.jp/");
        $(By.name("q")).val("Selenide").pressEnter();
        $(byText("Selenide: concise UI tests in Java")).click();
        $(byText("Blog")).click();
    }
}