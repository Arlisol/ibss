package com.xlkj.project.domain;

/** 
 * Project: ibss                                
 * Module ID: Commodity
 * Comments:   商品bean                          
 * JDK version used:      JDK1.7                              
 * Author：       赵志沅              
 * Create Date：  2018-6-23 
 * Modified By：                                 
 * Modified Date:                                  
 * Why & What is modified      
 * Version: 1.0                       
 */

public class Commodity {
	private String commodityKey;//商品主键
	private String commodityClassKey;//商品类别主键
	private String commodityClassName;//商品类别名称
	private String commodityName;//商品名称
	private String commodityID;//商品ID
	private String price;//单价
	private String stock;//库存
	private String remark;//备注
	private String createrKey;
	private String createDate;
	private String modifierKey;
	private String modifyDate;
	private String deleteFlag;
	public String getCommodityKey() {
		return commodityKey;
	}
	public void setCommodityKey(String commodityKey) {
		this.commodityKey = commodityKey;
	}
	public String getCommodityClassKey() {
		return commodityClassKey;
	}
	public void setCommodityClassKey(String commodityClassKey) {
		this.commodityClassKey = commodityClassKey;
	}
	public String getCommodityClassName() {
		return commodityClassName;
	}
	public void setCommodityClassName(String commodityClassName) {
		this.commodityClassName = commodityClassName;
	}
	public String getCommodityName() {
		return commodityName;
	}
	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}
	public String getCommodityID() {
		return commodityID;
	}
	public void setCommodityID(String commodityID) {
		this.commodityID = commodityID;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getStock() {
		return stock;
	}
	public void setStock(String stock) {
		this.stock = stock;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
