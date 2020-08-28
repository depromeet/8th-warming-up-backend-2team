package com.depromeet.health.payload;

import com.depromeet.health.model.Post;
import com.depromeet.health.model.enums.ExerciseType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostResponse {
    @JsonProperty("id")
    Long id;

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

    @JsonProperty("name")
    String name;

    @JsonProperty("profile")
    String profile;

    @JsonProperty("thumbnail")
    String thumbnail;

    @JsonProperty("playTime")
    String playTime;

    @JsonProperty("isEvaluated")
    Boolean isEvaluated;

    @JsonProperty("goodCount")
    Number goodCount;

    @JsonProperty("badCount")
    Number badCount;

    @JsonProperty("totalEvaluatedCount")
    Number totalEvaluatedCount;

    public PostResponse(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.vimeoId = post.getVimeoId();
        this.createdAt = post.getCreatedAt();
        this.type = post.getType();
        this.weight = post.getWeight();
        this.name = post.getUser().getName();
        this.profile = post.getUser().getProfile();
        this.thumbnail = post.getThumbnail();
        this.playTime = post.getPlayTime();
        this.isEvaluated = null;
        this.goodCount = post.getGoodCount();
        this.badCount = post.getBadCount();
        this.totalEvaluatedCount = post.getGoodCount() + post.getBadCount();
    }

    public PostResponse(Post post, Boolean isEvaluated) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.vimeoId = post.getVimeoId();
        this.createdAt = post.getCreatedAt();
        this.type = post.getType();
        this.weight = post.getWeight();
        this.name = post.getUser().getName();
        this.profile = post.getUser().getProfile();
        this.thumbnail = post.getThumbnail();
        this.playTime = post.getPlayTime();
        this.isEvaluated = isEvaluated;
        this.goodCount = post.getGoodCount();
        this.badCount = post.getBadCount();
        this.totalEvaluatedCount = post.getGoodCount() + post.getBadCount();
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getPlayTime() {
        return playTime;
    }

    public void setPlayTime(String playTime) {
        this.playTime = playTime;
    }

    public Boolean getEvaluated() {
        return isEvaluated;
    }

    public void setEvaluated(Boolean evaluated) {
        isEvaluated = evaluated;
    }

    public Number getTotalEvaluatedCount() {
        return totalEvaluatedCount;
    }

    public void setTotalEvaluatedCount(Number totalEvaluatedCount) {
        this.totalEvaluatedCount = totalEvaluatedCount;
    }
}
