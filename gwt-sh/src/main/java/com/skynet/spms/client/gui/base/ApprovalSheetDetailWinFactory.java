package com.skynet.spms.client.gui.base;

import com.skynet.spms.client.gui.customerService.buyBackService.buyBackPact.model.BuybackPactModelLocator;
import com.skynet.spms.client.gui.customerService.buyBackService.buyBackPact.view.BuyBackPactViewWin;
import com.skynet.spms.client.gui.customerService.commonui.BaseAddressModel;
import com.skynet.spms.client.gui.customerService.leaseService.leasecontract.view.LeaseContractViewWindow;
import com.skynet.spms.client.gui.customerService.repairService.repairContract.ContractModelLocator;
import com.skynet.spms.client.gui.customerService.repairService.repairContract.view.RepairContractTemplateViewWindow;
import com.skynet.spms.client.gui.customerService.salesService.salesContract.model.SaleContractModelLocator;
import com.skynet.spms.client.gui.customerService.salesService.salesContract.view.SalesContractViewWin;
import com.skynet.spms.client.gui.supplierSupport.consignment.consignRenew.model.ConsignRenewModel;
import com.skynet.spms.client.gui.supplierSupport.consignment.consignRenew.view.ConsignRenewViewWin;
import com.skynet.spms.client.gui.supplierSupport.leaseService.leaseContract.view.SSLeaseContractViewWindow;
import com.skynet.spms.client.gui.supplierSupport.leaseService.model.SSMainModelLocator;
import com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementContract.model.ProcurementContractModelLocator;
import com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementContract.view.ProcurementContractViewWin;
import com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementPlan.model.MainModelLocator;
import com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementPlan.view.ProcurementPlanViewWindow;
import com.skynet.spms.client.gui.supplierSupport.repairInspectClaimContractTemplate.model.ModelLocator;
import com.skynet.spms.client.gui.supplierSupport.repairInspectClaimContractTemplate.view.RepairInsClaimContractTemplateViewWindow;
import com.smartgwt.client.widgets.Window;

/**
 * 获取单据详细窗口
 * 
 * @author zhangqiang
 * 
 */
public class ApprovalSheetDetailWinFactory {
	static SaleContractModelLocator salesModel = SaleContractModelLocator
			.getInstance();
	static ProcurementContractModelLocator procurementModel = ProcurementContractModelLocator
			.getInstance();
	static BuybackPactModelLocator buybackPactModelLocator = BuybackPactModelLocator
			.getInstance();
	static ContractModelLocator model = ContractModelLocator.getInstance();

	static ConsignRenewModel consignRenewModel = ConsignRenewModel
			.getInstance();

	static ModelLocator modelLocator = ModelLocator.getInstance();

	static MainModelLocator ms = MainModelLocator.getInstance();

	static com.skynet.spms.client.gui.customerService.leaseService.model.MainModelLocator locator = com.skynet.spms.client.gui.customerService.leaseService.model.MainModelLocator
			.getInstance();
	static SSMainModelLocator ssMainModelLocator = SSMainModelLocator
			.getInstance();

	static BaseAddressModel addressModel=BaseAddressModel.getInstance();
	/**
	 * 根据单据类型获取详细面板
	 * 
	 * @param sheetNo
	 * @param sheetType
	 * @return
	 */
	public static Window getApprovalSheetDetailWin(String sheetNo,
			String sheetType) {
		Window win = null;
		addressModel.addr_sheetId=sheetNo;
		if (WorkFlowSheetType.BUYBACKCONTRACT.equals(sheetType)) {
			buybackPactModelLocator.contractID = sheetNo;
			win = new BuyBackPactViewWin("回购合同", true, null, null, null);
		} else if (WorkFlowSheetType.PROCUREMENTCONTRACT.equals(sheetType)) {
			procurementModel.contractID = sheetNo;
			win = new ProcurementContractViewWin("采购合同", true, null, null, null);
		} else if (WorkFlowSheetType.SALESCONTRACT.equals(sheetType)) {
			salesModel.contractID = sheetNo;
			win = new SalesContractViewWin("销售合同", true, null, null, null);
		} else if (WorkFlowSheetType.CONSIGNRENEWFORM.equals(sheetType)) {
			consignRenewModel.consignRenewId = sheetNo;
			win = new ConsignRenewViewWin("寄售补库申请单", true, null, null, null);
		} else if (WorkFlowSheetType.CUSTOMERREPAIRCONTRCT.equals(sheetType)) {
			model.viewDetailContractID = sheetNo;
			win = new RepairContractTemplateViewWindow();
		} else if (WorkFlowSheetType.SUPPIERREPAIRCONTRCT.equals(sheetType)) {
			modelLocator.viewDetailContractID = sheetNo;
			win = new RepairInsClaimContractTemplateViewWindow();
		} else if (WorkFlowSheetType.PROCUREMENTPLAN.equals(sheetType)) {
			ms.Pid = sheetNo;
			win = new ProcurementPlanViewWindow("查看采购计划", true, null, null,
					null);
		} else if (WorkFlowSheetType.LEASECONTRACT.equals(sheetType)) {

			locator.LeaseContractId = sheetNo;
			win = new LeaseContractViewWindow();
		} else if (WorkFlowSheetType.SSLEASECONTRACT.equals(sheetType)) {

			ssMainModelLocator.SSLeaseContractId = sheetNo;
			win = new SSLeaseContractViewWindow();
		} else {
			win = new Window();
			win.setTitle("默认面板");
		}
		return win;
	}
}
