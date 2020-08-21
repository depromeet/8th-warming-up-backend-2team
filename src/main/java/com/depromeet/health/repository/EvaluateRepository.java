package com.depromeet.health.repository;

import com.depromeet.health.model.Evaluate;
import com.depromeet.health.model.EvaluateId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EvaluateRepository extends JpaRepository<Evaluate, EvaluateId> {
}
