package com.skynet.spms.aop.event;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.skynet.spms.event.SpmsEventType;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EventBind {
	
	String log() default "info";
	
	String name();	
	
	SpmsEventType type();
	
	String sendTo()default "everyone";
}
