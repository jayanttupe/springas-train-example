package com.skynet.spms.client.gui.partcatalog.salesCatalog.salesPrice;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.google.gwt.user.client.Window;
import com.skynet.spms.client.constants.ConstantsUtil;
import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.BtnsHLayout;
import com.skynet.spms.client.tools.ValidateUtils;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.MultipleAppearance;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.CanvasItem;
import com.smartgwt.client.widgets.form.fields.DateItem;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.HiddenItem;
import com.smartgwt.client.widgets.form.fields.RadioGroupItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;

public class SalesPricePanel extends VLayout {
	private DynamicForm form;
	private AlternateSupplyLocationListGrid alternateSupplyLocationListGrid;
	private OtherChargeListGrid otherChargeListGrid;
	private PriceBreakListGrid priceBreakListGrid;

	public SalesPricePanel(){
		DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource("partCatalog.sales", "salesPrice_dataSource", new PostDataSourceInit() {
			@Override
			public void doPostOper(DataSource dataSource, DataInfo dataInfo) {
				initPanel(dataSource,dataInfo);
			}
		});
	}

	public void initPanel(DataSource dataSource,DataInfo dataInfo) {
		form = new DynamicForm();
		form.setDataSource(dataSource);
		form.setWidth100();
		form.setPadding(5);
		form.setNumCols(6);  
		form.setLayoutAlign(VerticalAlignment.BOTTOM);
		
		LinkedHashMap<String, String> boolValueMap = new LinkedHashMap<String, String>();
	    boolValueMap.put("true",ConstantsUtil.commonConstants.choiceYes());
	    boolValueMap.put("false",ConstantsUtil.commonConstants.choiceNo());
	    String requiredHint = "<font color=red>*</font>";
	    
	    //销售价格信息表单
		List<FormItem> itemList = new ArrayList<FormItem>();
		HiddenItem hdnId = new HiddenItem("id"); 
		itemList.add(hdnId);

		//包装代码
		SelectItem rgiPackagingCode = (SelectItem)dataInfo.getFieldInfoByName("m_PackagingCode").generFormField();
		rgiPackagingCode.setDefaultToFirstOption(true);
		rgiPackagingCode.setColSpan(3);
		rgiPackagingCode.setWidth(240);
		itemList.add(rgiPackagingCode);
		
		 //所有权代码
		SelectItem rgiProprietaryCode = (SelectItem)dataInfo.getFieldInfoByName("m_ProprietaryCode").generFormField();
		rgiProprietaryCode.setDefaultToFirstOption(true);
		rgiProprietaryCode.setWidth(240);
		itemList.add(rgiProprietaryCode);
		 
		//产品分类代码
		SelectItem rgiCategoryIContainerPriceAmount = (SelectItem)dataInfo.getFieldInfoByName("m_ProductCategoryCode").generFormField();
		rgiCategoryIContainerPriceAmount.setDefaultToFirstOption(true);
		rgiCategoryIContainerPriceAmount.setColSpan(3);
		rgiCategoryIContainerPriceAmount.setWidth(240);
		itemList.add(rgiCategoryIContainerPriceAmount);

		//声明状态
		SelectItem selClaimedStatusCode =(SelectItem)dataInfo.getFieldInfoByName("m_SupplierClaimedStatusCode").generFormField();
		selClaimedStatusCode.setDefaultToFirstOption(true);
		selClaimedStatusCode.setWidth(240);
		itemList.add(selClaimedStatusCode);
		 
		 //标准包装数量
		TextItem rgiStandardPackageQuantity = new TextItem("standardPackageQuantity");
		rgiStandardPackageQuantity.setRequired(true);
		rgiStandardPackageQuantity.setValidators(ValidateUtils.isIntValidator());
		rgiStandardPackageQuantity.setValidateOnChange(true);
		rgiStandardPackageQuantity.setWidth(100);
		itemList.add(rgiStandardPackageQuantity);
		//标准包装数量单位代码
		SelectItem selUnitOfMeasureCode=(SelectItem)dataInfo.getFieldInfoByName("spqUnitCode").generFormField();
		selUnitOfMeasureCode.setDefaultToFirstOption(true);
		selUnitOfMeasureCode.setWidth(80);
		itemList.add(selUnitOfMeasureCode);
		
		 //随件证书 类型       可多选
		SelectItem sltCertificateType= new SelectItem("certificateType");
		sltCertificateType.setWidth(240);
		sltCertificateType.setValueMap(
				"CASAForm917",
				"JAAFormOne",
				"CAAC_F038",
				"TCCA_24_0078",
				"EASAForm1",
				"certificateOfConformance",
				"FAAForm8130_3",
				"TransferDocument");
		sltCertificateType.setMultiple(true);
		sltCertificateType.setMultipleAppearance(MultipleAppearance.PICKLIST);
	    itemList.add(sltCertificateType);
		
		//最小销售数量
		TextItem textMinimumSalesQuantity= new TextItem("minimumSalesQuantity");
		textMinimumSalesQuantity.setValidators(ValidateUtils.isIntValidator());
		textMinimumSalesQuantity.setValidateOnChange(true);
		textMinimumSalesQuantity.setWidth(100);
		itemList.add(textMinimumSalesQuantity);
		
		//最小销售数量单位代码
		SelectItem selMinsqUnitCode=(SelectItem)dataInfo.getFieldInfoByName("minsqUnitCode").generFormField();
		selMinsqUnitCode.setDefaultToFirstOption(true);
		selMinsqUnitCode.setWidth(80);
		itemList.add(selMinsqUnitCode);
		
		//是否租赁
		RadioGroupItem rgiLeaseIndicator = new RadioGroupItem("m_LeaseIndicator");
		rgiLeaseIndicator.setWidth(200);
		LinkedHashMap<String, String> leaseValueMap = new LinkedHashMap<String, String>();
		leaseValueMap.put("Y", ConstantsUtil.commonConstants.choiceYes());
		leaseValueMap.put("N", ConstantsUtil.commonConstants.choiceNo());
		rgiLeaseIndicator.setValueMap(leaseValueMap);
		rgiLeaseIndicator.setValueMap(boolValueMap);
		rgiLeaseIndicator.setVertical(false);
		itemList.add(rgiLeaseIndicator);

		//可选供应地址
		CanvasItem cavLoc = new CanvasItem();
		cavLoc.setColSpan(3);
		cavLoc.setRowSpan(3);
		cavLoc.setTitle("可选供应地址");
		cavLoc.setWidth(240);
		VLayout canvas = new VLayout();
		alternateSupplyLocationListGrid = new AlternateSupplyLocationListGrid();
		HLayout asllgButtonPanel = new HLayout(5);
		asllgButtonPanel.setHeight(20);
		IButton asllgNewButton = new IButton(ConstantsUtil.buttonConstants.newButton());
		asllgNewButton.setWidth(50);
		asllgNewButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				String priceId = (String)form.getItem("id").getValue();
				if(priceId == null){
					Window.alert(ConstantsUtil.partCatalogConstants.alertForSelectSalePriceRelease());
					return ;
				}
				LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
				map.put("priceId", priceId);
				alternateSupplyLocationListGrid.startEditingNew(map);
			}
		});
		IButton asllgSaveButton = new IButton(ConstantsUtil.buttonConstants.saveButton());
		asllgSaveButton.setWidth(50);
		asllgSaveButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				alternateSupplyLocationListGrid.saveAllEdits();
			}
		});
		IButton asllgCancelButton=new IButton(ConstantsUtil.buttonConstants.cancelButton());
		asllgCancelButton.setWidth(50);
		asllgCancelButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				alternateSupplyLocationListGrid.discardAllEdits();
			}
		});
		IButton asllgDeleteButton = new IButton(ConstantsUtil.buttonConstants.deleteButton());
		asllgDeleteButton.setWidth(50);
		asllgDeleteButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				alternateSupplyLocationListGrid.removeSelectedData();
			}
		});
		asllgButtonPanel.addMember(asllgNewButton);
		asllgButtonPanel.addMember(asllgSaveButton);
		asllgButtonPanel.addMember(asllgCancelButton);
		asllgButtonPanel.addMember(asllgDeleteButton);
		
		canvas.addMember(alternateSupplyLocationListGrid);
		canvas.addMember(asllgButtonPanel);
		cavLoc.setCanvas(canvas);
		itemList.add(cavLoc);
		
		 //单位说明
		TextAreaItem taiUnitOfMeasureClarificationText = new TextAreaItem("unitOfMeasureClarificationText");
		taiUnitOfMeasureClarificationText.setRowSpan(3); 
		taiUnitOfMeasureClarificationText.setWidth(240);  
		itemList.add(taiUnitOfMeasureClarificationText);

		//是否特殊包装
		RadioGroupItem rgiIsSpecialPack= new RadioGroupItem("m_SpecialPackaging.specialPack");
		rgiIsSpecialPack.setWidth(200);
		rgiIsSpecialPack.setColSpan(3);
		rgiIsSpecialPack.setHint(requiredHint);
		rgiIsSpecialPack.setValueMap(boolValueMap);
		rgiIsSpecialPack.setVertical(false);
		itemList.add(rgiIsSpecialPack);
		//特殊包装说明
		 TextAreaItem taiSpecialPackingInstruction= new TextAreaItem("m_SpecialPackaging.specialPackingInstruction");
		 taiSpecialPackingInstruction.setWidth(240);  
		 taiSpecialPackingInstruction.setRowSpan(3);  
		 itemList.add(taiSpecialPackingInstruction);
		
		//特殊包装价格
		 TextItem texSpecialPackPrice= new TextItem("m_SpecialPackaging.specialPackPrice"); 
		 texSpecialPackPrice.setValidators(ValidateUtils.isFloatValidator());
		 texSpecialPackPrice.setValidateOnChange(true);
		 texSpecialPackPrice.setRowSpan(2);
		 texSpecialPackPrice.setWidth(100);
		 itemList.add(texSpecialPackPrice);
		//特殊包装价格币种
		 SelectItem selSppCurrencyCode=(SelectItem)dataInfo.getFieldInfoByName("m_SpecialPackaging.sppCurrencyCode").generFormField();
		 selSppCurrencyCode.setDefaultToFirstOption(true);
		 selSppCurrencyCode.setRowSpan(2);
		 selSppCurrencyCode.setWidth(80);
		 itemList.add(selSppCurrencyCode);
		 
		//价格类型代码
		 SelectItem selPriceTypeCode=(SelectItem)dataInfo.getFieldInfoByName("m_PriceTypeCode").generFormField();
		 selPriceTypeCode.setDefaultToFirstOption(true);
		 selPriceTypeCode.setColSpan(3);
		 selPriceTypeCode.setWidth(240);
		 itemList.add(selPriceTypeCode);
		 
		//价格有效期
		 DateItem texPriceEffectiveDate= new DateItem("priceEffectiveDate");
		 texPriceEffectiveDate.setWidth(240);
		 /*texPriceEffectiveDate.setUseTextField(true);  
		 texPriceEffectiveDate.setUseMask(true);*/
		itemList.add(texPriceEffectiveDate);
		
		//单价
		TextItem texUnitPriceAmount= new TextItem("unitPriceAmount");
		texUnitPriceAmount.setWidth(100);
		texUnitPriceAmount.setHint(requiredHint);
		texUnitPriceAmount.setRequired(true);
		texUnitPriceAmount.setValidateOnChange(true);
		itemList.add(texUnitPriceAmount);
		//单价币种
		SelectItem selUpaCurrencyCode=(SelectItem)dataInfo.getFieldInfoByName("upaCurrencyCode").generFormField();
		selUpaCurrencyCode.setDefaultToFirstOption(true);
		selUpaCurrencyCode.setWidth(80);
		itemList.add(selUpaCurrencyCode);
		
		//交货(期)天
		TextItem texLeadTime= new TextItem("leadTime");
		texLeadTime.setRequired(true);
		texLeadTime.setValidateOnChange(true);
		texLeadTime.setWidth(240);
		itemList.add(texLeadTime);
		
		//一类容器价格
		TextItem texCategoryOne= new TextItem("categoryOneContainersPrice");
		texCategoryOne.setWidth(100);
		texCategoryOne.setHint(requiredHint);
		texCategoryOne.setRequired(true);
		texCategoryOne.setValidateOnChange(true);
		itemList.add(texCategoryOne);
		//一类容器价格单位
		SelectItem selCocpCurrencyCodeb=(SelectItem)dataInfo.getFieldInfoByName("cocpCurrencyCode").generFormField();
		selCocpCurrencyCodeb.setDefaultToFirstOption(true);
		selCocpCurrencyCodeb.setWidth(80);
		itemList.add(selCocpCurrencyCodeb);
		
		FormItem[] items = new FormItem[itemList.size()];
	    itemList.toArray(items); 
	    form.setFields(items);
	    form.setColWidths(120, 100,40,80, 200, "*"); 
	    this.addMember(form);
	     
	    //form按钮条
	    HLayout formButtonPanel = new BtnsHLayout();
	    formButtonPanel.setHeight(30);

		IButton formSaveButton = new IButton(ConstantsUtil.buttonConstants.saveButton());
		formSaveButton.setIcon("icons/save.png");
		formSaveButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if(form.getItem("id").getValue() == null){
					Window.alert(ConstantsUtil.partCatalogConstants.alertForSelectSalePriceRelease());
					return;
				}
				/*ListGridRecord[] records = new ListGridRecord[alternateSupplyLocationListGrid.getTotalRows()];
				for (int i = 0; i < alternateSupplyLocationListGrid.getTotalRows(); i++){
					records[i] = (ListGridRecord)alternateSupplyLocationListGrid.getEditedRecord(i);
				}
				form.setValue("m_AlternateSupplyLocationText", records);*/
				form.validate();
				form.saveData(new DSCallback() {
					@Override
					public void execute(DSResponse response, Object rawData, DSRequest request) {
						form.rememberValues();
						Window.alert(ConstantsUtil.commonConstants.alertForSaveSuccess());
					}
				});
			}
		});
		IButton formCancelButton=new IButton(ConstantsUtil.buttonConstants.cancelButton());
		
		formCancelButton.setIcon("icons/remove.png");
		formCancelButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				form.reset();
			}
		});
		IButton helpButton = new IButton();
		helpButton.setIcon("icons/book_help.png");
	    helpButton.setTitle(ConstantsUtil.buttonConstants.helpButton());
	    helpButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				
			}
		});
		formButtonPanel.addMember(formSaveButton);
		formButtonPanel.addMember(formCancelButton);
		formButtonPanel.addMember(helpButton);
		this.addMember(formButtonPanel);
	     
		//其他费用
        SectionStackSection section1 = new SectionStackSection();
        section1.setTitle("其他费用");
        section1.setCanCollapse(false);  
        section1.setExpanded(true); 
        otherChargeListGrid = new OtherChargeListGrid();
        
        HLayout oclgButtonPanel = new BtnsHLayout();
        oclgButtonPanel.setHeight(30);
		IButton oclgNewButton = new IButton(ConstantsUtil.buttonConstants.newButton());
		oclgNewButton.setIcon("icons/add_list.png");
		oclgNewButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				String priceId = (String)form.getItem("id").getValue();
				if(priceId == null){
					Window.alert(ConstantsUtil.partCatalogConstants.alertForSelectSalePriceRelease());
					return ;
				}
				LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
				map.put("priceId", priceId);
				otherChargeListGrid.startEditingNew(map);
			}
		});
		IButton oclgSaveButton=new IButton(ConstantsUtil.buttonConstants.saveButton());
		oclgSaveButton.setIcon("icons/save.png");
		oclgSaveButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				otherChargeListGrid.saveAllEdits();
			}
		});
		IButton oclgCancelButton=new IButton(ConstantsUtil.buttonConstants.cancelButton());
		oclgCancelButton.setIcon("icons/remove.png");
		oclgCancelButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				otherChargeListGrid.discardAllEdits();
			}
		});
		IButton oclgDeleteButton = new IButton(ConstantsUtil.buttonConstants.deleteButton());
		oclgDeleteButton.setIcon("icons/delete_list.png");
		oclgDeleteButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if(otherChargeListGrid.getSelection().length == 0){
					Window.alert(ConstantsUtil.commonConstants.alertSelectForDelete());
				}else{
					boolean isDel = Window.confirm(ConstantsUtil.commonConstants.ConfirmForDelete());
					if (isDel){
						otherChargeListGrid.removeSelectedData();
				     } 
				}
			}
		});
		oclgButtonPanel.addMember(oclgNewButton);
		oclgButtonPanel.addMember(oclgSaveButton);
		oclgButtonPanel.addMember(oclgCancelButton);
		oclgButtonPanel.addMember(oclgDeleteButton);
        section1.setItems(otherChargeListGrid,oclgButtonPanel);
        
        SectionStack sectionStack1 = new SectionStack();
		sectionStack1.setWidth100();
		sectionStack1.setHeight(150);
	    sectionStack1.setSections(section1);
	    this.addMember(sectionStack1);

		//基础数量价格分段明细表
        SectionStackSection section2 = new SectionStackSection();
        section2.setTitle("基础数量价格分段明细表");
        section2.setCanCollapse(false);  
        section2.setExpanded(true); 
        priceBreakListGrid = new PriceBreakListGrid();
        
        HLayout pblgButtonPanel = new BtnsHLayout();
        pblgButtonPanel.setHeight(30);
		IButton pblgNewButton = new IButton(ConstantsUtil.buttonConstants.newButton());
		pblgNewButton.setIcon("icons/add_list.png");
		pblgNewButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				String priceId = (String)form.getItem("id").getValue();
				if(priceId == null){
					Window.alert(ConstantsUtil.partCatalogConstants.alertForSelectSalePriceRelease());
					return ;
				}
				LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
				map.put("priceId", priceId);
				priceBreakListGrid.startEditingNew(map);
			}
		});
		IButton pblgSaveButton=new IButton(ConstantsUtil.buttonConstants.saveButton());
		pblgSaveButton.setIcon("icons/save.png");
		pblgSaveButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				priceBreakListGrid.saveAllEdits();
			}
		});
		IButton pblgCancelButton=new IButton(ConstantsUtil.buttonConstants.cancelButton());
		pblgCancelButton.setIcon("icons/remove.png");
		pblgCancelButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				priceBreakListGrid.discardAllEdits();
			}
		});
		IButton pblgDeleteButton = new IButton(ConstantsUtil.buttonConstants.deleteButton());
		pblgDeleteButton.setIcon("icons/delete_list.png");
		pblgDeleteButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if(priceBreakListGrid.getSelection().length == 0){
					Window.alert(ConstantsUtil.commonConstants.alertSelectForDelete());
				}else{
					boolean isDel = Window.confirm(ConstantsUtil.commonConstants.ConfirmForDelete());
					if (isDel){
						priceBreakListGrid.removeSelectedData();
				     } 
				}
			}
		});
		pblgButtonPanel.addMember(pblgNewButton);
		pblgButtonPanel.addMember(pblgSaveButton);
		pblgButtonPanel.addMember(pblgCancelButton);
		pblgButtonPanel.addMember(pblgDeleteButton);
	    section2.setItems(priceBreakListGrid,pblgButtonPanel);
	    
	    SectionStack sectionStack2 = new SectionStack();
		sectionStack2.setWidth100();
		sectionStack2.setHeight(150);
	    sectionStack2.setSections(section2);
	    this.addMember(sectionStack2);    
	}
	
	public void fetchData(final String salesPriceId){
		Criteria criteria = new Criteria("id",salesPriceId);
        form.fetchData(criteria,new DSCallback() {	
			@Override
			public void execute(DSResponse response, Object rawData, DSRequest request) {
				Record record = response.getData()[0];
				form.getItem("id").setValue(record.getAttribute("id"));
				form.getItem("m_PackagingCode").setValue(record.getAttribute("m_PackagingCode"));
				form.getItem("m_ProprietaryCode").setValue(record.getAttribute("m_ProprietaryCode"));
				form.getItem("m_ProductCategoryCode").setValue(record.getAttribute("m_ProductCategoryCode"));
				form.getItem("m_SupplierClaimedStatusCode").setValue(record.getAttribute("m_SupplierClaimedStatusCode"));
				form.getItem("standardPackageQuantity").setValue(record.getAttribute("standardPackageQuantity"));
				form.getItem("spqUnitCode").setValue(record.getAttribute("spqUnitCode"));
				String types = record.getAttribute("certificateType");
				if(types != null){
					String[] typeArr = types.split(",");
					((SelectItem)form.getItem("certificateType")).setValues(typeArr);
				}
				
				form.getItem("minimumSalesQuantity").setValue(record.getAttribute("minimumSalesQuantity"));
				form.getItem("minsqUnitCode").setValue(record.getAttribute("minsqUnitCode"));
				form.getItem("m_LeaseIndicator").setValue(record.getAttribute("m_LeaseIndicator"));
				form.getItem("unitOfMeasureClarificationText").setValue(record.getAttribute("unitOfMeasureClarificationText"));
				form.getItem("m_SpecialPackaging.specialPack").setValue(record.getAttribute("m_SpecialPackaging.specialPack"));
				form.getItem("m_SpecialPackaging.specialPackingInstruction").setValue(record.getAttribute("m_SpecialPackaging.specialPackingInstruction"));
				form.getItem("m_SpecialPackaging.specialPackPrice").setValue(record.getAttribute("m_SpecialPackaging.specialPackPrice"));
				form.getItem("m_SpecialPackaging.specialPackPrice").setValue(record.getAttribute("m_SpecialPackaging.specialPackPrice"));
				form.getItem("m_PriceTypeCode").setValue(record.getAttribute("m_PriceTypeCode"));
				form.getItem("priceEffectiveDate").setValue(record.getAttribute("priceEffectiveDate"));
				form.getItem("unitPriceAmount").setValue(record.getAttribute("unitPriceAmount"));
				form.getItem("upaCurrencyCode").setValue(record.getAttribute("upaCurrencyCode"));
				form.getItem("leadTime").setValue(record.getAttribute("leadTime"));
				form.getItem("categoryOneContainersPrice").setValue(record.getAttribute("categoryOneContainersPrice"));
				form.getItem("cocpCurrencyCode").setValue(record.getAttribute("cocpCurrencyCode"));
	        }
		});
        
        Criteria criteria2 = new Criteria("priceId",salesPriceId);
        alternateSupplyLocationListGrid.fetchData(criteria2);
        otherChargeListGrid.fetchData(criteria2);
        priceBreakListGrid.fetchData(criteria2);
	}
	public void clearFormValues(){
		form.clearValues();
		Criteria criteria2 = new Criteria("priceId","clear");
		alternateSupplyLocationListGrid.fetchData(criteria2);
        otherChargeListGrid.fetchData(criteria2);
        priceBreakListGrid.fetchData(criteria2);
	}
}
		