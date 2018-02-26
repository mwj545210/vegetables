package com.mwj.core.utils;

import org.apache.commons.lang.StringUtils;

/***
 * 转换html标签符号
 */
public class HtmlEscape {

	public static String decodeHtml(String html) {
		if(StringUtils.isNotBlank(html)){
			html = html.replaceAll("&amp;", "&").replaceAll("&lt;", "<").replaceAll("&gt;", ">")
					.replaceAll("&apos;", "\'").replaceAll("&quot;", "\"").replaceAll("&nbsp;", " ")
					.replaceAll("&copy;", "@").replaceAll("&reg;", "?");
		}
		return html;
	}

	public static String encodeHtml(String html) {
		if(StringUtils.isNotBlank(html)){
			html = html.replaceAll("&","&amp;").replaceAll("<","&lt;").replaceAll(">","&gt;")
					.replaceAll("\'","&apos;").replaceAll("\"","&quot;").replaceAll(" ","&nbsp;")
					.replaceAll( "@","&copy;").replaceAll("\\?","&reg;");
		}
		return html;
	}

	public static void main(String[] args) {
		String html="<img alt=\"\" src=\"../data/pics/commodity/imagebrowser/aaa.jpg?\" />"
				+ "<img alt=\"\" src=\"../data/pics/commodity/imagebrowser/bbb.jpg\" />sadsads";
		String str = encodeHtml(html);
		System.out.println(str);

		String str1 = decodeHtml(str);
		System.out.println(str1);
	}
}
