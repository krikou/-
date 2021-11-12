package com.example.demo.mapper;


import com.example.demo.entity.User;
import com.example.demo.mapper.sql.UserSqlProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;


/**
 * @author nan.gai
 */
@Repository
@Mapper
public interface UserMapper {

	/**
	 * 通过用户名、密码获取用户信息
	 * @param userName
	 * @param password
	 * @return
	 */
	@SelectProvider(method = "select", type = UserSqlProvider.class)
	@Results(id="user", value={
			@Result(column="id", property="userId", id=true),
		    @Result(column="user_name", property="userName"),
	})
	User getUserInfo(String userName, String password);
	
	/**
	 * 通过用户ID查询用户基本信息
	 * @param userId
	 * @return
	 */
	@SelectProvider(method = "selectById", type = UserSqlProvider.class)
	@ResultMap(value="user")
	User getUser(Integer userId);
}
