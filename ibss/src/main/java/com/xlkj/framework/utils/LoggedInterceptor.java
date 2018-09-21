/**
 * 
 */
package com.xlkj.framework.utils;

import java.io.PrintWriter;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/** 
 * CopyRright (c)2015-2016:   大连校联科技有限公司
 * Project:  xiaolian                                       
 * Module ID: LoggedInterceptor
 * Comments:    验证用户是否登录的拦截器类                          
 * JDK version used:      JDK1.7                              
 * Author：        曹尧             
 * Create Date：  2015-9-27 
 * Modified By：                                           
 * Modified Date:                                      
 * Why & What is modified      
 * Version: 1.0                       
 */

public class LoggedInterceptor extends HandlerInterceptorAdapter {
	
	//读取配置文件中的系统ip和port
	Properties p = new PropertiesLoader(/*"classpath:/application.development.properties", */"classpath:/application.properties")
	.getProperties();
	private String BASEPATH = "http://" + p.getProperty("system.ip")+":"+p.getProperty("system.port") +"/ibss";
	
	/**
	 * controller之前执行
	 */
    @Override  
    public boolean preHandle(HttpServletRequest request,  
            HttpServletResponse response, Object handler) throws Exception {  
    	request.setCharacterEncoding("UTF-8");  
        response.setCharacterEncoding("UTF-8");  
        response.setContentType("text/html;charset=UTF-8");  
        
        //后台session控制  
        String[] noFilters = new String[] { "login", "static", "kaptcha","register" };  
        String uri = request.getRequestURI();
        //初始登录url不拦截
        if(!uri.equals("/ibss/") && !uri.equals("/") ) {
        	boolean beFilter = true;  
            for (String s : noFilters) {  
                if (uri.indexOf(s) != -1) {  
                    beFilter = false;  
                    break;  
                }
            }  
            if (beFilter) {  
                Object obj = request.getSession().getAttribute("user");  
                if (null == obj) {  
                    // 未登录  
                	PrintWriter out = response.getWriter();  
                    StringBuilder builder = new StringBuilder();  
                    builder.append("<script type=\"text/javascript\" charset=\"UTF-8\">");  
                    builder.append("alert(\"页面过期，请重新登录\");");  
                    builder.append("window.top.location.href=\"");  
                    builder.append(BASEPATH);  
                    builder.append("\";</script>");  
                    out.print(builder.toString());  
                    out.close();  
                    return false;  
                } 
            }  
        }
        
        return super.preHandle(request, response, handler);  
    }  
	  
	/**
	 * 生成视图之前执行
	 */
	@Override  
	public void postHandle(HttpServletRequest request,  
	        HttpServletResponse response, Object handler,  
	        ModelAndView modelAndView) throws Exception {  
	}  
	  
	/**
	 * 最后执行，可用于释放资源
	 */
	@Override  
	public void afterCompletion(HttpServletRequest request,  
	        HttpServletResponse response, Object handler, Exception ex)  
	        throws Exception {  
	}  
	
}
