package org.example.pom.template.usecase.my.pages;

import org.example.pom.template.usecase.PetClinicPageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SettingIconPage extends PetClinicPageObject {
    public SettingIconPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public void isReady() {
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("icon")));
    }

    @Override
    public void visit() {
        visit("/ja/icon.html");
    }

    public void upload() {
        WebElement uploadButton = driver.findElement(By.id("icon"));
        uploadButton.click();
        // TODO: ローカルでファイル選択ができるのか
    }

    public void submit() {

    }
}
