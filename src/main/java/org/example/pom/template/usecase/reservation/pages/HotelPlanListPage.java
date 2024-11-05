package org.example.pom.template.usecase.reservation.pages;

import org.example.pom.template.usecase.PetClinicPageObject;
import org.example.pom.template.usecase.reservation.models.HotelPlanInfo;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class HotelPlanListPage extends PetClinicPageObject {

    public HotelPlanListPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public void isReady() {
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("plan-list")));
    }

    @Override
    public void visit() {
        visit("/ja/plans.html");
    }

    public List<HotelPlanInfo> get() {
        List<HotelPlanInfo> plans = new ArrayList<>();

        WebElement hotelPlanList = driver.findElement(By.id("plan-list"));
        List<WebElement> cards = hotelPlanList.findElements(By.className("card-body"));


        for (WebElement card : cards) {
            String title = card.findElement(By.className("card-title")).getText();
            String reserveHotelHref = card.findElement(By.tagName("a")).getAttribute("href");
            String reserveHotelId = "";
            if (reserveHotelHref != null) {
                reserveHotelId = reserveHotelHref.substring(reserveHotelHref.length() - 1);
            }

            List<WebElement> body = card.findElements(By.tagName("li"));

            String price = body.get(0).getText().trim();
            String minimumPeople = body.get(1).getText().trim();
            String roomType = body.get(2).getText().trim();

            plans.add(new HotelPlanInfo(reserveHotelId, title, price, minimumPeople, roomType));
        }

        return plans;
    }

    public ReserveHotelPage selectHotelPlan(String id) {
        WebElement hotelPlanList = driver.findElement(By.id("plan-list"));
        List<WebElement> findAnchors = hotelPlanList.findElements(By.tagName("a"));

        for (WebElement anchor : findAnchors) {
            String href = anchor.getAttribute("href");
            if (href == null || !href.contains("plan-id")) {
                continue;
            }

            String reserveHotelId = href.substring(href.length() - 1);
            if (id.equals(reserveHotelId)) {
                anchor.click();
                ReserveHotelPage reserveHotelPage = new ReserveHotelPage(driver);
                reserveHotelPage.isReady();
                return reserveHotelPage;
            }
        }

        return null;
    }
}
