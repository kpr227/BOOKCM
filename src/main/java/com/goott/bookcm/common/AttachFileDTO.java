package com.goott.bookcm.common;

import lombok.Data;

@Data
public class AttachFileDTO {
	private String fileName;	//파일이름
	private String uploadPath;	//파일경로
	private String uuid;		//uuid
	private boolean image;		//이미지인가?
}
