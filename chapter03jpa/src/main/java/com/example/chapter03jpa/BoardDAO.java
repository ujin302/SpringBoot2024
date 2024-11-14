package com.example.chapter03jpa;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardDAO extends JpaRepository<BoardEntity, Integer> {
    public BoardEntity findBySeq(int seq);
    
    public List<BoardEntity> findByLogTimeNull();

    public List<BoardEntity> findByIdContaining(String str);

    public List<BoardEntity> findByLogTimeAfter(LocalDateTime date);

    public List<BoardEntity> findBySeqBetween(int i, int j);

    public List<BoardEntity> findAllByOrderBySeqDesc();

} 
