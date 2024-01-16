package com.exception.util;

/** 
* @author 作者 Your-Name: 
* @version 创建时间：2021年5月24日 上午10:50:46 
* 类说明 
*/
public class StrUtil {
	
	//判断字符串是否为空或者是空串
	public static boolean notEmpty(String str) {
		if(str == null || "".equals(str)) {
			return false;
		}
		return true;
	}
	
	/*
	 * 进行字符串拼接，只针对的是sql语句，这个是特别定制的
	 */
	public static StringBuffer splicingStrs(int len ,StringBuffer strBuf, String...strings) {
		for(int i = 0; i < len; i++) {
			if(i != len-1)
				strBuf.append(strings[i] + " and ");
			else 
				strBuf.append(strings[i]);
		}
		return strBuf;
	}
	
}
