/**
 * 
 */
package com.xlkj.framework.utils;

/** 
 * CopyRright (c)2015-2016:   大连校联科技有限公司 
 * Project:  ites                                     
 * Comments: 常量工具类                         
 * JDK version used:      JDK1.7                              
 * Author：       曹尧
 * Create Date：  2015年9月2日
 * Modified By：                                           
 * Modified Date:                                      
 * Why & What is modified      
 * Version: 1.0                       
 */
public class ConstantUtils {
		//机房屏蔽区
		public final static String PINGBIQU ="（屏蔽区）";  
	    public final static String FEIPINGBI="(非屏蔽区)";
		//设备类型ID
		public static String STODEVICEID ="0011";//存储设备ID
		public static String SURDEVICEID ="0012";//安全设备ID
		public static String VIRDEVICEID ="0014";//虚拟设备ID
		public static String PHYDEVICEID ="0013";//物理设备ID
		public static String NETDEVICEID ="0015";//网络设备ID
		public static String OTHERDEVICEID ="0016";//其他设备ID
	
	//平台用户标识定义
	public static String COMMONUSER="commonuser"; //普通用户角色
	public static String PLATADMIN="platAdmin";  //平台管理员
	public static String AREADMIN="areaAdmin";  //区域管理员
	public static String AUDITADMIN="auditAdmin";  //审核管理员	
	public static String SCHOOLUSER="schoolUser";  //学校用户
	public static String COMPANYUSER="companyUser";  //企业用户
	public static String PARTNERUSER="partnerUser";  //合伙人用户
	
	//BOOL 类型常量定义
	public static String TRUE="0";                 
	public static String FALSE="1";
	
	// 称谓标识表
	public final static String APPLICATION_CODE_LEARNER = "11";  //学习者
	public final static String APPLICATION_CODE_ITEXPER = "12";  //IT经验人士
	public final static String APPLICATION_CODE_TEACHER = "13";  //公司管理教师
	public final static String APPLICATION_CODE_COMPMARK = "14"; //公司市场运营人员
	public final static String APPLICATION_CODE_UNTEACHER = "15";//学校管理教师
	public final static String APPLICATION_CODE_UNTEAMER = "16"; //学校团队负责人
	
	//项目角色常量定义
	public final static String ITEM_ROLE_LEADER ="11"; //项目负责人
	public final static String ITEM_ROLE_PPQA ="12"; //PPQA	
	
	//开放创新系统项目类型常量定义
	public final static String INTERN_PROJECT ="11";  //实习项目
	public final static String TECH_PROJECT ="12";    //技术项目
	public final static String EXPER_PROJECT ="13";   //体验项目
	public final static String REAL_PROJECT ="14";    //实际项目
	public final static String COURSE_PROJECT ="15";  //开放课程
	//答疑角色定义
	public final static String LAOSHI ="LS";  //实习项目
	public final static String SHENHE ="SH";    //技术项目
	public final static String XUEYUAN ="XY";   //体验项目
	
	public static String DEFICON="pernoimg.png";
	
	//微信消息模板ID
	public final static String WORK_CALL ="OPENTM200799669";	//作业提醒
	public final static String BUYCOURSE_OK ="OPENTM203829700";	//课程购买成功通知
	public final static String SETCOURSE_OK ="OPENTM206051644";	//排课通知
	public final static String COURSE_CHANGE ="OPENTM206547500";	//课程状态变更通知
	public final static String CUSTOMER_ASSESS ="OPENTM207330942";	//课程评价通知
	public final static String CHERK_ERROR ="OPENTM401761532";	//缺勤提醒
	public final static String CONTINUE_PAY ="OPENTM405714145";	//学员续费提醒
	public final static String PAY_MONENY ="OPENTM405831457";	//缴费提醒
	public final static String ATTEND_CALL ="OPENTM406792304";	//学员考勤通知
	public final static String RETURNS_OK ="OPENTM410479201";	//退费成功通知
	public final static String TEST_RECORD ="OPENTM411869857";	//考试成绩通知
	public final static String TEACHER_ASSESS ="OPENTM412134750";	//课后评价通知
	public final static String CHECK_OK ="OPENTM412820001";	//课堂报告通知
}
