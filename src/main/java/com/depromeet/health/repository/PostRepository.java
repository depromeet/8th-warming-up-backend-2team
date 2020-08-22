package com.depromeet.health.repository;

import com.depromeet.health.model.Post;
import com.depromeet.health.model.enums.ExerciseType;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<List<Post>> findByTypeAndUserIdNot(ExerciseType type, Long id, Pageable pageable);

    Optional<List<Post>> findAllByUserIdNot(Long id, Pageable pageable);

    Optional<List<Post>> findByUserId(Long id, Pageable pageable);

    @Query(value = "select max(weight) from post where user_id=:id and post.type=:type", nativeQuery = true)
    Optional<Long> findOneByUserIdAndTypeOrderByWeightDesc(@Param("id") Long id, @Param("type") String type);
}
