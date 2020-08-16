package com.depromeet.health;

import com.depromeet.health.payload.LoginRequest;
import com.depromeet.health.payload.Request;
import com.depromeet.health.payload.Response;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class WorkFlowTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    String token;

    @Test
    @DisplayName("로그인 테스트")
    @Order(1)
    public void loginTest() throws Exception {
        // given
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setName("Harry");
        loginRequest.setEmail("harrycode@kakao.com");
        loginRequest.setProfile("profile");
        loginRequest.setUid("12345");

        Request<LoginRequest> request = new Request<>(loginRequest);

        // when
        String contentAsString = mockMvc.perform(post("/login")
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        Response<String> response = objectMapper.readValue(contentAsString, new TypeReference<>() {
        });
        token = response.getData();

        // then
        assertNotNull(token);
    }

}
