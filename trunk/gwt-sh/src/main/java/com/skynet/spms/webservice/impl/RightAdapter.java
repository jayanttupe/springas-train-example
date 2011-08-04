package com.skynet.spms.webservice.impl;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.skynet.spms.webservice.entity.FaultResponse;

@Aspect
@Component
public class RightAdapter {
	

	Logger log = LoggerFactory.getLogger(RightAdapter.class);

	@Before("execution(* com.skynet.spms.webservice.RFIDPortTypeSOAP.*(..))  " +
			" && args(request)")
	public void doRightVerify(Object request)throws Throwable{
					
		String euid=BeanUtils.getProperty(request, "euid");
		String pwd=BeanUtils.getProperty(request, "password");
		if(!valid(euid,pwd)){
			FaultResponse fault= new FaultResponse("euid / password invalid or not exist");
			throw fault;
		}
		
	}
	
	private boolean valid(String euid,String pwd){
		if(StringUtils.isNotBlank(euid)&&
				StringUtils.isNotBlank(pwd)){
			return euid.equals(pwd);
		}else{
			return false;
		}
	}
}
