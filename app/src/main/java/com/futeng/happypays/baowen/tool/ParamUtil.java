package com.futeng.happypays.baowen.tool;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

//import org.apache.log4j.Logger;

//import com.cn.util.ip.IPUtil;

/**
 * 参数处理
 * @author
 */
public class ParamUtil {
	
	static Logger logger = Logger.getLogger(String.valueOf(ParamUtil.class));
	
	/**
	 * 字符串转Map
	 * @param str
	 * @return
	 */
    public static Map<String,String> toMap(String str, Class o) {
    	Logger logger = Logger.getLogger(String.valueOf(o));
        Map<String,String> map = new HashMap<String,String>();
        String data[] = str.split("&");
        for (int i = 0; i < data.length; i++) {
        	map.put(data[i].split("=",2)[0], data[i].split("=",2)[1]);
        }
        logger.info("参数值："+map);
        return map;
    }
    
    public static Map<String,String> toMap(String str) {
        Map<String,String> map = new HashMap<String,String>();
        String data[] = str.split("&");
        for (int i = 0; i < data.length; i++) {
        	map.put(data[i].split("=",2)[0], data[i].split("=",2)[1]);
        }
        logger.info("参数值："+map);
        return map;
    }
	
	public static String getParam(HttpServletRequest request,String param){
		String strValue = "";
		Map map = request.getParameterMap();
		Set keSet = map.entrySet();
		Map<String,String> mapParam = new HashMap<String,String>();
		for (Iterator itr = keSet.iterator(); itr.hasNext();) {
			Map.Entry me = (Map.Entry) itr.next();
			Object ok = me.getKey();
			Object ov = me.getValue();
			String[] value = new String[1];
			if (ov instanceof String[]) {
				value = (String[]) ov;
			} else {
				value[0] = ov.toString();
			}
			for (int k = 0; k < value.length; k++) {
				mapParam.put(ok+"", value[k]);
				System.out.println(ok + "=" + value[k]);
			}
		}
		if(mapParam.containsKey(param)){
			strValue = mapParam.get(param);
		}
		return strValue;
	}
	
	public static String getParamValue(Map<String,String> map,String param){
		String value = "";
		if(map.containsKey(param)){
			value = map.get(param);
		}
		return value;
	}
	
	/**
	 * request参数处理,返回Map<参数名,参数值>
	 * @param request
	 * @return
	 */
	public static Map<String,String> getParamValues(HttpServletRequest request, Class o){
		Logger logger = Logger.getLogger(String.valueOf(o));
		Map map = request.getParameterMap();
		Set keSet = map.entrySet();
		Map<String,String> mapParam = new HashMap<String,String>();
		for (Iterator itr = keSet.iterator(); itr.hasNext();) {
			Map.Entry me = (Map.Entry) itr.next();
			Object ok = me.getKey();
			Object ov = me.getValue();
			String[] value = new String[1];
			if (ov instanceof String[]) {
				value = (String[]) ov;
			} else {
				value[0] = ov.toString();
			}
			for (int k = 0; k < value.length; k++) {
				mapParam.put(ok+"", value[k]);
			}
		}
		logger.info("参数值："+mapParam);
		return mapParam;
	}
	
	
	
	public static void main(String[] args) {
//		String s = "c:\\aa\bb\t.apk";
		String appPath = "192.0.2.5/a/b/tdd.apk";
		System.out.println(appPath.length());
		System.out.println(appPath.lastIndexOf("/"));
		System.out.println(appPath.substring(appPath.lastIndexOf("/")+1, appPath.length()));
	}
}
