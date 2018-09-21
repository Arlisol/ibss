package com.xlkj.project.modules.buyRecord.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.xlkj.project.domain.BuyRecord;
import com.xlkj.project.domain.UserInfo;

/** 
 * Project:  ibss                                      
 * Comments: 购买记录模块数据持久层接口                           
 * JDK version used:      JDK1.7                              
 * Author：赵志沅   
 * Create Date：   2018-6-26 
 * Modified By：                                           
 * Modified Date:                                      
 * Why & What is modified      
 * Version: 1.0                       
 */

public interface IBuyRecordDao {
	//获取购买记录信息分页对象
	public Page<BuyRecord> findWithPage(Pageable pageable, BuyRecord buyRecord, UserInfo user);
}
