package com.mwj.core.utils;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FilenameFilter;

public class ImageFilter implements FilenameFilter {
	
	private String partten;
	
	public ImageFilter() {
	}
	
	public ImageFilter(String partten) {
		this.partten = partten;
	}

	public boolean isGif(String file) {
		if (file.toLowerCase().endsWith(".gif")) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isJpg(String file) {
		if (file.toLowerCase().endsWith(".jpg")) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isPng(String file) {
		if (file.toLowerCase().endsWith(".png")) {
			return true;
		} else {
			return false;
		}
	}

	public boolean accept(File dir, String fname) {
		if(StringUtils.isNotBlank(partten)){
			if(fname.contains(partten)){
				return (isGif(fname) || isJpg(fname) || isPng(fname));
			}else{
				return false;
			}
		}else{
			return (isGif(fname) || isJpg(fname) || isPng(fname));
		}
		
		

	}

}
