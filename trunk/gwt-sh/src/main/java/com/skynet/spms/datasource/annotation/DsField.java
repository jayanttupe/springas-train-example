package com.skynet.spms.datasource.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.skynet.spms.client.entity.DsFieldType;




@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DsField {
	
	boolean require() default false;	

	float order();
	
	int width() default 20;
	
	DsFieldType type() default DsFieldType.ANY;
	
	boolean hidden() default false;

	boolean editable() default false;
	
	String refBinField() default "";
}
