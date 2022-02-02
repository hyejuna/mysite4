package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	public UserVo login(UserVo userVo) {
	
		System.out.println("[UserService.login()]");
		
		UserVo authUser = userDao.selectUser(userVo);
		return authUser;
		
	}
	
	public void join(UserVo userVo) {
		
		System.out.println("[UserService.join()]");
		
		userDao.insertUser(userVo);
		
	}
	
	/* 아이디 체크 */
	public UserVo checkId(UserVo userVo) {
		System.out.println("[UserService.checkId()]");
		
		return userDao.selectUserById(userVo);
		
	}
	
	public UserVo modifyForm(int no) {
		System.out.println("[UserService.modifyForm()]");
		
		UserVo userVo = userDao.selectOne(no);
		
		return userVo;
	}
	
	public void modify(UserVo userVo) {
		System.out.println("[UserService.modify()]");
		
		userDao.update(userVo);
		
	}

}
