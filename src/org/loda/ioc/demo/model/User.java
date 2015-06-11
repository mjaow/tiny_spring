package org.loda.ioc.demo.model;

/**
 * 
* @ClassName: User 
* @Description: demo演示--用户信息 
* @author minjun
* @date 2015年6月8日 下午11:40:08 
*
 */
public class User {

	private int id;
	
	private String username;
	
	private String password;

	public User() {
		super();
	}

	public User(int id, String username, String password) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password="
				+ password + "]";
	}
}
