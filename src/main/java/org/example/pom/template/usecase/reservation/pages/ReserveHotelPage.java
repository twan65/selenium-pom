package org.example.pom.template.usecase.reservation.pages;

import org.example.pom.template.usecase.PetClinicPageObject;
import org.example.pom.template.usecase.reservation.models.AdditionalPlan;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ReserveHotelPage extends PetClinicPageObject {

    public ReserveHotelPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public void isReady() {
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("reserve-form")));
    }

    // TODO: この形でも大丈夫なのか
    @Deprecated
    public void visit() {
        throw new RuntimeException("visitWithを利用してください。");
    }

    public void visitWith(String planId) {
        visit("/ja/reserve.html?plan-id=" + planId);
    }

    public ReserveHotelConfirmPage reserve(String date, String term, String headCount
            , String name, String contact, String comment, AdditionalPlan ...additionalPlans) {

        driver.findElement(By.id("date")).sendKeys(date);
        driver.findElement(By.id("term")).sendKeys(term);
        driver.findElement(By.id("headCount")).sendKeys(headCount);

        for (AdditionalPlan additionalPlan : additionalPlans) {
            switch (additionalPlan) {
                case BREAKFAST: {
                    driver.findElement(By.id("breakfast")).click();
                }
                case EARLY_CHECK_IN: {
                    driver.findElement(By.id("early-check-in")).click();
                }
                case SIGHTSEEING: {
                    driver.findElement(By.id("sightseeing")).click();
                }
            }
        }

        driver.findElement(By.id("name")).sendKeys(name);
        driver.findElement(By.id("contact")).sendKeys(contact);
        driver.findElement(By.id("comment")).sendKeys(comment);

        ReserveHotelConfirmPage reserveHotelConfirmPage = new ReserveHotelConfirmPage(driver);
        reserveHotelConfirmPage.isReady();

        return reserveHotelConfirmPage;
    }
}
