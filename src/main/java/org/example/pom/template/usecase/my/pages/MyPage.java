package org.example.pom.template.usecase.my.pages;

import org.example.pom.template.usecase.PetClinicPageObject;
import org.example.pom.template.usecase.my.models.MyInfo;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MyPage extends PetClinicPageObject {
    public MyPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public void isReady() {
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
    }

    @Override
    public void visit() {
        visit("/ja/mypage.html");
    }

    /**
     * アイコン設定画面へ遷移
     * @return アイコン設定PO
     */
    public SettingIconPage setIcon() {
        WebElement findSettingIconButton = driver.findElement(By.id("icon-link"));
        findSettingIconButton.click();

        SettingIconPage settingIconPage = new SettingIconPage(driver);
        settingIconPage.isReady();

        return settingIconPage;
    }

    // TODO: 押下時に、ブラウザのデフォルトのダイアログが表示
    public void deleteUser() {

    }

    public MyInfo get() {
        String title = driver.getTitle();
        String email = driver.findElement(By.id("email")).getText();
        String username = driver.findElement(By.id("username")).getText();
//        String rank = driver.findElement(By.id("rank")).getText();

        // TODO
        String rank = "一般会員";
        return new MyInfo(title, email, username, rank);
    }

}
