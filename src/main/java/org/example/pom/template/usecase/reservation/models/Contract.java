package org.example.pom.template.usecase.reservation.models;

public enum Contract {
    NO,
    EMAIL,
    TEL;

    public String toValue() {
        return this.name().toLowerCase();
    }
}
