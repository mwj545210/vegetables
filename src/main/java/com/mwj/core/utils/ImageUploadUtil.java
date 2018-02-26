package com.mwj.core.utils;


import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/***
 * 图片上传工具类
 */
public class ImageUploadUtil {

	private static Logger logger = Logger.getLogger(ImageUploadUtil.class);

    /**
	 * 上传图片
	 *
	 * @param path
	 * @param file
	 * @param request
	 */
	public static String uploadImage(String path, CommonsMultipartFile file, HttpServletRequest request) {
		String contextPath = request.getServletContext().getRealPath(path); // 获取本地存储路径
		if(file.getSize()==0){
			return null;
		}
		if(StringUtils.isEmpty(contextPath)){
			contextPath = request.getServletContext().getRealPath("/")+path;
		}
		File fileDir = new File(contextPath);
		if (!fileDir.exists()) {
			fileDir.mkdirs();
		}
		String name = file.getFileItem().getName();
		String suffix = name.substring(name.lastIndexOf("."));
		String fileName = System.currentTimeMillis() + suffix;
		String originfileName = ImageConstans.FALG_RESOURCE + fileName;
		String smallfileName = originfileName + ImageConstans.FALG_SMALL_SUFFIX;
		String middlefileName = originfileName+ ImageConstans.FALG_MIDDLE_SUFFIX;
		String bigfileName = originfileName + ImageConstans.FALG_BIG_SUFFIX;

		//原图片相对路径
		String accessPath = path + "/" +  originfileName;
//		//原图片绝对路径
//		String physicPath = contextPath + "/" + originfileName;
		// 新建一个文件
		File newFile = new File(contextPath + "/" + originfileName);
		try {

			file.getFileItem().write(newFile);
			ImageConverter converter = new ImageConverter(newFile);
//			converter.compressJpg(80, 80, suffix.substring(1), contextPath
//					+ "/" + smallfileName);
//			converter.compressJpg(220, 220, suffix.substring(1), contextPath
//					+ "/" + middlefileName);
//			converter.compressJpg(600, 600, suffix.substring(1), contextPath
//					+ "/" + bigfileName);


			return accessPath;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * 大图
	 * @param defalutPicPath
	 * @return
	 */
	public static String getBigImagePath(String defalutPicPath){
		return getImagePath(defalutPicPath, ImageConstans.FALG_BIG);
	}

	/**
	 * 中图
	 * @param defalutPicPath
	 * @return
	 */
	public static String getMiddleImagePath(String defalutPicPath){
		return getImagePath(defalutPicPath, ImageConstans.FALG_MIDDLE);
	}

	/**
	 * 小图
	 * @param defalutPicPath
	 * @return
	 */
	public static String getSmallImagePath(String defalutPicPath){
		return getImagePath(defalutPicPath, ImageConstans.FALG_SMALL);
	}

	/**
	 * 原图
	 * @param defalutPicPath
	 * @return
	 */
	public static String getResourceImagePath(String defalutPicPath){
		return getImagePath(defalutPicPath, ImageConstans.FALG_RESOURCE);
	}

	private static String getImagePath(String defalutPicPath, String flag){
		if(StringUtils.isNotBlank(defalutPicPath)){
			int idx = defalutPicPath.lastIndexOf("/");
			String path = defalutPicPath.substring(0, idx+1);
			String name = defalutPicPath.substring(idx+1);
			String newImagePath = path + flag + name.substring(2);
			return newImagePath;
		}else{
			return "";
		}
	}

	/***
	 * 去除字符串中的制表符号
	 * @param htmlStr
	 * @return
	 */
	public static List<String> getImgStr(String htmlStr) {
		String img = "";
		Pattern p_image;
		Matcher m_image;
		List<String> pics = new ArrayList<String>();
		String regEx_img = "]*?>";
		p_image = Pattern.compile(regEx_img, Pattern.CASE_INSENSITIVE);
		m_image = p_image.matcher(htmlStr);
		while (m_image.find()) {
			img = img + "," + m_image.group();
			Matcher m = Pattern.compile("src\\s*=\\s*\"?(.*?)(\"|>|\\s+)")
					.matcher(img);
			while (m.find()) {
				pics.add(m.group(1));
			}
		}
		return pics;
	}

	//	public static void main(String[] args) {
//		String s = getMiddleImagePath("data/pic/product/1010/b_1221212.jpg");
//		System.out.println(s);
//	}

}
