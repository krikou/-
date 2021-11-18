package com.example.demo.service.impl;

import java.util.Map;
import java.util.UUID;

import com.example.demo.base.BaseService;
import com.example.demo.base.ResponseBase;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.example.demo.mapper.UserMapper;
import com.example.demo.util.JwtUtil;
import com.example.demo.util.ProductToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sound.midi.Soundbank;

import static com.example.demo.util.JwtUtil.getterToken;

/**
 * @author nan.gai
 */
@Service
@Transactional
public class UserServiceImpl extends BaseService implements UserService {

	@Override
	public ResponseBase UserLogin(String username, String password) {
		//1.校验登陆是否成功
		User user = userMapper.getUserInfo(username, password);

		//2.如果不成功返回提示
		if (user == null) {
			return setResultError("账户名密码错误！！！");
		} else {
			//3.如果成功，生产一个token
			String token = UUID.randomUUID().toString().replaceAll("-", "");
			Map<String, String> mapInfo = productToken.productToken(user.getUserId().toString(), token);
			//4.返回token信息（有效期50分钟）
			return setResultSuccess((Object)mapInfo);
		}
	}

	@Override
	public ResponseBase getUserInfo(Integer userId) {
		User user = userMapper.getUser(userId);
		if(user != null) {
			return setResultSuccess((Object)user);
		} else {
			return setResultSuccess("无此用户");
		}
	}
	
	@Autowired
	private ProductToken productToken;
	@Autowired
	private UserMapper userMapper;

}
