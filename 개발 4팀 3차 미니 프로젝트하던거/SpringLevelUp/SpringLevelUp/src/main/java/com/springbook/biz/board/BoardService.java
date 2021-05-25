package com.springbook.biz.board;

import java.util.List;

import com.springbook.biz.notice.NoticeVO;

public interface BoardService {
	// CRUD ����� �޼ҵ� ����
	// �� ���
	void insertBoard(BoardVO vo);

	// �� ����
	void updateBoard(BoardVO vo);

	// �� ����
	void deleteBoard(BoardVO vo);

	// �� �� ��ȸ
	BoardVO getBoard(BoardVO vo);

	// �� ��� ��ȸ
	List<BoardVO> getBoardList(BoardVO vo);
	
	// ��ȸ��
	void viewCount(BoardVO vo);
}
