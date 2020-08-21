package com.depromeet.health.service;

import com.depromeet.health.model.Evaluate;

public interface EvaluateService {
    Evaluate createEvaluate(Long userId, Long postId);

    Boolean isEvaluatePost(Long userId, Long postId);
}
