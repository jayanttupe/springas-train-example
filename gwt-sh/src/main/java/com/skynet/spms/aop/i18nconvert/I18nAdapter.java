package com.skynet.spms.aop.i18nconvert;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.common.prop.PropManager;

@Aspect
@Component
public class I18nAdapter {

	Logger log = LoggerFactory.getLogger(I18nAdapter.class);

	@Autowired
	private PropManager messageProp;

	@AfterReturning("execution(* com.skynet.spms.web.control.*.*(..)) " +
			"&& @annotation(com.skynet.spms.aop.i18nconvert.I18nResource)")
	public void doConvert(JoinPoint jp) {
		
		String locale=(String)jp.getArgs()[0];
		Object param=jp.getTarget();
		
		log.info("start resource filter,locale:"+locale+" "+jp.getSignature().getName());

//		doResultConvert(param,locale);
	}
		
			
//	public void doResultConvert(Object param,String locale) {
//		
//				
//		PropertyEntity prop = messageProp.getPropEntity(locale);
//		
//		if(param==null){
//			return;
//		}					
//		Class<?> cls=param.getClass();
//		
//		
//		ConvertPile convert=PileFactory.getConvertInst(cls,true);
//		
//		convert.doConvert(param, prop);
//		
//		return;
//		
//	}




}
