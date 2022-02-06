package com.javaex.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.GuestBookService;
import com.javaex.vo.GuestBookVo;

@Controller
@RequestMapping ("/api/guestbook")
public class ApiGuestbookController {
	
	@Autowired
	private GuestBookService guestbookService;
	
	@RequestMapping("/addList")
	public String addList() {
		System.out.println("[ApiGuestbookController.addList()]");
	
		return "aGuestbook/addList";
	}
	
	//데이터만 가져오기
	@ResponseBody //데이터를 model에 담는게 아니라 response의 body에 붙여서 보낼거임.
	@RequestMapping("/list")
	public List<GuestBookVo> list() {
		System.out.println("[ApiGuestbookController.list()]");
		List<GuestBookVo> guestbookList = guestbookService.list();
		System.out.println(guestbookList);
		
		return guestbookList; //리다이렉트나 포워드가 아니라 데이터 내보냄.
	}
	
	@ResponseBody
	@RequestMapping("/write")
	public GuestBookVo write(@ModelAttribute GuestBookVo guestbookVo) {
		System.out.println("[ApiGuestbookController.write()]");
		System.out.println(guestbookVo);
		
		//저장하고, 저장된 값 리턴
		GuestBookVo gVo = guestbookService.addGuestResultVo(guestbookVo);
		System.out.println(gVo);
		return gVo;
	}
	
	@ResponseBody
	@RequestMapping("/remove")
	public String remove(@ModelAttribute GuestBookVo guestbookVo) {
		System.out.println("[ApiGuestbookController.remove()]");
		System.out.println(guestbookVo);
		
		String result = guestbookService.removeGuest(guestbookVo);
		System.out.println(result);
		return result;
		
	}
}
