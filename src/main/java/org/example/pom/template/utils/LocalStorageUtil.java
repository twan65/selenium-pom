package org.example.pom.template.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

/**
 * TODO: エラー処理を追加すること
 */
public class LocalStorageUtil {
    private final JavascriptExecutor js;

    public LocalStorageUtil(WebDriver driver) {
        this.js = (JavascriptExecutor) driver;
    }

    public void setItem(String key, String value) {
        js.executeScript(String.format(
                "window.localStorage.setItem('%s', '%s');", key, value));
    }

    public String getItem(String key) {
        return (String) js.executeScript(String.format(
                "return window.localStorage.getItem('%s');", key));
    }

    public void removeItem(String key) {
        js.executeScript(String.format(
                "window.localStorage.removeItem('%s');", key));
    }

    public void clear() {
        js.executeScript("window.localStorage.clear();");
    }

    public Long getSize() {
        return (Long) js.executeScript("return window.localStorage.length;");
    }
}
