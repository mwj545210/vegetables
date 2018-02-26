package com.mwj.core.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 根据正则表达式验证
 * 汉字，邮箱，字母，数字
 * @author x001393
 *
 */
public class Verification {
	//由字母和数字组成
    public static final Pattern p = Pattern.compile("[a-zA-Z]+[0-9]+");
    public static final Pattern pp = Pattern.compile("[0-9]+[a-zA-Z]+");
    //验证邮箱
    public static final Pattern p1 = Pattern.compile("[a-zA-Z0-9@.]+");
    //验证手机
    public static final Pattern p2 = Pattern.compile("^[1][3,5,7,8]+\\d{9}");
    //验证汉字
    public static final Pattern p3 = Pattern.compile("^[\u4e00-\u9fa5]+$");
    //验证数字
    public static final Pattern p4 = Pattern.compile("[0-9]+");
    //验证字母
    public static final Pattern p5 = Pattern.compile("[a-zA-Z]+");
    //验证字母或数字组成
    public static final Pattern p6 = Pattern.compile("[a-zA-Z0-9]+");
    //大小写转换
    public static final Pattern p7 = Pattern.compile("[a-z0-9]+");
    //大小写转换
    public static final Pattern p8 = Pattern.compile("[A-Z0-9]+");
    /**
     * 去除空格
     */
    public static String removalSpace(String str) {
		return str.replaceAll("\\s+","");
	}
    
    /**
	 * 验证由数字和字母组成
	 */
	public static boolean checkLetterAndNumber(String str) {
		Matcher m = p.matcher(str);
		Matcher m1 = pp.matcher(str);
		return m.matches()||m1.matches();
	}
    
    /**
	 * 验证邮箱
	 */
	public static boolean checkEmail(String str) {
		Matcher m = p1.matcher(str);
		return m.matches();
	}
    
    /**
     * 验证手机
     */
	public static boolean checkMobile(String str) {
		Matcher m = p2.matcher(str);
		return m.matches();
	}

	/**
	 * 验证汉字
	 */
	public static boolean checkChineseCharacters(String str) {
		Matcher m = p3.matcher(str);
		return m.matches();
	}

	/**
	 * 验证数字
	 * @param str
	 * @return
	 */
	public static boolean checkNumber(String str) {
		Matcher m = p4.matcher(str);
		return m.matches();
	}

	/**
	 * 验证字母
	 * @param str
	 * @return
	 */
	public static boolean checkLetter(String str) {
		Matcher m = p5.matcher(str);
		return m.matches();
	}
	
	/**
	 * 验证字母或数字组成
	 */
	public static boolean checkLetterOrNumber(String str) {
		Matcher m = p6.matcher(str);
		return m.matches();
	}
	
	/**
	 * 大小写转换
	 */
	public static String caseConversion(String str) {
		Matcher m = p7.matcher(str);
		Matcher m1 = p8.matcher(str);
		if(m.matches()){
			return str.toUpperCase();
		}
		if(m1.matches()){
			return str.toLowerCase();
		}
		return str;
	}

    public static String toUpperCase(String str) {
        return str.toUpperCase();
    }

    public static String toLowerCase(String str) {
        return str.toLowerCase();
    }



}
