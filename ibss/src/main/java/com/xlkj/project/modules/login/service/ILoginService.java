package com.xlkj.project.modules.login.service;

import java.util.List;

import com.xlkj.project.domain.LoginRecord;
import com.xlkj.project.domain.UserInfo;
import com.xlkj.project.domain.Menu;

/** 
 * Project:  ibss                                       
 * Module ID: login
 * Comments:   登录模块业务逻辑层接口                                          
 * JDK version used:      JDK1.7                              
 * Author：       赵志沅           
 * Create Date：  2018-6-23
 * Modified By：                                           
 * Modified Date:                                      
 * Why & What is modified      
 * Version: 1.0                       
 */

public interface ILoginService {
	//判断用户名密码是否正确
	public UserInfo findUser(UserInfo userInfo);
	//查询用户信息
	public UserInfo findUserInfo(UserInfo userInfo);
	//登陆时间注入
	public void lastLoginTimeUpdate(String userKey);
	//获取一级菜单
	public List<Menu> getMenuList(String userType);
	//获取二级菜单
	public List<Menu> getSubMenuList(String menuKey,String userType);
	//获取错误消息数
	public int errorCount(String userKey);
	public int isLogined(LoginRecord loginRecord);
	public boolean setSession(LoginRecord loginRecord);
	public void cleanSession(String sessionId,String userKey);
}
