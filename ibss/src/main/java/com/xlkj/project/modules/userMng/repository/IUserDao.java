package com.xlkj.project.modules.userMng.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.xlkj.project.domain.UserInfo;

/** 
 * Project:  ibss                                       
 * Module ID: userMng
 * Comments:   用户管理模块数据持久层接口                                          
 * JDK version used:      JDK1.7                              
 * Author：       赵志沅          
 * Create Date：  2018-6-24 
 * Modified By：                                           
 * Modified Date:                                      
 * Why & What is modified      
 * Version: 1.0                       
 */

public interface IUserDao {
	//获取用户信息分页对象
	public Page<UserInfo> findWithPage(Pageable pageable, UserInfo userInfo);
	//查询最大用户ID
	public int maxUserID();
	//用户添加操作
	public boolean addUser(UserInfo userInfo, UserInfo user);
	//用户信息添加操作
	public boolean addUserInfo(UserInfo userInfo, UserInfo user);
	//获取用户原内容
	public UserInfo getUserInfo(String userKey);
	//用户修改操作
	public boolean modifyUser(UserInfo userInfo,UserInfo user);
	//用户信息修改操作
	public boolean modifyUserInfo(UserInfo userInfo,UserInfo user);	
	//用户删除操作
	public boolean deleteUser(String userKey,UserInfo user);
	//用户信息删除操作
	public boolean deleteUserInfo(String userKey,UserInfo user);
	//获取用户详细
	public UserInfo detail(String userKey);
	//充值
	public boolean recharge(UserInfo userInfo, UserInfo user);
	//查询用户余额
	public int getBalance(String userKey);
	//用户名唯一性校验
	public boolean userNameUnique(UserInfo userInfo);
}
