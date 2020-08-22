package com.depromeet.health.service;

import com.depromeet.health.config.security.JwtTokenProvider;
import com.depromeet.health.exception.RequestNullPointerException;
import com.depromeet.health.model.Post;
import com.depromeet.health.model.User;
import com.depromeet.health.model.enums.EvaluateType;
import com.depromeet.health.model.enums.ExerciseType;
import com.depromeet.health.payload.PostRequest;
import com.depromeet.health.repository.PostRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    PostRepository postRepository;

    @Autowired
    UserServiceImpl userServiceImpl;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Override
    public Post createUserByToken(String token, PostRequest postRequest) {
        User writer = readUserByToken(token);
        Post post = new Post(postRequest, writer);
        return postRepository.save(post);
    }

    @Override
    public Post readPost(Long id) {
        return postRepository.findById(id).orElseThrow(RequestNullPointerException::new);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Post> readPosts(ExerciseType type, String token, Pageable pageable) {
        User user = readUserByToken(token);
        if (type == null) {
            return postRepository.findAllByUserIdNot(user.getId(), pageable).orElse(new ArrayList<>());
        }
        return postRepository.findByTypeAndUserIdNot(type, user.getId(), pageable).orElse(new ArrayList<>());
    }

    @Override
    public List<Post> readPostByToken(String token, Pageable pageable) {
        User writer = readUserByToken(token);
        Long userId = writer.getId();
        return postRepository.findByUserId(userId, pageable).orElse(new ArrayList<>());
    }

    private User readUserByToken(String token) {
        String userPk = jwtTokenProvider.getUserPk(token);
        return (User) userServiceImpl.loadUserByUsername(userPk);
    }

    @Override
    public Post updatePostByEvaluateType(Long id, EvaluateType type) {
        Post post = readPost(id);
        switch (type) {
            case up:
                post.setGoodCount(post.getGoodCount() + 1);
                break;
            case down:
                post.setBadCount(post.getBadCount() + 1);
                break;
        }
        return postRepository.save(post);
    }

    @Override
    public Long readMaxWeight(Long userId, ExerciseType exerciseType) {
        Optional<Long> postByMaxWeight = postRepository.findOneByUserIdAndTypeOrderByWeightDesc(userId, exerciseType.name());
        return postByMaxWeight.orElse(null);
    }
}
