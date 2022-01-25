package com.javaex.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.BoardService;
import com.javaex.vo.BoardVo;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value="/board/")
public class BoardController {
	
	@Autowired
	private BoardService bService;
	//리스트
	@RequestMapping(value="list", method= {RequestMethod.GET, RequestMethod.POST})
	public String list(Model model) {
		System.out.println("[BoardController.list()]");
		
		List<BoardVo> bList = bService.list();
		model.addAttribute("bList", bList);
		System.out.println(bList);
		return "board/list";
	}
	
	//삭제
	@RequestMapping(value="delete", method= {RequestMethod.GET, RequestMethod.POST})
	public String delete(@ModelAttribute BoardVo bVo) {
		System.out.println("[BoardController.delete()]");
		
		bService.delete(bVo);
		return "redirect:/board/list";
	}
	
	//글읽기
	@RequestMapping(value="read", method= {RequestMethod.GET, RequestMethod.POST})
	public String read(@ModelAttribute BoardVo bVo, Model model) {
		System.out.println("[BoardController.read()]");
		
		BoardVo readVo = bService.read(bVo);
		model.addAttribute("readVo", readVo);
		System.out.println(readVo);
		return "board/read";
	}
	
	//수정폼
	@RequestMapping(value="modifyForm", method= {RequestMethod.GET, RequestMethod.POST})
	public String modifyForm(@ModelAttribute BoardVo bVo, Model model) {
		System.out.println("[BoardController.modifyForm()]");
		
		BoardVo readVo = bService.read(bVo);
		model.addAttribute("readVo", readVo);
		System.out.println(readVo);
		return "board/modifyForm";
	}
	
	//수정
	@RequestMapping(value="modify", method= {RequestMethod.GET, RequestMethod.POST})
	public String modify(@ModelAttribute BoardVo bVo) {
		System.out.println("[BoardController.modify()]");
		
		System.out.println(bVo);
		bService.modify(bVo);
		return "redirect:/board/list";
	}
	
	//글쓰기 폼
	@RequestMapping(value="writeForm", method= {RequestMethod.GET, RequestMethod.POST})
	public String writeForm() {
		System.out.println("[BoardController.writeForm()]");
		
		return "board/writeForm";
	}
	
	//글쓰기
	@RequestMapping(value="write", method= {RequestMethod.GET, RequestMethod.POST})
	public String write(@ModelAttribute BoardVo bVo, HttpSession session) {
		System.out.println("[BoardController.write()]");
		
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		bVo.setUserNo(authUser.getNo());
		bService.write(bVo);	
		
		return "redirect:/board/list";
	}

}
