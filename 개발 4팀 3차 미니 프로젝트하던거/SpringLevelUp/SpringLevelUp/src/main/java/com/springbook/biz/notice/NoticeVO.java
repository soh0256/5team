package com.springbook.biz.notice;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

//VO(Value Object)
@Getter @Setter
public class NoticeVO {
	private String notice_no;	// 공지사항 게시글 번호
	private String notice_title;	// 공지사항 제목
	private String notice_contents;	// 공지사항 내용
	private Date notice_date;	// 작성날짜
	private int notice_hits;	// 조회수
	private String searchCondition; // 검색조건
	private String searchKeyword; // 검색 내용
	private MultipartFile uploadFile; // 파일 업로드

	@Override
	public String toString() {
		return "NoticeVO [notice_no=" + notice_no + ", notice_title=" + notice_title + ",  notice_contents="
				+ notice_contents + ", notice_date=" + notice_date + ", notice_hits=" + notice_hits + "]";
	}
}