package com.xlkj.project.modules.userMng.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xlkj.framework.utils.DbUtils;
import com.xlkj.project.domain.UserInfo;
import com.xlkj.project.modules.userMng.repository.IUserDao;

/** 
 * Project:  ibss                                       
 * Module ID: userMng
 * Comments:   用户管理模块业务逻辑层实现类                                          
 * JDK version used:      JDK1.7                              
 * Author：      赵志沅           
 * Create Date：  2018-6-24
 * Modified By：                                           
 * Modified Date:                                      
 * Why & What is modified      
 * Version: 1.0                       
 */

@Service
@Transactional
public class UserServiceImpl implements IUserService {

	//日志记录
	Logger logger =  LoggerFactory.getLogger(UserServiceImpl.class);
		
	@Autowired
	private IUserDao userDao;

	/**
	* @Description		用户分页查询
	* @param pageable	Pageable实例	
	* @param menu	Menu实例
	* @return findWithPage	Page<Menu>实例
	*/
	@Override
	public Page<UserInfo> findWithPage(Pageable pageable, UserInfo userInfo) {
		
		return userDao.findWithPage(pageable, userInfo);
	}

	/**
	* @Description		添加操作
	* @param userInfo	UserInfo实例	
	* @param user	UserInfo实例
	* @return boolean
	*/
	@Override
	public boolean add(UserInfo userInfo, UserInfo user) {
		userInfo.setUserKey(DbUtils.createId());
		userInfo.setPassword(DbUtils.ibssEncrypt("000000"));
		userInfo.setUserID(userDao.maxUserID()+1);
		if(!userDao.addUser(userInfo, user)){
			return false;
		}
		if(!userDao.addUserInfo(userInfo, user)){
			return false;
		}
		return true;
	}

	/**
	* @Description		获取用户原内容
	* @param userKey	用户主键	
	* @return getUserInfo		UserInfo实例
	*/
	@Override
	public UserInfo getUserInfo(String userKey) {
		
		return userDao.getUserInfo(userKey);
	}

	/**
	* @Description		修改操作
	* @param userInfo	UserInfo实例
	* @param user	UserInfo对象	
	* @return boolean
	*/
	@Override
	public boolean modify(UserInfo userInfo, UserInfo user) {
		if(!userDao.modifyUser(userInfo, user)){
			return false;
		}
		if(!userDao.modifyUserInfo(userInfo, user)){
			return false;
		}
		return true;
	}

	/**
	* @Description		删除操作
	* @param userKey	用户主键
	* @param user	UserInfo对象	
	* @return boolean
	*/
	@Override
	public boolean delete(String userKey, UserInfo user) {
		if(!userDao.deleteUser(userKey, user)){
			return false;
		}
		if(!userDao.deleteUserInfo(userKey, user)){
			return false;
		}
		return true;
	}

	/**
	* @Description		获取用户详细
	* @param userKey	用户主键
	* @return detail		UserInfo实例
	*/
	@Override
	public UserInfo detail(String userKey) {
		
		return userDao.detail(userKey);
	}

	/**
	* @Description		充值
	* @param userInfo	UserInfo实例
	* @param user	UserInfo实例
	* @return boolean
	*/
	@Override
	public boolean recharge(UserInfo userInfo, UserInfo user) {
		if(userInfo.getPresentSum()!=null){
			double rechargeSum = Double.parseDouble(userInfo.getRechargeSum());
			double presentSum = Double.parseDouble(userInfo.getPresentSum());
			double result = rechargeSum+presentSum;
			double balance = userDao.getBalance(userInfo.getUserKey());
			double newBalance = result+balance;
			String newData = Double.toString(newBalance);
			userInfo.setBalance(newData);
		}else{
			double rechargeSum = Double.parseDouble(userInfo.getRechargeSum());
			double balance = userDao.getBalance(userInfo.getUserKey());
			double newBalance = rechargeSum+balance;
			String newData = Double.toString(newBalance);
			userInfo.setBalance(newData);
		}
		return userDao.recharge(userInfo, user);
	}
	
	/**
	* @Description		用户名唯一性校验
	* @param userInfo	UserInfo实例
	* @return boolean
	*/
	@Override
	public boolean userNameUnique(UserInfo userInfo) {

		return userDao.userNameUnique(userInfo);
	}
}
