package com.xlkj.project.modules.personalInfo.service;

/** 
 * Project:  ibss                                       
 * Module ID: personalInfo
 * Comments:   个人资料模块业务逻辑层实现类                                          
 * JDK version used:      JDK1.7                              
 * Author：      赵志沅           
 * Create Date：  2018-6-23
 * Modified By：                                           
 * Modified Date:                                      
 * Why & What is modified      
 * Version: 1.0                       
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xlkj.project.domain.UserInfo;
import com.xlkj.project.modules.personalInfo.repository.IPersonalInfoDao;

@Service
@Transactional
public class PersonalInfoServiceImpl implements IPersonalInfoService {
	
	@Autowired
	private IPersonalInfoDao personalInfoDao;
	
	@Override
	public UserInfo getUserInfo(String userKey) {
		
		return personalInfoDao.getUserInfo(userKey);
	}

	@Override
	public boolean userInfoModify(UserInfo userInfo,UserInfo user) {

		return personalInfoDao.userInfoModify(userInfo,user);
	}
}
