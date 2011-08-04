package com.skynet.spms.client.data;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.event.shared.HandlerManager;
import com.skynet.spms.client.PanelFactory;
import com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.common.LogisticsPlug;
import com.skynet.spms.client.gui.basedatamanager.organization.common.OrganizationPlugin;
import com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.common.StockServicePlug;
import com.skynet.spms.client.gui.commonOrder.InitLocal;
import com.skynet.spms.client.gui.contractManagement.common.ContractManagementPlug;
import com.skynet.spms.client.gui.customerService.common.CustomerServicePlug;
import com.skynet.spms.client.gui.customerplatform.common.CustomerPlatformPlugin;
import com.skynet.spms.client.gui.finance.common.FinancePlugin;
import com.skynet.spms.client.gui.partcatalog.common.PartcatalogPlugin;
import com.skynet.spms.client.gui.supplierSupport.common.SupplierSupportPlug;


/**
 * @author 曹宏炜
 * 面板工厂映射类
 *
 */
public class PanelMapFactrory {

	private static Map<String, PanelFactory> map;

	/**
	 * @param eventBus
	 */
	public static void initPanelMap(HandlerManager eventBus) {
		//初始化资源到客户端(add by tz_taiqc)
		new InitLocal();
		
		map = new HashMap<String, PanelFactory>();
		// 组织机构管理模块
		new OrganizationPlugin().plug(map);
		// 备件技术目录
		new PartcatalogPlugin().plug(map);
		// 财务模块
		new FinancePlugin().plug(map);
		// 仓储管理模块
		new StockServicePlug().plug(map);
		// 物流模块管理
		new LogisticsPlug().plug(map);
		
		// 客户服务模块
		new CustomerServicePlug().plug(map);
		// 合同管理模块
		new ContractManagementPlug().plug(map);
		// 供应商服务模块
		new SupplierSupportPlug().plug(map);

		
		/***客户平台**/
		//客户平台模块
		new CustomerPlatformPlugin().plug(map);

	}

	/**
	 * @param factoryKey
	 * @return
	 */
	public static PanelFactory getFactory(String factoryKey) {
		return map.get(factoryKey);
	}
}
