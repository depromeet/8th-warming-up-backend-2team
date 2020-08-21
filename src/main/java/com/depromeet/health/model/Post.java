package com.depromeet.health.model;

import com.depromeet.health.model.enums.ExerciseType;
import com.depromeet.health.payload.PostRequest;
import com.depromeet.health.util.TimeUtil;
import com.depromeet.health.util.VimeoUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    String title;

    String content;

    Long vimeoId;

    LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    ExerciseType type;

    Long weight;

    String thumbnail;

    String playTime;

    Long goodCount;

    Long badCount;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    public Post() {
    }

    public Post(PostRequest postRequest, User writer) {
        this.title = postRequest.getTitle();
        this.content = postRequest.getContent();
        this.vimeoId = postRequest.getVimeoId();
        this.createdAt = postRequest.getCreatedAt();
        this.type = postRequest.getType();
        this.weight = postRequest.getWeight();
        this.thumbnail = VimeoUtil.extractThumbnailFromVimeoVideo(postRequest.getVimeoId());
        this.playTime = TimeUtil.convertMillisecondToMinuteSecond(postRequest.getPlayTime());
        this.goodCount = 0L;
        this.badCount = 0L;
        this.user = writer;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getWeight() {
        return weight;
    }

    public void setWeight(Long weight) {
        this.weight = weight;
    }

    public Long getVimeoId() {
        return vimeoId;
    }

    public void setVimeoId(Long vimeoId) {
        this.vimeoId = vimeoId;
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

    public Long getGoodCount() {
        return goodCount;
    }

    public void setGoodCount(Long goodCount) {
        this.goodCount = goodCount;
    }

    public Long getBadCount() {
        return badCount;
    }

    public void setBadCount(Long badCount) {
        this.badCount = badCount;
    }
}
