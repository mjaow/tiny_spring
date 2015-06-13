package org.loda.ioc.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @ClassName: RegExUtil
 * @Description: 正则表达式相关的工具类
 * @author minjun
 * @date 2015年6月12日 上午10:47:35
 * 
 */
public class RegExUtil {

	/**
	 * 
	 * @Title: match
	 * @Description: 给定字符串target是否符合正则表达式regex的格式
	 * @param @param regex
	 * @param @param target
	 * @param @return 设定文件
	 * @return boolean 返回类型
	 * @throws
	 */
	public static boolean match(String regex, String target) {
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(target);
		return m.matches();
	}
	
	public static void main(String[] args) {
		String reg="com.*";
		
		String target="com.loda.cn";

		System.out.println(match(reg, target));
	}

}
