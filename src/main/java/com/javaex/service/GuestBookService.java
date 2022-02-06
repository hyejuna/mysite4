package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.GuestBookDao;
import com.javaex.vo.GuestBookVo;

@Service
public class GuestBookService {
	
	@Autowired
	private GuestBookDao gbDao;
	
	//리스트
	public List<GuestBookVo> list(){
		
		System.out.println("[GuestBookService.list()]");
		
		List<GuestBookVo> gbList = gbDao.getList();
		return gbList;
		
	}
	
	//등록
	public void add(GuestBookVo gbVo) {
		
		System.out.println("[GuestBookService.add()]");
		
		gbDao.insert(gbVo);
		
	}
	
	//등록 + 저장글 리턴
	public GuestBookVo addGuestResultVo(GuestBookVo guestbookVo) {
		System.out.println("[GuestBookService.addGuestResultVo()]");
		//System.out.println(guestbookVo);
		
		//저장하기
		gbDao.insertSelectKey(guestbookVo);
		
		//저장한 내용 가져오기
		int no = guestbookVo.getNo();
		GuestBookVo gVo = gbDao.selectGuest(no);
		
		return gVo;
	}
	
	
	//삭제
	public void delete(GuestBookVo deleteVo) {
		
		System.out.println("[GuestBookService.delete()]");
		
		gbDao.delete(deleteVo);
		
	}
	
	//ajax 방명록 글 삭제
	public String removeGuest(GuestBookVo guestbookVo) {
		System.out.println("[GuestBookService.removeGuest()]");
		
		int count = gbDao.delete(guestbookVo);
		
		if(count>0) {
			return "success";
		} else {
			return "fail";
		}
		
	}
}
