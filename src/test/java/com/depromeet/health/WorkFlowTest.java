package com.depromeet.health;

import com.depromeet.health.model.enums.ExerciseType;
import com.depromeet.health.payload.LoginRequest;
import com.depromeet.health.payload.PostRequest;
import com.depromeet.health.payload.Request;
import com.depromeet.health.payload.Response;
import com.depromeet.health.repository.UserRepository;
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
    UserRepository userRepository;

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

    @Test
    @DisplayName("게시글 작성")
    @Order(2)
    public void writePostTest() throws Exception {
        // given
        loginTest();

        PostRequest postRequest = new PostRequest();
        postRequest.setTitle("제 데드 리프트 어떻습니까 형님들");
        postRequest.setContent("Hi");
        postRequest.setType(ExerciseType.deadlift);
        postRequest.setVimeoId(1234L);
        postRequest.setWeight(300L);
        postRequest.setPlayTime(324234L);

        Request<PostRequest> request = new Request<>(postRequest);


        // when
        mockMvc.perform(post("/api/post")
                .header("TOKEN", token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
