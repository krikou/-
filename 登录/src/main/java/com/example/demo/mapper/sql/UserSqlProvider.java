package com.example.demo.mapper.sql;


import org.apache.ibatis.jdbc.SQL;

/**
 * @author nan.gai
 */
public class UserSqlProvider {

	public String select(final String userName, final String password) {

		String sql = new SQL() {
			{
				SELECT("*");
				FROM("user");
				WHERE("user_name='" + userName + "' AND password= '" + password + "'");
			}
		}.toString();
		return sql;
	}

	public String selectById(final Integer userId) {

		String sql = new SQL() {
			{
				SELECT("*");
				FROM("user");
				WHERE("id= '" + userId + "'");
			}
		}.toString();
		return sql;
	}

}
