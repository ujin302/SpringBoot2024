package com.example.chapter03jpa;

import java.security.Timestamp;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "boardtb1")
public class BoardEntity {
    @Id
    @Column(name = "seq")
    // @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BOARD_SEQ_GENERATOR")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // 시퀀스 생성
    private int seq;

    @Column(name = "id", nullable = false, unique = true, length = 30)
    private String id;

    @Column(name = "name", nullable = false, length = 30)
    private String name;

    private String subject;

    private String content;

    // private Timestamp logtime;
    // @CreationTimestamp // 엔티티가 생성되는 시저므이 시간 등록: insert할 때 자동으로 시간 등록
    @UpdateTimestamp // update할 때 마다 자동으로 시간 등록
    private LocalDateTime logTime = LocalDateTime.now();
    
}
