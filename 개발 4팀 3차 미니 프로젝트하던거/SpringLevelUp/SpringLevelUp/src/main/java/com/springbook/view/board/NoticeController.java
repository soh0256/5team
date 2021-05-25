package com.springbook.view.board;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.springbook.biz.notice.NoticeService;
import com.springbook.biz.notice.NoticeVO;

@Controller
@SessionAttributes("notice")
public class NoticeController {
	@Autowired
	private NoticeService noticeService;

	// 글 등록
	@RequestMapping(value = "/insertNotice.do")
	public String insertNotice(NoticeVO vo) throws IOException {
		// 파일 업로드 처리
		MultipartFile uploadFile = vo.getUploadFile();
		if (!uploadFile.isEmpty()) {
			String fileName = uploadFile.getOriginalFilename();
			uploadFile.transferTo(new File("D:/" + fileName));
		}

		noticeService.insertNotice(vo);
		return "getNoticeList.do";
	}

	// 글 수정
	@RequestMapping("/updateNotice.do")
	public String updateNotice(@ModelAttribute("board") NoticeVO vo) {
		noticeService.updateNotice(vo);
		return "getNoticeList.do";
	}

	// 글 삭제
	@RequestMapping("/deleteNotice.do")
	public String deleteNotice(NoticeVO vo) {
		noticeService.deleteNotice(vo);
		return "getNoticeList.do";
	}

	// 글 상세 조회
	@RequestMapping("/getNotice.do")
	public String getNotice(NoticeVO vo, Model model) {
		model.addAttribute("notice", noticeService.getNotice(vo)); // Model 정보 저장
		noticeService.viewCount(vo);	// 글 조회시 조회수 sql문 실행
		return "getNotice.jsp"; // View 이름 리턴
	}

	// 검색 조건 목록 설정
	@ModelAttribute("conditionMap")
	public Map<String, String> searchConditionMap() {
		Map<String, String> conditionMap = new HashMap<String, String>();
		conditionMap.put("제목", "Notice_TITLE");
		conditionMap.put("내용", "Notice_CONTENTS");
		return conditionMap;
	}

	// 글 목록 검색
	@RequestMapping("/getNoticeList.do")
	public String getNoticeList(NoticeVO vo, Model model) {
		// Null Check
		if (vo.getSearchCondition() == null)
			vo.setSearchCondition("NOTICE_TITLE");
		if (vo.getSearchKeyword() == null)
			vo.setSearchKeyword("");
		// Model 정보 저장
		model.addAttribute("noticeList", noticeService.getNoticeList(vo));
		return "getNoticeList.jsp"; // View 이름 리턴
	}
	
}
