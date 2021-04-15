package com.goott.bookcm.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnailator;

//@Controller
@Log4j
public class UploadController {
	
	//저장경로(톰캣이 실행되는 운영체제가 window 환경이므로 경로구분자를 \\로 설정)
	//저장경로(톰캣이 실행되는 운영체제가 linux 환경일때 구분자를 //로 설정)
	String uploadFolder = "C:\\upload" ;
	/*
	//uploadForm.jsp와 form을 ㅅㅏ용함
	@GetMapping("/uploadForm")
	public void uploadForm() {
		log.info("==============uploadForm실행===============");

	}
	
	@PostMapping("uploadFormAction")
	//public void uploadFormPost(MultipartFile[] uploadFile, Model model) {
	public void uploadFormPost(MultipartFile[] uploadFile, @RequestParam("name") String name) {
		log.info("name: "+name);
		for(MultipartFile multipartFile: uploadFile) {
			log.info("==============uploadFormPost실행===============");
			log.info("Up load File Name:"+multipartFile.getOriginalFilename());	//1 (1).jpg
			log.info("Up load File Size:"+multipartFile.getSize());		//264315
			
			File saveFile = new File(uploadFolder, multipartFile.getOriginalFilename());	//File("파일경로","파일저장할이름")
			try {
				multipartFile.transferTo(saveFile);	//업로드 한 파일 데이터를 지정한 파일에 저장한다.
			}catch(Exception e) {
				log.error(e.getMessage());
			}//EndCatch
		}//End for
	}//End public void uploadFormPost
	*/
	
	//step1: 날짜 형식 경로 문자열 생성 메소드
	private String getFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		String str = sdf.format(date);
		//안정성때문에 생성해 놓은 코드
		return str.replace("-",File.separator);
	}
	
	//step1: 업로드 파일이 이미지 파일여부 검사
	private boolean checkImageType(File file) {
		try{
			String contentType = Files.probeContentType(file.toPath());
			return contentType.startsWith("image");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@GetMapping("/uploadAjax")
	public void uploadAjax() {
		log.info("upload-ajax");
	}
	
	/*
	//page 515p까지 완성
	@PostMapping("/uploadAjaxAction")
	//public void uploadFormPost(MultipartFile[] uploadFile, Model model) {
	public void uploadAjaxPost(MultipartFile[] uploadFile) {
		//--------make folder
		File uploadPath = new File(uploadFolder, getFolder());
		log.info("File Path: "+uploadPath);
		
		if(uploadPath.exists()==false) {
			System.out.println("성공??");
			uploadPath.mkdirs();
		}
		
		for(MultipartFile multipartFile: uploadFile) {
			log.info("==============uploadAjaxPost실행===============");
			log.info("업로드 파일 이름: "+multipartFile.getOriginalFilename());
			log.info("파일 사이즈: "+multipartFile.getSize());
			
			System.out.println("uploadFileName 전의 값"+multipartFile.getOriginalFilename());
			
			String uploadFileName = multipartFile.getOriginalFilename(); 
			
			//IE has file path
			//일부 IE에서 파일이름에 경로가 포함된 경우, 파일 이름만 추출: multipartFile.getOriginalFilename()
			//파일이름만 있는 경우, 파일 이름만 추출됨
			uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\")+1);
			
			log.info("only file name:"+uploadFileName);
			
			UUID uuid = UUID.randomUUID();
			
			uploadFileName = uuid.toString()+"_"+uploadFileName;
			//업로드 정보(저장폴더와 파일이름 문자열)의 파일객체 생성
			//File saveFile = new File(uploadFolder, multipartFile.getOriginalFilename());
			File saveFile = new File(uploadPath, uploadFileName);
			
			try {
				//서버에 파일 객체를 이용하여 업로드 파일 저장
				multipartFile.transferTo(saveFile);
				
				//step2: uploadAjaxPost() 메소드에 step1에서 생성한 메소드를 이용해서 폴더를 생성하도록 업로드 되도록 추가한다.
				//check ImageFileType
				if(checkImageType(saveFile)) {
					FileOutputStream thumbnail = new FileOutputStream(new File(uploadPath, "s_" + uploadFileName));
					Thumbnailator.createThumbnail(multipartFile.getInputStream(),thumbnail,100,100);
					thumbnail.close();
				}
				
			}catch(Exception e) {
				log.error(e.getMessage());
			}//EndCatch
		}//End for
	}//End public void uploadFormPost
	 */
	
	
	//피드백 정보 구성 구현부터 시작 page[516p]
	@PostMapping(value="/uploadAjaxAction", 
			produces={"application/json; charset=UTF-8"})
	@ResponseBody			//RESTcontroller로 실행되어야 하지만, 
	public ResponseEntity<List<AttachFileDTO>> uploadAjaxPost(MultipartFile[] uploadFile) {
		//-----
		List<AttachFileDTO> list = new ArrayList<>();
		//2021\02\03
		String uploadFolderPath = getFolder();	
		System.out.println("getFolder: "+uploadFolderPath);

		//--------make folder
		File uploadPath = new File(uploadFolder, getFolder());
		log.info("File Path: "+uploadPath);
		
		if(uploadPath.exists()==false) {
			System.out.println("성공??");
			uploadPath.mkdirs();
		}
		
		for(MultipartFile multipartFile: uploadFile) {
			log.info("==============uploadAjaxPost실행===============");
			log.info("Up Load File Name:"+multipartFile.getOriginalFilename());
			log.info("Up Load File Size:"+multipartFile.getSize());
			
			//브라우저에 피드백 정보로 전달되는 값이 저장되는 객체
			AttachFileDTO attachDTO = new AttachFileDTO();
			
			System.out.println("uploadFileName 전의 값"+multipartFile.getOriginalFilename());
			
			String uploadFileName = multipartFile.getOriginalFilename(); 
			
			//IE has file path
			//일부 IE에서 파일이름에 경로가 포함된 경우, 파일 이름만 추출: multipartFile.getOriginalFilename()
			//파일이름만 있는 경우, 파일 이름만 추출됨
			uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\")+1);
			
			log.info("경로가 제거된 업로드 파일  이름:"+uploadFileName);
			
			//attachDTO에 원본파일이름 저장
			attachDTO.setFileName(uploadFileName);
			
			UUID uuid = UUID.randomUUID();	
			uploadFileName = uuid.toString()+"_"+uploadFileName;
			File saveFile = new File(uploadPath, uploadFileName);
			
			try {
				//서버에 파일 객체를 이용하여 업로드 파일 저장
				multipartFile.transferTo(saveFile);
				
				attachDTO.setUuid(uuid.toString());
				attachDTO.setUploadPath(uploadFolderPath);
				
				//check ImageFileType
				if(checkImageType(saveFile)) {
					attachDTO.setImage(true);
					FileOutputStream thumbnail = new FileOutputStream(new File(uploadPath, "s_" + uploadFileName));
					Thumbnailator.createThumbnail(multipartFile.getInputStream(),thumbnail,300,300);
					thumbnail.close();
				}
				//전달된 list객체 sttachDTO 추가
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
		log.info("filen Name: "+fileName);
		
		File file = new File(uploadFolder+"\\"+fileName);
		
		log.info("file: "+file);
		
		ResponseEntity<byte[]> result = null;
		
		try {
			HttpHeaders httpHeader = new HttpHeaders();
			//HttpHeaders 객체에 이미지파일의(jpg. png, bmp....) content-type추가
			//웹브라우저에 어떤 값인지 표시하기 위해 사용한다.
			httpHeader.add("Content-Type",Files.probeContentType(file.toPath()));	//파일의 경로를 가져와
			
			//
			log.info("httpHeader toString: "+httpHeader.toString());
			log.info("httpHeader getContent: "+httpHeader.getContentType());
			//이미지 파일복사ㅗㅁ과 content-Type이 추가된 httpHeader와 상태값을 가직
			//ResponseEntity<byte[]>객체를 생성하여 반환
			result = new ResponseEntity<byte[]>(FileCopyUtils.copyToByteArray(file),httpHeader,HttpStatus.OK);
			
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		return result;
		
	}
	
	//Chrome은 한글.jpg가능
	//explore는 한글.jpg는 불가능
	/*
	@GetMapping(value="/download", produces={"apllication/octet-stram"})
	@ResponseBody
	public ResponseEntity<Resource> downloadFile(String fileName){
		log.info("download file: "+fileName);
		
		Resource resource = new FileSystemResource("c:\\upload\\"+fileName);
		log.info("resource: "+resource);
		
		String resourceName = resource.getFilename();
		
		HttpHeaders headers = new HttpHeaders();
		
		try {
			headers.add("Content-Disposition","attachment; filename="+ new String(resourceName.getBytes("UTF-8"),"ISO-8859-1"));
		}catch(UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
	}
	*/

	@GetMapping(value="/download", produces={"apllication/octet-stram"})
	@ResponseBody
	public ResponseEntity<Resource> downloadFile(
		@RequestHeader("User-Agent")String userAgent, String fileName){
		
		Resource resource = new FileSystemResource("c:\\upload\\" + fileName);
		
		if(resource.exists() == false) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		String resourceName = resource.getFilename();
		
		//removeUUID - 원본이름으로 저장
		resourceName = resourceName.substring(resourceName.indexOf("_")+1);
		
		HttpHeaders headers = new HttpHeaders();
		try {
			String downloadName = null;
		
			//Micreo Soft Internet Explorer 환경
			if (userAgent.contains("Trident") || userAgent.contains("MSIE")) {
				log.info("IE brower");
				
				//MSIE환경에서는 URLEncoder.encode가 아닌 java.net.URLEncoder.encode형식을 사용한다.
				//downloadName = URLEncoder.encode(resourceName, "UTF8");
				//replace 처리전
				downloadName = java.net.URLEncoder.encode(resourceName, "UTF8");
				log.info("IE에서의 파일이름(replace처리 전): " +downloadName);
				
				//replace 처리후  "\\+"를 " "로 변경하라
				downloadName = downloadName.replaceAll("\\+", " ");
				log.info("IE에서의 파일이름(replace처리 후): " +downloadName);
				 
			//Edge환경
			}else if(userAgent.contains("Edge")|| userAgent.contains("Edg")){
				log.info("Edge brower");
				
				downloadName = new String(resourceName.getBytes("UTF-8"));
				log.info("Edge 에서의 파일이름: " +downloadName);
			
			//Chrome환경
			}else {
				log.info("Chrome brower");
				downloadName = new String(resourceName.getBytes("UTF-8"), "ISO-8859-1");
			}
		//"Content-Disposition"은 Mime type을 의미한다. 
		headers.add("Content-Disposition", "attachment; filename=" + downloadName);
		
		 
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
		}
	
	@PostMapping("/deleteFile")
	@ResponseBody
	public ResponseEntity<String> deleteFile(String fileName, String type){
		log.info("deleteFile: "+fileName);
		
		File file;
		
		try {
			file = new File("c:\\upload\\"+URLDecoder.decode(fileName, "UTF-8"));
			file.delete();
			
			if(type.equals("image")) {
				String largeFileName = file.getAbsolutePath().replace("s_", "");
				log.info("largeFileName: "+largeFileName);
				file = new File(largeFileName);
				file.delete();
			}
			
		}catch(UnsupportedEncodingException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<String>("deleted", HttpStatus.OK);
	}
	
	
	
	
}




