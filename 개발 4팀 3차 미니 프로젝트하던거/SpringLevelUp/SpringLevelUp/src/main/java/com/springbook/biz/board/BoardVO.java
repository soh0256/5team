package com.springbook.biz.board;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

//VO(Value Object)
@Getter @Setter
@XmlAccessorType(XmlAccessType.FIELD)
public class BoardVO {
	@XmlAttribute// seq을 속성으로 표현하라는 의미
	private String board_no;
	private String board_title;
	private String id;
	private String board_contents;
	private Date board_date;
	private int board_hits;
	@XmlTransient // xml 변환에서 제외
	private String searchCondition; // 검색조건
	@XmlTransient
	private String searchKeyword; // 검색 내용
	@XmlTransient
	private MultipartFile uploadFile; // 파일 업로드

	@Override
	public String toString() {
		return "BoardVO [board_no=" + board_no + ", board_title=" + board_title + ", id=" + id + ", board_contents="
				+ board_contents + ", board_date=" + board_date + ", board_hits=" + board_hits + "]";
	}
}