package com.skynet.spms.schedule;

import java.lang.reflect.InvocationTargetException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.support.ArgumentConvertingMethodInvoker;
import org.springframework.util.ClassUtils;

public class JobMethodInvokingRunnable extends ArgumentConvertingMethodInvoker
		implements Runnable, BeanClassLoaderAware, InitializingBean {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	private ClassLoader beanClassLoader = ClassUtils.getDefaultClassLoader();
	
	private boolean sign=true;
		
	public void stop(){
		sign=false;
	}
	
	public void start(){
		sign=true;
	}
	
	public boolean isRun(){
		return sign;
	}

	@Override
	public void setBeanClassLoader(ClassLoader classLoader) {
		this.beanClassLoader = classLoader;
	}

	@Override
	protected Class resolveClassName(String className)
			throws ClassNotFoundException {
		return ClassUtils.forName(className, this.beanClassLoader);
	}

	@Override
	public void afterPropertiesSet() throws ClassNotFoundException,
			NoSuchMethodException {
		prepare();
	}

	@Override
	public void run() {
		
		if(!sign){
			return;
		}
		
		try {
			invoke();
		} catch (InvocationTargetException ex) {
			logger.error(getInvocationFailureMessage(), ex);
			// Do not throw exception, else the main loop of the Timer will
			// stop!
		} catch (Throwable ex) {
			logger.error(getInvocationFailureMessage(), ex);
			// Do not throw exception, else the main loop of the Timer will
			// stop!
		}
	}

	private String getInvocationFailureMessage() {
		return "Invocation of method '" + getTargetMethod()
				+ "' on target object [" + getTargetObject() + "] failed";
	}

}
