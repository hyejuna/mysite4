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
	
	
	//삭제
	public void delete(GuestBookVo deleteVo) {
		
		System.out.println("[GuestBookService.delete()]");
		
		gbDao.delete(deleteVo);
		
	}
}
