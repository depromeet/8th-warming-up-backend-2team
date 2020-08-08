package com.depromeet.health.payload;

import com.depromeet.health.model.enums.ExerciseType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BoardRequest {
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

    public String getVimeoURL() {
        return vimeoURL;
    }

    public void setVimeoURL(String vimeoURL) {
        this.vimeoURL = vimeoURL;
    }

    public ExerciseType getType() {
        return type;
    }

    public void setType(ExerciseType type) {
        this.type = type;
    }
}
