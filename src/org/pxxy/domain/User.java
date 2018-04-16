package org.pxxy.domain;

//用户类，对应数据库中的user表
public class User {

	private String userName;// 用户名
	private String password;// 密码

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
