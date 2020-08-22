package com.depromeet.health.service;

import com.depromeet.health.model.User;
import com.depromeet.health.model.enums.WeightType;
import com.depromeet.health.payload.LoginRequest;
import com.depromeet.health.payload.WeightResponse;

public interface UserService {

    User readUser(LoginRequest request);

    WeightResponse readWeight(String token, WeightType weightType);
}
