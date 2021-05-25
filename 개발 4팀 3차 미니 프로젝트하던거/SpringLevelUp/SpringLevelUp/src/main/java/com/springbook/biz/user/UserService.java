package com.springbook.biz.user;

public interface UserService {
	// CRUD 기능의 메소드 구현
	// 회원 등록
	public UserVO getUser(UserVO vo);
	
	void insertUser(UserVO vo);
	
	void modifyUser(UserVO vo);
	
	void deleteUser(UserVO vo);
}
