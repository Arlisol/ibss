package com.xlkj.project.domain;

/** 
 * Project: ibss                                
 * Module ID: Music
 * Comments:   音乐bean                          
 * JDK version used:      JDK1.7                              
 * Author：       赵志沅              
 * Create Date：  2018-6-23 
 * Modified By：                                 
 * Modified Date:                                  
 * Why & What is modified      
 * Version: 1.0                       
 */

public class Music {
	private String musicKey;//音乐主键
	private String musicName;//音乐名称
	private String singer;//歌手
	private String album;//专辑
	private String musicStyle;//音乐风格
	private String minute;//时长(分)
	private String second;//时长(秒)
	private String issueDate;//发行时间
	private String language;//歌曲语言
	private String doWords;//填词
	private String writeMusic;//谱曲
	private String remark;//备注
	private String createrKey;	
	private String createDate;	
	private String modifierKey;
	private String modifyDate;	
	private String deleteFlag;
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
	public String getSinger() {
		return singer;
	}
	public void setSinger(String singer) {
		this.singer = singer;
	}
	public String getAlbum() {
		return album;
	}
	public void setAlbum(String album) {
		this.album = album;
	}
	public String getMusicStyle() {
		return musicStyle;
	}
	public void setMusicStyle(String musicStyle) {
		this.musicStyle = musicStyle;
	}
	public String getMinute() {
		return minute;
	}
	public void setMinute(String minute) {
		this.minute = minute;
	}
	public String getSecond() {
		return second;
	}
	public void setSecond(String second) {
		this.second = second;
	}
	public String getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getDoWords() {
		return doWords;
	}
	public void setDoWords(String doWords) {
		this.doWords = doWords;
	}
	public String getWriteMusic() {
		return writeMusic;
	}
	public void setWriteMusic(String writeMusic) {
		this.writeMusic = writeMusic;
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
