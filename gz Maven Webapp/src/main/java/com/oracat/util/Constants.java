package com.oracat.util;



public class Constants {
	
	// 数据库表常量
	public static final String USERTABLE = "user_inf";
	public static final String YZTABLE = "yz_puyao";
	public static final String DCTABLE = "dc_puyao";
	public static final String GFTABLE = "goods_for_goods";
	public static final String JOBTABLE = "job_inf";
	public static final String EMPLOYEETABLE = "employee_inf";
	public static final String NOTICETABLE = "notice_inf";
	public static final String DOCUMENTTABLE = "document_inf";
	
	// 登录
	public static final String LOGIN = "loginform";
	// 用户的session对象
	public static final String USER_SESSION = "user_session";
	
	// 默认每页4条数据
	public static int PAGE_DEFAULT_SIZE = 15;


	/**
	 * 定时任务启动状态
	 */
	public static final int STATUS_RUNNING = 0;
	/**
	 * 定时任务暂停状态
	 */
	public static final int STATUS_NOT_RUNNING = 1;
}