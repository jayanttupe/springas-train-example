package com.skynet.spms.web.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.skynet.common.gwt.GwtRpcEndPoint;
import com.skynet.spms.client.service.CheckUniqueService;
import com.skynet.spms.manager.CheckUniqueManager;

@Controller
@GwtRpcEndPoint
public class CheckUniqueAction implements CheckUniqueService {
	
	@Autowired
	private CheckUniqueManager checkUniqueManager;
	
	@Override
	public Boolean isUnique(String entityClassName, String fieldName,String value) {
		
		return checkUniqueManager.isUnique(entityClassName, fieldName, value);
	}

	
}
