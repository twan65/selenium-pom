package org.example.pom.template.usecase.reservation.models;

public class HotelPlanInfo {
    private final String id;
    private final String title;
    private final String price;
    private final String minimumPeople;
    private final String roomType;


    public HotelPlanInfo(String id, String title, String price, String minimumPeople, String roomType) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.minimumPeople = minimumPeople;
        this.roomType = roomType;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getPrice() {
        return price;
    }

    public String getMinimumPeople() {
        return minimumPeople;
    }

    public String getRoomType() {
        return roomType;
    }
}
