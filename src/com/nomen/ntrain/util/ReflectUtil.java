package com.nomen.ntrain.util;

/**
 * 反射类
 * @author 连金亮
 * @date 2011-05-20
 */
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ReflectUtil 
{
	private static final Log logger = LogFactory.getLog(ReflectUtil.class);

	@SuppressWarnings("unchecked")
	public static void setFieldValue(Object target, String fname, Class ftype,Object fvalue) {
		if (target == null || fname == null || "".equals(fname) 
				|| (fvalue != null && !ftype.isAssignableFrom(fvalue.getClass()))) {
			return;
		}
		Class clazz = target.getClass();
		try {
			Method method = clazz.getDeclaredMethod("set"
					+ Character.toUpperCase(fname.charAt(0))
					+ fname.substring(1), ftype);
			if (!Modifier.isPublic(method.getModifiers())) {
				method.setAccessible(true);
			}
			method.invoke(target, fvalue);
        } catch (Exception ex) {
        	if (logger.isDebugEnabled()) {
        		logger.debug(ex);
        	}
        	try {
        		Field field = clazz.getDeclaredField(fname);
        		if (!Modifier.isPublic(field.getModifiers())) {
        			field.setAccessible(true);
        		}
        		field.set(target, fvalue);
        	} catch (Exception e) {
        		if (logger.isDebugEnabled()) {
        			logger.debug(e);
        		}
        	}
        }
    }
	
	public static Object getFieldValue(Object target, String fname)
	{
		Object reslut = null;
		if (target == null || fname == null || "".equals(fname)) {
			return null;
		}
		Class clazz = target.getClass();
		try {
			Field field = clazz.getDeclaredField(fname);
			reslut =  field.get(fname);
		} catch (Exception ex) {
			if (logger.isDebugEnabled()) {
				logger.debug(ex);
			}
		}
		return reslut;
	}
}
