package com.example.manager.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
//이름기본규칙은 camelCase임
//굳이 NAME속성을 지정해주는 이유? 기본값인 CamelCase대로 쓰면 되는 것 아닌가?
//여기엔 @XmlAccesorType이 없고  매핑 역할을 하는  @Entity만 붙어있음... 이 차이가 뭘까?

// JPA 설명 : https://seokr.tistory.com/824

@Getter @Setter
@Entity
@Table(name = "BOARD")
public class Board {
    @Id
    @Column(name = "NO", unique = true) // unique : 제약 조건을 추가한다 (기본값 : false)
    private Number no;

    // 게시글 작성자 아이디
    // updatable : 수정 SQL 명령어를 자동 생성할 때 해당 칼럼을 포함할 것인지 (기본값 : true)
    @Column(name = "ID", updatable = false) 
    private String id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "CONTENTS")
    private String contents;

    @Column(name = "DATES")
    private String dates;

    @Column(name = "HITS")
    private int hits;
 
    @Column(name="IMAGE")
    private String image;
    
    
    private String SearchContents;
    
    private String SearchTitle;
    
    


}
