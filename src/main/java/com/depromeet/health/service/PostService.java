package com.depromeet.health.service;

import com.depromeet.health.model.Post;
import com.depromeet.health.model.enums.ExerciseType;
import com.depromeet.health.payload.PostRequest;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface PostService {

    Post createUserByToken(String token, PostRequest postRequest);

    Post readPost(Long id);

    List<Post> readPosts(ExerciseType type, String token, Pageable pageable);

    List<Post> readPostByToken(String token, Pageable pageable);

    Post updatePostAsGood(Long id);

    Post updatePostAsBad(Long id);
}
