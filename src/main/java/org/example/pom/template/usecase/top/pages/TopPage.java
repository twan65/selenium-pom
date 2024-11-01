package org.example.pom.template.usecase.top.pages;

import org.example.pom.template.usecase.PetClinicPageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TopPage extends PetClinicPageObject {
    public TopPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public void isReady() {

    }

    @Override
    public void visit() {
        visit("/top");
    }

    public TopInfo get() {
        String title = driver.findElement(By.tagName("title")).getText();
        String userName = driver.findElement(By.id("userName")).getText();

        return new TopInfo(title, userName);
    }

    /**
     * TODO: 本来はvoidではなく、XXXPageになる
     */
    public void moveXXXPage() {

    }
}
