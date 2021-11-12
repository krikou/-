package com.example.demo.service;

import com.example.demo.base.ResponseBase;


/**
 * @author nan.gai
 */
public interface UserService {

	/**
	 * 用户登录
	 * @param username
	 * @param password
	 * @return
	 */
	public ResponseBase UserLogin(String username, String password);
	
	/**
	 * 获取用户基本信息
	 * @param userId
	 * @return
	 */
	public ResponseBase getUserInfo(Integer userId);
}
