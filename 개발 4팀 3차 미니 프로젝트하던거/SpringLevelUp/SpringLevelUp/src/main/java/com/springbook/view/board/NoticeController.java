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

	// �� ���
	@RequestMapping(value = "/insertNotice.do")
	public String insertNotice(NoticeVO vo) throws IOException {
		// ���� ���ε� ó��
		MultipartFile uploadFile = vo.getUploadFile();
		if (!uploadFile.isEmpty()) {
			String fileName = uploadFile.getOriginalFilename();
			uploadFile.transferTo(new File("D:/" + fileName));
		}

		noticeService.insertNotice(vo);
		return "getNoticeList.do";
	}

	// �� ����
	@RequestMapping("/updateNotice.do")
	public String updateNotice(@ModelAttribute("board") NoticeVO vo) {
		noticeService.updateNotice(vo);
		return "getNoticeList.do";
	}

	// �� ����
	@RequestMapping("/deleteNotice.do")
	public String deleteNotice(NoticeVO vo) {
		noticeService.deleteNotice(vo);
		return "getNoticeList.do";
	}

	// �� �� ��ȸ
	@RequestMapping("/getNotice.do")
	public String getNotice(NoticeVO vo, Model model) {
		model.addAttribute("notice", noticeService.getNotice(vo)); // Model ���� ����
		noticeService.viewCount(vo);	// �� ��ȸ�� ��ȸ�� sql�� ����
		return "getNotice.jsp"; // View �̸� ����
	}

	// �˻� ���� ��� ����
	@ModelAttribute("conditionMap")
	public Map<String, String> searchConditionMap() {
		Map<String, String> conditionMap = new HashMap<String, String>();
		conditionMap.put("����", "Notice_TITLE");
		conditionMap.put("����", "Notice_CONTENTS");
		return conditionMap;
	}

	// �� ��� �˻�
	@RequestMapping("/getNoticeList.do")
	public String getNoticeList(NoticeVO vo, Model model) {
		// Null Check
		if (vo.getSearchCondition() == null)
			vo.setSearchCondition("NOTICE_TITLE");
		if (vo.getSearchKeyword() == null)
			vo.setSearchKeyword("");
		// Model ���� ����
		model.addAttribute("noticeList", noticeService.getNoticeList(vo));
		return "getNoticeList.jsp"; // View �̸� ����
	}
	
}
