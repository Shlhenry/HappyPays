package com.futeng.happypays.baowen.tool;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class ByteUtil {
	
	/**
	 * 将数组截取
	 * @param array	截取的数组
	 * @param start 开始的序号
	 * @param len 长度
	 * @return
	 */
	public static byte[] subArray(byte[] array, int start, int len) {
		ByteArrayInputStream input = new ByteArrayInputStream(array);
		if (len == -1) {
			len = array.length - start;
		}
		System.out.println("array:"+array.length);
		System.out.println("start:"+start);
		System.out.println("len:"+len);
		byte[] result = new byte[len];
		System.out.println(result);
		input.read(result, 0, start);
		input.read(result, 0, len);
		return result;
	}

	/**
	 * int  转   byte[]
	 * @param num		指定的数字
	 * @param length	长度
	 * @return
	 */
	public static byte[] intToByte(int num, int length) {
		byte[] b = new byte[length];
		for (int i = 0; i < length; i++) {
			b[i] = (byte) (num >> ((8 * (length - 1)) - i * 8));
		}
		return b;
	}
	
	/**
	 * byte[] 转  int
	 * @param bytes
	 * @return
	 */
	public static int bytesToInt(byte[] bytes) {
		return Integer.parseInt(ConvertUtil.BCD2ASC(bytes),10);
	}
	
	/**
	 * 
	 * @param hexStr
	 * @param w
	 * @return
	 */
	public static List<String> sipltHexStr(String hexStr, int w) {
		List<String> hexs = new ArrayList<String>();
		int i = 0;
		while (true) {
			if ((i + 1) * w > hexStr.length()) {
				break;
			}
			String str = hexStr.substring((i * w), (i + 1) * w);
			hexs.add(str);
			i++;
		}
		return hexs;
	}

	/**
	 * 00 74 60 00 00 00 00 08 00 22 20 00 00 08 c0 09 01 31 39 30 30 30 30 30
	 * 36 32 将十六进制字符串转byte[]
	 * @param ss
	 * @return
	 */
	public static byte[] getByte(String ss) {
		String[] shil = ss.split(" ");
		StringBuilder er = new StringBuilder();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		for (String string : shil) {
			if ("".equals(string)) {
				continue;
			}
			int i = Integer.valueOf(string, 16);
			out.write(i);
		}
		return out.toByteArray();
	}

	/**
	 * @param ss
	 * @return
	 */
	public static byte[] getByteByNoSpli(String ss) {
		byte[] arrB = ss.getBytes();
		int iLen = arrB.length;
		byte[] arrOut = new byte[iLen / 2];
		for (int i = 0; i < iLen; i = i + 2) {
			String stdfrTmp = new String(arrB, i, 2);
			arrOut[i / 2] = (byte) Integer.parseInt(stdfrTmp, 16);
		}
		return arrOut;
	}
	/**
	 * byte
	 * 获取没有空格的十六进制
	 * @param b
	 * @return
	 */
	public static String getHexStrNoSpli(byte[] b) {
		StringBuilder builder = new StringBuilder();
		ByteArrayInputStream input = new ByteArrayInputStream(b);
		int i = 0;
		while ((i = input.read()) != -1) {
			builder.append(Integer.toHexString(i).length() == 2 ? Integer .toHexString(i) : 0 + Integer.toHexString(i));
		}
		return builder.toString();
	}

	/**
	 * byte
	 * 获取有空格的十六进制字符串
	 * @param b
	 * @return
	 * 3fe4b19ca5a7807c19ac60358f7b548254177e5d3f23797cfeace072ecf07bfb4f020000
	 */
	public static String getHexStr(byte[] b) {
		StringBuilder builder = new StringBuilder();
		ByteArrayInputStream input = new ByteArrayInputStream(b);
		int i = 0;
		while ((i = input.read()) != -1) {
			builder.append(Integer.toHexString(i).length() == 2 ? Integer .toHexString(i) : 0 + Integer.toHexString(i));
			builder.append(" ");
		}
		return builder.toString();
	}
	
	private static boolean isBEnd(byte[] request) {
		byte[] b = request;
		if (b == null) {
			return false;
		}
		if (b.length < 4) {
			return false;
		}
		int resNum = ByteUtil.bytesToInt4B(request);
		return resNum == (b.length - 4);
	}

	public static int bytesToInt4B(byte b[]) {
		return b[0] & 0xff | (b[1] & 0xff) << 8 | (b[2] & 0xff) << 16 | (b[3] & 0xff) << 24;
	}

	public static int bytesToInt2B(byte b[]) {
		return b[0] & 0xff << 8 | (b[1] & 0xff);
	}

	/**
	 * @param b
	 * @return
	 */
	public static String getShi(byte[] buf) {
		StringBuilder builder = new StringBuilder();
		ByteArrayInputStream input = new ByteArrayInputStream(buf);
		int i = 0;
		while ((i = input.read()) != -1) {
			builder.append(i);
		}
		return builder.toString();
	}
	
	public static void printHexStr(byte[] strToBcd,String title) {
		System.out.println(title+ByteUtil.getHexStr(strToBcd));
	}
	
	public static void printHexStr(byte[] strToBcd) {
		System.out.println(ByteUtil.getHexStr(strToBcd));
	}
	
	/**
	 * Main方法
	 * @param args
	 */
	public static void main(String[] args) {
		Pattern pattern = Pattern.compile("//W+");
	}
}
