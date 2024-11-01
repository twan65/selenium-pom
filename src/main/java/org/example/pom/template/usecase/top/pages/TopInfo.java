package org.example.pom.template.usecase.top.pages;

/**
 * トップページ遷移後、確認する必要がある項目を定義する。
 */
public class TopInfo {
    private final String title;
    private final String userName;

    public TopInfo (String title, String userName) {
        this.title = title;
        this.userName = userName;
    }

    public String getTitle() {
        return this.title;
    }

    public String getUserName() {
        return this.userName;
    }
}
