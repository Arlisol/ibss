package com.xlkj.project.modules.login.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xlkj.project.domain.LoginRecord;
import com.xlkj.project.domain.Menu;
import com.xlkj.project.domain.UserInfo;
import com.xlkj.project.modules.login.repository.ILoginDao;

/** 
 * Project:  ibss                                       
 * Module ID: login
 * Comments:   登录模块业务逻辑层实现类                                          
 * JDK version used:      JDK1.7                              
 * Author：      赵志沅           
 * Create Date：  2018-6-23
 * Modified By：                                           
 * Modified Date:                                      
 * Why & What is modified      
 * Version: 1.0                       
 */

@Service
@Transactional
public class LoginServiceImpl implements ILoginService{

	@Autowired
	private ILoginDao loginDao;
		
	@Override
	public UserInfo findUser(UserInfo userInfo) {
		
		return loginDao.findUser(userInfo);
	}
	
	/**
	* @Description: 获取用户信息
	* @param user
	* @return UserBean
	*/
	public UserInfo findUserInfo(UserInfo userInfo) {
		return loginDao.findUserInfo(userInfo);
	}
	
	public int errorCount(String userKey) {
		return loginDao.errorCount(userKey);
	}
	
	/**
	 * 更新登录时间
	 * @param user
	 */
	public void lastLoginTimeUpdate(String userKey) {
		loginDao.lastLoginTimeUpdate(userKey);
	}

	/**
	* @Description: 获取一级菜单
	* @return List<Menu>
	*/
	@Override
	public List<Menu> getMenuList(String userType) {
		return loginDao.getMenuList(userType);
	}
	
	/**
	* @Description: 获取二级菜单
	* @param menuKey
	* @return List<Menu>
	*/
	@Override
	public List<Menu> getSubMenuList(String userType,String menuKey) {
		return loginDao.getSubMenuList(userType,menuKey);
	}
	

	@Override
	public int isLogined(LoginRecord loginRecord) {
		return loginDao.isLogined(loginRecord);
	}

	@Override
	public boolean setSession(LoginRecord loginRecord) {
		return loginDao.setSession(loginRecord);
	}

	@Override
	public void cleanSession(String sessionId,String userKey) {
		loginDao.cleanSession(sessionId,userKey);
	}
}
