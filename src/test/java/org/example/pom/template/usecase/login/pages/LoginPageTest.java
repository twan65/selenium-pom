package org.example.pom.template.usecase.login.pages;

import com.google.gson.Gson;
import org.example.pom.template.usecase.my.models.MyInfo;
import org.example.pom.template.usecase.my.pages.MyPage;
import org.example.pom.template.utils.LocalStorageUtil;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * ログインページのテストクラス
 */
public class LoginPageTest {
    private static final WebDriver driver = new ChromeDriver();
    private final LoginPage page = new LoginPage(driver);

    private final static String USER_EMAIL = "test123@gmail.com";
    private final static String USER_PASSWORD = "12345678";
    private final static String USER_NAME = "test123";

    @BeforeAll
    static void setup() {
    }

    @AfterAll
    static void close() {
        driver.close();
    }

    @Test
    void loginOK() {
        page.visit();
        createUser();

        MyPage myPage = page.login(USER_EMAIL, USER_PASSWORD);
        MyInfo myInfo = myPage.get();

        assertEquals("マイページ | HOTEL PLANISPHERE - テスト自動化練習サイト", myInfo.getTitle());
        assertEquals(USER_EMAIL, myInfo.getEmail());
        assertEquals(USER_NAME, myInfo.getUsername());
        assertEquals("一般会員", myInfo.getRank());
    }

    @Test
    void loginNG_Email() {
        page.visit();

        page.login("testtest@gmail.com", USER_PASSWORD);

        String errorMessageWithEmail = driver.findElement(By.id("email-message")).getText();
        String errorMessageWithPassword = driver.findElement(By.id("password-message")).getText();

        assertEquals("メールアドレスまたはパスワードが違います。", errorMessageWithEmail);
        assertEquals("メールアドレスまたはパスワードが違います。", errorMessageWithPassword);
    }

    @Test
    void loginNG_Password() {
        page.visit();

        page.login(USER_EMAIL, "11111111");

        String errorMessageWithEmail = driver.findElement(By.id("email-message")).getText();
        String errorMessageWithPassword = driver.findElement(By.id("password-message")).getText();

        assertEquals("メールアドレスまたはパスワードが違います。", errorMessageWithEmail);
        assertEquals("メールアドレスまたはパスワードが違います。", errorMessageWithPassword);;
    }

    private void createUser() {
        LocalStorageUtil localStorage = new LocalStorageUtil(driver);
        Map<String, String> user = new HashMap<>();
        user.put("email", USER_EMAIL);
        user.put("password", USER_PASSWORD);
        user.put("username", USER_NAME);
        user.put("rank", "normal");
        user.put("address", "");
        user.put("tel", "");
        user.put("gender", "0");
        user.put("birthday", "");
        user.put("notification", "false");

        Gson gson = new Gson();
        String initLoginUserInfo = gson.toJson(user);
        localStorage.setItem(USER_EMAIL, initLoginUserInfo);
    }
}
