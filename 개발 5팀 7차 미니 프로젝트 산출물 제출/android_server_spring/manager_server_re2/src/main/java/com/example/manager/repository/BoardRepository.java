package com.example.manager.repository;

import com.example.manager.domain.Board;
import com.example.manager.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<Board, String> {

    /**
     * ID, Password 에 일치하는 회원 조회
     * @param id 조회 대상 ID
     * @param password 조회 대상 Password
     * @return 회원 조회 결과
     */
    Optional<Board> findByTitle(String title, String content);

    /**
     * 이름으로 회원 조회
     * @param name 조회할 이름
     * @return 조회 결과(동명이인이 있을 수 있기에 List)
     */
//    List<Board> findByName(String name);
}
