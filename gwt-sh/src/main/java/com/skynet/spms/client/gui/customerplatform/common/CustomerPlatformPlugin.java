package com.skynet.spms.client.gui.customerplatform.common;

import java.util.Map;
import com.skynet.spms.client.PanelFactory;
import com.skynet.spms.client.gui.base.ModulePlug;
import com.skynet.spms.client.gui.customerplatform.DoQuotationPanel.DoQuotationPanel;
import com.skynet.spms.client.gui.customerplatform.buybackSheetService.ui.BuyBackSheetPanel;
import com.skynet.spms.client.gui.customerplatform.customerdata.accountinfo.AccountInfoPanel;
import com.skynet.spms.client.gui.customerplatform.customerdata.customerinfo.CustomerInfoPanel;
import com.skynet.spms.client.gui.customerplatform.exchangeRequisitionSheet.ExchangeRequisitionSheetPanel;
import com.skynet.spms.client.gui.customerplatform.guaranteeForm.ui.GuaranteePanel;
import com.skynet.spms.client.gui.customerplatform.partindexquery.SingleQueryPanel;
import com.skynet.spms.client.gui.customerplatform.repairsheet.RepairRequisitionPanel;
import com.skynet.spms.client.gui.customerplatform.salesQuotationSheet.SalesQuotationPanel;
import com.skynet.spms.client.gui.customerplatform.salesRequisitionSheet.SalesRequisitionSheetPanel;
import com.skynet.spms.client.gui.customerplatform.salesRequisitionSheet.SalesRequisitionTrackPanel;
/**
 * @author 曹宏炜 技术目录映射插件类
 */
public class CustomerPlatformPlugin implements ModulePlug {

	@Override
	public void plug(Map<String, PanelFactory> map) {
		map.put(ModuleKey.C_SINGLE_SPARE_QUERY, new SingleQueryPanel.Factory());
		map.put(ModuleKey.C_CONTACT, new DoQuotationPanel.Factory());
		map.put(ModuleKey.C_CONTACTDETAIL, new SalesQuotationPanel.Factory());
		map.put(ModuleKey.C_ORDERCREATE,
				new SalesRequisitionSheetPanel.Factory());
		map.put(ModuleKey.C_ORDERTRACK,
				new SalesRequisitionTrackPanel.Factory());
		map.put(ModuleKey.C_ACCOUNT_INFO, new AccountInfoPanel.Factory());
		map.put(ModuleKey.C_CUSTOMER_INFO, new CustomerInfoPanel.Factory(
				"organization.enterprise.customer", "customer_dataSource"));
		map.put(ModuleKey.C_EXCHANGE_REQUISITION,
				new ExchangeRequisitionSheetPanel.Factory());
		map.put(ModuleKey.C_BUYBACKSHEET, new BuyBackSheetPanel.Factory());
		map.put(ModuleKey.C_GUARANTEEFORM, new GuaranteePanel.Factory());
		map.put(ModuleKey.CUSTOMERREPAIRSHEET,
				new RepairRequisitionPanel.Factory());

	}

}
