package com.futeng.happypays.baowen.tool;

public class FieldParseUtil {
	
	public static class FieldContent{
		private String field99;
		private String field5F24;
		private String field5F34;
		private String field55;
		@Override
		public String toString() {
			return "field99:"+field99+"\tfield5F24:"+field5F24+"\tfield5F34:"+field5F34+"\n"+field55;
		}
		public String getField55() {
			return field55;
		}
		public void setField55(String field55) {
			this.field55 = field55;
		}
		public String getfield99() {
			return field99;
		}
		public void setfield99(String field99) {
			this.field99 = field99;
		}
		public String getfield5F24() {
			return field5F24;
		}
		public void setfield5F24(String field5f24) {
			field5F24 = field5f24;
		}
		public String getField5F34() {
			return field5F34;
		}
		public void setField5F34(String field5f34) {
			field5F34 = field5f34;
		}
	}
	
	public static FieldContent getField(String str){
		int star= str.indexOf("9908");
		String s= str.substring(star+2, star+4);
		String temp = "";
		FieldContent content=new FieldContent();
		try {
			String field99=str.substring(star+4,star+4+Integer.parseInt(s)*2);
			temp = str.replaceFirst("9908"+field99, "*");
			content.setfield99(field99);
		} catch (Exception e) {
			e.printStackTrace();
		}
		int star5f24 = str.indexOf("5F24");
		System.out.println("star5f24:"+star5f24);
		s= str.substring(star5f24+4, star5f24+6);
		System.out.println("5f24:"+s);
		String field5F24=str.substring(star5f24+6,star5f24+6+Integer.parseInt(s)*2);
		temp = temp.replaceFirst("5F24[0-9]{2}"+field5F24, "*");
		int strat5f34 = str.indexOf("5F34");
		s= str.substring(strat5f34+4, strat5f34+6);
		String field5F34=str.substring(strat5f34+6,strat5f34+6+Integer.parseInt(s)*2);
		temp = temp.replaceFirst("5F34[0-9]{2}"+field5F34, "*");
		temp=temp.replaceAll("F+$", "").replace("*", "");
		System.out.println(temp);
		content.setField55(temp);
		content.setfield5F24(field5F24);
		content.setField5F34(field5F34);
		return content;
	}
	public static FieldContent getField1(String str){
//		int star= str.indexOf("99");
		int star = str.lastIndexOf("99");
		String s= str.substring(star+2, star+4);
		System.out.println(s);
		String temp = "";
		FieldContent content=new FieldContent();
		try {
			String field99=str.substring(star+4,star+4+Integer.parseInt(s)*2);
			temp = str.replaceFirst("99[0-9]{2}"+field99, "*");
			content.setfield99(field99);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		int star5f24 = str.indexOf("5F24");
		System.out.println(star5f24);
		s= str.substring(star5f24+4, star5f24+6);
		System.out.println("5f24:"+s);
		String field5F24=str.substring(star5f24+6,star5f24+6+Integer.parseInt(s)*2);
		temp = temp.replaceFirst("5F24[0-9]{2}"+field5F24, "*");
		int strat5f34 = str.indexOf("5F34");
		s= str.substring(strat5f34+4, strat5f34+6);
		String field5F34=str.substring(strat5f34+6,strat5f34+6+Integer.parseInt(s)*2);
		temp = temp.replaceFirst("5F34[0-9]{2}"+field5F34, "*");
		temp=temp.replaceAll("F+$", "").replace("*", "");
		System.out.println(temp);
		content.setField55(temp);
		content.setfield5F24(field5F24);
		content.setField5F34(field5F34);
		return content;
	}
	
	/**
	 * Main方法
	 * @param args
	 */
	public static void main(String[] args) {
//		String encOnlineMessage1 = "9F2608FA78F7A71069149C9F2701809F101307010103A0A800010A0100000000005C10BAD09F3704695859F69F36020029950580A004E8009A030000009C01009F02060000000010005F2A02084082027C009F1A0201569F03060000000000009F330360F8009F34030203009F3501219F1E0831323334353637388408A0000003330101019F090200019F4104000486075F340100990806354E06FFFEE6995F2403221231FFFFFF".toUpperCase();	
		String encOnlineMessage1 = "9F26080582D08908286E349F2701809F101307080103A00000010A010000000000FA487F9B9F3704ABEB62D69F3602005F950500000000009A031704259C01009F02060000000001235F2A02015682027C009F1A0201569F03060000000000009F3303E0E1C89F34033F00009F3501229F1E0830303030303034388408A0000003330101019F0902008C9F410400000000".toUpperCase();
		FieldContent fc = FieldParseUtil.getField(encOnlineMessage1);
		String c14 = fc.getfield5F24().substring(0,4);//卡有效期
		String c23 = fc.getField5F34();//IC卡序列号
		String c55 = fc.getField55();
		String c52= fc.getfield99();//密码
		System.out.println("c14 :" + c14);
		System.out.println("c23 :" + c23);
		System.out.println("c55 :" + c55);
		System.out.println("passinfo :" + c52);
	}
}
