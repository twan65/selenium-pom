package org.example.pom.template.usecase.login.pages;

import org.example.pom.template.usecase.PetClinicPageObject;
import org.example.pom.template.usecase.top.pages.TopPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends PetClinicPageObject {

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public void isReady() {
        // 画面ががブラウザで準備できたのかを確認
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
    }

    @Override
    public void visit() {
        visit("/login");
    }

    /**
     * ログインを実行する
     * @param id ユーザーネーム
     * @param pass パスワード
     * @return トップページ
     */
    public TopPage login(String id, String pass) {
        driver.findElement(By.id("username")).sendKeys(id);
        driver.findElement(By.id("password")).sendKeys(pass);

        WebElement findLoginButton = driver.findElement(By.id("login-form")).findElement(By.tagName("button"));
        findLoginButton.click();

        TopPage topPage = new TopPage(driver);
        topPage.isReady();
        return topPage;
    }

}
