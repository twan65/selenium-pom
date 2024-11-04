package org.example.pom.template.usecase.signup.pages;

import org.example.pom.template.usecase.PetClinicPageObject;
import org.example.pom.template.usecase.my.pages.MyPage;
import org.example.pom.template.usecase.signup.models.SignupInfo;
import org.example.pom.template.usecase.signup.models.UserRank;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SignupPage extends PetClinicPageObject {

    public SignupPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public void isReady() {
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
    }

    @Override
    public void visit() {
        visit("/ja/signup.html");
    }

    public MyPage signup(SignupInfo signupUserInfo) {
        driver.findElement(By.id("email")).sendKeys(signupUserInfo.getEmail());
        driver.findElement(By.id("password")).sendKeys(signupUserInfo.getPassword());
        driver.findElement(By.id("password-confirmation")).sendKeys(signupUserInfo.getPasswordConfirmation());
        driver.findElement(By.id("username")).sendKeys(signupUserInfo.getUserName());

        // ラジオ入力の制御
        WebElement findNormalUserRadio;
        if (UserRank.NORMAL.equals(signupUserInfo.getRank())) {
            findNormalUserRadio = driver.findElement(By.id("rank-normal"));
        } else {
            findNormalUserRadio = driver.findElement(By.id("rank-premium"));
        }
        findNormalUserRadio.click();

        WebElement findSubmitButton = driver.findElement(By.id("signup-form")).findElement(By.tagName("button"));
        findSubmitButton.click();

        MyPage myPage = new MyPage(driver);
        myPage.isReady();
        return myPage;
    }
}
