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
		System.out.println("===> Mybatis�� insertNotice() ��� ó��");
		mybatis.insert("NoticeDAO.insertNotice", vo);
	}

	public void updateNotice(NoticeVO vo) {
		System.out.println("===> Mybatis�� updateNotice() ��� ó��");
		mybatis.update("NoticeDAO.updateNotice", vo);
	}

	public void deleteNotice(NoticeVO vo) {
		System.out.println("===> Mybatis�� deleteNotice() ��� ó��");
		mybatis.delete("NoticeDAO.deleteNotice", vo);
	}

	public NoticeVO getNotice(NoticeVO vo) {
		System.out.println("===> Mybatis�� getNotice() ��� ó��");
		return (NoticeVO) mybatis.selectOne("NoticeDAO.getNotice", vo);
	}

	public List<NoticeVO> getNoticeList(NoticeVO vo) {
		System.out.println("===> Mybatis�� getNoticeList() ��� ó��");
		return mybatis.selectList("NoticeDAO.getNoticeList", vo);
	}
	
	public void viewCount(NoticeVO vo) {
		System.out.println("===> Mybatis�� viewCount() ��� ó��");
		mybatis.update("NoticeDAO.viewCount", vo);
	}
}