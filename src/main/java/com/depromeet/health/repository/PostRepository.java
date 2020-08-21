package com.depromeet.health.repository;

import com.depromeet.health.model.Post;
import com.depromeet.health.model.enums.ExerciseType;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<List<Post>> findByTypeAndUserIdNot(ExerciseType type, Long id, Pageable pageable);

    Optional<List<Post>> findAllByUserIdNot(Long id, Pageable pageable);

    Optional<List<Post>> findByUserId(Long id, Pageable pageable);
}
