package com.depromeet.health.service;

import com.depromeet.health.config.security.JwtTokenProvider;
import com.depromeet.health.model.Board;
import com.depromeet.health.model.User;
import com.depromeet.health.model.enums.ExerciseType;
import com.depromeet.health.payload.BoardRequest;
import com.depromeet.health.repository.BoardRepository;
import com.depromeet.health.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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

    public List<Board> loadBoardByType(ExerciseType type) {
        return boardRepository.findByType(type).orElse(new ArrayList<>());
    }

    public List<Board> loadAllBoard() {
        return boardRepository.findAll();
    }

    private User getUserByToken(String token) {
        String userPk = jwtTokenProvider.getUserPk(token);
        return (User) userService.loadUserByUsername(userPk);
    }
}
