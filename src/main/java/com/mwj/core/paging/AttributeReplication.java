package com.mwj.core.paging;

import javax.persistence.metamodel.SingularAttribute;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;

public class AttributeReplication {

    public static void copying(Object sourceObj, Object destObj, SingularAttribute... args) {
        Class clazz = sourceObj.getClass();
        for (SingularAttribute arg : args) {
            Member member = arg.getJavaMember();
            Object value = null;
            try {
                if (member instanceof Method) {
                    value = ((Method) member).invoke(sourceObj);
                } else if (member instanceof Field) {
                    value = ((Field) member).get(sourceObj);
                } else {
                    //throw new IllegalArgumentException("Unexpected java member type. Expecting method or field, found: " + member);
                    continue;
                }
                String fieldName = arg.getName();
                String firstLetter = fieldName.substring(0, 1).toUpperCase();
                String setMethodName = "set" + firstLetter + fieldName.substring(1);
                Method setMethod = clazz.getMethod(setMethodName, new Class[]{arg.getJavaType()});
                setMethod.invoke(destObj, value);
            } catch (IllegalAccessException  | InvocationTargetException |NoSuchMethodException  e ) {
                e.printStackTrace();
                continue;
            }
        }
    }
}
