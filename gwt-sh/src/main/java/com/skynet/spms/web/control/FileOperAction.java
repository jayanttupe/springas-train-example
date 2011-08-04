package com.skynet.spms.web.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.skynet.common.gwt.GwtRpcEndPoint;
import com.skynet.spms.client.service.FileOperService;
import com.skynet.spms.service.FileManageService;

@Controller
@GwtRpcEndPoint
public class FileOperAction implements FileOperService{

	@Autowired
	private FileManageService fileManageService;
	
	@Override
	public void fileManualOper() throws Exception {
		
		fileManageService.FileOperate();
	}
	
	
	
	

	
}
