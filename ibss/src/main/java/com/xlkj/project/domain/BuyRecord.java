package com.xlkj.project.domain;

/** 
 * Project: ibss                                
 * Module ID: BuyRecord
 * Comments:   购买记录bean                          
 * JDK version used:      JDK1.7                              
 * Author：       赵志沅              
 * Create Date：  2018-6-23 
 * Modified By：                                 
 * Modified Date:                                  
 * Why & What is modified      
 * Version: 1.0                       
 */

public class BuyRecord {
	private String buyRecordKey;//购买记录主键
	private String userKey;//用户主键
	private String userName;//用户名
	private String name;//姓名
	private String commodityKey;//商品主键
	private String commodityName;//商品名称
	private String recordCount;//购买数量
	private String recordDate;//购买日期
	private String cost;//花费
	private String createrKey;
	private String createDate;
	private String modifierKey;
	private String modifyDate;
	private String deleteFlag;
	public String getBuyRecordKey() {
		return buyRecordKey;
	}
	public void setBuyRecordKey(String buyRecordKey) {
		this.buyRecordKey = buyRecordKey;
	}
	public String getUserKey() {
		return userKey;
	}
	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCommodityKey() {
		return commodityKey;
	}
	public void setCommodityKey(String commodityKey) {
		this.commodityKey = commodityKey;
	}
	public String getCommodityName() {
		return commodityName;
	}
	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}
	public String getRecordCount() {
		return recordCount;
	}
	public void setRecordCount(String recordCount) {
		this.recordCount = recordCount;
	}
	public String getRecordDate() {
		return recordDate;
	}
	public void setRecordDate(String recordDate) {
		this.recordDate = recordDate;
	}
	public String getCost() {
		return cost;
	}
	public void setCost(String cost) {
		this.cost = cost;
	}
	public String getCreaterKey() {
		return createrKey;
	}
	public void setCreaterKey(String createrKey) {
		this.createrKey = createrKey;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getModifierKey() {
		return modifierKey;
	}
	public void setModifierKey(String modifierKey) {
		this.modifierKey = modifierKey;
	}
	public String getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}
	public String getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
}
