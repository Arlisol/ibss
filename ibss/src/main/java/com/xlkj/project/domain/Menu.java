package com.xlkj.project.domain;

import java.util.List;

/** 
* Project:  ibss                                       
* Module ID: Menu
* Comments:  菜单bean                                           
* JDK version used:      JDK1.7                              
* Author：        赵志沅                
* Create Date：  2018-6-23
* Modified By：                                           
* Modified Date:                                      
* Why & What is modified      
* Version: 1.0
 */
public class Menu {
	private String menuKey;//菜单主键	
	private String menuName;//菜单名称	
	private String menuLevel;//菜单级别：一级，二级菜单	
	private String parentMenuKey;//上级菜单	
	private String parentMenuName;//上级菜单名
	private String groupSequence;//分组序号	
	private String menuSequence;//菜单序号	
	private String menuURL;//菜单URL	
	private String userType;//用户类型
	private String remark;//备注
	private String menuLogo;//菜单logo
	private String createrKey;	
	private String createDate;	
	private String modifierKey;
	private String modifyDate;	
	private String deleteFlag;	
    private String hidMenuName;
	private List<Menu> subMenuList;	//子菜单;	
	public String getCreateDate() {
		return createDate;
	}
	public String getCreaterKey() {
		return createrKey;
	}
	
	public String getDeleteFlag() {
		return deleteFlag;
	}
	public String getGroupSequence() {
		return groupSequence;
	}
	public String getMenuKey() {
		return menuKey;
	}
	public String getMenuLevel() {
		return menuLevel;
	}
	public String getMenuName() {
		return menuName;
	}
	public String getMenuSequence() {
		return menuSequence;
	}
	public String getMenuURL() {
		return menuURL;
	}
	public String getModifierKey() {
		return modifierKey;
	}
	public String getModifyDate() {
		return modifyDate;
	}
	public String getParentMenuKey() {
		return parentMenuKey;
	}
	public String getParentMenuName() {
		return parentMenuName;
	}
	public String getRemark() {
		return remark;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public void setCreaterKey(String createrKey) {
		this.createrKey = createrKey;
	}
	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	public void setGroupSequence(String groupSequence) {
		this.groupSequence = groupSequence;
	}
	public void setMenuKey(String menuKey) {
		this.menuKey = menuKey;
	}
	public void setMenuLevel(String menuLevel) {
		this.menuLevel = menuLevel;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public void setMenuSequence(String menuSequence) {
		this.menuSequence = menuSequence;
	}
	public void setMenuURL(String menuURL) {
		this.menuURL = menuURL;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public void setModifierKey(String modifierKey) {
		this.modifierKey = modifierKey;
	}
	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}
	public void setParentMenuKey(String parentMenuKey) {
		this.parentMenuKey = parentMenuKey;
	}
	public void setParentMenuName(String parentMenuName) {
		this.parentMenuName = parentMenuName;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getHidMenuName() {
		return hidMenuName;
	}
	public void setHidMenuName(String hidMenuName) {
		this.hidMenuName = hidMenuName;
	}
	public String getMenuLogo() {
		return menuLogo;
	}
	public void setMenuLogo(String menuLogo) {
		this.menuLogo = menuLogo;
	}
	public List<Menu> getSubMenuList() {
		return subMenuList;
	}
	public void setSubMenuList(List<Menu> subMenuList) {
		this.subMenuList = subMenuList;
	}
}
