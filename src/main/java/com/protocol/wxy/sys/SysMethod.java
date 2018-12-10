package com.protocol.wxy.sys;

import java.io.UnsupportedEncodingException;

public class SysMethod {
	
	/**
	 * 字符串拼接
	 * @param Strs
	 * @return
	 */
	public static String StrPlus(String... Strs) {

		StringBuilder Builder = new StringBuilder("");

		for (String str : Strs)
			Builder.append(str);

		return Builder.toString();
	}
	
	/**
	 * 解决中文传输乱码
	 * @param msg
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String Encode_GBK(String msg) throws UnsupportedEncodingException{
		byte [] array= msg.getBytes("GBK");		 
		return  new String(array,"ISO-8859-1");
	}
	
	
	/**
	 * 统计指定字符个数
	 * @param c
	 * @param str
	 * @return
	 */
	public static int  getCharCount(char c,String str){
		int count = 0;
		char s[] = str.toCharArray();
		for(int i = 0;i<s.length;i++){
			if (c==s[i]){
				count = count+1;
			}			
		}	
		return count;	
	}
	
	
	
	
	
	

    


    

    

   
   
   
   
    

}
