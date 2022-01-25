package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Service
public class BoardService {
	
	@Autowired
	private BoardDao bDao;
	
	public List<BoardVo> list(){
		System.out.println("[BoardService.list()");
		
		List<BoardVo> bList = bDao.getList();
		return bList;
	}
	
	public void delete(BoardVo bVo) {
		System.out.println("[BoardService.delete()");
		
		bDao.delete(bVo);
	}
	
	public BoardVo read(BoardVo bVo) {
		System.out.println("[BoardService.read()");
		//조회수 +1
		bDao.hitUpdate(bVo);
		//글 읽기 데이터 가져오기
		BoardVo readVo = bDao.selectOne(bVo);
		return readVo;
	}
	
	public BoardVo modifyForm(BoardVo bVo) {
		System.out.println("[BoardService.modifyForm()");
		
		BoardVo readVo = bDao.selectOne(bVo);
		return readVo;
	}
	
	public void modify(BoardVo bVo) {
		System.out.println("[BoardService.modify()");
		
		bDao.update(bVo);
	}
	
	public void write(BoardVo bVo) {
		System.out.println("[BoardService.write()]");
		
		bDao.insert(bVo);
		System.out.println(bVo);
	}

}
