package com.futeng.happypays.baowen.tool;

//import java.awt.Graphics2D;
//import java.awt.RenderingHints;
//import java.awt.geom.AffineTransform;
//import java.awt.image.BufferedImage;
//import java.awt.image.ColorModel;
//import java.awt.image.WritableRaster;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 公共方法
 * @author yang
 * 2013-2-25
 */
public class CommonUtil {
	public static void main(String[] args) {
//		2023
		System.out.println(2023&2048);
	}
//	public static BufferedImage hebingImage(List<BufferedImage> images){
//		int sumWitd = 0;
//		int height=0;
//		List<BufferedImage> news=new ArrayList<BufferedImage>();
//		for (BufferedImage image : images) {
//			if (image.getWidth()>800||image.getHeight()>800) {
//				image=CommonUtil.resize(image, 800, 800);
//				news.add(image);
//			}else{
//				news.add(image);
//			}
//			if (height<image.getHeight()) {
//				height=image.getHeight();
//			}
//			sumWitd+=image.getWidth();
//		}
//		BufferedImage imageResult=new BufferedImage(sumWitd, height, BufferedImage.TYPE_INT_RGB);
//		int w=0;
//		for (BufferedImage bufferedImage : news) {
//			int[] old=new int[height*bufferedImage.getWidth()];
//			old=bufferedImage.getRGB(0, 0, bufferedImage.getWidth(),
//					bufferedImage.getHeight(), old, 0, bufferedImage.getWidth());
//			imageResult.setRGB(w, 0, bufferedImage.getWidth(), height,
//					old, 0, bufferedImage.getWidth());// 设置右半部分的RGB
//			w+=bufferedImage.getWidth();
//		}
//		return imageResult;
//	}
//	public static BufferedImage resize(BufferedImage source, int targetW,
//            int targetH) {
//        // targetW，targetH分别表示目标长和宽
//        int type = source.getType();
//        BufferedImage target = null;
//        double sx = (double) targetW / source.getWidth();
//        double sy = (double) targetH / source.getHeight();
//        // 这里想实现在targetW，targetH范围内实现等比缩放。如果不需要等比缩放
//        // 则将下面的if else语句注释即可
//        if (sx < sy) {
//            sy = sx;
//            targetH = (int) (sx * source.getHeight());
//        } else {
//            sx = sy;
//            targetW = (int) (sy * source.getWidth());
//        }
//        if (type == BufferedImage.TYPE_CUSTOM) { // handmade
//            ColorModel cm = source.getColorModel();
//            WritableRaster raster = cm.createCompatibleWritableRaster(targetW,
//                    targetH);
//            boolean alphaPremultiplied = cm.isAlphaPremultiplied();
//            target = new BufferedImage(cm, raster, alphaPremultiplied, null);
//        } else
//            target = new BufferedImage(targetW, targetH, type);
//        Graphics2D g = target.createGraphics();
//        // smoother than exlax:
//        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
//                RenderingHints.VALUE_INTERPOLATION_BICUBIC);
//        g.drawRenderedImage(source, AffineTransform.getScaleInstance(sx, sy));
//        g.dispose();
//        return target;
//
//	}
//
//
//	public static String toPlain(Object b){
//		java.math.BigDecimal bg=new java.math.BigDecimal(b.toString());
//		System.out.println(bg);
//		return bg.toPlainString();
//	}
////	public static void main(String[] args) {
////		System.out.println(CommonUtil.toPlain("0.0045"));
////	}
////	public static void main(String[] args) {
////		System.out.println(String.format("%6s", "182").replace(" ", "0"));
////	}
//
//
//	/**
//	 * 随机数
//	 * @return
//	 */
//	public static Integer randNumber(int leng){
//		StringBuilder builder=new StringBuilder();
//		Random rom=new Random();
//		for (int i = 0; i < leng; i++) {
//			builder.append(rom.nextInt(9));
//		}
//		return Integer.parseInt(builder.toString());
//	}
//
//	/**
//	 * 验证身份证
//	 * @param IDStr
//	 * @return
//	 * @throws ParseException
//	 */
//	public static String IDCardValidate(String IDStr){
//		String errorInfo = "";//记录错误信息
//		String[] ValCodeArr = {"1","0","X","9","8","7","6","5","4","3","2"};
//		String[] Wi = {"7","9","10","5","8","4","2","1","6","3","7","9","10","5","8","4","2"};
//		//String[] Checker = {"1","9","8","7","6","5","4","3","2","1","1"};
//		String Ai="";          //================ 号码的长度 15位或18位 ================
//		if(IDStr.length()!=15 && IDStr.length()!=18) {
//			errorInfo="身份证号码长度应该为15位或18位。";
//			return errorInfo;
//		}
//		//================ 数字 除最后以为都为数字 ================
//		if(IDStr.length()==18) {
//			Ai=IDStr.substring(0,17);
//		} else if(IDStr.length()==15) {
//			Ai=IDStr.substring(0,6)+"19"+IDStr.substring(6,15);
//		} if(isNumeric(Ai)==false) {
//			errorInfo="15位身份证号码都应为数字 ; 18位身份证号码除最后一位外，都应为数字。";
//			return errorInfo;
//		}
//		//================ 出生年月是否有效 ================
//		String strYear =Ai.substring(6 ,10);//年份
//		String strMonth=Ai.substring(10,12);//月份
//		String strDay        =Ai.substring(12,14);//月份
//		if(isDate(strYear+"-"+strMonth+"-"+strDay)==false){
//			errorInfo="身份证生日无效。";
//			return errorInfo;
//		}
//		GregorianCalendar gc=new GregorianCalendar();
//		SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd");
//		try {
//			if((gc.get(Calendar.YEAR)-Integer.parseInt(strYear))>150 || (gc.getTime().getTime()-s.parse(strYear+"-"+strMonth+"-"+strDay).getTime())<0){
//				errorInfo="身份证生日不在有效范围。";
//				return errorInfo;
//			} if(Integer.parseInt(strMonth)>12 || Integer.parseInt(strMonth)==0) {
//				errorInfo="身份证月份无效";
//				return errorInfo;
//			}if(Integer.parseInt(strDay)>31 || Integer.parseInt(strDay)==0) {
//				errorInfo="身份证日期无效";
//				return errorInfo;
//			}
//		} catch (NumberFormatException e) {
//			e.printStackTrace();
//			return "请填写正确的身份证格式";
//		} catch (ParseException e) {
//			e.printStackTrace();
//			return "内部解析错误";
//		}
//		//================ 地区码时候有效 ================
//		Hashtable h=GetAreaCode();
//		if(h.get(Ai.substring(0,2))==null){
//			errorInfo="身份证地区编码错误。";
//			return errorInfo;
//		}
//		//================ 判断最后一位的值 ================
//		int TotalmulAiWi=0;
//		for(int i=0 ; i<17 ; i++){
//			TotalmulAiWi = TotalmulAiWi + Integer.parseInt(String.valueOf(Ai.charAt(i))) * Integer.parseInt(Wi[i]);
//		}
//		int modValue=TotalmulAiWi % 11;
//		String strVerifyCode=ValCodeArr[modValue];
//		Ai=Ai+strVerifyCode;
//		if(IDStr.length()==18){
//			if(Ai.equals(IDStr)==false) {
//				errorInfo="身份证无效，最后一位字母错误";
//				return errorInfo;
//			}
//		}else {
//			return null;
//		}
//		return null;
//	}
//
//	/**
//	 * 功能：设置地区编码
//	 * @return
//	 */
//	private static Hashtable GetAreaCode(){
//		Hashtable hashtable=new Hashtable();
//		hashtable.put("11","北京");
//		hashtable.put("12","天津");
//		hashtable.put("13","河北");
//		hashtable.put("14","山西");
//		hashtable.put("15","内蒙古");
//		hashtable.put("21","辽宁");
//		hashtable.put("22","吉林");
//		hashtable.put("23","黑龙江");
//		hashtable.put("31","上海");
//		hashtable.put("32","江苏");
//		hashtable.put("33","浙江");
//		hashtable.put("34","安徽");
//		hashtable.put("35","福建");
//		hashtable.put("36","江西");
//		hashtable.put("37","山东");
//		hashtable.put("41","河南");
//		hashtable.put("42","湖北");
//		hashtable.put("43","湖南");
//		hashtable.put("44","广东");
//		hashtable.put("45","广西");
//		hashtable.put("46","海南");
//		hashtable.put("50","重庆");
//		hashtable.put("51","四川");
//		hashtable.put("52","贵州");
//		hashtable.put("53","云南");
//		hashtable.put("54","西藏");
//		hashtable.put("61","陕西");
//		hashtable.put("62","甘肃");
//		hashtable.put("63","青海");
//		hashtable.put("64","宁夏");
//		hashtable.put("65","新疆");
//		hashtable.put("71","台湾");
//		hashtable.put("81","香港");
//		hashtable.put("82","澳门");
//		hashtable.put("91","国外");
//		return hashtable;
//	}
//
//	/**
//	 * 功能：判断字符串是否为数字
//	 * @param str
//	 * @return
//	 */
//	private static boolean isNumeric(String str){
//		Pattern pattern=Pattern.compile("[0-9]*");
//		Matcher isNum=pattern.matcher(str);
//		if(isNum.matches()) {
//			return true;
//		} else {
//			return false;
//		}
//	}
//
//	/**
//	 * 功能：判断字符串是否为日期格式
//	 * @param strDate
//	 * @return
//	 */
//	public static boolean isDate(String strDate){
//		Pattern pattern = Pattern.compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
//		Matcher m=pattern.matcher(strDate);
//		if(m.matches()){
//			return true;
//		} else {
//			return false;
//		}
//	}
//
//	/**
//	 * 功能:在判定已经是正确的身份证号码之后,查找出身份证所在地区
//	 * @param idCard
//	 * @return
//	 */
//	public static String GetArea(String idCard){
//		Hashtable<String,String> ht = GetAreaCode();
//		String area = ht.get(idCard.substring(0,2));
//		return area;
//	}
//
//	/**
//	 * 功能:在判定已经是正确的身份证号码之后,查找出此人性别
//	 * @param idCard
//	 * @return
//	 */
//	public static String GetSex(String idCard){
//		String sex = "";
//		if(idCard.length()==15)
//			sex = idCard.substring(idCard.length()-3,idCard.length());
//		if(idCard.length()==18)
//			sex = idCard.substring(idCard.length()-4,idCard.length()-1);
//		int sexNum = Integer.parseInt(sex)%2;
//		if(sexNum == 0){
//			return "女";
//		}else{
//			return "男";
//		}
//	}
//
//	/**
//	 * 功能:在判定已经是正确的身份证号码之后,查找出此人出生日期
//	 * @param idCard
//	 * @return
//	 */
//	public static String GetBirthday(String idCard){
//		String Ain = "";
//		if(idCard.length()==18){
//			Ain=idCard.substring(0,17);
//		} else if(idCard.length()==15) {
//			Ain=idCard.substring(0,6)+"19"+idCard.substring(6,15);
//		}
//		//================ 出生年月是否有效 ================
//		String strYear =Ain.substring(6 ,10);//年份
//		String strMonth=Ain.substring(10,12);//月份
//		String strDay =Ain.substring(12,14);//日期
//		return strYear+"-"+strMonth+"-"+strDay;
//	}

}
