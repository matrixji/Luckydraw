package com.luckydraw.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigRW {
	public String getProperty(String key) {
		Properties prop = new Properties();// 属性集合对象
		try
		{
	        FileInputStream fis = new FileInputStream(Constant.PATH_OF_PROGRAM_RESTORE);// 属性文件输入流
	        prop.load(fis);// 将属性文件流装载到Properties对象中
	        fis.close();// 关闭流
	        return prop.getProperty(key);
		}
		catch( FileNotFoundException e )
        {
            e.printStackTrace();
        }
        catch( IOException e )
        {
            e.printStackTrace();
        }
		return "";
	}

	public boolean setProperty(String key, String value) {
		Properties prop = new Properties();// 属性集合对象
		try
		{
			FileInputStream fis = new FileInputStream(Constant.PATH_OF_PROGRAM_RESTORE);// 属性文件输入流
	        prop.load(fis);// 将属性文件流装载到Properties对象中
	        fis.close();// 关闭流
	        prop.setProperty(key, value);
			FileOutputStream fos = new FileOutputStream(Constant.PATH_OF_PROGRAM_RESTORE);// 属性文件输入流
			prop.store(fos, "Copyright (c) Lottery");
	        fos.close();// 关闭流
	        return true;
		}
		catch( FileNotFoundException e )
        {
            e.printStackTrace();
        }
        catch( IOException e )
        {
            e.printStackTrace();
        }
		return false;
	}
}
