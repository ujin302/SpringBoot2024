package com.example.chapter03jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class BoardController {
    @Autowired
    private BoardService boardService;

    @GetMapping("/board/write")
    public String write() {
        boardService.write();
        return "등록 성공";
    }

    @GetMapping("/board/list")
    public List<BoardEntity> list() {
        return boardService.getList();
    }
    
}
