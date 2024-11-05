package org.example.pom.template.usecase.reservation.models;

import java.util.List;

public class ReserveHotelConfirmInfo {
    private final String term;
    private final String headCount;
    private final String username;
    private final String comment;
    private final List<String> plans;

    public ReserveHotelConfirmInfo(String term, String headCount, String username, String comment, List<String> plans) {
        this.term = term;
        this.headCount = headCount;
        this.username = username;
        this.comment = comment;
        this.plans = plans;
    }

    public String getTerm() {
        return term;
    }

    public String getHeadCount() {
        return headCount;
    }

    public String getUsername() {
        return username;
    }

    public String getComment() {
        return comment;
    }

    public List<String> getPlans() {
        return plans;
    }
}
