package com.springbook.biz.notice;

import java.util.List;

public interface NoticeService {
	// CRUD 기능의 메소드 구현
	// 글 등록
	void insertNotice(NoticeVO vo);

	// 글 수정
	void updateNotice(NoticeVO vo);

	// 글 삭제
	void deleteNotice(NoticeVO vo);

	// 글 상세 조회
	NoticeVO getNotice(NoticeVO vo);

	// 글 목록 조회
	List<NoticeVO> getNoticeList(NoticeVO vo);
	
	// 조회수
	void viewCount(NoticeVO vo);
	
}
