package com.xlkj.framework.utils.dictionary.component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Maps;
import com.xlkj.framework.exception.ServiceException;
import com.xlkj.framework.utils.dictionary.domain.Dictionary;
import com.xlkj.framework.utils.dictionary.service.DictionaryService;
import com.xlkj.framework.utils.mapper.BeanMapper;
public class DictionaryDefaultManager implements IDictionaryManager {
	

	private static Logger logger = LoggerFactory.getLogger(DictionaryDefaultManager.class);
	
	@Autowired
	private DictionaryService dictionaryService;
	
	private static List<Dictionary> dicts = new ArrayList<Dictionary>();
	
	private static Map<String,List<Dictionary>> groupDictionaryMap = Maps.newLinkedHashMap();
	
	
	/**
	 * 实例化数据字典列表
	 */
	@SuppressWarnings("unused")
	@PostConstruct
	private void initDicts(){
		
		logger.debug("init datadictionary starting ……");
		
		dicts = dictionaryService.getAllDictionary();

		logger.debug("finshed init datadictionary instance!");
		
		logger.debug("invoke storedByGroup() method !");
		
		storedByGroup();
	}
	
	/**
	 * 将数据字典列表按照groupCode分类存储
	 */
	private void storedByGroup(){
		
		for(Dictionary dict :dicts){
			String groupCode = dict.getFieldId();
			//根据groupCode从groupDictionaryMap里去dictionary列表，这里称作cacheDicts
			List<Dictionary> cacheDicts = groupDictionaryMap.get(groupCode);
			if(null == cacheDicts){
				cacheDicts = new ArrayList<Dictionary>();
			}
			cacheDicts.add(dict);
			groupDictionaryMap.put(groupCode, cacheDicts);
		}
		
		logger.debug("拆分后的groupDictionaryMap："+groupDictionaryMap.toString());
		
	}
	
	@Override
	public List<Dictionary> getDictionaryByGroupCode(String groupCode){
		
		List<Dictionary> groupDicts = new ArrayList<Dictionary>();
		
		//遍历dicts，将分组编码等于groupCode值的对象添加到groupDicts列表中
//		for(Dictionary dict :dicts)
//		{
//			if(groupCode.equals(dict.getFieldId())){
//				Dictionary dictCopy = new Dictionary();
//				BeanMapper.copy(dict, dictCopy);
//				groupDicts.add(dictCopy);
//			}
//		}
		
		//优化后直接从groupDictionaryMap取，减少内存遍历耗时，提高性能
		List<Dictionary> cacheGroupDicts = groupDictionaryMap.get(groupCode);
		
		if(null == cacheGroupDicts){
			logger.info("groupcode:{}",groupCode);
			throw new ServiceException("数据字典groupcode对应的列表为空，请校验groupcode是否正确");
		}
		
		BeanMapper.copy(cacheGroupDicts, groupDicts);
		
		return groupDicts;
	}
	
	/**
	 * 查询所有数据字典列表
	 */
	public List<Dictionary> findAll(){
		return dicts;
		
	}

	
	@Override
	public Dictionary findDictionaryByCodeWithGroupCode(String groupCode,
			String code) {
		
		Dictionary d = new Dictionary();
		
		List<Dictionary> cacheGroupDicts = getDictionaryByGroupCode(groupCode);
		
		for(Dictionary dict : cacheGroupDicts){
			  if(dict.getCode().equals(code)){
				  
				  BeanMapper.copy(dict, d);
				  
				  break;
			  }
		}
		
		return d;
	}
	
}
