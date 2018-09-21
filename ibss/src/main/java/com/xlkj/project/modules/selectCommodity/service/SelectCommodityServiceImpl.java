package com.xlkj.project.modules.selectCommodity.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xlkj.project.domain.BuyRecord;
import com.xlkj.project.domain.UserInfo;
import com.xlkj.project.modules.selectCommodity.repository.ISelectCommodityDao;

/** 
 * Project:  ibss                                      
 * Comments: 购物模块业务逻辑层实现类                           
 * JDK version used:      JDK1.7                              
 * Author：赵志沅   
 * Create Date：   2018-6-27 
 * Modified By：                                           
 * Modified Date:                                      
 * Why & What is modified      
 * Version: 1.0                       
 */

@Service
@Transactional
public class SelectCommodityServiceImpl implements ISelectCommodityService {
	
	//日志记录
	Logger logger =  LoggerFactory.getLogger(SelectCommodityServiceImpl.class);
	
	@Autowired
	private ISelectCommodityDao selectCommodityDao;

	/**
	 *  @Description	添加购物记录
	 *  @param	buyRecords	信息拼接字符串
	 *	@param	user	UserInfo实例
	 * 	@return	boolean	
	 */
	@Override
	public boolean add(String buyRecords,BuyRecord buyRecord,UserInfo user) {
		String[] buyRecordList =buyRecords.split(",");
		String recordCount = buyRecordList[1];
		String price = buyRecordList[2];
		int countNum = Integer.parseInt(recordCount);
		double priceNum = Double.parseDouble(price);
		double result = countNum*priceNum;
		String cost = Double.toString(result);
		buyRecord.setCommodityKey(buyRecordList[0]);
		buyRecord.setRecordCount(buyRecordList[1]);
		buyRecord.setCost(cost);
		return selectCommodityDao.add(buyRecord, user);
	}
}
