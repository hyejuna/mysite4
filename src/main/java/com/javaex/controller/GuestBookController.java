package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.GuestBookService;
import com.javaex.vo.GuestBookVo;

@Controller
@RequestMapping(value = "/guestbook/")
public class GuestBookController {
	
	@Autowired
	private GuestBookService gbService;
    
	//리스트 + 등록화면
	@RequestMapping(value = "addList", method = {RequestMethod.GET, RequestMethod.POST})
	public String addList(Model model) {
		
		System.out.println("[GuestBookController.addList()]");
		
		List<GuestBookVo> gbList = gbService.list();
		model.addAttribute("gbList", gbList);
		return"guestbook/addList";
	}
	
	//등록
	@RequestMapping(value = "/add", method = { RequestMethod.GET, RequestMethod.POST })
	public String add(@ModelAttribute GuestBookVo gbVo) {
		System.out.println("[GuestBookController.add()]");
		
		gbService.add(gbVo);
		
		return "redirect:/guestbook/addList";
	}
	
	//deleteForm
	@RequestMapping(value = "/deleteForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String deleteForm() {
		System.out.println("[GuestBookController.deleteForm()]");
		
		return "guestbook/deleteForm";
	}
	
	
	//delete
	@RequestMapping(value = "/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public String delete(@ModelAttribute GuestBookVo deleteVo) {
		System.out.println("[GuestBookController.delete()]");
		
		gbService.delete(deleteVo);
		
		return "redirect:/guestbook/addList";
	}
}
