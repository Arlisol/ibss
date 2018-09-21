package com.xlkj.project.domain;

/** 
 * Project: ibss                                
 * Module ID: MusicRecord
 * Comments:   点歌记录bean                          
 * JDK version used:      JDK1.7                              
 * Author：       赵志沅              
 * Create Date：  2018-6-23 
 * Modified By：                                 
 * Modified Date:                                  
 * Why & What is modified      
 * Version: 1.0                       
 */

public class MusicRecord {
	private String musicRecordKey;//点歌记录主键
	private String userKey;//用户主键
	private String userName;//用户名
	private String name;//姓名
	private String musicKey;//音乐主键
	private String musicName;//音乐名称
	private String recordDate;//点歌日期
	private String createrKey;//	
	private String createDate;//	
	private String modifierKey;//
	private String modifyDate;//	
	private String deleteFlag;//
	public String getMusicRecordKey() {
		return musicRecordKey;
	}
	public void setMusicRecordKey(String musicRecordKey) {
		this.musicRecordKey = musicRecordKey;
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
	public String getMusicKey() {
		return musicKey;
	}
	public void setMusicKey(String musicKey) {
		this.musicKey = musicKey;
	}
	public String getMusicName() {
		return musicName;
	}
	public void setMusicName(String musicName) {
		this.musicName = musicName;
	}
	public String getRecordDate() {
		return recordDate;
	}
	public void setRecordDate(String recordDate) {
		this.recordDate = recordDate;
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
