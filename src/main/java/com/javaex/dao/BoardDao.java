package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public List<BoardVo> getList(){
		System.out.println("[BoardDao.getList()]");
		
		List<BoardVo> bList = sqlSession.selectList("board.getList");
		return bList;
	}
	
	public void delete(BoardVo bVo) {
		System.out.println("[BoardDao.delete()]");
		
		int count = sqlSession.delete("board.delete", bVo);
		
		System.out.println(count + "건 삭제 성공(board)");
	}
	
	public void hitUpdate(BoardVo bVo) {
		System.out.println("[BoardDao.hitUpdate()]");
		
		int count = sqlSession.update("board.hitUpdate", bVo);
		System.out.println("조회수 +" + count);
	}
	
	public BoardVo selectOne(BoardVo bVo) {
		System.out.println("[BoardDao.selectOne()]");
		
		BoardVo readVo = sqlSession.selectOne("board.selectOne", bVo);
		return readVo;
	}
	
	public void update(BoardVo bVo) {
		System.out.println("[BoardDao.update()]");
		
		int count = sqlSession.update("board.update",bVo);
		System.out.println(count + "건 수정 완료(board)");
	}
	
	public void insert(BoardVo bVo) {
		System.out.println("[BoardDao.insert()]");
		
		int count = sqlSession.insert("board.insert", bVo);
		System.out.println(count + "건 등록 완료(board)");
	}

}
