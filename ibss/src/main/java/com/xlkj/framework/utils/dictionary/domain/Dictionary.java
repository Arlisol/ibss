package com.xlkj.framework.utils.dictionary.domain;

import java.io.Serializable;

public class Dictionary  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3025722961516448163L;
	
	/**
	 * 分组编码
	 */
	private String fieldId;
	
	/**
	 * 分组名称，没有参与实际的业务的业务判断，只是用户说明分组编码的含义
	 */
	private String fieldName;
	
	/**
	 * 数据编码
	 */
	private String code;
	
	/**
	 * 数据内容
	 */
	private String label;
	
	/**
	 * 数据排序
	 */
	private int sequence;
	
	/**
	 * 备注
	 */
	
	private String remark;
	
	/**
	 * 排序
	 */
	private String orderField;
	
	public int getSequence() {
		return sequence;
	}
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}
	public String getFieldId() {
		return fieldId;
	}
	public void setFieldId(String fieldId) {
		this.fieldId = fieldId;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getOrderField() {
		return orderField;
	}
	public void setOrderField(String orderField) {
		this.orderField = orderField;
	}
	
	@Override
	public String toString() {
		return "fieldId:"+fieldId+",fieldName:"+fieldName+",code:"+code+",label"+label+",sequence:"+sequence;
	}
	
}
