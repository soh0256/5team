package com.springbook.biz.notice;

import java.util.List;

public interface NoticeService {
	// CRUD ����� �޼ҵ� ����
	// �� ���
	void insertNotice(NoticeVO vo);

	// �� ����
	void updateNotice(NoticeVO vo);

	// �� ����
	void deleteNotice(NoticeVO vo);

	// �� �� ��ȸ
	NoticeVO getNotice(NoticeVO vo);

	// �� ��� ��ȸ
	List<NoticeVO> getNoticeList(NoticeVO vo);
	
	// ��ȸ��
	void viewCount(NoticeVO vo);
	
}
