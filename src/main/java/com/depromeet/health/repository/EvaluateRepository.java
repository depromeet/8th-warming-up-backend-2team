package com.depromeet.health.repository;

import com.depromeet.health.model.Evaluate;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EvaluateRepository extends JpaRepository<Evaluate, Long> {
    Optional<Evaluate> findByUserIdAndPostId(Long userId, Long postId);
}
