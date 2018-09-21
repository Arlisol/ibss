package com.xlkj.project.domain;

/** 
 * Project: ibss                                
 * Module ID: CommodityClass
 * Comments:   商品类别bean                          
 * JDK version used:      JDK1.7                              
 * Author：       赵志沅              
 * Create Date：  2018-6-23 
 * Modified By：                                 
 * Modified Date:                                  
 * Why & What is modified      
 * Version: 1.0                       
 */

public class CommodityClass {
	private String commodityClassKey;//商品类别主键
	private String commodityClassName;//商品类别名称
	private String commodityClassID;//商品类别ID
	private String remark;//备注
	private String createrKey;
	private String createDate;
	private String modifierKey;
	private String modifyDate;
	private String deleteFlag;
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
	public String getCommodityClassID() {
		return commodityClassID;
	}
	public void setCommodityClassID(String commodityClassID) {
		this.commodityClassID = commodityClassID;
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
