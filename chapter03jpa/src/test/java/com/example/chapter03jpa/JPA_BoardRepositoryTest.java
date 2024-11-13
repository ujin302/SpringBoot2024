package com.example.chapter03jpa;

import java.util.List;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

// 내장 DB을 H2를 수행하지 않겠다. 
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
// @DataJdbcTest를 사용하면 자동으로 EmbededDatabase-H2를 사용하게 된다.
// MySQL과 같이 외부의 DB를 연결하려는 경우엔 이 어노테이션 설정
@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class JPA_BoardRepositoryTest {
    
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
    public void list1() {
        System.out.println("[ list1 ]");
        List<BoardEntity> list = boardDAO.findAll();
        list.stream().forEach(e -> System.out.println(e));
    }

    @Test
    public void list2() {
        System.out.println("[ list2 ]");
        List<BoardEntity> list = boardDAO.findAll(Sort.by("name").ascending());
        list.stream().forEach(e -> System.out.println(e.getId()));
    }

    @Test
    public void list3() {
        System.out.println("[ list3 ]");
        List<BoardEntity> list = boardDAO.findAll(Sort.by("name").descending());
        list.stream().forEach(e -> System.out.println(e.getId()));
    }

    @Test
    public void list4() {
        System.out.println("[ list4 ]");
        Page<BoardEntity> list = boardDAO.findAll(PageRequest.of(0, 3)); // 0번 페이지에 3개 출력
        list.stream().forEach(e -> System.out.println(e.getId()));
    }

    @Test
    public void list5() {
        System.out.println("[ list5 ]");
        Page<BoardEntity> list = boardDAO.findAll(PageRequest.of(1, 3)); // 1번 페이지에 3개 출력
        list.stream().forEach(e -> System.out.println(e.getId()));
    }

    @Test
    public void list6() {
        System.out.println("[ list6 ]");
        Page<BoardEntity> list = boardDAO.findAll(PageRequest.of(0,3, Sort.by("name"))); 
        // 1번 페이지에 3개 출력 & 이름 정렬
        list.stream().forEach(e -> System.out.println(e.getId()));
    }
}
