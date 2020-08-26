package com.depromeet.health.controller;

import com.depromeet.health.model.Post;
import com.depromeet.health.model.enums.EvaluateType;
import com.depromeet.health.model.enums.ExerciseType;
import com.depromeet.health.payload.PostRequest;
import com.depromeet.health.payload.PostResponse;
import com.depromeet.health.payload.Request;
import com.depromeet.health.payload.Response;
import com.depromeet.health.service.EvaluateService;
import com.depromeet.health.service.PostService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class PostController extends AbstractController {

    @Autowired
    PostService postService;

    @Autowired
    EvaluateService evaluateService;

    @Value("${thumbnail}")
    String DEFAULT_THUMBNAIL;

    @PostMapping("post")
    public Response<String> writePost(
            @RequestHeader(value = "TOKEN") String token,
            @RequestBody Request<PostRequest> request
    ) {
        PostRequest postRequest = request.getData();
        postRequest.validateNotNull();
        postService.createUserByToken(token, postRequest);
        return ok();
    }

    @GetMapping("post")
    public Response<List<PostResponse>> getPosts(
            @RequestHeader(value = "TOKEN") String token,
            @RequestParam(value = "type", required = false) ExerciseType type,
            @PageableDefault(sort = "createdAt", size = 20, direction = Sort.Direction.DESC) Pageable pageable
    ) {

        List<Post> posts = postService.readPosts(type, token, pageable);
        List<PostResponse> postResponses = posts.stream().map(PostResponse::new).collect(Collectors.toList());
        return ok(postResponses);
    }

    @DeleteMapping("post/{id}")
    public Response<String> deletePost(
            @PathVariable("id") Long postId) {
        postService.deletePost(postId);
        return ok();
    }

    @PutMapping("post/{id}")
    public Response<String> updatePost(
            @RequestHeader(value = "TOKEN") String token,
            @PathVariable("id") Long postId,
            @RequestBody Request<PostRequest> request) {
        PostRequest postRequest = request.getData();
        postRequest.validateNotNull();
        postService.updatePost(postId, token, postRequest);
        return ok();
    }

    @GetMapping("post/my")
    public Response<List<Post>> getMyPosts(
            @RequestHeader(value = "TOKEN") String token,
            @PageableDefault(sort = "createdAt", size = 20, direction = Sort.Direction.DESC) Pageable pageable
    ) {
        List<Post> posts = postService.readPostByToken(token, pageable);
        return ok(posts);
    }

    @GetMapping("post/{post_id}")
    public Response<PostResponse> getPost(
            @PathVariable("post_id") Long postId
    ) {
        Post post = postService.readPost(postId);
        Boolean isEvaluated = evaluateService.isEvaluatePost(post.getUser().getId(), postId);
        PostResponse postResponse = new PostResponse(post, isEvaluated);
        return ok(postResponse);
    }

    @PatchMapping("post/{post_id}")
    public Response<PostResponse> evaluatePost(
            @PathVariable("post_id") Long postId,
            @RequestParam("type") EvaluateType evaluateType
    ) {
        Post post = postService.updatePostByEvaluateType(postId, evaluateType);
        evaluateService.createEvaluate(post.getUser().getId(), postId);
        PostResponse postResponse = new PostResponse(post, true);
        return ok(postResponse);
    }
}
