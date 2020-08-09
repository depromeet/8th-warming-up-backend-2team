package com.depromeet.health.service;

import com.depromeet.health.config.security.JwtTokenProvider;
import com.depromeet.health.model.Board;
import com.depromeet.health.model.User;
import com.depromeet.health.model.enums.ExerciseType;
import com.depromeet.health.payload.BoardRequest;
import com.depromeet.health.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@Service
public class BoardService {
    @Autowired
    BoardRepository boardRepository;

    @Autowired
    UserService userService;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    public Board insertUserByToken(String token, BoardRequest boardRequest) {
        User writer = getUserByToken(token);
        Board board = new Board(boardRequest.getTitle(), boardRequest.getVimeoURL(), boardRequest.getCreatedAt(), boardRequest.getType(), writer);
        return boardRepository.save(board);
    }

    public List<Board> loadBoardByToken(String token) {
        User writer = getUserByToken(token);
        return writer.getBoard();
    }

    @Transactional(readOnly = true)
    public List<Board> loadBoardByType(ExerciseType type, Pageable pageable) {
        Assert.notNull(pageable, "'pageable' must not be null");

        return boardRepository.findByType(type, pageable).orElse(new ArrayList<>());
    }

    @Transactional(readOnly = true)
    public List<Board> loadAllBoard(Pageable pageable) {
        Assert.notNull(pageable, "'pageable' must not be null");

        return boardRepository.findAll(pageable).toList();
    }

    private User getUserByToken(String token) {
        String userPk = jwtTokenProvider.getUserPk(token);
        return (User) userService.loadUserByUsername(userPk);
    }
}
