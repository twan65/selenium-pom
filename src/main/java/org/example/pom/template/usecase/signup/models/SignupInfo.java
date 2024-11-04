package org.example.pom.template.usecase.signup.models;

public class SignupInfo {
    private String email;
    private String password;
    private String passwordConfirmation;
    private String userName;
    private UserRank rank;

    public SignupInfo(String email, String password, String passwordConfirmation, String userName, UserRank rank) {
        this.email = email;
        this.password = password;
        this.passwordConfirmation = passwordConfirmation;
        this.userName = userName;
        this.rank = rank;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public String getUserName() {
        return userName;
    }

    public UserRank getRank() {
        return rank;
    }
}
