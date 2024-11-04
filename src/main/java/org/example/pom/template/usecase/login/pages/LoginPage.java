package org.example.pom.template.usecase.login.pages;

import org.example.pom.template.usecase.PetClinicPageObject;
import org.example.pom.template.usecase.my.pages.MyPage;
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
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
    }

    @Override
    public void visit() {
        visit("/ja/login.html");
    }

    /**
     * ログインを実行する
     *
     * @param email メールアドレス
     * @param pass  パスワード
     * @return マイページPO
     */
    public MyPage login(String email, String pass) {
        driver.findElement(By.id("email")).sendKeys(email);
        driver.findElement(By.id("password")).sendKeys(pass);

        WebElement findLoginButton = driver.findElement(By.id("login-form")).findElement(By.tagName("button"));
        findLoginButton.click();

        MyPage myPage = new MyPage(driver);
        myPage.isReady();
        return myPage;
    }

}
