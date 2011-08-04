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
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tile.TileGrid;

public class SupplierHistoryPriceShowWindow extends BaseWindow{

	public SupplierHistoryPriceShowWindow(String windowTitle, boolean isMax,
			Rectangle srcRect, ListGrid listGrid, String iconUrl) {
		super(windowTitle, isMax, srcRect, listGrid, iconUrl);
	}
	@Override
	protected Canvas getLeftLayout(final Rectangle srcRect, ListGrid listGrid) {
		IndexInfoListGrid indexInfoListGrid=(IndexInfoListGrid)listGrid;
		addCloseClickHandler(new CloseClickHandler() {
			public void onCloseClick(CloseClientEvent event) {
				ShowWindowTools.showCloseWindow(srcRect, SupplierHistoryPriceShowWindow.this, -1);
			}
		});
		setWidth(470);
		setHeight(280);
		setTitle("供应商历史报价信息");
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
		
		ListGridField lgfSupplierCode = new ListGridField("supplierCode", "供应商代码");  
		lgfSupplierCode.setCanEdit(false);
	    fieldList.add(lgfSupplierCode);
		
	    ListGridField lgfUnitPriceAmount  = new ListGridField("unitPriceAmount ", "价格");  
	    lgfUnitPriceAmount.setCanEdit(false);
	    fieldList.add(lgfUnitPriceAmount);
		
	    ListGridField lgfInternationalCurrencyCode  = new ListGridField("internationalCurrencyCode ", "币种");  
	    lgfInternationalCurrencyCode.setCanEdit(false);
	    fieldList.add(lgfInternationalCurrencyCode);
		
	    ListGridField lgfQuotationDate  = new ListGridField("quotationDate ", "报价日期");  
	    lgfQuotationDate.setCanEdit(false);
	    fieldList.add(lgfQuotationDate);
		
	    ListGridField lgfPriceEffectiveDate = new ListGridField("priceEffectiveDate ", "报价有效期");  
	    lgfPriceEffectiveDate.setCanEdit(false);
	    fieldList.add(lgfPriceEffectiveDate);
		
	
	    ListGridField lgfUnitOfMeasureCode = new ListGridField("unitOfMeasureCode", "计量单位");  
	    lgfUnitOfMeasureCode.setCanEdit(false);
	    fieldList.add(lgfUnitOfMeasureCode);
	    
	   
	    
	    SectionStack sectionStack = new SectionStack();
	    
	    sectionStack.setWidth100();
	    SectionStackSection section = new SectionStackSection();
	    section.setTitle("供应商分段报价明细项");
	    section.setCanCollapse(false);  
	    section.setExpanded(true); 
		
	    List<ListGridField> fieldList1 = new ArrayList<ListGridField>();
	    
	    
	    ListGridField lgfItemNumber = new ListGridField("itemNumber", "项号");  
	    lgfItemNumber.setCanEdit(false);
	    fieldList1.add(lgfItemNumber);
		
	    ListGridField lgfQuantityScope = new ListGridField("quantityScope", "数量范围");  
	    lgfQuantityScope.setCanEdit(false);
	    fieldList1.add(lgfQuantityScope);
	    
	    ListGridField lgfUnitPriceAmount1  = new ListGridField("unitPriceAmount ", "价格");  
	    lgfUnitPriceAmount.setCanEdit(false);
	    fieldList.add(lgfUnitPriceAmount);
	    
	    ListGridField lgfInternationalCurrencyCode1  = new ListGridField("internationalCurrencyCode ", "币种");  
	    lgfInternationalCurrencyCode.setCanEdit(false);
	    fieldList.add(lgfInternationalCurrencyCode);
	    
	    ListGridField lgfUnitOfMeasureCode1 = new ListGridField("unitOfMeasureCode", "计量单位");  
	    lgfUnitOfMeasureCode.setCanEdit(false);
	    fieldList.add(lgfUnitOfMeasureCode);
	    
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
