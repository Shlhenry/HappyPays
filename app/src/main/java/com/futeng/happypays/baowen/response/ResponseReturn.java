package com.futeng.happypays.baowen.response;

import java.io.IOException;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;
//import org.apache.log4j.Logger;
//import com.cn.futeng.happypays.zhmhr.entity.ScanPayResponse;
//import com.cn.util.tool.Service;
import com.futeng.happypays.baowen.tool.Service;
import com.google.gson.Gson;


public class ResponseReturn {

	static Logger logger = Logger.getLogger(String.valueOf(ResponseReturn.class));

	/**
	 * Response返回
	 * @param s
	 * @param response
	 */
	public static void returnResponse(Service s, HttpServletResponse response){
		Gson gson = new Gson();
		try {
			logger.info("返回参数："+gson.toJson(s));
			response.getWriter().write(gson.toJson(s));
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
