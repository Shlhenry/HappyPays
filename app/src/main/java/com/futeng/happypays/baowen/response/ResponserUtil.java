package com.futeng.happypays.baowen.response;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.http.HttpServletResponse;

import com.futeng.happypays.baowen.log.LogerUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 
 * @author
 */
public class ResponserUtil {

	/**
	 * 
	 * @param response
	 * @param responseResult
	 */
	public static void returnMessage(HttpServletResponse response, Object responseResult) {
		try {
			Writer writer = response.getWriter();
			Gson gson = new Gson();
			writer.write(gson.toJson(responseResult));
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param response
	 * @param responseResult
	 */
	public static void returnMessage(HttpServletResponse response, ResponseResult responseResult) {
		try {
			Writer writer = response.getWriter();
			GsonBuilder build = new GsonBuilder().excludeFieldsWithoutExposeAnnotation();
			String str = build.create().toJson(responseResult);
			// 添加日志
			LogerUtil logerUtil = new LogerUtil();
			logerUtil.logger(str, ResponserUtil.class);
			writer.write(str);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
