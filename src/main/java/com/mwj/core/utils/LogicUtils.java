package com.mwj.core.utils;

import java.util.Collection;
import java.util.Map;

/**
 * 
 * @author Aidy_He
 *
 */
public class LogicUtils {
	
	public static final int ZERO=0;
	
	public static final String EMPTY_STRING="";
	
    public static boolean isNullOrEmpty(Collection<?> collection) {
        if (null == collection || ZERO == collection.size()) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isNotNullAndEmpty(Collection<?> collection) {
        return !isNullOrEmpty(collection);
    }

    public static boolean isNullOrEmpty(Map<?, ?> map) {
        if (null == map || ZERO == map.size()) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isNotNullAndEmpty(Map<?, ?> map) {
        return !isNullOrEmpty(map);
    }

    public static boolean isNullOrEmpty(Object[] objects) {
        if (null == objects || ZERO == objects.length) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isNotNullAndEmpty(Object[] objects) {
        return !isNullOrEmpty(objects);
    }

    public static boolean isNull(Object object) {
        if (null == object) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isNotNull(Object Object) {
        return !isNull(Object);
    }

    public static boolean isNullOrEmpty(String subject) {
        if (null == subject || EMPTY_STRING.equals(subject)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isNotNullAndEmpty(String subject) {
        return !isNullOrEmpty(subject);
    }

}
