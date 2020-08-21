package com.depromeet.health.payload;

import com.depromeet.health.exception.RequestNullPointerException;
import com.depromeet.health.model.enums.ExerciseType;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class PostRequest {
    @JsonProperty("title")
    String title;

    @JsonProperty("content")
    String content;

    @JsonProperty("vimeoId")
    Long vimeoId;

    @JsonProperty("createdAt")
    LocalDateTime createdAt;

    @JsonProperty("type")
    @Enumerated(EnumType.STRING)
    ExerciseType type;

    @JsonProperty("weight")
    Long weight;

    @JsonProperty("playTime")
    Long playTime;

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
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

    public Long getVimeoId() {
        return vimeoId;
    }

    public void setVimeoId(Long vimeoId) {
        this.vimeoId = vimeoId;
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

    public Long getPlayTime() {
        return playTime;
    }

    public void setPlayTime(Long playTime) {
        this.playTime = playTime;
    }

    public void validateNotNull() throws RequestNullPointerException {
        if (this.title == null | this.content == null | this.vimeoId == null | this.createdAt == null | this.weight == null | this.type == null | this.playTime == null) {
            throw new RequestNullPointerException();
        }
    }
}
