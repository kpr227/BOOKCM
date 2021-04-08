package com.goott.bookcm.domain;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberVO {
	String id;
	String password;
	String name;
	String nickName;
	String email;
	String phone;
	String grade;
	Timestamp regDtae;
	Timestamp editDate;
	String editUser;
	String admin_yn;
	String member_yn;
}
