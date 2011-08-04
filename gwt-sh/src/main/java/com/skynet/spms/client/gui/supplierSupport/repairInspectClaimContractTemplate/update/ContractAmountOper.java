package com.skynet.spms.client.gui.supplierSupport.repairInspectClaimContractTemplate.update;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.skynet.spms.client.service.BaseCodeService;
import com.skynet.spms.client.service.BaseCodeServiceAsync;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.data.RecordList;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.grid.ListGrid;

public class ContractAmountOper {

	private BaseCodeServiceAsync service = GWT.create(BaseCodeService.class);

	private ListGrid inspectOutLayGrid;

	private ListGrid repairQuoteGrid;

	public ListGrid getInspectOutLayGrid() {
		return inspectOutLayGrid;
	}

	public void setInspectOutLayGrid(ListGrid inspectOutLayGrid) {
		this.inspectOutLayGrid = inspectOutLayGrid;
	}

	public ListGrid getRepairQuoteGrid() {
		return repairQuoteGrid;
	}

	public void setRepairQuoteGrid(ListGrid repairQuoteGrid) {
		this.repairQuoteGrid = repairQuoteGrid;
	}

	/**
	 * 计算金额
	 * 
	 * @param contractID
	 */
	public void accountAmount(String contractID) {
		
		Float contractAmount = 0.0f;

		RecordList inspectList = inspectOutLayGrid.getRecordList();

		RecordList repairQuoteList = repairQuoteGrid.getRecordList();

		for (int i = 0; i < inspectList.getLength(); i++) {
			Record record = inspectList.get(i);
			String unitOfPrice = record.getAttribute("unitOfPrice");
			String quantity = record.getAttribute("quantity");
			Float amount = Float.parseFloat(unitOfPrice)
					* Float.parseFloat(quantity);
			contractAmount += amount;
		}

		for (int i = 0; i < repairQuoteList.getLength(); i++) {
			Record record = repairQuoteList.get(i);
			String unitOfPrice = record.getAttribute("unitOfPrice");
			String quantity = record.getAttribute("quantity");
			Float amount = Float.parseFloat(unitOfPrice)
					* Float.parseFloat(quantity);
			contractAmount += amount;
		}

		service.updateSupplierRepairContractAmount(contractID, contractAmount,
				new AsyncCallback<Void>() {

					@Override
					public void onSuccess(Void result) {
					}

					@Override
					public void onFailure(Throwable caught) {
						SC.warn("Account contract amount failed." + caught);
					}
				});

	}

}
