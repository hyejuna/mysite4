package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	//유저 정보 가져오기(로그인시 사용)
	public UserVo selectUser(UserVo userVo) {
		System.out.println("[UserDao.selectUser()]");
		//System.out.println(userVo);
		
		UserVo authUser = sqlSession.selectOne("user.selectUser", userVo);
		return authUser;
			
		}
	
	public void insertUser(UserVo userVo) {
		System.out.println("[UserDao.insertUser()]");
		
		int count = sqlSession.insert("user.insertUser", userVo);
		
		System.out.println(count+"건 등록 성공(Users)");
				
	}
	
	public UserVo selectOne(int no) {
		System.out.println("[UserDao.selectOne()]");
		
		UserVo userVo = sqlSession.selectOne("user.selectOne", no);
		return userVo;
	}
	
	public void update(UserVo userVo) {
		System.out.println("[UserDao.update()]");
		
		int count = sqlSession.update("user.update", userVo);
		
		System.out.println(count+"건 수정 성공(Users)");
		
	}

}
