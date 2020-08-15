package com.depromeet.health.payload;

import com.depromeet.health.exception.RequestNullPointerException;
import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginRequest {
    @JsonProperty("email")
    String email;

    @JsonProperty("name")
    String name;

    @JsonProperty("profile")
    String profile;

    @JsonProperty("uid")
    String uid; // Firebase UID

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void validateNotNull() throws RequestNullPointerException {
        if (this.email == null | this.name == null | this.profile == null | this.uid == null) {
            throw new RequestNullPointerException();
        }
    }
}
