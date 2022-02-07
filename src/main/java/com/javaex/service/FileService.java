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
		System.out.println("[FileService.restore()]");
		String saveDir = "C:\\javaStudy\\upload";
		
		/* 파일관련 정보 추출 */
		// 원본파일이름
		String orgName = file.getOriginalFilename();
		System.out.println(orgName);

		// 확장자
		String exName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")); //lastIndexOf : 끝에서부터 주어진 문자 찾아서 위치 알려줌.

		// 저장파일이름
		String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName; //고유 번호 만드려고 현재시간 + 랜덤 id 조합 사용

		// 파일패스 생성
		String filePath = saveDir + "\\" + saveName; //폴더에 저장할때 한 폴더에 저장 가능한 파일 최대 개수 정해져 있어서 나눠 저장.

		// 파일 사이즈
		long fileSize = file.getSize(); //long형으로 써야만 함!!!

		
		// 하드디스크에 파일 저장(업로드)
		try {
			byte[] fileData = file.getBytes();
			OutputStream out = new FileOutputStream(filePath);
			BufferedOutputStream bout = new BufferedOutputStream(out);

			bout.write(fileData);
			bout.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// DB에 저장
		// 과제
		
		
		return saveName;
		
		
	}

}
