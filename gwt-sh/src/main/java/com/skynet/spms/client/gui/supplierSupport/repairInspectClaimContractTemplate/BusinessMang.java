package com.skynet.spms.client.gui.supplierSupport.repairInspectClaimContractTemplate;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.skynet.spms.client.gui.base.BaseBusiness;
import com.skynet.spms.client.gui.base.ValidateUtil;
import com.skynet.spms.client.gui.base.WorkFlowBusinessType;
import com.skynet.spms.client.gui.base.WorkFlowSheetType;
import com.skynet.spms.client.gui.base.i18n.I18n;
import com.skynet.spms.client.gui.supplierSupport.repairInspectClaimContractTemplate.model.ModelLocator;
import com.skynet.spms.client.gui.supplierSupport.repairInspectClaimContractTemplate.update.RepairInsClaimContractTemplateUpdateWin;
import com.skynet.spms.client.service.ApprovalService;
import com.skynet.spms.client.service.ApprovalServiceAsync;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.grid.ListGrid;

public class BusinessMang extends BaseBusiness {

	public ModelLocator model = ModelLocator.getInstance();
	
	private I18n ui=new I18n();

	private ApprovalServiceAsync approvalService = GWT
			.create(ApprovalService.class);

	/**
	 * 修改
	 * 
	 * @param listGrid
	 */
	public void modifyContract(final ListGrid listGrid) {

		if (!ValidateUtil.isRecordSelected(listGrid)) {
			return;
		}

		Record target = listGrid.getSelectedRecord();

		String publishStatus = target.getAttribute(PUBLISHSTATUSCONS);

		if (PULISHSTATUS_CHANGING.equals(publishStatus)
				|| PULISHSTATUS_UNPUBLISHED.equals(publishStatus)) {
			model.contractID = listGrid.getSelectedRecord().getAttribute("id");
			RepairInsClaimContractTemplateUpdateWin win = new RepairInsClaimContractTemplateUpdateWin();
			win.show();
		} else {
			SC.warn(ui.getRepairModule().publish_contract_changing());
		}

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
				WorkFlowSheetType.SUPPIERREPAIRCONTRCT, contractAmount,
				priority, WorkFlowBusinessType.SUPPIERREPAIRCONTRCT,
				new AsyncCallback<Void>() {
					@Override
					public void onFailure(Throwable caught) {
						SC.say("error", caught.toString());
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

}
