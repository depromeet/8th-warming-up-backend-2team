package com.depromeet.health.service;

import com.depromeet.health.model.Board;
import com.depromeet.health.model.enums.ExerciseType;
import com.depromeet.health.payload.BoardRequest;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface BoardService {

    Board insertUserByToken(String token, BoardRequest boardRequest);

    List<Board> loadBoardByToken(String token, Pageable pageable);

    List<Board> loadBoards(ExerciseType type, Pageable pageable);
}
