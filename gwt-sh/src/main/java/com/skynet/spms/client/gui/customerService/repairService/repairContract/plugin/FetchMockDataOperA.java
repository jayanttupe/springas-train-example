package com.skynet.spms.client.gui.customerService.repairService.repairContract.plugin;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.skynet.spms.client.gui.base.i18n.I18n;
import com.skynet.spms.client.vo.repairmodule.MockItem;
import com.skynet.spms.client.vo.repairmodule.MockRecord;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.grid.ListGridRecord;

public class FetchMockDataOperA extends MockDataOper {
	
	InspectMockView view;
	
	private I18n ui=new I18n();

	public FetchMockDataOperA(MockView view) {
		this.view=(InspectMockView) view;
	}

	@Override
	public void drawView(String contractID) {
		
		if(contractID==null){
			SC.say(ui.getRepairModule().supplier_contract_cantnot_create());
			return;
		}

		service.getByContractID(contractID, MockRecord.MOCK_A,
				new AsyncCallback<MockRecord>() {

					@Override
					public void onSuccess(MockRecord result) {
						
						view.getCheckDateItem().setValue(result.getField1());
						
						view.getCheckCompanyItem().setValue(result.getField2());
						
						view.getInspectDescriptionItem().setValue(result.getField3());
						
						ListGridRecord[] records = new ListGridRecord[result.getItems().size()];
						
						for (int i = 0; i < records.length; i++) {
							
							MockItem item = result.getItems().get(i);
							
							ListGridRecord record = new ListGridRecord();
							
							record.setAttribute("itemNumber", i+1);
							
							record.setAttribute("quantity", item.getQuantity());
							
							record.setAttribute("itemDescription", item.getItemDescription());
							
							record.setAttribute("unitOfPrice", item.getUnitOfPrice());
							
							record.setAttribute("quantity", item.getQuantity());
							
							record.setAttribute("amount", item.getAmount());
							
							record.setAttribute("m_InternationalCurrencyCode", item.getInternationalCurrencyCode());
							
							record.setAttribute("remarkText", item.getRemarkText());
							
							records[i] = record;
						}
						
						view.grid.setData(records);
						
					}

					@Override
					public void onFailure(Throwable caught) {
						SC.say("error:" + caught);
					}
				});

	}

}
