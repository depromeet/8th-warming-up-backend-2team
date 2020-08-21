package com.depromeet.health.util;

import com.depromeet.health.exception.ResponseNullPointerException;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class VimeoUtil {
    static String TOKEN = "a0b9e2f8c546cb768c1842c291913748";
    static String VIMEO_URL = "https://api.vimeo.com/videos/";

    public static String extractThumbnailFromVimeoVideo(Long vimeoId) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "Bearer " + TOKEN);
        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<JsonNode> response = restTemplate.exchange(VIMEO_URL + vimeoId.toString(), HttpMethod.GET, httpEntity, JsonNode.class);
        JsonNode jsonNode = response.getBody();
        if (jsonNode == null) {
            throw new ResponseNullPointerException();
        }
        return jsonNode.get("pictures").get("sizes").get(3).get("link").asText();
    }
}
