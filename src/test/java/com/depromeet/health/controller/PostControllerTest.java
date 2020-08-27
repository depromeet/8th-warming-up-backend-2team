package com.depromeet.health.controller;

import com.depromeet.health.TestBase;
import com.depromeet.health.model.enums.ExerciseType;
import com.depromeet.health.payload.PostRequest;
import com.depromeet.health.payload.Request;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PostControllerTest extends TestBase {

    @Test
    @DisplayName("게시글 작성 테스트")
    public void writePostTest() throws Exception {
        // given
        PostRequest postRequest = new PostRequest();
        postRequest.setTitle("제 데드 리프트 어떻습니까 형님들");
        postRequest.setContent("Hi");
        postRequest.setType(ExerciseType.deadlift);
        postRequest.setVimeoId(1234L);
        postRequest.setWeight(150L);
        postRequest.setPlayTime(324234L);
        postRequest.setThumbnail("test");

        Request<PostRequest> request = new Request<>(postRequest);

        // when, then
        mockMvc.perform(post("/api/post")
                .header("TOKEN", token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("전체 게시글 조회 테스트")
    public void getPostTests() throws Exception {
        // when
        String result = mockMvc.perform(get("/api/post")
                .header("TOKEN", this.token))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        JsonNode jsonNode = objectMapper.readTree(result);

        JsonNode data = jsonNode.get("data");

        // then
        assertTrue(data.isEmpty());
    }

}
