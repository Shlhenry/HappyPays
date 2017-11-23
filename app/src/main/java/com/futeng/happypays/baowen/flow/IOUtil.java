package com.futeng.happypays.baowen.flow;

import java.io.IOException;
import java.io.InputStream;

/**
 * IO工具类
 * @author Administrator
 */
public class IOUtil {

	public static String readInputStream(InputStream inputStream, String encoding) throws IOException {
		if (inputStream == null) {
			return null;
		}
		StringBuffer buffer = new StringBuffer();
		int length = 0;
		byte[] bytes = new byte[1024];
		while ((length = inputStream.read(bytes, 0, bytes.length)) != -1) {
			String read = new String(bytes, 0, length, "ISO_8859_1");
			buffer.append(read);
		}
		byte[] allBytes = buffer.toString().getBytes("ISO_8859_1");
		String returnMsg = new String(allBytes, encoding);
		return returnMsg;
	}

	
	public static InputStream getResourceAsStream(String name) {
		InputStream iis = IOUtil.class.getClassLoader().getResourceAsStream(name);
		return iis;
	}
}
