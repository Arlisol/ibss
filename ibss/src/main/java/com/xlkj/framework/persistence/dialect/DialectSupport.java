package com.xlkj.framework.persistence.dialect;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.util.NoSuchElementException;
import java.util.Properties;

import com.xlkj.framework.utils.PropertiesLoader;

public class DialectSupport {

	public static String currentDialect ="MySQL5InnoDBDialect";
	
	public String getCurrentDialect(){
		
		Properties p = new PropertiesLoader("classpath:/application.development.properties", "classpath:/application.properties")
		.getProperties();
		
		try {
			assertNull(p.getProperty("notexist"));
			fail("should fail here");
		} catch (NoSuchElementException e) {
		}
//		return p.getProperty(key);
		return null;
	}
}
