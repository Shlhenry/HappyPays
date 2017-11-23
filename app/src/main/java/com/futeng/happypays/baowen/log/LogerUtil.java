package com.futeng.happypays.baowen.log;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

//import javax.servlet.http.HttpServletRequest;
//
//import org.apache.log4j.Logger;

/**
 * 日志工具
 * @author Administrator
 */
public class LogerUtil {
	
	private static Logger logger = Logger.getLogger(String.valueOf(LogerUtil.class));

	public void logger(HttpServletRequest request, Class zz) {
		Enumeration e = request.getParameterNames();
		Map m = new HashMap();
		while (e.hasMoreElements()) {
			String param = (String) e.nextElement();
			m.put(param, request.getParameter(param));
		}
		logger.info(zz.getSimpleName() + "|传入值|" + m);
	}

	public void logger(Object re, Class zz) {
		logger.info(zz.getSimpleName() + "|返回值|" + re);
	}

	public void logger(String re, Class zz) {
		logger.info(zz.getSimpleName() + "|返回值|" + re);
	}

}
