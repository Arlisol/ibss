package com.xlkj.project.modules.buyRecord.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.xlkj.project.domain.BuyRecord;
import com.xlkj.project.domain.UserInfo;

/** 
 * Project:  ibss                                      
 * Comments: 购买记录模块业务逻辑层接口                           
 * JDK version used:      JDK1.7                              
 * Author：赵志沅   
 * Create Date：   2018-6-26 
 * Modified By：                                           
 * Modified Date:                                      
 * Why & What is modified      
 * Version: 1.0                       
 */

public interface IBuyRecordService {
	//获取购买记录信息分页对象
	public Page<BuyRecord> findWithPage(Pageable pageable, BuyRecord buyRecord, UserInfo user);
}
