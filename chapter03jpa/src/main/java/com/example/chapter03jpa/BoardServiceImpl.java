package com.example.chapter03jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class BoardServiceImpl implements BoardService {
    @Autowired
    private BoardDAO boardDAO;
    
    @Override
    public void write() {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setId("grape");
        boardDTO.setName("포토");
        boardDTO.setSubject("포도송이");
        boardDTO.setContent("알알이 붙은 포도알");

        boardDAO.save(boardDTO.dtoToEntity(boardDTO));   
    }

    @Override
    public List<BoardEntity> getList() {
        // return boardDAO.findAll(Sort.by("seq").descending());
        return boardDAO.findAllByOrderBySeqDesc();
    }
    
}
