package com.skynet.spms.client.gui.partcatalog.salesCatalog.partSupplierPriceIndex;

import com.skynet.spms.client.constants.ConstantsUtil;
import com.skynet.spms.client.gui.base.BaseWindow;
import com.skynet.spms.client.gui.partcatalog.technicalCatalog.indexInfomation.IndexInfoListGrid;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.events.CloseClientEvent;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.DateItem;
import com.smartgwt.client.widgets.form.fields.RadioGroupItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class PartSupplierPriceIndexAddWindow  extends BaseWindow   {

 
	public PartSupplierPriceIndexAddWindow(String windowTitle, boolean isMax,
			Rectangle srcRect, ListGrid listGrid, String iconUrl) {
		super(windowTitle, isMax, srcRect, listGrid, iconUrl);
	}
	@Override
	protected Canvas getLeftLayout(final Rectangle srcRect, ListGrid listGrid) {
		IndexInfoListGrid indexInfoListGrid=(IndexInfoListGrid)listGrid;
		addCloseClickHandler(new CloseClickHandler() {
			public void onCloseClick(CloseClientEvent event) {
				ShowWindowTools.showCloseWindow(srcRect, PartSupplierPriceIndexAddWindow.this, -1);
			}
		});
		setWidth(470);
		setHeight(280);
		setTitle("新建备件销售价格信息");
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
		final DynamicForm form = new DynamicForm();
		//form.setDataSource(appliactionDataPanel.getDataSource());
		form.setHeight100();
		form.setWidth100();
		form.setPadding(5);
		form.setMargin(10);
		form.setNumCols(4);  
		form.setLayoutAlign(VerticalAlignment.BOTTOM);
		form.enableHiliting(false);
		
		TextItem tiUnitPriceAmount = new TextItem("unitPriceAmount","单价");
		
		TextItem tiStandardPackageQuantity  = new TextItem("standardPackageQuantity ","标准包装数量");
		
		TextItem tiCategoryIContainerPriceAmount = new TextItem("categoryIContainerPriceAmount","一类容器价格");
		
		TextItem tiMinimumSalesQuantity = new TextItem("minimumSalesQuantity","最小销售数量");
		
		TextItem tiLeadTime  = new TextItem("leadTime ","交货期(天)");
		
		SelectItem siUnitOfMeasureCode = new SelectItem("unitOfMeasureCode", "计量单位");  
		siUnitOfMeasureCode.setValueMap( "null","null","null");
	
		DateItem diPriceEffectiveDate = new DateItem("diPriceEffectiveDate", "价格有效期");  
		diPriceEffectiveDate.setUseTextField(true);  
		diPriceEffectiveDate.setUseMask(true);  
		
		SelectItem siPackagingCode = new SelectItem("packagingCode", "包装代码");  
		siPackagingCode.setValueMap( "null","null","null");
		
		//币种字段未找到
		SelectItem siCurrencyKind = new SelectItem("currencyKind", "币种");  
		siCurrencyKind.setValueMap( "null","null","null");
		
		//是否租赁字段未找到
		RadioGroupItem rgiSupplierLease = new RadioGroupItem("supplierLease","是否租赁");
		rgiSupplierLease.setValueMap("是","否");
		rgiSupplierLease.setDefaultValue("是");
		
		TextAreaItem taiAlternateSupplyLocationText = new TextAreaItem("alternateSupplyLocationText","可选供应地址");
		taiAlternateSupplyLocationText.setWidth(300);  
		taiAlternateSupplyLocationText.setRowSpan(3); 
		 
		TextAreaItem taiUnitOfMeasureClarificationText = new TextAreaItem("unitOfMeasureClarificationText","单位说明");
		taiUnitOfMeasureClarificationText.setWidth(300);  
		taiUnitOfMeasureClarificationText.setRowSpan(3); 
		
		//是否特殊包装字段未找到
		RadioGroupItem rgiSpecialPackaging = new RadioGroupItem("specialPackaging","是否特殊包装");
		rgiSpecialPackaging.setValueMap("是","否");
		rgiSpecialPackaging.setDefaultValue("是");
		
		//特殊包装说明字段未找到
		TextAreaItem taiSpecialPackagingText = new TextAreaItem("specialPackagingText","特殊包装说明");
		taiSpecialPackagingText.setWidth(300);  
		taiSpecialPackagingText.setRowSpan(3); 
		
		//特殊包装价格字段未找到
		TextItem tiSpecialPackagingPrice = new TextItem("specialPackagingPrice ","特殊包装价格");
		
		TextItem tiPriceTypeCode = new TextItem("priceTypeCode ","价格类型代码");
		
		SelectItem siProductCategoryCode = new SelectItem("productCategoryCode ","产品种类代码");
		siProductCategoryCode.setValueMap( "null","null","null");
		
		TextItem tiPriceHeldFirm = new TextItem("priceHeldFirm ","价格持续时间");
		
		SelectItem siProprietaryCode = new SelectItem("proprietaryCode ","所有权代码");
		siProprietaryCode.setValueMap( "null","null","null");
		
		//产品分类代码与产品种类代码字段相同
		SelectItem siproductCategoryCode = new SelectItem("productCategoryCode ","产品分类代码");
		siproductCategoryCode.setValueMap( "null","null","null");
		
		SelectItem siSupplierClaimedStatusCode = new SelectItem("supplierClaimedStatusCode ","供应商声明代码");
		siSupplierClaimedStatusCode.setValueMap( "null","null","null");
		
		form.setFields(tiUnitPriceAmount,tiStandardPackageQuantity,tiCategoryIContainerPriceAmount
		, tiMinimumSalesQuantity,tiLeadTime,siUnitOfMeasureCode,diPriceEffectiveDate,siPackagingCode
		,siCurrencyKind,rgiSupplierLease,taiAlternateSupplyLocationText,taiUnitOfMeasureClarificationText,
		rgiSpecialPackaging,taiSpecialPackagingText,tiSpecialPackagingPrice,tiPriceTypeCode,
		siProductCategoryCode,tiPriceHeldFirm,siProprietaryCode,siproductCategoryCode,siSupplierClaimedStatusCode
		);
		
		
		
	    
	    HLayout buttonPanel = new HLayout(10);
		IButton saveButton = new IButton();
		saveButton.setIcon("icons/save.png");
	    saveButton.setTitle(ConstantsUtil.buttonConstants.saveButton());
	    saveButton.setAlign(Alignment.CENTER);
	    IButton cancelButton = new IButton(ConstantsUtil.buttonConstants.cancelButton());
	    cancelButton.setIcon("icons/remove.png");
	    
	    IButton helpButton = new IButton(ConstantsUtil.buttonConstants.cancelButton());
	    helpButton.setIcon("icons/book_help.png");
		buttonPanel.addMember(saveButton);
		buttonPanel.addMember(cancelButton);
		buttonPanel.addMember(helpButton);
		
	   saveButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				form.saveData();
				ShowWindowTools.showCloseWindow(srcRect, PartSupplierPriceIndexAddWindow.this, -1);
			}
		});

	   HLayout tileGrid = new HLayout();
		tileGrid.setWidth100();
		tileGrid.setHeight("90%");

		tileGrid.setBorder("0px solid #9C9C9C");
		tileGrid.addChild(form);
		
		HLayout buttonGrid = new HLayout();
		buttonGrid.setWidth100();
		buttonGrid.setHeight("10%");

		buttonGrid.setBorder("0px solid #9C9C9C");
		saveButton.setTop("25%");
		buttonGrid.addChild(saveButton);

		
		VLayout vLayout = new VLayout();
		vLayout.addMember(tileGrid); 
		vLayout.addMember(buttonGrid);
		return vLayout;
		
	 } 
	}
 
