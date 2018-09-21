package com.xlkj.project.modules.personalInfo.repository;

/** 
 * Project:  ibss                                       
 * Module ID: personalInfo
 * Comments:   个人资料模块数据持久层接口                                          
 * JDK version used:      JDK1.7                              
 * Author：       赵志沅          
 * Create Date：  2018-6-23 
 * Modified By：                                           
 * Modified Date:                                      
 * Why & What is modified      
 * Version: 1.0                       
 */

import com.xlkj.project.domain.UserInfo;

public interface IPersonalInfoDao {
	//查询个人信息
	public UserInfo getUserInfo(String userKey);
	//修改个人信息
	public boolean userInfoModify(UserInfo userInfo,UserInfo user);
}
