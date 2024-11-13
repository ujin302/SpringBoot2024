package com.example.chapter03jpa;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class JPA_BoardRepositoryTest2 {
    @Autowired
    private BoardDAO boardDAO;

    @BeforeEach
    public void solid() {
        System.out.println("-".repeat(100));
    }
    
    
    @AfterEach
    public void solid2() {
        System.out.println("*".repeat(100));
    }

    @Test
    @Order(1) // 실행 순서 (숫자가 작을수록 먼저 실행)
    @Rollback(false)
    // Test의 Default: rollback
    // rollback를 사용하고 싶지 않을 경우, false 설정
    public void insert() {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setId("banana");
        boardDTO.setName("바나나");
        boardDTO.setSubject("노란 바나나");
        boardDTO.setContent("노랑노랑~~ 미니언즈가 좋아하는 바나나~");

        // boardDTO.setId("apple");
        // boardDTO.setName("사과");
        // boardDTO.setSubject("빨간 사과");
        // boardDTO.setContent("나는 별로 안 좋아해...");

        boardDAO.save(boardDTO.dtoToEntity(boardDTO));
        // 레코드가 없으면 입력, 없으면 수정
    }

    @Test
    @Order(2)
    public void list() {
        List<BoardEntity> list = boardDAO.findAll();
        list.stream().forEach(System.out::println);
    }
} 
