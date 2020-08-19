package com.depromeet.health.payload;

import com.depromeet.health.model.Board;
import com.depromeet.health.model.enums.ExerciseType;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class BoardResponse {
    @JsonProperty("title")
    String title;

    @JsonProperty("content")
    String content;

    @JsonProperty("vimeoURL")
    String vimeoURL;

    @JsonProperty("createdAt")
    LocalDateTime createdAt;

    @JsonProperty("type")
    @Enumerated(EnumType.STRING)
    ExerciseType type;

    @JsonProperty("weight")
    Long weight;

    @JsonProperty("name")
    String name;

    @JsonProperty("profile")
    String profile;

    public BoardResponse(Board board) {
        this.title = board.getTitle();
        this.content = board.getContent();
        this.vimeoURL = board.getUrl();
        this.createdAt = board.getCreatedAt();
        this.type = board.getType();
        this.weight = board.getWeight();
        this.name = board.getUser().getName();
        this.profile = board.getUser().getProfile();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getVimeoURL() {
        return vimeoURL;
    }

    public void setVimeoURL(String vimeoURL) {
        this.vimeoURL = vimeoURL;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public ExerciseType getType() {
        return type;
    }

    public void setType(ExerciseType type) {
        this.type = type;
    }

    public Long getWeight() {
        return weight;
    }

    public void setWeight(Long weight) {
        this.weight = weight;
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
}
