package com.skynet.spms.schedule;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ScheduledJob {
	
	
	String cron() default "";
	
	long fixedRate() default -1;
	
	TimeUnit timeUnit() default TimeUnit.SECONDS;

	String jobName();
}
