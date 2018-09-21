package com.xlkj.project.modules.buyRecord.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xlkj.project.domain.BuyRecord;
import com.xlkj.project.domain.UserInfo;
import com.xlkj.project.modules.buyRecord.repository.IBuyRecordDao;
/** 
 * Project:  ibss                                      
 * Comments: 购买记录模块业务逻辑层实现类                           
 * JDK version used:      JDK1.7                              
 * Author：赵志沅   
 * Create Date：   2018-6-26 
 * Modified By：                                           
 * Modified Date:                                      
 * Why & What is modified      
 * Version: 1.0                       
 */

@Service
@Transactional
public class BuyRecordServiceImpl implements IBuyRecordService {
	
	//日志记录
	Logger logger =  LoggerFactory.getLogger(BuyRecordServiceImpl.class);
		
	@Autowired
	private IBuyRecordDao buyRecordDao;
	
	/**
	* @Description		购买记录分页查询
	* @param pageable	Pageable实例	
	* @param buyRecord	BuyRecord实例
	* @param user	UserInfo实例
	* @return findWithPage	Page<BuyRecord>实例
	*/
	@Override
	public Page<BuyRecord> findWithPage(Pageable pageable,BuyRecord buyRecord, UserInfo user) {
		
		return buyRecordDao.findWithPage(pageable, buyRecord, user);
	}
}
