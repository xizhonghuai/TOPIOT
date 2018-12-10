package com.protocol.safemon.sys;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Conf {

	private Properties prop;

	public Conf(String filename) throws IOException {

		String path = "";

		path = Conf.class.getClassLoader().getResource(filename).getPath();

		FileInputStream fis = new FileInputStream(path);// 属性文件输入流
		prop = new Properties();// 属性集合对象
		prop.load(fis);// 将属性文件流装载到Properties对象中
		fis.close();// 关闭流
	}

	public String GetKeyValue(String key, String defaultValue) {

		return prop.getProperty(key, defaultValue);
	}

	public void SetKeyValue(String key, String value) {
		prop.setProperty(key, value);
	}

}
