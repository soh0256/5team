package com.example.manager.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.manager.domain.Board;
import com.example.manager.domain.Member;
import com.example.manager.dto.MemberLoginDto;
import com.example.manager.service.BoardService;
import com.example.manager.service.MemberService;

@RestController 
/*Json형태로 객체 데이터를 반환, Spring Boot를 API서버로 사용할 때 혹은 앱 개발시 사용 
Spring MVC Controlle에 @Responsbody가 추가된 것.  
client - http Request -> Dispatcher Servlet -> Handler Mapping -> Rest Controller 
(@Controller + @ResponseBody) -> client */
//Handler Mapping : 해당 요청 정보를 기준으로 어떤 컨트롤러를 사용할 것인가를 결정하는 인터페이스 
@RequestMapping("/board")
public class BoardController {
	@Autowired//
    private BoardService boardService;

	//게시글 목록 보여주기
    @GetMapping("")
    public List<Board> getBoardList() {
    		return boardService.getBoardList(); // View 이름 리턴
    }
	
    
    //게시글 상세 내용보기
    @GetMapping("/{no}")
    public List<Board> getBoard(@PathVariable("no") int no, Board board){
    	return boardService.getBoard(board);
    }
    
    //게시글 쓰기
    @PutMapping("/insert")
    public void insertBoard(@RequestBody Board board, @RequestPart("image") MultipartFile file) {
    	boardService.insertBoard(board, file);
    }
    
    //게시글 수정 
    @PutMapping("/update")
    public void updateBoard(@RequestBody Board board) {
    	boardService.updateBoard(board);
    }
    
    //게시글 삭제
    @DeleteMapping("/delete")
    public void deleteBoard(@RequestBody Board board) {
    	boardService.deleteBoard(board);
    }
    
   
}
