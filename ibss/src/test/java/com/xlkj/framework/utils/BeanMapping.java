package com.xlkj.framework.utils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import com.xlkj.project.domain.Specialty;

public class BeanMapping<T> {
	
	public static Logger logger =  LoggerFactory.getLogger(BeanMapping.class);
	
	/**
     * 将一个 JavaBean 对象转化为一个  Map
     * @param bean 要转化的JavaBean 对象
     * @return 转化出来的  Map 对象
     * @throws IntrospectionException 如果分析类属性失败
     * @throws IllegalAccessException 如果实例化 JavaBean 失败
     * @throws InvocationTargetException 如果调用属性的 setter 方法失败
     */
    public static Map convertBean(Object bean)
            throws IntrospectionException, IllegalAccessException, InvocationTargetException {
        Class type = bean.getClass();
        Map returnMap = new HashMap();
        BeanInfo beanInfo = Introspector.getBeanInfo(type);
 
        PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors();
        for (int i = 0; i< propertyDescriptors.length; i++) {
            PropertyDescriptor descriptor = propertyDescriptors[i];
            String propertyName = descriptor.getName();
            if (!propertyName.equals("class")) {
                Method readMethod = descriptor.getReadMethod();
                Object result = readMethod.invoke(bean, new Object[0]);
                if (result != null) {
                    returnMap.put(propertyName, result);
                } else {
                    returnMap.put(propertyName, "");
                }
            }
        }
        return returnMap;
    }
    
    
    /**
     * 将一个 Map 对象转化为一个 JavaBean
     * @param type 要转化的类型
     * @param map 包含属性值的 map
     * @return 
     * @return 转化出来的 JavaBean 对象
     * @throws IntrospectionException
     *             如果分析类属性失败
     * @throws IllegalAccessException
     *             如果实例化 JavaBean 失败
     * @throws InstantiationException
     *             如果实例化 JavaBean 失败
     * @throws InvocationTargetException
     *             如果调用属性的 setter 方法失败
     */
    public static <T> T convertMap(Class<T> type, Map<String,Object> map){
       
		BeanInfo beanInfo;
		try {
			beanInfo = Introspector.getBeanInfo(type);
			// 获取类属性
			T obj = type.newInstance(); // 创建 JavaBean 对象
			// 给 JavaBean 对象的属性赋值
			PropertyDescriptor[] propertyDescriptors = beanInfo
					.getPropertyDescriptors();
			for (int i = 0; i < propertyDescriptors.length; i++) {
				PropertyDescriptor descriptor = propertyDescriptors[i];
				String propertyName = descriptor.getName();
				if (map.containsKey(propertyName)) {
					// 下面一句可以 try 起来，这样当一个属性赋值失败的时候就不会影响其他属性赋值。
					Object value = map.get(propertyName);
					Object[] args = new Object[1];
					args[0] = value;
					descriptor.getWriteMethod().invoke(obj, args);
				}
			}
			return obj;
		} catch (IllegalAccessException e) {
			logger.debug(" 实例化 JavaBean 失败");
			e.printStackTrace();
		} catch (InstantiationException e) {
			logger.debug("分析类属性失败");
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			logger.debug("调用属性的 setter 方法失败");
			e.printStackTrace();
		} catch (IntrospectionException e) {
			logger.debug(" 如果实例化 JavaBean 失败");
			e.printStackTrace();
		}
		return null;
    }
    
    public static void main(String [] args){
    	Map<String,Object> map = new HashMap<String, Object>();
    	
    	map.put("nameCh", "计算机科学与技术");
    
//		Specialty specialty = convertMap(Specialty.class, map);
			
//		System.out.println(specialty.getNameCh());
			
    }
}
