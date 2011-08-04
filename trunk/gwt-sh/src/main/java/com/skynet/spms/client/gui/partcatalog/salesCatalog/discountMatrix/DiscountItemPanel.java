package com.skynet.spms.client.gui.partcatalog.salesCatalog.discountMatrix;

import java.util.LinkedHashMap;
import java.util.Map;

import com.google.gwt.user.client.Window;
import com.skynet.spms.client.constants.ConstantsUtil;
import com.skynet.spms.client.gui.base.BtnsHLayout;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class DiscountItemPanel extends VLayout {

	private DiscountItemListGrid discountItemListGrid;

	public DiscountItemPanel() {
		discountItemListGrid = new DiscountItemListGrid();
		discountItemListGrid.setHeight100();
		this.addMember(discountItemListGrid);

		HLayout buttonPanel = new BtnsHLayout();
		buttonPanel.setHeight(30);
		final IButton newButton = new IButton(ConstantsUtil.buttonConstants.newButton());
		newButton.setIcon("icons/add_list.png");
		newButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if(discountItemListGrid.getPartSaleReleaseId()==null){
					Window.alert(ConstantsUtil.partCatalogConstants.alertForSelectSalePriceRelease());
					return;
				}
				Map<String, String> map = new LinkedHashMap<String, String>();
				map.put("partSaleReleaseId",discountItemListGrid.getPartSaleReleaseId());
				discountItemListGrid.startEditingNew(map);
			}
		});

		IButton saveButton = new IButton(ConstantsUtil.buttonConstants.saveButton());
		saveButton.setIcon("icons/save.png");
		saveButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				discountItemListGrid.saveAllEdits();
			}
		});
		
		final IButton cancelButton = new IButton(ConstantsUtil.buttonConstants.cancelButton());
		cancelButton.setIcon("icons/remove.png");
		cancelButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				discountItemListGrid.discardAllEdits();
			}
		});

		final IButton deleteButton = new IButton(ConstantsUtil.buttonConstants.deleteButton());
		deleteButton.setIcon("icons/delete_list.png");
		deleteButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (discountItemListGrid.getSelection().length == 0) {
					Window.alert(ConstantsUtil.commonConstants.alertSelectForDelete());
				} else {
					boolean isDel = Window.confirm(ConstantsUtil.commonConstants.ConfirmForDelete());
					if (isDel) {
						discountItemListGrid.removeSelectedData();
					}
				}

			}
		});

		/*	final IButton salesMatrixButton = new IButton(ConstantsUtil.buttonConstants.salesMatrixButton());
		salesMatrixButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
			//暂时不知道该怎样实现
			//销售矩阵
				
			}	
		});*/

		buttonPanel.addMember(newButton);
		buttonPanel.addMember(saveButton);
		buttonPanel.addMember(cancelButton);
		buttonPanel.addMember(deleteButton);
		// buttonPanel.addMember(salesMatrixButton);

		this.addMember(buttonPanel);
	}

	public void fetchData(String partSaleReleaseId) {
		discountItemListGrid.setPartSaleReleaseId(partSaleReleaseId);
		Criteria criteria = new Criteria("partSaleReleaseId", partSaleReleaseId);
		discountItemListGrid.fetchData(criteria);
	}
	public void clearData(){
		discountItemListGrid.setPartSaleReleaseId(null);
		Criteria criteria = new Criteria("partSaleReleaseId", "clear");
		discountItemListGrid.fetchData(criteria);
	}
}
