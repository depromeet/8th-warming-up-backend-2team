package com.depromeet.health.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class EvaluateId implements Serializable {
    private Long userId;

    private Long postId;

    public EvaluateId() {
    }

    public EvaluateId(Long userId, Long postId) {
        this.userId = userId;
        this.postId = postId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }
}
