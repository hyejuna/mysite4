package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestBookVo;
@Repository
public class GuestBookDao {
	@Autowired
	private SqlSession sqlSession;
	
	//리스트 불러오기
	public List<GuestBookVo> getList(){
		System.out.println("[GuestBookDao.getList()]");
		
		List<GuestBookVo> gbList = sqlSession.selectList("guestbook.getList");
		
		return gbList;
	}
	
	//등록
	public int insert(GuestBookVo gbVo) {
		System.out.println("[GuestBookDao.insert()]");

		int count = sqlSession.insert("guestbook.insert", gbVo);

		System.out.println(count + "건 등록되었습니다.");
		return count;
	}

	//삭제
	public int delete(GuestBookVo deleteVo) {
		System.out.println("[GuestBookDao.delete()]");

		int count = sqlSession.delete("guestbook.delete", deleteVo);

		System.out.println(count + "건 삭제되었습니다.");
		return count;
	}

}
