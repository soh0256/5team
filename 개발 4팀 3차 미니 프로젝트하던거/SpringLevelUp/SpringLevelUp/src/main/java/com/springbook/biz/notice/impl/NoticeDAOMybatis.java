package com.springbook.biz.notice.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springbook.biz.notice.NoticeVO;

@Repository
public class NoticeDAOMybatis{
	
	@Autowired
	private SqlSessionTemplate mybatis;

	public void insertNotice(NoticeVO vo) {
		System.out.println("===> Mybatis로 insertNotice() 기능 처리");
		mybatis.insert("NoticeDAO.insertNotice", vo);
	}

	public void updateNotice(NoticeVO vo) {
		System.out.println("===> Mybatis로 updateNotice() 기능 처리");
		mybatis.update("NoticeDAO.updateNotice", vo);
	}

	public void deleteNotice(NoticeVO vo) {
		System.out.println("===> Mybatis로 deleteNotice() 기능 처리");
		mybatis.delete("NoticeDAO.deleteNotice", vo);
	}

	public NoticeVO getNotice(NoticeVO vo) {
		System.out.println("===> Mybatis로 getNotice() 기능 처리");
		return (NoticeVO) mybatis.selectOne("NoticeDAO.getNotice", vo);
	}

	public List<NoticeVO> getNoticeList(NoticeVO vo) {
		System.out.println("===> Mybatis로 getNoticeList() 기능 처리");
		return mybatis.selectList("NoticeDAO.getNoticeList", vo);
	}
	
	public void viewCount(NoticeVO vo) {
		System.out.println("===> Mybatis로 viewCount() 기능 처리");
		mybatis.update("NoticeDAO.viewCount", vo);
	}
}