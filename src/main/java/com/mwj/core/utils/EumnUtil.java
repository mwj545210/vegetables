package com.mwj.core.utils;

/**
 * Created by gonglei on 2015/4/30.
 */
public class EumnUtil {

    public static Object parseEumn(Class ref, String enumName) {
        return Enum.valueOf(ref, enumName);
    }
}