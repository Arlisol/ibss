package com.xlkj.project.domain;

/** 
 * Project: ibss                                
 * Module ID: LoginRecord
 * Comments:   登录记录bean                          
 * JDK version used:      JDK1.7                              
 * Author：       赵志沅              
 * Create Date：  2018-6-23 
 * Modified By：                                 
 * Modified Date:                                  
 * Why & What is modified      
 * Version: 1.0                       
 */

import java.sql.Timestamp;

public class LoginRecord {
	private String userKey; // 用户登录的userKey
	private String seesionId; // 用户登录的会话Id
	private String ipAddr; // 用户登录的IP地址
	private Timestamp loginTime; // 登录时间,可以利用数据库的默认值
	private int loginFlag; // 登录标识
	
	public String getUserKey() {
		return userKey;
	}
	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}
	public String getSeesionId() {
		return seesionId;
	}
	public void setSeesionId(String seesionId) {
		this.seesionId = seesionId;
	}
	public String getIpAddr() {
		return ipAddr;
	}
	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}
	public Timestamp getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Timestamp loginTime) {
		this.loginTime = loginTime;
	}
	public int getLoginFlag() {
		return loginFlag;
	}
	public void setLoginFlag(int loginFlag) {
		this.loginFlag = loginFlag;
	}
}
