package com.skynet.spms.tools;

import java.lang.reflect.Method;
import javax.persistence.Column;

public class AnnotationUtil {

	public static String getColumnName(Class clazz, String propertyName)
	{
		try {
			String methodName = "get" + propertyName.substring(0, 1).toUpperCase() +
				propertyName.substring(1, propertyName.length());
			Method method = clazz.getMethod(methodName);
			if (method.isAnnotationPresent(Column.class)) {
				Column annotation = method.getAnnotation(Column.class);
				return annotation.name();
			} else {
				return "";
			}
		} catch (SecurityException e) {
			e.printStackTrace();
			return "";
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			return "";
		}
	}
}
