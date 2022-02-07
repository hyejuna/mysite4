package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.FileService;

@Controller
@RequestMapping("/fileupload")
public class FileController {

	@Autowired
	private FileService fileService;
	
	//파일업로드 폼
	@RequestMapping("/form")
	public String form() {
		System.out.println("[FileController.form()]");
		
		return "fileupload/form";
	}
	
	
	//파일업로드 처리
	@RequestMapping("/upload")
	public String upload(@RequestParam("file") MultipartFile file, Model model) {
		System.out.println("[FileController.upload()]");
		//System.out.println(file); //object의 toString 찍히는데 첨부파일 없어도 찍힘. -> 오류확인 안 됨.
		//System.out.println(file.getOriginalFilename()); //이걸로 파일 받았는지 확인.
		
		String saveName = fileService.restore(file); 
		model.addAttribute("saveName",saveName);
		
		return "fileupload/result";
	}
	
	
	
}
