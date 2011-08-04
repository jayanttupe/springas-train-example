package com.skynet.spms.client.gui.customerService.repairService.repairContract;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.skynet.spms.client.gui.base.BaseBusiness;
import com.skynet.spms.client.gui.base.Cache;
import com.skynet.spms.client.gui.base.ValidateUtil;
import com.skynet.spms.client.gui.base.WorkFlowBusinessType;
import com.skynet.spms.client.gui.base.WorkFlowSheetType;
import com.skynet.spms.client.gui.base.i18n.I18n;
import com.skynet.spms.client.gui.customerService.repairService.customerRepairInspectionOrder.CustomerRepairInspectionOrderAddWin;
import com.skynet.spms.client.gui.customerService.repairService.repairContract.update.RepairContractTemplateUpdateWin;
import com.skynet.spms.client.gui.customerService.repairService.repairContract.view.RepairContractTemplateViewWindow;
import com.skynet.spms.client.service.ApprovalService;
import com.skynet.spms.client.service.ApprovalServiceAsync;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.toolbar.ToolStripMenuButton;

/**
 * 处理业务
 * 
 * @author tqc
 * 
 */
public class Business extends BaseBusiness {

	ContractModelLocator model = ContractModelLocator.getInstance();
	
	private I18n ui=new I18n();

	private ApprovalServiceAsync approvalService = GWT
			.create(ApprovalService.class);

	public Business() {

	}

	/**
	 * 加入工作审批流
	 * 
	 * @param target
	 */
	private void putInFlow(Record target) {
		String priority = target.getAttribute("m_Priority");

		String sheetNo = target.getAttribute("id");

		String itemNumber = target.getAttribute("contractNumber");
		
		Float contractAmount=target.getAttributeAsFloat("extendedValueTotalAmount");

		approvalService.saveApproval(sheetNo, itemNumber,
				WorkFlowSheetType.CUSTOMERREPAIRCONTRCT, contractAmount,
				priority, WorkFlowBusinessType.CUSTOMERREPAIRCONTRACT,
				new AsyncCallback<Void>() {
					@Override
					public void onFailure(Throwable caught) {
						SC.say("error:", caught.toString());
					}

					@Override
					public void onSuccess(Void result) {
						SC.say(ui.getRepairModule().msgPulishSuccess());
					}
				});
	}

	/**
	 * 初审合同
	 */
	public void approvalContractFirst(final ListGrid grid) {
		if (ValidateUtil.isRecordSelected(grid)) {

			Record target = grid.getSelectedRecord();

			String approvalStage = target.getAttribute("approvalStage");

			String auditStatus = target.getAttribute("auditStatus");

			// 初审中&&未通过 || null &&null
			if ((null == approvalStage && null == auditStatus)
					|| (APPROVALSTAGE_FIRSTING.equals(approvalStage) && AUDITSTATUS_NOPASS
							.equals(auditStatus))) {

				target.setAttribute("approvalStage", APPROVALSTAGE_FIRSTING);

				grid.updateData(target);

				putInFlow(target);

			} else {
				SC.warn(ui.getRepairModule().opp_failed());
			}
		}
	}

	/**
	 * 终审合同
	 */
	public void approvalContractFinally(final ListGrid grid) {

		if (ValidateUtil.isRecordSelected(grid)) {

			Record target = grid.getSelectedRecord();

			String approvalStage = target.getAttribute("approvalStage");

			String auditStatus = target.getAttribute("auditStatus");

			if (APPROVALSTAGE_FIRSTING.equals(approvalStage)
					&& AUDITSTATUS_PASS.equals(auditStatus)) {

				target.setAttribute("approvalStage", APPROVALSTAGE_FINALLYING);

				target.setAttribute("auditStatus", "");

				grid.updateData(target);

				putInFlow(target);

			} else {
				SC.warn(ui.getRepairModule().opp_failed());
			}
		}
	}

	/**
	 * 修改合同
	 */
	public void updateContract(RepairContractListGrid grid) {

		Record target = grid.getSelectedRecord();

		String publishStatus = target.getAttribute(PUBLISHSTATUSCONS);

		if (PULISHSTATUS_CHANGING.equals(publishStatus)
				|| PULISHSTATUS_UNPUBLISHED.equals(publishStatus)) {

			RepairContractTemplateUpdateWin updateWin = new RepairContractTemplateUpdateWin();

			Cache.getInstance().putObject("RepairContractTemplateUpdateWin",
					updateWin);

			updateWin.show();

		} else {
			SC.warn(ui.getRepairModule().publish_contract_changing());
		}

	}

	/**
	 * 新建客户送修送检指令
	 * 
	 * @param grid
	 */
	public void createCustomerRepairInspectionOrder(final ListGrid grid,
			ToolStripMenuButton bussinessOrderBtn) {
		//Check if last one row was selected.
		if (!ValidateUtil.isRecordSelected(grid)) {
			return;
		}

		Record reocrd = grid.getSelectedRecord();
		
		String publishstatus = reocrd.getAttribute(PUBLISHSTATUSCONS);
		
		String approvalStage = reocrd.getAttribute("approvalStage");
		
		String auditStatus = reocrd.getAttribute("auditStatus");

		// Check the sheet is published.
		if (PULISHSTATUS_UNPUBLISHED.equals(publishstatus)) {
			SC.warn("Please publish the sheet first.");
			return;
		}

		// Check the sheet's approve result.
		if(APPROVALSTAGE_FIRSTING.equals(approvalStage)&& AUDITSTATUS_PASS.equals(auditStatus)){
			
			model.selectedContract = reocrd;
			//Show the order add window.
			CustomerRepairInspectionOrderAddWin addWin = new CustomerRepairInspectionOrderAddWin(reocrd);

			addWin.show();
			
		}else{
			SC.warn("Please check sheet approve status.");
			return;
		}
		

	}

	/**
	 * 查看详细
	 * 
	 * @param grid
	 */
	public void viewDetail(String contractID) {
		model.viewDetailContractID = contractID;
		RepairContractTemplateViewWindow viewWin = new RepairContractTemplateViewWindow();
		viewWin.show();

	}

}
