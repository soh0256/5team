package com.example.manager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.manager.domain.Member;
import com.example.manager.dto.MemberLoginDto;
import com.example.manager.service.MemberService;

@RestController /*Json형태로 객체 데이터를 반환, Spring Boot를 API서버로 사용할 때 혹은 앱 개발시 사용 
Spring MVC Controlle에 @Responsbody가 추가된 것.  
client - http Request -> Dispatcher Servlet -> Handler Mapping -> Rest Controller 
(@Controller + @ResponseBody) -> client */
//Handler Mapping : 해당 요청 정보를 기준으로 어떤 컨트롤러를 사용할 것인가를 결정하는 인터페이스 
@RequestMapping("/member")
public class MemberController {
	@Autowired//객체가 필요로하는 어떤 객체를 생성사자 
    private MemberService memberService;

    /**
     * 전체 회원 조회
     */
    @GetMapping("")
    public List<Member> getMembers() {
        return memberService.findAllMembers();
    }

    /**
     * 회원 로그인 처리
     */
    @PostMapping("/login")
    public Member loginMember(@RequestBody MemberLoginDto dto) {
        return memberService.loginMember(dto);
    }

    /**
     * 회원 가입
     */
    @PostMapping(value = "/sign-up")
    public void signUpMember(@RequestPart("member") Member member, @RequestPart("profilePic") MultipartFile file){
        memberService.signUpMember(member, file);
    }

    /**
     * 회원 정보 수정
     */
    @PostMapping("/modify-profile")
    public void modifyMemberWithProfile(@RequestPart("member") Member member, @RequestPart("profilePic") MultipartFile file) {
        memberService.modifyMember(member, file);
    }  
    
    /**
     * 회원 정보 수정
     */
    @PutMapping("/modify")
    public void modifyMember(Member member) {
        memberService.modifyMember(member);
    }

    /**
     * 회원 삭제
     */
    @DeleteMapping("")
    public void deleteMember(@RequestParam("targets") String targets) {
        memberService.deleteMembers(targets);
    }

    /**
     * 회원 이름으로 조회
     */
    @GetMapping("/{name}")
    public List<Member> findMembersByName(@PathVariable("name") String name) {
        return memberService.findMembersByName(name);
    }
}
