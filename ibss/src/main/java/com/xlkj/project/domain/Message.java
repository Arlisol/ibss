package com.xlkj.project.domain;

/** 
 * Project: ibss                                
 * Module ID: Message
 * Comments:   消息bean                          
 * JDK version used:      JDK1.7                              
 * Author：       赵志沅           
 * Create Date：  2018-6-23 
 * Modified By：                                           
 * Modified Date:                                    
 * Why & What is modified      
 * Version: 1.0                       
 */

public class Message {
	private String messageKey; //消息主键
	private String userKey; //用户主键
	private String name; //标题
	private String template; //标题
	private String title; //标题
	private String content; //内容
	private String result; //发送结果
	private String errorMessage; //错误消息
	private String sendUser; //发送人主键
	private String senderName; //发送人姓名
	private String sendTime; //发送时间
	private String createrKey;
	private String createDate;
	private String modifierKey;
	private String modifyDate;
	private String deleteFlag;

	public String getMessageKey() {
		return messageKey;
	}
	public void setMessageKey(String messageKey) {
		this.messageKey = messageKey;
	}
	public String getUserKey() {
		return userKey;
	}
	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getSendUser() {
		return sendUser;
	}
	public void setSendUser(String sendUser) {
		this.sendUser = sendUser;
	}
	public String getSenderName() {
		return senderName;
	}
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	public String getSendTime() {
		return sendTime;
	}
	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
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
	public String getTemplate() {
		return template;
	}
	public void setTemplate(String template) {
		this.template = template;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
