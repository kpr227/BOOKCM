package com.goott.bookcm.service;

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

}
