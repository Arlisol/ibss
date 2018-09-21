package com.xlkj.framework.utils.dictionary.component;

import java.util.List;

import com.xlkj.framework.utils.dictionary.domain.Dictionary;
/**
 * 数据字典对外接口，数据提供的策略在其实现类中定义：
 * 
 * 比如：是采用第三方cache，还是采用静态（static）变量来存储
 * 
 * @author debao.wang
 *
 */
public interface IDictionaryManager {

	
	public List<Dictionary>  getDictionaryByGroupCode(String groupCode);


	public List<Dictionary> findAll();
	
	public Dictionary findDictionaryByCodeWithGroupCode(String groupCode,String code);
	
}