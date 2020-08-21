package com.depromeet.health.service;

import com.depromeet.health.config.security.JwtTokenProvider;
import com.depromeet.health.model.Board;
import com.depromeet.health.model.User;
import com.depromeet.health.model.enums.ExerciseType;
import com.depromeet.health.payload.BoardRequest;
import com.depromeet.health.repository.BoardRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BoardServiceImpl implements BoardService {
    @Autowired
    BoardRepository boardRepository;

    @Autowired
    UserServiceImpl userServiceImpl;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Override
    @Transactional(readOnly = true)
    public List<Board> loadBoards(ExerciseType type, Pageable pageable) {
        if (type == null) {
            return boardRepository.findAll(pageable).toList();
        }
        return boardRepository.findByType(type, pageable).orElse(new ArrayList<>());
    }

    @Override
    public Board insertUserByToken(String token, BoardRequest boardRequest) {
        User writer = getUserByToken(token);
        Board board = new Board(boardRequest.getTitle(), boardRequest.getContent(), boardRequest.getVimeoURL(), boardRequest.getCreatedAt(), boardRequest.getType(), boardRequest.getWeight(), writer);
        return boardRepository.save(board);
    }

    @Override
    public List<Board> loadBoardByToken(String token, Pageable pageable) {
        User writer = getUserByToken(token);
        Long userId = writer.getId();
        return boardRepository.findByUserId(userId, pageable).orElse(new ArrayList<>());
    }

    private User getUserByToken(String token) {
        String userPk = jwtTokenProvider.getUserPk(token);
        return (User) userServiceImpl.loadUserByUsername(userPk);
    }
}
