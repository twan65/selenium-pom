package org.example.pom.template.usecase.reservation;

import com.google.gson.Gson;
import org.example.pom.template.usecase.login.pages.LoginPage;
import org.example.pom.template.usecase.reservation.models.AdditionalPlan;
import org.example.pom.template.usecase.reservation.models.Contract;
import org.example.pom.template.usecase.reservation.models.ReserveHotelConfirmInfo;
import org.example.pom.template.usecase.reservation.pages.HotelPlanListPage;
import org.example.pom.template.usecase.reservation.pages.ReserveHotelConfirmPage;
import org.example.pom.template.usecase.reservation.pages.ReserveHotelPage;
import org.example.pom.template.usecase.top.pages.TopPage;
import org.example.pom.template.utils.LocalStorageUtil;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReserveHotelTest {
    private static final WebDriver driver = new ChromeDriver();


    private final static String USER_EMAIL = "test123@gmail.com";
    private final static String USER_PASSWORD = "12345678";
    private final static String USER_NAME = "test123";

    @BeforeAll
    static void setup() {
        TopPage topPage = new TopPage(driver);
        topPage.visit();
        // ローカルストレージにユーザー登録
        createUser();
    }

    @AfterAll
    static void close() {
        driver.close();
    }

    private void login() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.visit();
        loginPage.login(USER_EMAIL, USER_PASSWORD);
    }

    private ReserveHotelPage selectHotelPlan() {
        HotelPlanListPage hotelPlanListPage = new HotelPlanListPage(driver);
        hotelPlanListPage.visit();
        return hotelPlanListPage.selectHotelPlan("2");
    }

    private ReserveHotelConfirmPage reserveHotelPlan() {
        ReserveHotelPage reserveHotelPage = selectHotelPlan();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String date = LocalDate.now().plusDays(3).format(formatter);
        String term = "1";
        String headCount = "1";
        Contract contact = Contract.EMAIL;
        String comment = "ああ";

        return reserveHotelPage.reserve(date, term, headCount, USER_NAME
                , contact, comment, AdditionalPlan.BREAKFAST);
    }

    @Test
    void reserveConfirmHotelPlan() {
        login();
        ReserveHotelConfirmPage reserveHotelConfirmPage = reserveHotelPlan();

        ReserveHotelConfirmInfo reserveHotelConfirmInfo = reserveHotelConfirmPage.get();

        // TODO: reserveHotelConfirmInfo検証

        reserveHotelConfirmPage.reserveConfirm();
        String modalTitle = reserveHotelConfirmPage.getModalTitle();

        assertEquals("予約を完了しました", modalTitle);

        reserveHotelConfirmPage.closeSuccessModalWithCloseButton();

    }

    private static void createUser() {
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
