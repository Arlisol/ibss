package com.xlkj.framework.utils.dictionary.domain;
/**
* CopyRright (c)2013-2014:   大连四校联科技有限公司 
* Project:  xiaolian                                       
* Module ID: DictionaryCodes
* Comments:	数据字典编码对应类                                             
* JDK version used:      JDK1.7                              
* Author：        栾喜员                 
* Create Date：  2014年2月18日 
* Modified By：                                           
* Modified Date:                                      
* Why & What is modified      
* Version: 1.0
 */
public class DictionaryCodes {
	
	/**
	 * 性别
	 */
	public enum Gender {
        Man(1),					//男
        Woman(0);				//女
        private final int value;
        public int getValue() {
            return value;
        }
        Gender(int value) {
            this.value = value;
        }
    }
}
