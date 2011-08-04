package com.skynet.spms.client.gui.contractManagement.common;

import java.util.Map;

import com.skynet.spms.client.PanelFactory;
import com.skynet.spms.client.gui.base.ModulePlug;
import com.skynet.spms.client.gui.contractManagement.template.ContractProvisionPanel;

/**
 * 客户服务模块加载器
 * 
 * @author tqc
 * 
 */
public class ContractManagementPlug implements ModulePlug {

	public void plug(Map<String, PanelFactory> map) {
		map.put("contractManagement.template",
				new ContractProvisionPanel.Factory());

	}
}
