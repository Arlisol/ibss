package com.xlkj.project.modules.selectMusic.service;

import com.xlkj.project.domain.UserInfo;

/** 
 * Project:  ibss                                      
 * Comments: 点歌模块业务逻辑层接口                           
 * JDK version used:      JDK1.7                              
 * Author：赵志沅   
 * Create Date：   2018-6-27
 * Modified By：                                           
 * Modified Date:                                      
 * Why & What is modified      
 * Version: 1.0                       
 */

public interface ISelectMusicService {
	//添加点歌记录
	public boolean add(String musicKey,UserInfo user);
}
