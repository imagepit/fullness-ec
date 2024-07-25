package com.fullness.ec.selenide;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.conditions.Text;
import com.codeborne.selenide.junit5.TextReportExtension;
import com.codeborne.selenide.logevents.SelenideLogger;

import io.qameta.allure.Attachment;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import io.qameta.allure.selenide.AllureSelenide;

@ExtendWith(TextReportExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Epic("Selenide + Allureのテスト")
public class ScreenshotTest {
    // private static ChromeDriver driver;

    @LocalServerPort
    private int port;

    @SuppressWarnings("deprecation")
	  @BeforeAll
    public static void setUp() {
    	SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));
      System.setProperty("chromeoptions.args", "--remote-allow-origins=*");
      System.setProperty("webdriver.chrome.driver", "driver2/chromedriver");
      Configuration.browser = "chrome";
    	// 匿名クラスを使って実装したイベント補足クラスのインスタンスを登録
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

    private String url(String path) {
        return "http://localhost:%d%s".formatted(port, path);
    }

    @Test
    @Description("担当者アカウントログインテスト")
    @Story("担当者アカウントログインテスト")
    @Step("testユーザでログイン")
    public void ログインテスト() {
        // LoginPage loginPage = new LoginPage();
        // loginPage.open(url("/admin"));
        // loginPage.login("takahashi", "takahashi");
        // MenuPage menuPage = new MenuPage();
        // menuPage.shouldHaveTitle();
        open(url("/admin/login"));
        $(By.linkText("ログイン")).click();
        $("input[name='username']").val("takahashi");
        $("input[name='password']").val("takahashi");
        $("input[type=\"submit\"]").click();
        $("h2").shouldHave(Text.text("メニュー"));
    }
}