package com.futeng.happypays.baowen.tool;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;


public class PropertiesUtil {
	
	private  File file = new File("properties.properties");
	private  String filename = "properties.properties";
	
	private  Properties properties=new Properties();
	
	public PropertiesUtil(String filename){
		this.filename=filename;
		file = new File(filename);		
	}
	
	public PropertiesUtil() {
		super();
	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	public  String getValue(String key){		
		String value=null;
		String filepath=null;
		try {
			if (filename==null) {
				filepath=java.net.URLDecoder.decode(getClass().getResource("/"+filename).getFile(),"utf-8");
			}else{
				filepath=filename;
			}
			InputStream inputStream=new FileInputStream(new File(filepath));
			properties.load(inputStream);
			value=properties.getProperty(key);
			inputStream.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return value;
	}
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public  Map<String,String> getValues() throws Exception{
		Map<String,String> values=new TreeMap<String, String>();
		String filepath=java.net.URLDecoder.decode(getClass().getResource("/"+filename).getFile(),"utf-8");		
		InputStream inputStream=new FileInputStream(new File(filepath));
		properties.load(inputStream);
		Enumeration<String> names  =(Enumeration<String>) properties.propertyNames();
		while (names.hasMoreElements()) {
			String name=names.nextElement();
			values.put(name,properties.getProperty(name));
		}
		inputStream.close();		
		return values;
	}
	public  void appendValue(String key,String value) throws Exception{
		Properties prop = new Properties();  
        InputStream fis = null;  
        OutputStream fos = null;  
        try {            
            String filepath=java.net.URLDecoder.decode(getClass().getResource("/"+filename).getFile(),"utf-8");            
            File file = new File(filepath);  
            fis = new FileInputStream(file);  
            prop.load(fis);  
            fis.close();//一定要在修改值之前关闭fis  
            fos = new FileOutputStream(file);  
            prop.setProperty(key, value);  
            System.out.println("修改"+key+"||"+value);
            prop.store(fos, "Update '" + key + " value");
            fos.flush();
            System.out.println("修改完毕");
        } catch (IOException e) {  
           e.printStackTrace();
        } 
        finally{  
            try {  
				fos.close();
                fis.close();  
            } catch (IOException e) {  
                // TODO Auto-generated catch block  
                e.printStackTrace();  
            }  
        }  
	}
	public  void appendValues(Map<String,String> values) throws Exception{
		Properties prop = new Properties();  
        InputStream fis = null;  
        OutputStream fos = null;  
        try {            
            String filepath=java.net.URLDecoder.decode(getClass().getResource("/"+filename).getFile(),"utf-8");            
            File file = new File(filepath);  
            fis = new FileInputStream(file);  
            prop.load(fis);  
            fis.close();//一定要在修改值之前关闭fis  
            fos = new FileOutputStream(file);
            Set<String> keys=values.keySet();
            for (String key : keys) {
            	prop.setProperty(key, values.get(key));  
            	prop.store(fos, "Update '" + key + " value");
			}            
            fos.flush();
            fos.close();
            System.out.println("修改完毕");
              
        } catch (IOException e) {  
           e.printStackTrace();
        } 
        finally{  
            try {  
                fos.close();  
                fis.close();  
            } catch (IOException e) {  
                // TODO Auto-generated catch block  
                e.printStackTrace();  
            }  
        }  
	}
		
	public String getFilename() {
		
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	
}
