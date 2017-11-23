package com.futeng.happypays.baowen.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

//import com.cn.util.flow.IOUtil;
import com.futeng.happypays.baowen.flow.IOUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class HttpUtil {

	public static String sendPost(String url, String param) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try
		{
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(new OutputStreamWriter(conn.getOutputStream(),"UTF-8"));
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
			String line;
			while ((line = in.readLine()) != null)
			{
				result += "\n" + line;
			}
		}
		catch (Exception e){
			System.out.println("发送POST请求出现异常！" + e);
			e.printStackTrace();
			return null;
		}
		finally{
			try
			{
				if (out != null)
				{
					out.close();
				}
				if (in != null)
				{
					in.close();
				}
			}
			catch (IOException ex)
			{
				ex.printStackTrace();
				return null;
			}
		}
		return result;
}

	public static void main(String[] args) {
		String result = HttpUtil.sendPost("http://120.27.138.219:8080/PhonePOSPInterface/SubQrPayServlet", "money=1300&merc=896330187762029&mcc=45&qrno=4c296dc700005025&term=87810503161231152056&subMercName=南通营业部");
		System.out.println(result);
		Gson gson = new Gson();
		Map<String,String> mapResult  =gson.fromJson(result, new TypeToken<Map<String,String>>(){}.getType());
		String code = mapResult.get("code");
		System.out.println(code);
	}
	public static String post(String url, String request) {
			PrintWriter out = null;
			BufferedReader in = null;
			String result = "";
			try {
				URL realUrl = new URL(url);
				URLConnection conn = realUrl.openConnection();
				conn.setRequestProperty("accept", "*/*");
				conn.setRequestProperty("connection", "Keep-Alive");
				conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
				conn.setDoOutput(true);
				conn.setDoInput(true);
				out = new PrintWriter(conn.getOutputStream());
				out.print(request);
				out.flush();
				in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				String line;
				while ((line = in.readLine()) != null) {
					result += line;
				}
			} catch (Exception e) {
				System.out.println("发送数据异常:" + e);
				e.printStackTrace();
			}
			finally {
				try {
					if (out != null) {
						out.close();
					}
					if (in != null) {
						in.close();
					}
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
			return result;
		}
	
		public static String get(String url, String request) {
			String response = null;
			InputStream iis = null;
			try {
				HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(url + "?" + request).openConnection();
				httpURLConnection.setRequestMethod("GET");
				httpURLConnection.setDoInput(true);
				httpURLConnection.setAllowUserInteraction(true);
		
				iis = httpURLConnection.getInputStream();
				response = IOUtil.readInputStream(iis, "UTF-8");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return response;
		}
}
