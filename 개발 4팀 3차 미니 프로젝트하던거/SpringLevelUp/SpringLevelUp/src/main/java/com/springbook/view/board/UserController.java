package com.springbook.view.board;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.springbook.biz.user.UserService;
import com.springbook.biz.user.UserVO;
import com.springbook.biz.user.impl.UserDAO;

@Controller
@SessionAttributes("user")
public class UserController implements HttpSessionListener{//드디어 사진오류의 원인? 인터페이스 구현인가..?

	@Autowired//반드시 써주어야 객체 주입됨, 중요
	private UserService userService;//널값의 원인일까 드디어
	
	// 회원가입
	@RequestMapping(value = "/signUp.do", method = RequestMethod.GET)
	public String signUpView(UserVO vo) {

		System.out.println("회원가입 화면으로 이동");
			
		return "signUp.jsp";
	}
	
	
		
	// HttpSession객체를 매개변수로 받음
	@RequestMapping(value = "/signUp.do", method = RequestMethod.POST)
	public String signUp(UserVO vo) throws IOException{

		System.out.println("UserController의 signUp 호출");
			
		if(vo.getId() == null || vo.getId().equals("")) { 
			throw new IllegalArgumentException("아이디는 반드시 입력해야 합니다."); 
		}else if(vo.getPw() == null || vo.getPw().equals("")) { 
			throw new IllegalArgumentException("비밀번호는 반드시 입력해야 합니다."); 
		}else if(vo.getName() == null || vo.getName().equals("")) { 
			throw new IllegalArgumentException("이름은 반드시 입력해야 합니다."); 
		}
		
		userService.insertUser(vo);//널값의 원인일까 드디어(userDao.insertUser가 아니라)
		
		return "login.jsp";
		
	}
	
	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	public String loginView(UserVO vo) {
		return "login.jsp";
	}
	
	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public String getUser(UserVO vo, HttpSession session) {
		try {
			UserVO user = userService.getUser(vo);
			session.setAttribute("user", user);//한 데이터를 담는게 아니라 전체를 담는다.
			// 공지사항 권한 나누기 위해 "userGrad"에 로그인하는 대상의 권한 저장
			session.setAttribute("userGrad", user.getGrade()); 
			if(user!=null) 
				return "getBoardList.do";
		}catch(Exception e) {
			e.printStackTrace();
			return "index.jsp";
		} 
		
		return null;
		
	}
	
	
	// 암호 변경
	@RequestMapping(value = "/modify.do", method = RequestMethod.GET)
	public String modifyView(UserVO vo, HttpSession session) {
		System.out.println("회원 정보 변경 화면으로 이동");
		session.setAttribute("user", vo);
		return "modify.jsp";
	}
	
	// 암호 변경
	@RequestMapping(value = "/modify.do", method = RequestMethod.POST)
	public String modifyUser(UserVO vo, HttpSession session) {
		System.out.println("회원 정보 변경 값 post로 넘김");
		session.setAttribute("user", vo);
		userService.deleteUser(vo);
		return "getBoardList.jsp";
	}
	
	// 회원 탈퇴
	@RequestMapping(value = "/delete.do", method = RequestMethod.GET)
	public String deleteView(UserVO vo) {
		System.out.println("회원 탈퇴 화면으로 이동");
		return "delete.jsp";
	}
	
	//HttpSession 객체를 매개변수로 받음
	@RequestMapping(value = "/delete.do", method = RequestMethod.POST)
	public String delete(UserVO vo, UserDAO userDAO) {
		System.out.println("회원 삭제 처리");
			
		userService.deleteUser(vo);
			
		return "login.jsp";
			
	}
	
	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		HttpSession session = arg0.getSession();
		session.removeAttribute("id");
		session.invalidate();
	}
		
	
	

}
