package com.skynet.common.aop.message;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MsgDriven {

	String selection() default "";
	
	String session();
	
	SessionType type() default SessionType.Topic;
		
}
