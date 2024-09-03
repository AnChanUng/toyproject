package com.example.demo.service;

import com.example.demo.entity.Board;
import com.example.demo.entity.UserBoard;
import com.example.demo.repository.UserBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserBoardService {

    @Autowired
    UserBoardRepository userBoardRepository;

    public List<UserBoard> findAll() {  // 전체 조회
        return userBoardRepository.findAll();
    }

    public Optional<UserBoard> findById(Long id) {  // 아이디로 찾기
        return userBoardRepository.findById(id);
    }

    public UserBoard save(UserBoard userBoard) {    // 보드 저장
        return userBoardRepository.save(userBoard);
    }

    public void deleteById(Long id) {   // 아이디로 보드 삭제
        userBoardRepository.deleteById(id);
    }
}
