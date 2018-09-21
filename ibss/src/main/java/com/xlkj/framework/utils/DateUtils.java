package com.xlkj.framework.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
/** 
 * CopyRright (c)2017-2018:   大连校联科技有限公司 
 * Project:  sams                                       
 * Comments: 日期工具类                           
 * JDK version used:      JDK1.7                              
 * Author：曹尧   
 * Create Date：   2018-1-11
 * Modified By：                                           
 * Modified Date:                                      
 * Why & What is modified      
 * Version: 1.0                       
 */
public class DateUtils {
	 /**
	  * @Description:获取时间段内日期
	  * @param dBegin：开始时间
	  * @param dEnd：结束时间
	  * @return
	  */
	 public static List<Date> findDates(Date dBegin, Date dEnd)  
	 {  
		  List lDate = new ArrayList();  
		  lDate.add(dBegin);  
		  Calendar calBegin = Calendar.getInstance();  
		  // 使用给定的 Date 设置此 Calendar 的时间  
		  calBegin.setTime(dBegin);  
		  Calendar calEnd = Calendar.getInstance();  
		  // 使用给定的 Date 设置此 Calendar 的时间  
		  calEnd.setTime(dEnd);  
		  // 测试此日期是否在指定日期之后  
		  while (dEnd.after(calBegin.getTime()))  
		  {  
		   // 根据日历的规则，为给定的日历字段添加或减去指定的时间量  
		   calBegin.add(Calendar.DAY_OF_MONTH, 1);  
		   lDate.add(calBegin.getTime());  
		  }  
		  return lDate;  
		 
	 } 
	 /**
	  * @Description:获取指定日期为星期几
	  * @param date
	  * @return
	  */
	 public static  String getWeekOfDate(Date date) {      
		    String[] weekOfDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};        
		    Calendar calendar = Calendar.getInstance();      
		    if(date != null){        
		         calendar.setTime(date);      
		    }        
		    int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;      
		    if (w < 0){        
		        w = 0;      
		    }      
		    return weekOfDays[w];    
	}
	 /**
	  * @Description:比较两个日期小时分钟大小
	  * @param date1
	  * @param date2
	  * @return
	  */
	public static boolean timeCompareByHM(Date date1,Date date2){
		Calendar cal1=Calendar.getInstance();//使用日历类 
		cal1.setTime(date1);
		int hour1=cal1.get(cal1.HOUR_OF_DAY);  //获得时
		int minutes1=cal1.get(cal1.MINUTE); //获得分
		Calendar cal2=Calendar.getInstance();//使用日历类 
		cal2.setTime(date2);
		int hour2=cal2.get(cal1.HOUR_OF_DAY);  //获得时
		int minutes2=cal2.get(cal1.MINUTE); //获得分
		if(hour1>hour2){
			return false;
		}else if(hour1==hour2){
			if(minutes1>minutes2){
				return false;
			}else if(minutes1==minutes2){	
				return false;
			}else{
				return true;
			}
		}else{
			return true;
		}
		
	}
	public static String getCourseTime(Date date1,Date date2){
		Calendar cal1=Calendar.getInstance();//使用日历类 
		cal1.setTime(date1);
		int hour1=cal1.get(cal1.HOUR_OF_DAY);  //获得时
		int minutes1=cal1.get(cal1.MINUTE); //获得分
		long tt= date2.getTime()+60*60*1000*hour1+60*1000*minutes1;
		Calendar cal2=Calendar.getInstance();//使用日历类 
		cal2.setTimeInMillis(tt);
		Date date3= cal2.getTime();
		System.out.println(date3);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");  
		System.out.println(sdf.format(date3));
		return sdf.format(date3);
	}
	
}
