package com.depromeet.health.controller;

import com.depromeet.health.model.User;
import com.depromeet.health.model.enums.WeightType;
import com.depromeet.health.payload.LoginRequest;
import com.depromeet.health.payload.Request;
import com.depromeet.health.payload.Response;
import com.depromeet.health.payload.WeightResponse;
import com.depromeet.health.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController extends AbstractController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public Response<String> login(@RequestBody Request<LoginRequest> request) {
        LoginRequest loginRequest = request.getData();
        loginRequest.validateNotNull();
        User user = userService.readUser(loginRequest);
        return ok(user.getToken());
    }

    @GetMapping("/api/weight")
    public Response<WeightResponse> getWeight(
            @RequestHeader(value = "TOKEN") String token,
            @RequestParam(value = "type") WeightType type
    ) {
        WeightResponse weightResponse = userService.readWeight(token, type);
        return ok(weightResponse);
    }
}
