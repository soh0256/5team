package com.example.manager.service;

import java.io.File;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.manager.common.LocalConfig;
import com.example.manager.common.MemberNotFoundException;
import com.example.manager.common.UploadFailureException;
import com.example.manager.domain.Board;
import com.example.manager.domain.Member;
import com.example.manager.repository.BoardRepository;

@Service
public class BoardService {
	@Autowired
    private LocalConfig localConfig;
	@Autowired
    private BoardRepository boardRepository;

    //게시판 글 가져오기
    public List<Board> getBoardList() {
        return boardRepository.findAll();
    }

   //게시글 찾기 다른 방법: SearchBoardDto 클래스 이용
//    public List<Board> searchBoard(SearchBoardDto dto){
//    	if(dto.getTitle()!=null) {
//    		Optional<Board> board = boardRepository.findByTitle(dto.getTitle());
//    		if(board.isPresent()) {
//    			return boardRepository.findAll();
//    		} else {
//    			throw new BoardNotFoundException("검색어에 맞는 게시글을 찾지 못하였습니다.");
//    		}
//    	}else {
//    		Optional<Board> board = boardRepository.findByContents(dto.getContents());
//    		if(board.isPresent()) {
//    			return boardRepository.findAll();
//    		} else {
//    			throw new BoardNotFoundException("검색어에 맞는 게시글을 찾지 못하였습니다.");
//    		}
//    	}
//    }
    
    //게시글삽입
    public void insertBoard(Board board, MultipartFile bFile) {
    	boardRepository.save(board);
    	try {
            // 게시글 파일 업로드
    		uploadBoard(board.getNo(), bFile);
        } catch (Exception e) {
            // file upload 실패시 생성하려던 게시글 삭제
        	boardRepository.delete(board);
            throw e;
        }
    }
   
    
    //게시글 내용보기(해당 게시글을 선택하면, 해당 게시글에 해당되는 제목과 내용 및 작성자 등등이 다 따라오니까 
    //매개변수로 Board를 넣어준다
    public List<Board> getBoard(Board board) {
    	Optional<Board> target = boardRepository.findById(board.getNo());
    	if(target.isPresent()) {
    		return boardRepository.findAll();
    	}else {
		return null;
    	}
    }
    
    //게시글 수정 1 (게시글 내용만 수정할 때 실행)
    // Optional<T> findById(ID id);
    // Entity의 id로 검색 = Board.java에서 id는 no로 지정함
    public void updateBoard(Board board) {
    	Optional<Board> target = boardRepository.findById(board.getNo());
    	if(target.isPresent()) {
    		// 게시글 내용 수정
    		boardRepository.save(board);
    	}
    }
    
    // controller에 두가지 방식을 생성?
    //게시글 수정 2 (게시글 내용, 업로드한 사진까지 수정할 때 실행) 
    // Optional<T> findById(ID id);
    // Entity의 id로 검색 = Board.java에서 id는 no로 지정함
    public void updateBoard(Board board, MultipartFile bFile) {
    	Optional<Board> target = boardRepository.findById(board.getNo());
    	if(target.isPresent()) {
    		// 게시글 내용 수정
    		boardRepository.save(board);
    		// 사진 수정
    		uploadBoard(board.getNo(), bFile);
    	}
    }
  
    //게시글 삭제
    // Optional<T> findById(ID id);
    // Entity의 id로 검색 = Board.java에서 id는 no로 지정함
    public void deleteBoard(Board board) {//여기서 String으로 받는 매개변수 targets는 어디서 받아오는 값인가 
    		Optional<Board> target = boardRepository.findById(board.getNo());
            if (target.isPresent()) { // .isPresent() : Optional 객체에 저장된 값이 null인지 아닌지를 먼저 확인
                // DB 에서 회원 삭제
                boardRepository.delete(board);
            }    
                
    }

    // 게시글 사진 등록
    // Optional<T> findById(ID id);
    // Entity의 id로 검색 = Board.java에서 id는 no로 지정함
    private void uploadBoard(Number boardrNo, MultipartFile bFile) {
        Optional<Board> target = boardRepository.findById(boardrNo);
        if(target.isPresent()) {
            try {
                String bFileName = bFile.getOriginalFilename();   // 원본 파일 이름

                // 저장할 디렉토리 생성
                createDirectory(localConfig.getBoardPath());
                String saveUri = localConfig.getBoardPath() + "\\" + bFileName;  // 최종 저장 경로
                File profileFile = new File(saveUri);
                if (profileFile.exists()) {
                	profileFile.delete();
                }
                profileFile = new File(saveUri);

                // 파일 저장
                bFile.transferTo(profileFile);

                // board 의 profile image 설정
                Board boatd = target.get();
                boatd.setImage(localConfig.getBaseUrl() + bFileName);
                boardRepository.save(boatd);
            } catch (Exception e) {
                throw new UploadFailureException("Failed to upload file, because " + e.getMessage());
            }
        } else {
            throw new MemberNotFoundException("There is no Member by " + boardrNo);
        }
    }
    
    
    private void createDirectory(String path) {
        File directory = new File(path);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

}
