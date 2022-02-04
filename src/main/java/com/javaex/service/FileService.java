package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {

	public String restore(MultipartFile file) {
		System.out.println("FileService.restore()");
		String saveDir = "C:\\javastudy\\upload";
		
		System.out.println(file.getOriginalFilename());
		
		//파일을 하드 디스크에 저장(운영 내용)
		
		//원본 파일 이름
		String orgName = file.getOriginalFilename();
		
		//확장자
		String exName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		//String exName = orgName.substring(orgName.lastIndexOf("."));

		//저장 파일 이름
		String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;
		//System.currentTimeMillis(): 현재 시간(파일 저장 시간)을 밀리세컨드 단위로 변환-객체 구별 목적
		//UUID.randomUUID(): 범용 고유 식별자 - 해당 파일 정보(객체)를 구별할 수 있도록 임의로 부여한 값/생성시 UUID 형태이므로 String 형태로 변환 필요
		//System.out.println(saveName);
		
		//파일 패스: 실제 파일의 위치, 경로
		String filePath = saveDir + "\\" + saveName;
		
		//파일 사이즈
		long fileSize = file.getSize();//return 타입의 자료형: long
		
		//★파일 저장(upload)
		try {
			
			byte[] fileData = file.getBytes();
			OutputStream out = new FileOutputStream(filePath);
			BufferedOutputStream bout = new BufferedOutputStream(out);
			
			bout.write(fileData);
			bout.close();
			
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		
		//DB에 저장-과제
		
		return saveName;
	}
	
}
