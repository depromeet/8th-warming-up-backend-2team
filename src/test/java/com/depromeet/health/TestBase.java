package com.depromeet.health;

import com.depromeet.health.config.security.JwtTokenProvider;
import com.depromeet.health.model.Post;
import com.depromeet.health.model.User;
import com.depromeet.health.model.enums.ExerciseType;
import com.depromeet.health.payload.LoginRequest;
import com.depromeet.health.payload.Request;
import com.depromeet.health.payload.Response;
import com.depromeet.health.repository.PostRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class TestBase {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Qualifier("userService")
    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    PostRepository postRepository;

    protected String token;

    @BeforeAll
    @DisplayName("테스트용 계정 생성")
    public void init() throws Exception {
        // given
        join();
        writePost();
    }

    private void join() throws Exception {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setName("Harry");
        loginRequest.setEmail("harrycode@kakao.com");
        loginRequest.setProfile("profile");
        loginRequest.setUid("12345");

        Request<LoginRequest> request = new Request<>(loginRequest);

        String contentAsString = mockMvc.perform(post("/login")
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        Response<String> response = objectMapper.readValue(contentAsString, new TypeReference<>() {
        });
        token = response.getData();
    }

    private void writePost() {
        User user = readUserByToken(token);

        Post deadPost = new Post();
        deadPost.setTitle("테스트 데드리프트");
        deadPost.setContent("이게 본문");
        deadPost.setVimeoId(12345L);
        deadPost.setCreatedAt(LocalDateTime.now());
        deadPost.setType(ExerciseType.deadlift);
        deadPost.setWeight(150L);
        deadPost.setThumbnail("testURL");
        deadPost.setPlayTime("01:00");
        deadPost.setGoodCount(5L);
        deadPost.setBadCount(3L);
        deadPost.setUser(user);
        postRepository.save(deadPost);

        Post benchPost = new Post();
        benchPost.setTitle("테스트 벤치 포스트");
        benchPost.setContent("이게 본문");
        benchPost.setVimeoId(12345L);
        benchPost.setCreatedAt(LocalDateTime.now());
        benchPost.setType(ExerciseType.bench);
        benchPost.setWeight(100L);
        benchPost.setThumbnail("testURL");
        benchPost.setPlayTime("01:00");
        benchPost.setGoodCount(5L);
        benchPost.setBadCount(3L);
        benchPost.setUser(user);
        postRepository.save(benchPost);

        Post benchPost2 = new Post();
        benchPost2.setTitle("테스트 벤치 포스트2");
        benchPost2.setContent("이게 본문");
        benchPost2.setVimeoId(12345L);
        benchPost2.setCreatedAt(LocalDateTime.now());
        benchPost2.setType(ExerciseType.bench);
        benchPost2.setWeight(50L);
        benchPost2.setThumbnail("testURL");
        benchPost2.setPlayTime("01:00");
        benchPost2.setGoodCount(5L);
        benchPost2.setBadCount(3L);
        benchPost2.setUser(user);
        postRepository.save(benchPost2);

        Post squatPost = new Post();
        squatPost.setTitle("테스트 스쿼트 포스트");
        squatPost.setContent("이게 본문");
        squatPost.setVimeoId(12345L);
        squatPost.setCreatedAt(LocalDateTime.now());
        squatPost.setType(ExerciseType.squat);
        squatPost.setWeight(50L);
        squatPost.setThumbnail("testURL");
        squatPost.setPlayTime("01:00");
        squatPost.setGoodCount(5L);
        squatPost.setBadCount(3L);
        squatPost.setUser(user);
        postRepository.save(squatPost);

    }

    private User readUserByToken(String token) {
        String userPk = jwtTokenProvider.getUserPk(token);
        return (User) userDetailsService.loadUserByUsername(userPk);
    }
}
