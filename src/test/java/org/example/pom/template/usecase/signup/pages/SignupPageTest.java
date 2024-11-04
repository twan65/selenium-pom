package org.example.pom.template.usecase.signup.pages;

import org.example.pom.template.usecase.my.models.MyInfo;
import org.example.pom.template.usecase.my.pages.MyPage;
import org.example.pom.template.usecase.signup.models.SignupInfo;
import org.example.pom.template.usecase.signup.models.UserRank;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SignupPageTest {

    static WebDriver driver = new ChromeDriver();
    final SignupPage page = new SignupPage(driver);

    @AfterAll
    static void clean() {
        driver.close();
    }

    @Test
    public void signup() {
        page.visit();

        String email = "test123@gmail.com";
        String password = "12345678";
        String passwordConfirmation = "12345678";
        String userName = "test123";
        UserRank rank = UserRank.NORMAL;

        SignupInfo signupUserInfo = new SignupInfo(email, password, passwordConfirmation, userName, rank);

        MyPage myPage = page.signup(signupUserInfo);
        MyInfo myInfo = myPage.get();

        assertEquals("マイページ | HOTEL PLANISPHERE - テスト自動化練習サイト", myInfo.getTitle());
        assertEquals(email, myInfo.getEmail());
        assertEquals(userName, myInfo.getUsername());
        assertEquals("一般会員", myInfo.getRank());
    }
}
