package com.xlkj.project.domain;

/** 
 * Project: ibss                                 
 * Module ID: UserInfo
 * Comments:   用户信息bean                          
 * JDK version used:      JDK1.7                              
 * Author：       赵志沅            
 * Create Date：  2018-6-23 
 * Modified By：                                        
 * Modified Date:                                  
 * Why & What is modified      
 * Version: 1.0                       
 */

public class UserInfo {
	private String userInfoKey;//用户信息主键
	private String userKey;//用户主键
	private String name;//姓名
	private String gender;//性别
	private String birth;//出生日期
	private String phone;//电话
	private String email;//电子邮箱
	private String balance;//余额
	private String rechargeSum;//充值金额
	private String presentSum;//赠送金额
	private String remark;//备注
	private String createKey;
	private String createDate;
	private String modifierKey;
	private String modifyDate;
	private String deleteFlag;
	private String userName;//用户名
	private String hidUserName;
	private int userID;//用户ID
	private String newUserID;
	private String password;//密码
	private String rePassword;
	private String userType;//用户类型标识 1-管理员 2-普通用户 3-会员
	private String lastLoginTime;//上次登录时间
	private int version;
	public String getUserInfoKey() {
		return userInfoKey;
	}
	public void setUserInfoKey(String userInfoKey) {
		this.userInfoKey = userInfoKey;
	}
	public String getUserKey() {
		return userKey;
	}
	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	public String getRechargeSum() {
		return rechargeSum;
	}
	public void setRechargeSum(String rechargeSum) {
		this.rechargeSum = rechargeSum;
	}
	public String getPresentSum() {
		return presentSum;
	}
	public void setPresentSum(String presentSum) {
		this.presentSum = presentSum;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCreateKey() {
		return createKey;
	}
	public void setCreateKey(String createKey) {
		this.createKey = createKey;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getModifierKey() {
		return modifierKey;
	}
	public void setModifierKey(String modifierKey) {
		this.modifierKey = modifierKey;
	}
	public String getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}
	public String getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getHidUserName() {
		return hidUserName;
	}
	public void setHidUserName(String hidUserName) {
		this.hidUserName = hidUserName;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getNewUserID() {
		return newUserID;
	}
	public void setNewUserID(String newUserID) {
		this.newUserID = newUserID;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRePassword() {
		return rePassword;
	}
	public void setRePassword(String rePassword) {
		this.rePassword = rePassword;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
}