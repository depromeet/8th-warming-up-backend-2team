package com.depromeet.health.controller;

import com.depromeet.health.model.Board;
import com.depromeet.health.model.enums.ExerciseType;
import com.depromeet.health.payload.BoardRequest;
import com.depromeet.health.payload.Request;
import com.depromeet.health.payload.Response;
import com.depromeet.health.service.BoardService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class BoardController extends AbstractController {

    @Autowired
    BoardService boardService;

    @GetMapping("test")
    public String healthcheck() {
        return "I'm alive";
    }

    @PostMapping("board")
    public Response<String> addBoard(
            @RequestHeader(value = "TOKEN") String token,
            @RequestBody Request<BoardRequest> request
    ) {
        BoardRequest boardRequest = request.getData();
        boardService.insertUserByToken(token, boardRequest);
        return ok();
    }

    @GetMapping("board")
    public Response<List<Board>> getBoards(
            @RequestParam(value = "type",required = false) ExerciseType type
    ) {
        List<Board> boards;
        
        if(type == null) {
            boards = boardService.loadAllBoard();
        } else {
            boards = boardService.loadBoardByType(type);
        }
        return ok(boards);
    }

    @GetMapping("board/my")
    public Response<List<Board>> getMyBoards(
            @RequestHeader(value = "TOKEN") String token
    ){
        List<Board> boards = boardService.loadBoardByToken(token);
        return ok(boards);
    }
}
