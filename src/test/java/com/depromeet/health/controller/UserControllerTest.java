package com.depromeet.health.controller;

import com.depromeet.health.TestBase;
import com.depromeet.health.model.enums.WeightType;
import com.depromeet.health.payload.Response;
import com.depromeet.health.payload.WeightResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerTest extends TestBase {

    @Test
    @DisplayName("최대 중량 조회 테스트")
    public void getMaxWeightTest() throws Exception {
        // when
        String result = mockMvc.perform(get("/api/weight")
                .queryParam("type", WeightType.max.name())
                .header("TOKEN", token))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        Response<WeightResponse> response = objectMapper.readValue(result, new TypeReference<>() {
        });

        WeightResponse weightResponse = response.getData();

        // then
        assertEquals(100L, weightResponse.getBenchWeight());
        assertEquals(150L, weightResponse.getDeadLiftWeight());
        assertEquals(50L, weightResponse.getSquatWeight());
    }
}
