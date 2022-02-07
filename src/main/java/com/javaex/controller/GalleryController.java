package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.GalleryService;
import com.javaex.vo.UserVo;

@RequestMapping("/gallery/")
@Controller
public class GalleryController {
	
	@Autowired
	private GalleryService galleryService; 
	
	@RequestMapping("list")
	public String list() {
		System.out.println("[GalleryController.list()]");
		
		return "gallery/list";
	}
	
	@RequestMapping("upload")
	public String upload(@RequestParam("file") MultipartFile file, @RequestParam("content") String content, @SessionAttribute UserVo authUser) {
		System.out.println("[GalleryController.upload()]");
		System.out.println(file.getOriginalFilename());
		System.out.println(content);
		System.out.println(authUser);
		
		
		galleryService.save(authUser, content, file);
		return "redirect:/gallery/list";
	}
}
