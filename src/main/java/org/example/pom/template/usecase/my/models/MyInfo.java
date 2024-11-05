package org.example.pom.template.usecase.my.models;

public class MyInfo {
    private final String title;
    private final String email;
    private final String username;
    private final String rank;

    public MyInfo(String title, String email, String username, String rank) {
        this.title = title;
        this.email = email;
        this.username = username;
        this.rank = rank;
    }

    public String getTitle() {
        return title;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getRank() {
        return rank;
    }
}
