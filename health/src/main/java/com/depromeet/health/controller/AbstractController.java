package com.depromeet.health.controller;

import com.depromeet.health.payload.Response;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AbstractController {
    protected Response<String> ok()  {
        return new Response<>("Success", true);
    }

    protected <T> Response<T> ok(T data)  {
        return new Response<>("Message", true, data);
    }
}
