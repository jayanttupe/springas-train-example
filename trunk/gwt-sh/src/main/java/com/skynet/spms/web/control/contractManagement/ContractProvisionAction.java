package com.skynet.spms.web.control.contractManagement;

import java.io.File;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.context.ContextLoader;

import com.ibm.icu.text.SimpleDateFormat;
import com.skynet.common.gwt.GwtActionHelper;
import com.skynet.common.gwt.GwtRpcEndPoint;
import com.skynet.spms.client.service.contractManagement.provision.ContractProvisionService;
import com.skynet.spms.client.vo.contractManagement.Provision;
import com.skynet.spms.manager.contractManagement.template.xml.XmlProvisionManager;
import com.skynet.spms.persistence.dto.ProvisionDTO;
import com.skynet.spms.web.control.BaseAction;

/**
 * deal with action for xml
 * 
 * @author tqc
 * 
 */
@Controller
@GwtRpcEndPoint
public class ContractProvisionAction extends BaseAction implements
		ContractProvisionService {

	private XmlProvisionManager manager = new XmlProvisionManager();

	public void addProvision(Provision p) {
		String realPath = ContextLoader.getCurrentWebApplicationContext()
				.getServletContext()
				.getRealPath("ds/templates/" + p.getTempType() + ".xml");
		File xmlFile = new File(realPath);
		ProvisionDTO pro = new ProvisionDTO();
		this.copySimpleProperties(pro, p);
		pro.setCreateBy(GwtActionHelper.getCurrUser());
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		pro.setCreateDate(df.format(new Date()));
		pro.setDeleted("false");
		pro.setItemNumber("1");
		pro.setKeywordkey("key");
		pro.setVersion("1");
		if (xmlFile.exists()) {
			manager.insertElement(pro, realPath);
		} else {
			manager.createDocument(pro, realPath);
		}
	}

	public void deleteProvision(String id, String fileName) {
		String realPath = ContextLoader.getCurrentWebApplicationContext()
				.getServletContext()
				.getRealPath("ds/templates/" + fileName);
		manager.removeElement(id, realPath);
	}

	public void updateProvision(Provision p) {
		String realPath = ContextLoader.getCurrentWebApplicationContext()
		.getServletContext()
		.getRealPath("ds/templates/" + p.getTempType());
		if(p.getParentId()==null){
			p.setParentId("root");
		}
		ProvisionDTO pro = new ProvisionDTO();
		this.copySimpleProperties(pro, p);
		manager.updateElement(pro, realPath);
	}

}
