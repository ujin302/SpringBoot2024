package com.example.chapter03jpa;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class BoardDTO {
    private int seq;
    private String id;
    private String name;
    private String subject;
    private String content;
    private LocalDateTime logTime = LocalDateTime.now();

    
    public BoardEntity dtoToEntity(BoardDTO dto) {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setId(dto.getId());
        boardEntity.setName(dto.getName());
        boardEntity.setSubject(dto.getSubject());
        boardEntity.setContent(dto.getContent());
        return boardEntity;
    }
}
