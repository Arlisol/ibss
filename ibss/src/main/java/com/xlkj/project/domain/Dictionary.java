package com.xlkj.project.domain;

/** 
 * Project: ibss                                
 * Module ID: Dictionary
 * Comments:   数据字典bean                          
 * JDK version used:      JDK1.7                              
 * Author：       赵志沅              
 * Create Date：  2018-6-23 
 * Modified By：                                 
 * Modified Date:                                  
 * Why & What is modified      
 * Version: 1.0                       
 */

public class Dictionary {
	private String dictionaryKey;
	private String groupCode;
	private String groupLabel;
	private String itemCode;
	private String itemLabel;
	private String itemSequence;
	private String remark;
	public String getDictionaryKey() {
		return dictionaryKey;
	}
	public void setDictionaryKey(String dictionaryKey) {
		this.dictionaryKey = dictionaryKey;
	}
	public String getGroupCode() {
		return groupCode;
	}
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
	public String getGroupLabel() {
		return groupLabel;
	}
	public void setGroupLabel(String groupLabel) {
		this.groupLabel = groupLabel;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public String getItemLabel() {
		return itemLabel;
	}
	public void setItemLabel(String itemLabel) {
		this.itemLabel = itemLabel;
	}
	public String getItemSequence() {
		return itemSequence;
	}
	public void setItemSequence(String itemSequence) {
		this.itemSequence = itemSequence;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}



}
