package org.example.pom.template.usecase.login.pages;

import org.example.pom.template.usecase.top.pages.TopInfo;
import org.example.pom.template.usecase.top.pages.TopPage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginPageTest {
    private static final WebDriver driver = new ChromeDriver();
    private final LoginPage page = new LoginPage(driver);

    @BeforeAll
    static void setup() {
        // TODO: DBデータ生成
    }

    @AfterAll
    static void close() {
        driver.close();
        // TODO: DBデータロールバック
    }

    @Test
    void login() {
        page.visit();

        String id = "";
        String pass = "";

        TopPage topPage = page.login(id, pass);
        TopInfo topInfo = topPage.get();

        assertEquals("pageTitle", topInfo.getTitle());
        assertEquals(id, topInfo.getUserName());

    }
}
