package com.xlkj.project.domain;

/** 
 * Project: ibss                                 
 * Module ID: Result
 * Comments:   结果bean                          
 * JDK version used:      JDK1.7                              
 * Author：        赵志沅             
 * Create Date：  2018-6-23 
 * Modified By：                                           
 * Modified Date:                                      
 * Why & What is modified      
 * Version: 1.0                       
 */

public class Result {
	private boolean status;//操作结果状态			
	private String message;//操作后的提示信息
	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
