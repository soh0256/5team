package com.example.manager.repository;

import com.example.manager.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// Using JPA
@Repository
public interface BoardRepository extends JpaRepository<Board, Number> {

    //제목으로 원하는 글 찾기//NullPointException 예외 방지//수정필요 
    Optional<Board> findByTitle(String title);

    //내용으로 원하는 글 조회 //NullPointException 예외 방지//수정필요
    Optional<Board> findByContents(String contents);
    
    
    
    
}
