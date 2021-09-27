package Locker;


import java.io.Serializable;

public class AppDtls implements Serializable{
	private String appName;
	private String userId;
	private String password;
	public AppDtls(String appName, String userId, String password) {
		super();
		this.appName = appName;
		this.userId = userId;
		this.password = password;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}