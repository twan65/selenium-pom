package org.example.pom.template.usecase;

import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public abstract class PetClinicPageObject {
    // TODO: 設定ファイルから出力先を取得するように変更
    private final static String OUTPUT_PATH = "";
    protected final WebDriver driver;

    public PetClinicPageObject(WebDriver webDriver) {
        this.driver = webDriver;
    }

    public abstract void isReady();

    protected void captureFullScreen() {
        // スクロールしながら画面全体をキャプチャ
        BufferedImage screenshot = new AShot()
                .shootingStrategy(ShootingStrategies.viewportPasting(100))
                .takeScreenshot(driver)
                .getImage();

        // 画像を保存
        try {
            ImageIO.write(screenshot, "PNG", new File(OUTPUT_PATH));
        } catch (IOException e) {
            // TODO: ログに変える
            System.out.println("キャプチャが保存できません。");
        }

        // TODO: ログに変える
        System.out.println("Screenshot saved successfully to: " + OUTPUT_PATH);
    }

    public void visit() {
        throw new RuntimeException("このページは画面遷移がありません。");
    }

    /**
     * ページ遷移
     *
     * @param path 画面パス
     */
    protected void visit(String path) {
        // TODO: 設定ファイルからURLを取得するように変更
        driver.get("http://localhost:8080" + path);
        isReady();
        captureFullScreen();
    }
}
