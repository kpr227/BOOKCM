package com.goott.bookcm.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageVO {
	private String uuid;
	private String uploadPath;
	private String fileName;
	private String fileType;
	private Long bno;
}
