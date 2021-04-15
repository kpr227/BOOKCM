package com.goott.bookcm.common;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnailator;

@Controller
@Log4j
public class UploadControllers {
	
	//저장경로(톰캣이 실행되는 운영체제가 window 환경이므로 경로구분자를 \\로 설정)
	//저장경로(톰캣이 실행되는 운영체제가 linux 환경일때 구분자를 //로 설정)
	String uploadFolder = "C:\\MyDev\\MySpringWebWorkspace\\bookcm\\uploadFile" ;
	
	@GetMapping("/upload")
	public void uploadForm() {
		log.info("upload form");
		//return "/upLoadForm";
	}
	
	@GetMapping("/uploadAjax")
	public void uploadAjax() {
		log.info("uploadAjax form");
		//return "/upLoadForm";
	}
	
	@PostMapping("/uploadFormAction")
	public void uploadFormAction(MultipartFile[] uploadFile, Model model) {
		log.info("upload form");
		
		for(MultipartFile multipartFile : uploadFile) {
			log.info("------------------------------------------------------");
			//getOriginalFilename 업로드되는 파일의 이름
			log.info("Upload File Name:"+ multipartFile.getOriginalFilename());
			log.info("Upload File Name:"+ multipartFile.getSize());
			
			File saveFile = new File(uploadFolder, multipartFile.getOriginalFilename());
							//	File("경로", "파일이름");
			try {
				multipartFile.transferTo(saveFile);
				
			}catch(Exception e) {
				log.error(e.getMessage());
			}
			
		}
	}
		
	@PostMapping(value="/uploadAjaxAction", 
			produces = {"application/json; charset=utf-8"})
	@ResponseBody
	public ResponseEntity<List<AttachFileDTO>> uploadAjaxPost(MultipartFile[] uploadFile) {
		
		List<AttachFileDTO> list = new ArrayList<>();
		String uploadFolderPath = getFolder();
		File uploadPath = new File(uploadFolder, uploadFolderPath);
		
		System.out.println("uploadFolder: "+uploadPath);
		
		if(uploadPath.exists() == false) {
			uploadPath.mkdirs();
		}
		
		for(MultipartFile multipartFile: uploadFile) {
			AttachFileDTO attachDTO = new AttachFileDTO();
			
			String uploadFileName = multipartFile.getOriginalFilename();
			
			log.info("==============uploadAjaxPost실행===============");
			log.info("업로드 파일 이름: "+multipartFile.getOriginalFilename());
			log.info("파일 사이즈: "+multipartFile.getSize());
			
			 
			
			// IE has file path
			uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\")+1);
			
			attachDTO.setFileName(uploadFileName);
			
			
			log.info("only file name:"+uploadFileName);
			
			UUID uuid = UUID.randomUUID();
			
			uploadFileName = uuid.toString() + "_" + uploadFileName;
			
			File saveFile = new File(uploadPath, uploadFileName);
			
			try {
				multipartFile.transferTo(saveFile);	//저장
				attachDTO.setUuid(uuid.toString());
				attachDTO.setUploadPath(uploadFolderPath);
				//check image type file
				if(checkImageType(saveFile)) {
					attachDTO.setImage(true);
					
					FileOutputStream thumbnail = new FileOutputStream(
							new File(uploadPath, "s_" + uploadFileName));
					
					Thumbnailator.createThumbnail(
							multipartFile.getInputStream(), thumbnail, 100,100);
					
					thumbnail.close();
				}
				
				list.add(attachDTO);
				
			}catch(Exception e) {
				log.error(e.getMessage());
			}//EndCatch
		}//End for
		return new ResponseEntity<>(list, HttpStatus.OK);
	}//End public void uploadFormPost
	
	
	@GetMapping("/display")
	@ResponseBody
	public ResponseEntity<byte[]> getFile(String fileName){
		
		File file = new File(uploadFolder+"\\"+fileName);
		
		System.out.println("file: "+fileName);
		
		
		ResponseEntity<byte[]> result = null;
		
		try {
			HttpHeaders httpHeader = new HttpHeaders();
			//HttpHeaders 객체에 이미지파일의(jpg. png, bmp....) content-type추가
			//웹브라우저에 어떤 값인지 표시하기 위해 사용한다.
			httpHeader.add("Content-Type",Files.probeContentType(file.toPath()));	//파일의 경로를 가져와
			
			//이미지 파일복사ㅗㅁ과 content-Type이 추가된 httpHeader와 상태값을 가직
			//ResponseEntity<byte[]>객체를 생성하여 반환
			result = new ResponseEntity<byte[]>(FileCopyUtils.copyToByteArray(file),httpHeader,HttpStatus.OK);
			
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		return result;
		
	}
	
	@GetMapping(value="/download", 
			produces = {"application/json; charset=utf-8"})
	@ResponseBody
	public ResponseEntity<Resource> downloadFile(@RequestHeader("User-Agent")String userAgent, String fileName){
																									//fileName : 날짜 경로 + 사진 이름
		//resource : 전체 저장경로 + 파일 이름
		Resource resource = new FileSystemResource(uploadFolder+"\\"+fileName);
		
		if(resource.exists() == false) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		//resource : 파일 이름
		String resourceName =resource.getFilename();
		
		System.out.println("-----resourceName: "+resourceName);
		
		//uuid 삭제
		String resourceOriginalName = resourceName.substring(resourceName.indexOf("_") + 1);
		
		System.out.println("----resourceOriginalName: "+resourceOriginalName);
		
		HttpHeaders headers  = new HttpHeaders();
		
		try {
			String downloadName = null;
			
			//Micreo Soft Internet Explorer 환경
			if (userAgent.contains("Trident") || userAgent.contains("MSIE")) {
				log.info("IE brower");
				
				//MSIE환경에서는 URLEncoder.encode가 아닌 java.net.URLEncoder.encode형식을 사용한다.
				//downloadName = URLEncoder.encode(resourceName, "UTF8");
				//replace 처리전
				downloadName = java.net.URLEncoder.encode(resourceOriginalName, "UTF8");
				log.info("IE에서의 파일이름(replace처리 전): " +downloadName);
				
				//replace 처리후  "\\+"를 " "로 변경하라
				downloadName = downloadName.replaceAll("\\+", " ");
				log.info("IE에서의 파일이름(replace처리 후): " +downloadName);
				 
			//Edge환경
			}else if(userAgent.contains("Edge")|| userAgent.contains("Edg")){
				log.info("Edge brower");
				
				downloadName = new String(resourceOriginalName.getBytes("UTF-8"));
				log.info("Edge 에서의 파일이름: " +downloadName);
			
			//Chrome환경
			}else {
				log.info("Chrome brower");
				downloadName = new String(resourceOriginalName.getBytes("UTF-8"), "ISO-8859-1");
			}
			
			//"Content-Disposition"은 Mime type을 의미한다. 
			headers.add("Content-Disposition", "attachment; filename=" + downloadName);
			
		}catch(UnsupportedEncodingException e){
			e.printStackTrace();
		}
		
		return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
	}
	
	@PostMapping("/deleteFile")
	@ResponseBody
	public ResponseEntity<String> deleteFile(String fileName, String type){
		File file, file1;
		try {
			file = new File(uploadFolder +"\\"+ URLDecoder.decode(fileName, "UTF-8"));
			file.delete();
			System.out.println("type: "+type);
			if(type.equals("image")) {
				
				String largeFileName = file.getAbsolutePath().replace("s_","");
				String largeFileName2 = file.getAbsolutePath();
				log.info("largeFileName: "+largeFileName);
				log.info("largeFileName: "+largeFileName2);
				
				file = new File(largeFileName);
				
				file1 = new File(largeFileName2);
				
				file.delete();
				
				file1.delete();
			}
		}catch(UnsupportedEncodingException e){
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND); 
		}
		return new ResponseEntity<String>("deleted",HttpStatus.OK);
	}
	
	
	
	
	
	//이미지 파일 판단
	private boolean checkImageType(File file) {
		try {
			String contentType = Files.probeContentType(file.toPath());
			return contentType.startsWith("image");
			
		}catch(IOException e){
			e.printStackTrace();
		}
		return false;
	}
	
	
	
	//날짜에 대한 폴더 생성
	private String getFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String str = sdf.format(date);
		return str.replace("-", File.separator);
	}
}




