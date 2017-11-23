package com.futeng.happypays.baowen.auth;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import Decoder.BASE64Decoder;

public class AuthUtil {

	/**
	 * 传图
	 * @param filePath			路径
	 * @param pictureName		图片名
	 * @param picture			图片流
	 */
	public static void authPicture(String filePath, String pictureName, String picture){
		File file = new File(filePath);
		if (!file.exists()) {
			file.mkdirs();
		}
		filePath += File.separator + pictureName;
		File imageFile = new File(filePath);
		if (!imageFile.exists()) {
			try {
				imageFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			if (picture != null) {
				System.out.println("有图片!");
			}
			FileOutputStream output = new FileOutputStream(imageFile);
			System.out.println("picture:"+picture);
			output.write(new BASE64Decoder().decodeBuffer(picture.toString()));
			output.flush();
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
