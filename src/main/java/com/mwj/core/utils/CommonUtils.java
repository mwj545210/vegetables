package com.mwj.core.utils;

import java.util.Random;

public class CommonUtils {
	
	
	private static Random randGen = null;
	
	private static char[] numbersAndLetters = null;
	
	
	/**
	 * 产生随机字符串
	 * @param length 长度
	 * @param rule 规则 LOWER：全小写 ，UPPER：全大写 ，IGNORE：不区分大小写
	 * @return
	 */
	public static final String randomString(int length,RANDRULE rule) {
	         if (length < 1) {
	             return null;
	         }
	         if (randGen == null) {
	                randGen = new Random();
	                numbersAndLetters = ("0123456789abcdefghijklmnopqrstuvwxyz" +
	                   "ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();
	                 }
	         char [] randBuffer = new char[length];
	         if(rule.equals(RANDRULE.RAND_NUMBER)){
	        	 for (int i=0; i<randBuffer.length; i++) {
		             randBuffer[i] = numbersAndLetters[randGen.nextInt(9)];
		         }	
	         }else{
	        	 for (int i=0; i<randBuffer.length; i++) {
		             randBuffer[i] = numbersAndLetters[randGen.nextInt(61)];
		         }	 
	         }
	         String res=new String(randBuffer);
	         System.out.println("res:"+res);
	         if(rule.equals(RANDRULE.RAND_LOWER)){
	        	 res=res.toLowerCase();
	         }else if(rule.equals(RANDRULE.RAND_UPPER)){
	        	 res=res.toUpperCase();
	         }
	         return res;
	}
	
	public enum RANDRULE{
		RAND_NUMBER,RAND_LOWER,RAND_UPPER,RAND_IGNORE
	}
}

