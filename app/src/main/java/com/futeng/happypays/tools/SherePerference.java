package com.futeng.happypays.tools;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * 
 * @author pengxiaolog
 * 
 * 轻量级数据存储
 *
 */
public class SherePerference {

    
    public static SherePerference share;
  
    public SharedPreferences dataBase;
    
    
    public SherePerference() {
		// TODO Auto-generated constructor stub
	}
    
    public static SherePerference getUntilIntence()
    {
        if (share == null)
        {
            return share = new SherePerference();
        }
        
        return share;
    }
    
    //==========================================保存是否是第一次安装应用==================
    
    public void saveIsFristLogin(Context context, boolean isfrist)
    {
        dataBase = context.getSharedPreferences("Wellcome", 0);
        dataBase.edit().putBoolean("isFrist", isfrist).commit();
        
    }
    
    public boolean getIsFristLogin(Context context)
    {
    	 dataBase = context.getSharedPreferences("Wellcome", 0);
    	
         return dataBase.getBoolean("isFrist", false);
    	
    }
    
	/**
	 * 保存登陆信息
	 * 
	 * @param context
	 * @param
	 */
	public static void savelogin(Context context, String userName, String userPw) {
		SharedPreferences sp = context.getSharedPreferences("login",
				Activity.MODE_PRIVATE);
		SharedPreferences.Editor mEdit1 = sp.edit();
		mEdit1.putString("name", StringUtils.ebotongEncrypto(userName));
		mEdit1.putString("pw", StringUtils.ebotongEncrypto(userPw));
		mEdit1.commit();
	}

	/**
	 * 得到登陆用户
	 * 
	 * @param context
	 * @param
	 */
	public static String getloginUser(Context context) {
		SharedPreferences sp = context.getSharedPreferences("login",
				Activity.MODE_PRIVATE);
		if (sp.getString("name", "").equals("")) {
			return "";
		}
		return StringUtils.ebotongDecrypto(sp.getString("name", ""));

	}

	/**
	 * 得到登陆密码
	 * 
	 * @param context
	 * @param
	 */
	public static String getloginPw(Context context) {
		SharedPreferences sp = context.getSharedPreferences("login",
				Activity.MODE_PRIVATE);
		if (sp.getString("pw", "").equals("")) {
			return "";
		}
		return StringUtils.ebotongDecrypto(sp.getString("pw", Config.URL));

	}
	
	
	/**
	 * 保存登陆IP
	 * 
	 * @param context
	 * @param
	 */
	public static void saveIP(Context context, String IP) {
		SharedPreferences sp = context.getSharedPreferences("IPSET",
				Activity.MODE_PRIVATE);
		SharedPreferences.Editor mEdit1 = sp.edit();
		mEdit1.putString("IP", IP);
		mEdit1.commit();
	}
	
	/**
	 * get登陆IP
	 * 
	 * @param context
	 * @param
	 */
	public static String getIP(Context context) {
		SharedPreferences sp = context.getSharedPreferences("IPSET",
				Activity.MODE_PRIVATE);
		return sp.getString("IP", Config.URL);
	}
}
