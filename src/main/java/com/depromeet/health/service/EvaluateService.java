package com.depromeet.health.service;

import com.depromeet.health.model.Evaluate;
import com.depromeet.health.repository.EvaluateRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EvaluateService {

    @Autowired
    EvaluateRepository evaluateRepository;

    public Evaluate createEvaluate(Long userId, Long postId) {
        Evaluate evaluate = new Evaluate(userId, postId);
        return evaluateRepository.save(evaluate);
    }

    public Boolean isEvaluatePost(Long userId, Long postId) {
        Optional<Evaluate> evaluate = evaluateRepository.findByUserIdAndPostId(userId, postId);
        return evaluate.isPresent();
    }
}
