package com.depromeet.health.model.enums;

public enum GenderType {
    MAN("man"), WOMAN("woman"), NONE("none");

    private final String gender;

    GenderType(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }
}
