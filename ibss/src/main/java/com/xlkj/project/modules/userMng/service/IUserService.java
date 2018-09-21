package com.xlkj.project.modules.userMng.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.xlkj.project.domain.UserInfo;

/** 
 * Project:  ibss                                       
 * Module ID: userMng
 * Comments:   用户管理模块业务逻辑层接口                                          
 * JDK version used:      JDK1.7                              
 * Author：       赵志沅           
 * Create Date：  2018-6-24
 * Modified By：                                           
 * Modified Date:                                      
 * Why & What is modified      
 * Version: 1.0                       
 */

public interface IUserService {
	//获取用户信息分页对象
	public Page<UserInfo> findWithPage(Pageable pageable, UserInfo userInfo);
	//添加操作
	public boolean add(UserInfo userInfo, UserInfo user);
	//获取用户原内容
	public UserInfo getUserInfo(String userKey);
	//修改操作
	public boolean modify(UserInfo userInfo,UserInfo user);	
	//删除操作
	public boolean delete(String userKey,UserInfo user);
	//获取用户详细
	public UserInfo detail(String userKey);
	//充值
	public boolean recharge(UserInfo userInfo, UserInfo user);
	//用户名唯一性校验
	public boolean userNameUnique(UserInfo userInfo);
}
