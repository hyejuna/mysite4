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
	
	//등록(selectKey)
	public int insertSelectKey(GuestBookVo guestbookVo) {
		System.out.println("[GuestBookDao.insertSelectKey()]");
		
		//System.out.println(guestbookVo);
		int count = sqlSession.insert("guestbook.insertSelectKey",guestbookVo);
		//System.out.println(guestbookVo);
		
		System.out.println(count+"건 등록 성공(guestbook)");
		return count; //성공한 개수 리턴. (no는 Vo에 있고 서비스도 Vo 주소 알고 있으므로 안넘겨줘도 됨.)
	}
	
	//방금 등록한 글 가져오기
	public GuestBookVo selectGuest(int no) {
		System.out.println("[GuestBookDao.selectGuest()]");
		
		GuestBookVo gbVo = sqlSession.selectOne("guestbook.selectByNo", no);
		//System.out.println(gbVo);
		return gbVo;
	}

	//삭제
	public int delete(GuestBookVo deleteVo) {
		System.out.println("[GuestBookDao.delete()]");

		int count = sqlSession.delete("guestbook.delete", deleteVo);

		System.out.println(count + "건 삭제되었습니다.");
		return count;
	}

}
