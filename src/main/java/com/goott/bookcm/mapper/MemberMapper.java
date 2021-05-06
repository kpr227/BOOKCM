package com.goott.bookcm.mapper;

import com.goott.bookcm.domain.MemberVO;

public interface MemberMapper {
	public MemberVO test();
	
	// id가 존재하는가?
	public String existId(String id);
	
	// Nickname이가 존재하는가?
	public String existNick(String nickName);
	
	// 회원가입
	public int regMember(MemberVO memberVO);
	
	// id 와 password가 일치하는가?
	public String checkLogin(MemberVO memberVO);
	
	// 내정보 조회
	public MemberVO getMyInfo(String id);

	// 내정보 조회
	public String getGrade(String loginId);
	
	
}
