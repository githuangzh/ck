package com.will.init;
/**
 * 2010(c) Copyright Oceansoft Information System Co.,LTD. All rights reserved.
 * <p>
 * Compile: JDK 1.6+
 * <p>
 * 版权所有(C)：江苏欧索软件有限公司
 * <p>
 * 公司名称：江苏欧索软件有限公司
 * <p>
 * 公司地址：中国苏州科技城青山路1号
 * <p>
 * 网址: http://www.oceansoft.com.cn
 * <p>
 * 版本: 苏州公安统一用户管理平台1.0
 * <p>
 * 作者: 090922(陈伟)
 * <p>
 * 文件名:SysConfigLoader.java
 * <p>
 * 类产生时间: May 30, 2013 12:27:26 PM
 * <p>
 * 负责人: 090922(陈伟)
 * <p>
 * Email:javacspring@gmail.com
 * <p>
 * 所在组 : 苏州公安统一用户管理平台
 * <p>
 * 所在部门: 电信/国土——技术二部
 * <p>
 * <p>
 */


import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class SystemPropertiesLoader implements ServletContextListener {
	Logger log = Logger.getLogger("SysConfigLoader");
	public static Properties pro = new Properties();
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext sc = sce.getServletContext();
		InputStream is = sc.getResourceAsStream("/WEB-INF/config/properties/application.properties");
		try {
			pro.load(is);
			Set<Object> keys = pro.keySet();
			Iterator<Object> itor = keys.iterator();
			while (itor.hasNext()) {
				String key = itor.next().toString();
				if (key.startsWith("application") || key.startsWith("page")) {
					String value = pro.getProperty(key);
					sc.setAttribute(key.substring(key.indexOf(".") + 1, key.length()), value);
					System.out.println(key.substring(key.indexOf(".") + 1, key.length()) + "==>" + value);
				}
			}
		} catch (IOException e) {
			log.log(Level.SEVERE, "读取系统配置文件异常！");
			e.printStackTrace();
		}
	}

	
	public void contextDestroyed(ServletContextEvent sce) {
	}
}
