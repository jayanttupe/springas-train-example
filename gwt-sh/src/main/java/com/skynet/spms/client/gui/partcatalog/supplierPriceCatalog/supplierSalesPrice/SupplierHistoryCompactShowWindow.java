package com.skynet.spms.client.gui.partcatalog.supplierPriceCatalog.supplierSalesPrice;

import java.util.ArrayList;
import java.util.List;

import com.skynet.spms.client.gui.base.BaseWindow;
import com.skynet.spms.client.gui.partcatalog.technicalCatalog.indexInfomation.IndexInfoListGrid;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.events.CloseClientEvent;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tile.TileGrid;

public class SupplierHistoryCompactShowWindow extends BaseWindow{
	
	public SupplierHistoryCompactShowWindow(String windowTitle, boolean isMax,
			Rectangle srcRect, ListGrid listGrid, String iconUrl) {
		super(windowTitle, isMax, srcRect, listGrid, iconUrl);
	}
	@Override
	protected Canvas getLeftLayout(final Rectangle srcRect, ListGrid listGrid) {
		IndexInfoListGrid indexInfoListGrid=(IndexInfoListGrid)listGrid;
		addCloseClickHandler(new CloseClickHandler() {
			public void onCloseClick(CloseClientEvent event) {
				ShowWindowTools.showCloseWindow(srcRect, SupplierHistoryCompactShowWindow.this, -1);
			}
		});
		setWidth(470);
		setHeight(280);
		setTitle("供应商历史合同信息");
		setShowMinimizeButton(false);
		setIsModal(true);
		setShowModalMask(true);
		centerInPage();
		addCloseClickHandler(new CloseClickHandler() {
			public void onCloseClick(CloseClientEvent event) {
				//buttonTouchThis.setTitle("Touch This");
				destroy();
			}
		});
		
		List<ListGridField> fieldList = new ArrayList<ListGridField>();
		
		
		ListGridField lgfContractNumber = new ListGridField("contractNumber", "采购合同编号");  
		lgfContractNumber.setCanEdit(false);
	    fieldList.add(lgfContractNumber);
	
	    ListGridField lgfUnitPriceAmount  = new ListGridField("unitPriceAmount ", "单价");  
	    lgfUnitPriceAmount.setCanEdit(false);
	    fieldList.add(lgfUnitPriceAmount);
	
	    ListGridField lgfQuantity  = new ListGridField("quantity ", "数量");  
	    lgfQuantity.setCanEdit(false);
	    fieldList.add(lgfQuantity);
	
	    ListGridField lgfTotalAmount  = new ListGridField("totalAmount ", "总金额");  
	    lgfTotalAmount.setCanEdit(false);
	    fieldList.add(lgfTotalAmount);
	    
	    ListGridField lgfInternationalCurrencyCode  = new ListGridField("internationalCurrencyCode ", "币种");  
	    lgfInternationalCurrencyCode.setCanEdit(false);
	    fieldList.add(lgfInternationalCurrencyCode);
	    
	    ListGridField lgfIsInstallment  = new ListGridField("isInstallment ", "是否分期付款");  
	    lgfIsInstallment.setCanEdit(false);
	    fieldList.add(lgfIsInstallment);
	    
	    ListGridField lgfDateOfDelivery = new ListGridField("dateOfDelivery ", "交货日期");  
	    lgfDateOfDelivery.setCanEdit(false);
	    fieldList.add(lgfDateOfDelivery);
	    
	    ListGridField[] fields = new ListGridField[fieldList.size()];
        fieldList.toArray(fields);
        
        TileGrid tileGrid = new TileGrid();
		tileGrid.setWidth100();
		tileGrid.setHeight("90%");
		tileGrid.setTileWidth(150);
		tileGrid.setTileHeight(150);
		tileGrid.setBorder("0px solid #9C9C9C");
		
        
        VLayout vLayout = new VLayout();
		vLayout.addMember(tileGrid); 
		return vLayout;
	}
	
}	
