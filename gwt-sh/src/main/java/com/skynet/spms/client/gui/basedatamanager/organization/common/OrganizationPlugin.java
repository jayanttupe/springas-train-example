package com.skynet.spms.client.gui.basedatamanager.organization.common;

import java.util.Map;

import com.skynet.spms.client.PanelFactory;
import com.skynet.spms.client.gui.base.ModulePlug;
import com.skynet.spms.client.gui.basedatamanager.organization.department.DepartmentInfoPanel;
import com.skynet.spms.client.gui.basedatamanager.organization.enterpriseInfo.EnterpriseInfoPanel;
import com.skynet.spms.client.gui.basedatamanager.organization.user.UserManagerPanel;
import com.skynet.spms.client.gui.basedatamanager.organization.usergroup.UserGroupPanel;

public class OrganizationPlugin implements ModulePlug {

	@Override
	public void plug(Map<String, PanelFactory> map) {
		// TODO Auto-generated method stub
		map.put("organization.userinfo", new UserManagerPanel.Factory());
		map.put("organization.userGroup", new UserGroupPanel.Factory());
		map.put("organization.enterprise.COMACSC",new EnterpriseInfoPanel.Factory("organization.enterprise.COMACSC","COMACSC_dataSource"));
		map.put("organization.enterprise.customer",new EnterpriseInfoPanel.Factory("organization.enterprise.customer","customer_dataSource"));
		map.put("organization.enterprise.supplier",new EnterpriseInfoPanel.Factory("organization.enterprise.supplier","supplier_dataSource"));
		map.put("organization.enterprise.manufacturer",new EnterpriseInfoPanel.Factory("organization.enterprise.manufacturer","manufacturer_dataSource"));
		map.put("organization.enterprise.clearanceAgency",new EnterpriseInfoPanel.Factory("organization.enterprise.clearanceAgency","clearanceAgency_dataSource"));
		map.put("organization.enterprise.carrier",new EnterpriseInfoPanel.Factory("organization.enterprise.carrier","carrier_dataSource"));
		map.put("organization.enterprise.repairAgency",new EnterpriseInfoPanel.Factory("organization.enterprise.repairAgency","repairAgency_dataSource"));
		map.put("organization.enterprise.department", new DepartmentInfoPanel.Factory());
	}

}
