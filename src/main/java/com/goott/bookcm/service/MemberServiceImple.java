package com.goott.bookcm.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goott.bookcm.domain.MemberVO;
import com.goott.bookcm.mapper.MemberMapper;


@Service
public class MemberServiceImple implements MemberService{

	@Autowired
	private MemberMapper memberMapper;
	
	@Override
	public MemberVO test() {
		return memberMapper.test();
	}
	
	// id가 존재하는가?
	@Override
	public String existId(String id) {
		// 존재 - TRUE | 미존재 - FALSE
		return memberMapper.existId(id);
	}
	
	// nickName가 존재하는가?
	@Override
	public String existNick(String nickName) {
		// 존재 - TRUE | 미존재 - FALSE
		return memberMapper.existNick(nickName);
	}
	
	// 회원가입
	@Override
	public int regMember(MemberVO memberVO) {
		//이전페이지가 존재하는지 없다면
		// 성공-1 | 실패-2
		return memberMapper.regMember(memberVO);
	}

	// id 와 password가 일치하는가? - 내정보조회 후 id와 nickname session에 저장
	@Override
	public int checkLogin(HttpSession session, MemberVO memberVO) {
		if(memberMapper.checkLogin(memberVO).equals("TRUE")) {
			memberMapper.getMyInfo(memberVO.getId());
			//session에 저장한다.
			//로그인한 아이디
			session.setAttribute("loginId", memberVO.getId());
			return 1;
			
		}else {
			return 0;
		}
		
	}

	// 내정보 조회
	@Override
	public MemberVO getMyInfo(String id) {
		// TODO Auto-generated method stub
		return memberMapper.getMyInfo(id);
	}

	//사용자 레벨 가져오기
	@Override
	public String getGrade(String loginId) {
		// TODO Auto-generated method stub
		//System.out.println("loginId: "+loginId.substring(1,loginId.length()-1));
		return memberMapper.getGrade(loginId.substring(1,loginId.length()-1));
	}

}
