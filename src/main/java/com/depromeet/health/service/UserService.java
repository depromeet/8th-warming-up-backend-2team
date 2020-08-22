package com.depromeet.health.service;

import com.depromeet.health.model.User;
import com.depromeet.health.model.enums.WeightType;
import com.depromeet.health.payload.LoginRequest;

public interface UserService {

    User readUser(LoginRequest request);

    User readWeight(String token, WeightType weightType);
}
