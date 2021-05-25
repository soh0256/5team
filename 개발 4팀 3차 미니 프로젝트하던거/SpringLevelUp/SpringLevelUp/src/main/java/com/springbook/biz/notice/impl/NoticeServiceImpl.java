package com.springbook.biz.notice.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbook.biz.notice.NoticeService;
import com.springbook.biz.notice.NoticeVO;

@Service("noticeService")
public class NoticeServiceImpl implements NoticeService {
	@Autowired
	private NoticeDAOMybatis noticeDAO;

	public void insertNotice(NoticeVO vo) {
//		if (vo.getSeq() == 0) {
//			throw new IllegalArgumentException("0번 글은 등록할 수 없습니다.");
//		}
		noticeDAO.insertNotice(vo);
	}

	public void updateNotice(NoticeVO vo) {
		noticeDAO.updateNotice(vo);
	}

	public void deleteNotice(NoticeVO vo) {
		noticeDAO.deleteNotice(vo);
	}

	public NoticeVO getNotice(NoticeVO vo) {
		return noticeDAO.getNotice(vo);
	}

	public List<NoticeVO> getNoticeList(NoticeVO vo) {
		return noticeDAO.getNoticeList(vo);
	}

	@Override
	public void viewCount(NoticeVO vo) {
		noticeDAO.viewCount(vo);
		
	}
}