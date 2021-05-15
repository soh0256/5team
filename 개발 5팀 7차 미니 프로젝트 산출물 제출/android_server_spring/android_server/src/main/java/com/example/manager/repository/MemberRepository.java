package com.example.manager.repository;

import com.example.manager.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// Using JPA
@Repository
public interface MemberRepository extends JpaRepository<Member, String> {
	//Optional NullPointException 방지 
	/*메소드의 반환 값이 절대 null이 아니라면 Optional을 사용하지 않는 것이 성능저하가 적다. 즉, Optional은 메소드의 결과가 null이 될 수 있으며, 클라이언트가 이 상황을 처리해야 할 때 사용하는 것이 좋다.
	출처: https://mangkyu.tistory.com/70 [MangKyu's Diary]*/
    /**
     * ID, Password 에 일치하는 회원 조회
     * @param id 조회 대상 ID
     * @param password 조회 대상 Password
     * @return 회원 조회 결과
     */
    Optional<Member> findByIdAndPassword(String id, String password);

    /**
     * 이름으로 회원 조회
     * @param name 조회할 이름
     * @return 조회 결과(동명이인이 있을 수 있기에 List)
     */
    List<Member> findByName(String name);
}
