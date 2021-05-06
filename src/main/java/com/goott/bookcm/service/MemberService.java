package com.goott.bookcm.service;

import javax.servlet.http.HttpSession;

import com.goott.bookcm.domain.MemberVO;

public interface MemberService {
	public MemberVO test();
	
	// id가 존재하는가?
	public String existId(String id);
	
	// Nickname이가 존재하는가?
	public String existNick(String nickName);
	
	// 회원가입
	public int regMember(MemberVO memberVO);
	
	// id 와 password가 일치하는가? - 내정보조회 후 id와 nickname session에 저장
	public int checkLogin(HttpSession session, MemberVO memberVO);
	
	// 내정보 조회
	public MemberVO getMyInfo(String id);
	
	// 내정보 조회
	public String getGrade(String loginId);
	


}
