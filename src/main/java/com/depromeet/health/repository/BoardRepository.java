package com.depromeet.health.repository;

import com.depromeet.health.model.Board;
import com.depromeet.health.model.enums.ExerciseType;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
    Optional<List<Board>> findByType(ExerciseType type, Pageable pageable);

    Optional<List<Board>> findByUserId(Long id, Pageable pageable);
}
