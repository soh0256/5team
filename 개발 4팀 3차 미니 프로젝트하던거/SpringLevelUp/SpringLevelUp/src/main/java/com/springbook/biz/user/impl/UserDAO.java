package com.springbook.biz.user.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.springbook.biz.user.UserVO;

// DAO(Data Access Object)
@Repository("UserDAO")
public class UserDAO {
	
	@Autowired
	private SqlSessionTemplate mybatis;
	
	public void insertUser(UserVO vo) {
		System.out.println("===> UserDao의 insertUser() 호출");
		mybatis.insert("UserDAO.insertUser", vo);
	}
	
	public UserVO getUser(UserVO vo) {
		System.out.println("===> UserDao의 getUser() 호출");
		return mybatis.selectOne("UserDAO.getUser", vo);
		
	}

	public void updateUser(UserVO vo) {
		System.out.println("===> UserDao의 updateUser() 호출");
		mybatis.update("UserDAO.updateUser", vo);
		
	}
	public void deleteUser(UserVO vo) {
		System.out.println("===> UserDao의 deleteUser() 호출");
		mybatis.delete("UserDAO.deleteUser", vo);
		
	}
	
}
	
	