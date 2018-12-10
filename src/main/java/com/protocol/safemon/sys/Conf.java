package com.protocol.safemon.sys;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Conf {

	private Properties prop;

	public Conf(String filename) throws IOException {

		String path = "";

		path = Conf.class.getClassLoader().getResource(filename).getPath();

		FileInputStream fis = new FileInputStream(path);// �����ļ�������
		prop = new Properties();// ���Լ��϶���
		prop.load(fis);// �������ļ���װ�ص�Properties������
		fis.close();// �ر���
	}

	public String GetKeyValue(String key, String defaultValue) {

		return prop.getProperty(key, defaultValue);
	}

	public void SetKeyValue(String key, String value) {
		prop.setProperty(key, value);
	}

}
