package com.skynet.spms.schedule;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.ReflectionUtils.MethodCallback;
import org.springframework.util.StringValueResolver;


public class ScheduleWideAnnoBeanPostProcessor implements BeanPostProcessor,
		Ordered,  DisposableBean {


	@Autowired
	private ScheduleService registrar ;

	public int getOrder() {
		return LOWEST_PRECEDENCE;
	}

	public Object postProcessBeforeInitialization(Object bean, String beanName) {
		return bean;
	}

	public Object postProcessAfterInitialization(final Object bean,
			String beanName) {
		Class<?> targetClass = AopUtils.getTargetClass(bean);
		ReflectionUtils.doWithMethods(targetClass, new MethodCallback() {
			public void doWith(Method method) throws IllegalArgumentException,
					IllegalAccessException {
				ScheduledJob annotation = AnnotationUtils.getAnnotation(method,
						ScheduledJob.class);
				if (annotation != null) {

					Assert.isTrue(method.getParameterTypes().length == 0,
							"Only no-arg methods may be annotated with @Scheduled.");
															
					JobMethodInvokingRunnable runnable = new JobMethodInvokingRunnable();
					runnable.setTargetObject(bean);
					runnable.setTargetMethod(method.getName());
					runnable.setArguments(new Object[0]);
					try {
						runnable.prepare();
					} catch (Exception ex) {
						throw new IllegalStateException(
								"failed to prepare task", ex);
					}
					
					ScheduleTaskInfo info=new ScheduleTaskInfo(annotation);

					registrar.addTiggerTask(runnable,info);

				}
			}
		});
		return bean;
	}

	public void destroy() throws Exception {
		if (this.registrar != null) {
			this.registrar.stopTimer();
		}
	}
}
