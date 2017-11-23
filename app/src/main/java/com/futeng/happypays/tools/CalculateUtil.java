package com.futeng.happypays.tools;

import java.util.LinkedList;

/**
 * 计算器工具类
 * @author king
 */
public class CalculateUtil  {

	 // 保存数据链
    private static LinkedList<String> datalist =new LinkedList<String>();

    static boolean isPressEqualButton = false;



    public static void main(String[] args) {
    		System.out.println(execNumber(1+""));
    		System.out.println(execNumber("."));
    		System.out.println(execNumber("5"));
    		System.out.println(execOperation("+"));
    		System.out.println(execNumber(2+""));
    		System.out.println(execNumber("."));
    		System.out.println(execNumber("5"));
    		System.out.println(execOperation("+"));
    		System.out.println(execNumber("0"));
    		System.out.println(execNumber("."));
    		System.out.println(execNumber("5"));
    		System.out.println(execResult());
    		System.out.println(datalist.size());
	}

    public static void clear(){
        datalist.clear();// 清空列表数据
        isPressEqualButton=false;
    }
    public static String execNumber(String num){
        // 列表为空的情况,加数到链表中，并设置等号键为false
        if (datalist.size() == 0) {
            if (".".equals(num)){
                num = "0.";
            }
            datalist.add("" + num);
            isPressEqualButton = false;
            return num;
        }
        // 如果列表长度为1,取数合并结果
        else if ((datalist.size() == 1) && (isPressEqualButton == false))// 说明还是第一个数据中的
        {
            if(".".equals(num)){
                String data1 = datalist.get(0);
                if(data1.indexOf(".") > -1){  //说明有.
                    return data1;
                }
            }


            String str = datalist.getFirst();
            if (str.equals("0"))// 清除以0开头的数字
                str = "";
            String str0 = str.concat(String.valueOf(num));
            if(".".equals(str0)){
            	str0="0.";
            }
            // 覆盖列表中第一元素的内容
            datalist.set(0,str0);
            System.out.println("2size:"+datalist.size());
            return str0;
        } else if ((datalist.size() == 1) && (isPressEqualButton == true))// 表示已经是另外一个数据，上一个作废
        {
            if (".".equals(num)){
                num = "0.";
            }
            datalist.set(0, String.valueOf(num));// 覆盖
            isPressEqualButton = false;// 还原
            return "" + num;
        }
        // 列表为2，则加入第二个操作数
        else if (datalist.size() == 2) {
            if (".".equals(num)){
                num = "0.";
            }
            datalist.add("" + num);
            return "" + num;
        }
        // 列表为3，表示继续输入的数据为第二操作数
        else if (datalist.size() == 3) {
            String str = datalist.getLast();
            if (str.equals("0"))
                str = "";
            String strLast = str.concat("" + num);
            if("0".equals(strLast)){
            	strLast = "0.";
            }
            // 设置第二操作数的更新
            datalist.set(2, strLast);
            return strLast;
        }
        
        return "";
    }
    public  static String execOpera(String sign){
        String result =  (Double.parseDouble("0") - Double.parseDouble(sign))+"";
        datalist.clear();
        isPressEqualButton = false;
        datalist.add(result);
        return result;
    }

    public  static String execOperabaifen(double sign){
        double baifenvalue=sign/100;
        datalist.clear();
        isPressEqualButton = false;
        datalist.add(String.valueOf(baifenvalue));
        return String.valueOf(baifenvalue);
    }


    public  static String  execOperation(String sign) {//"+" "-"  "*"  "/"
        // 根据列表中元素的个数判断增加,列表元素为1时
        if (datalist.size() == 1) {
            datalist.add(sign);// 追加操作符
        }
        // 列表中元素为2，说明要替换现有的操作符
        else if (datalist.size() == 2) {
            datalist.set(1, sign);
        }
        // 列表中元素为3时，则取出前两个进行计算
        else if (datalist.size() == 3) {
        	String result = execResult();
            isPressEqualButton = false;
            datalist.add(sign);
            return result;// 调用此方法执行，等号键为false
        }
        return "";
    }
    
    public static String execResult() {
        isPressEqualButton = true;
        // 当列表长度为1或者2时，说明不能计算，保持数据不变
        if (datalist.size() > 0 && datalist.size() < 3) {
            String str = datalist.getFirst();
            datalist.clear();// 清空列表数据
            datalist.add(str);// 加入这个结果
            return "" + Double.parseDouble(str);// 去除小数点后面多余追加的零
        } else if (datalist.size() == 3) {
            double result = 0.0d;
            int temp = 0;// 去掉结果为整数时所带的小数点
            String num1 = datalist.getFirst();
            String op = datalist.get(1);
            String num2 = datalist.getLast();
            if (op.equals("+")) {// +++++++++++++++
                result = Double.parseDouble(num1) + Double.parseDouble(num2);
            } else if (op.equals("-")) {// ---------------
                result = Double.parseDouble(num1) - Double.parseDouble(num2);
            } else if (op.equals("*")) {// **************
                result = Double.parseDouble(num1) * Double.parseDouble(num2);
            } else if (op.equals("/")) {//////////////////
                result = Double.parseDouble(num1) / Double.parseDouble(num2);
            }
            datalist.clear();// 清空
            datalist.add("" + result);// 使得结果加入列表中
            temp = (int) result;
            if (Double.isInfinite(result) == true)// 判断结果是否除数为零
            {
                datalist.clear();// 再次清空列表数据
                return "除数不能为零";
            } else if (Double.isNaN(result) == true) {
                datalist.clear();// 再次清空列表数据
                return "结果未定义";
            } else if ((result - temp) == 0)
            	return "" + temp;
            else
            	return "" + result;
        }
		return "";
    }
}
