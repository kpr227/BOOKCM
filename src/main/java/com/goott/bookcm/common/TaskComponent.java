package com.goott.bookcm.common;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.goott.bookcm.domain.ImageVO;
import com.goott.bookcm.mapper.ImageMapper;


@Component
public class TaskComponent {
	
	@Autowired
	private ImageMapper imageMapper;
	
	private String getFolderYesterday() {
		// 문자열 형식 지정
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
		// 달력객체(년/월/일)생성
		Calendar calendar = Calendar.getInstance();
		// 달력에서의 하루전 날짜를 생성해서 달력 객체에 추가
		calendar.add(Calendar.DATE, -1);
		// 달력객체에 설정된 밀리세컨트 값을 가져와서 날짜형식 문자열로 변환
		String strYesterdayFolder = simpleDateFormat.format(calendar.getTime());
		// 문자열의 날짜구분자를 운영체제 디렉토리 구분자로 변경
		return strYesterdayFolder.replace("/", File.separator);
	}
	
	
	@Scheduled(cron = "0 0 0 * * *")
	public void checkFiles()throws Exception{
		String path = "C:\\MyDev\\MySpringWebWorkspace\\bookcm\\uploadFile";
		
		System.out.println("파일 검사 작업 시작.................");
		System.out.println("오늘 날짜: " + new Date());

		// 데이터베이스에 저장된 하루 전 첨부파일정보목록 생성(삭제하면 않되는 파일들)
		List<ImageVO> fileList = imageMapper.getYesterdayImage();
		// ImageVO를 생성된 경로객체로 하나씩 교체
		List<Path> fileListPath = fileList
				.stream() 
				.map(imageVO -> Paths.get(path,
						imageVO.getUploadPath(),
						imageVO.getUuid() + "_" + imageVO.getFileName()))
				// Paths객체들을, List 컬렉션 객체로 변환
				.collect(Collectors.toList());
		// 썸네일파일정보 추가
		fileList.stream()
				.filter(imageVO -> imageVO.getFileType().equals("image"))
				.map(imageVO -> Paths.get(path, imageVO.getUploadPath(),
						"s_" + imageVO.getUuid() + "_" + imageVO.getFileName()))
				.forEach(p -> fileListPath.add(p));
		
		
		System.out.println("===========================================");
		
		// 최종 지우면 않되는 파일(썸네일 포함)의 경로목록
		fileListPath.forEach(p -> System.out.println(p));

		// 하루전 날짜경로 파일 객체
		//toFile();// 하루전 날짜 경로 Path 객체를 File 객체로 변환
		File targetDir = Paths.get(path, getFolderYesterday()).toFile();// 하루전 날짜 경로 Path 객체를 File 객체로 변환
		
		// 디렉토리의 파일 중 DB에 정보가 없는 파일(삭제파일)들의 배열객체(DB정보와 비교)
		File[] removeFiles = targetDir // 하루전 날짜폴더에 있는 각 파일들의 절대경로와 파일이름으로 된 File 객체 배열 반환
							.listFiles(eachFile -> 
							fileListPath.contains(eachFile.toPath()) == false);
		System.out.println("-----------------------------------------");

		// DB에 정보가 없는 하루 전 폴더에 있는 파일 각각을
		for (File needlessFile : removeFiles) {
			// 삭제파일 정보 표시(DB에 정보가 없는 파일)
			System.out.println("=====다음의 파일들이 삭제됩니다.================");
			System.out.println("필요없는 파일 이름: " + needlessFile.getAbsolutePath());
			// 필요없는 파일 삭제
			needlessFile.delete();
		}
	}
}
