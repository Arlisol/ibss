package com.xlkj.framework.web;

import java.util.List;

import com.xlkj.framework.utils.dictionary.component.IDictionaryManager;
import com.xlkj.framework.utils.dictionary.domain.Dictionary;

public class Dict {
	
	private IDictionaryManager dictionaryManager;
	
	public IDictionaryManager getDictionaryManager() {
		return dictionaryManager;
	}


	public void setDictionaryManager(IDictionaryManager dictionaryManager) {
		this.dictionaryManager = dictionaryManager;
	}

	private static Dict dict;
	
	public void init(){
		
		dict = this;
		
		dict.dictionaryManager = this.dictionaryManager;
		
	}
	
	/**
	 * 通过fieldId 查找Dictionary列表
	 * 
	 * @param fieldId
	 * @return Dictionary列表
	 */
	public static List<Dictionary> list(String fieldId){
		
		List<Dictionary> dicts = dict.dictionaryManager.getDictionaryByGroupCode(fieldId);
			
		return dicts;
	}
	
	/**
	 * 根据groupCode和code显示value
	 * 例如：
	 * groupCode group code value 
	 * Gender     性别     0            女
	 * Gender     性别     1            男
	 * display("Gender","1")应该返回："男"
	 * @param groupCode:分组编码
	 * @param code：数据编码
	 * @return 根据分组编码和数据编码判断要显示的数据值
	 */
	public static String display(String groupCode,String code){
		
		Dictionary dictionary = dict.dictionaryManager.findDictionaryByCodeWithGroupCode(groupCode, code);
		
		return dictionary.getLabel();
	}
	
	/**
	* @Description: 根据groupCode和code显示itemsequence
	* @param groupCode
	* @param code
	* @return int
	 */
	public static int getItemSequence(String groupCode,String code) {
		Dictionary dictionary = dict.dictionaryManager.findDictionaryByCodeWithGroupCode(groupCode, code);
		
		return dictionary.getSequence();
	}
}
