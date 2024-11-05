package org.example.pom.template.usecase.reservation.pages;

import org.example.pom.template.usecase.PetClinicPageObject;
import org.example.pom.template.usecase.reservation.models.ReserveHotelConfirmInfo;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ReserveHotelConfirmPage extends PetClinicPageObject {

    public ReserveHotelConfirmPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public void isReady() {
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("confirm")));
    }

    @Override
    public void visit() {
        visit("/ja/confirm.html");
    }

    public ReserveHotelConfirmInfo get() {
        String term = driver.findElement(By.id("term")).getText();
        String headCount = driver.findElement(By.id("head-count")).getText();
        String username = driver.findElement(By.id("username")).getText();
        String comment = driver.findElement(By.id("comment")).getText();

        List<WebElement> planRows = driver.findElement(By.id("plans"))
                .findElements(By.tagName("li"));

        List<String> plans = planRows.stream().map(WebElement::getText)
                .collect(Collectors.toList());

        return new ReserveHotelConfirmInfo(term, headCount, username, comment, plans);
    }

    /**
     * 成功ダイアログのタイトルを取得する
     * @return 成功ダイアログのタイトル
     */
    public String getModalTitle() {
        return driver.findElement(By.id("success-modal"))
                .findElement(By.className("modal-title")).getText();
    }

    /**
     * 「この内容で予約する」押下
     */
    public void reserveConfirm() {
        WebElement findReserveConfirmButton = driver.findElement(By.id("confirm"))
                .findElement(By.tagName("button"));
        findReserveConfirmButton.click();
    }

    /**
     * 成功ダイアログにて「閉じる」押下
     */
    public void closeSuccessModalWithSuccessButton() {
        closeModal("btn-success");
    }

    /**
     * 成功ダイアログにて「X」押下
     */
    public void closeSuccessModalWithCloseButton() {
        closeModal("close");
    }

    private void closeModal(String className) {
        WebElement modal = driver.findElement(By.id("success-modal"))
                .findElement(By.className(className));
        modal.click();
    }
}
