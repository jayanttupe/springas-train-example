package com.skynet.spms.client.gui.partcatalog.technicalCatalog.basicInformation;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.google.gwt.user.client.Window;
import com.skynet.spms.client.constants.ConstantsUtil;
import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.BtnsHLayout;
import com.skynet.spms.client.gui.base.EnumTool;
import com.skynet.spms.client.tools.ValidateUtils;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.MultipleAppearance;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.HeaderItem;
import com.smartgwt.client.widgets.form.fields.HiddenItem;
import com.smartgwt.client.widgets.form.fields.RadioGroupItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;


public class BasicInfoPanel extends VLayout {
	private DynamicForm form;
	private HLayout buttonPanel;
	private Record selectedRecord; 
	
	public BasicInfoPanel(final boolean editable) {
		DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource("partCatalog.technical", "basicInfo_dataSource", new PostDataSourceInit() {
			
			@Override
			public void doPostOper(DataSource dataSource, DataInfo dataInfo) {
				initPanel(dataSource,dataInfo);
				if(!editable){
					//form.setDisabled(true);
					buttonPanel.setVisible(false);
				}
			}
		});
	}
	
	public void fetchData(String basicInfoId){
		Criteria criteria = new Criteria("id",basicInfoId);
		form.fetchData(criteria,new DSCallback() {	
			@Override
			public void execute(DSResponse response, Object rawData, DSRequest request) {
				selectedRecord = response.getData()[0];
				form.getItem("id").setValue(selectedRecord.getAttribute("id"));
				
				form.getItem("keyword_zh").setValue(selectedRecord.getAttribute("keyword_zh"));
				form.getItem("keyword_en").setValue(selectedRecord.getAttribute("keyword_en"));
				form.getItem("partName_zh").setValue(selectedRecord.getAttribute("partName_zh"));
				form.getItem("partName_en").setValue(selectedRecord.getAttribute("partName_en"));
				
				form.getItem("nextHigherAssemblyPartNumber").setValue(selectedRecord.getAttribute("nextHigherAssemblyPartNumber"));
				
				String models = selectedRecord.getAttribute("suitableAircraftModel");
				if(models != null){
					String[] modelArr = models.split(",");
					((SelectItem)form.getItem("suitableAircraftModel")).setValues(modelArr);
				}
				form.getItem("m_SparePartClassCode").setValue(selectedRecord.getAttribute("m_SparePartClassCode"));
				form.getItem("m_PMAPartCausedDefectIndicator").setValue(selectedRecord.getAttribute("m_PMAPartCausedDefectIndicator"));
				form.getItem("m_EquipmentType").setValue(selectedRecord.getAttribute("m_EquipmentType"));
				form.getItem("engine").setValue(selectedRecord.getAttribute("engine"));	
				form.getItem("m_UnitOfMeasureCode").setValue(selectedRecord.getAttribute("m_UnitOfMeasureCode"));
				form.getItem("serial").setValue(selectedRecord.getAttribute("serial"));
				form.getItem("remarkText").setValue(selectedRecord.getAttribute("remarkText"));
				form.getItem("m_BuyerFurnishedEquipmentIndicator").setValue(selectedRecord.getAttribute("m_BuyerFurnishedEquipmentIndicator"));
				form.getItem("dataSource").setValue(selectedRecord.getAttribute("dataSource"));		
				//form.getItem("partSourceCode").setValue(selectedRecord.getAttribute("partSourceCode"));
				form.getItem("hazardousMaterial").setValue(selectedRecord.getAttribute("hazardousMaterial"));
				form.getItem("electrostaticSensitiveDeviceIndicator").setValue(selectedRecord.getAttribute("electrostaticSensitiveDeviceIndicator"));
				form.getItem("m_DangerCategory").setValue(selectedRecord.getAttribute("m_DangerCategory"));	
				form.getItem("hazardousMaterialDescription").setValue(selectedRecord.getAttribute("hazardousMaterialDescription"));
				
				String ataNumbers = selectedRecord.getAttribute("ataNumber");
				if(ataNumbers != null){
					String[] ataNumberArr = ataNumbers.split(",");
					((SelectItem)form.getItem("ataNumber")).setValues(ataNumberArr);
				}
				form.getItem("m_technicalPublishRecord.referenceNumber").setValue(selectedRecord.getAttribute("m_technicalPublishRecord.referenceNumber"));
				form.getItem("m_technicalPublishRecord.m_TechnicalPublishType").setValue(selectedRecord.getAttribute("m_technicalPublishRecord.m_TechnicalPublishType"));
				form.getItem("comacPatent").setValue(selectedRecord.getAttribute("comacPatent"));
				form.getItem("patentDescription").setValue(selectedRecord.getAttribute("patentDescription"));
				
				boolean isHaz = Boolean.valueOf((String)form.getItem("hazardousMaterial").getValue());
				resetHazardousItem(isHaz);
			}
		});
	}
	
	public void clearFormValues(){
		form.clearValues();
	}
	
	public void initPanel(DataSource dataSource,DataInfo dataInfo) {
		form = new DynamicForm();
		form.setDataSource(dataSource);
		form.setWidth(680);
		form.setPadding(5);
		form.setNumCols(4);  
		form.setColWidths(120,200,160,200);
		String requiredHint = "<font color=red>*</font>";
		LinkedHashMap<String, String> boolValueMap = new LinkedHashMap<String, String>();
	    boolValueMap.put("true",ConstantsUtil.commonConstants.choiceYes());
	    boolValueMap.put("false",ConstantsUtil.commonConstants.choiceNo());
		
		
		final List<FormItem> itemList = new ArrayList<FormItem>();
		HiddenItem hdnId = new HiddenItem("id"); 
		itemList.add(hdnId);
		/*//中文关键字
		TextItem textKeyword_zh=new TextItem("keyword_zh");
		textKeyword_zh.setHint(requiredHint);
		textKeyword_zh.setRequired(true);
		//textKeyword_zh.setValidators(ValidateUtils.StringLenValidator(8, 8));
		//textKeyword_zh.setValidateOnExit(true);
		textKeyword_zh.setWidth(200);
		itemList.add(textKeyword_zh);
		//英文关键字
		TextItem textKeyword_en=new TextItem("keyword_en");
		//textKeyword_en.setHint(requiredHint);
		//textKeyword_en.setRequired(true);
		//textKeyword_en.setValidators(ValidateUtils.StringLenValidator(8, 8));
		//textKeyword_en.setValidateOnExit(true);
		textKeyword_en.setWidth(200);
		itemList.add(textKeyword_en);*/
		//中文名称 
		TextItem textPartName_zh=new TextItem("partName_zh");
		textPartName_zh.setHint(requiredHint);
		textPartName_zh.setRequired(true);
		textPartName_zh.setWidth(200);
		itemList.add(textPartName_zh);
		//英文名称
		TextItem textPartName_en=new TextItem("partName_en");
		//textPartName_en.setHint(requiredHint);
		//textPartName_en.setRequired(true);
		textPartName_en.setWidth(200);
		itemList.add(textPartName_en);
		
		//上级组合件号
	    TextItem textHigherAssemblyPartNumber = new TextItem("nextHigherAssemblyPartNumber");
	    textHigherAssemblyPartNumber.setWidth(200);
	    itemList.add(textHigherAssemblyPartNumber);
	    
	    //适用机型
	    SelectItem selM_PartAircraftModelIdentifier = new SelectItem("suitableAircraftModel");
	    EnumTool.setValueMap("com.skynet.spms.persistence.entity.csdd.a.AircraftModelIdentifier",selM_PartAircraftModelIdentifier);
	    selM_PartAircraftModelIdentifier.setMultiple(true);
	    selM_PartAircraftModelIdentifier.setMultipleAppearance(MultipleAppearance.PICKLIST);
	    selM_PartAircraftModelIdentifier.setHint(requiredHint);
	    selM_PartAircraftModelIdentifier.setRequired(true);
	    selM_PartAircraftModelIdentifier.setWidth(280);
	    itemList.add(selM_PartAircraftModelIdentifier);
	    
	      //重要性代码
	    SelectItem selM_EssentialityCode = (SelectItem)dataInfo.getFieldInfoByName("m_EssentialityCode").generFormField();
	    selM_EssentialityCode.setHint(requiredHint);
	    selM_EssentialityCode.setRequired(true);
	    selM_EssentialityCode.setDefaultToFirstOption(true);
	    selM_EssentialityCode.setWidth(200);
	    itemList.add(selM_EssentialityCode);
	    
	    //航材类型代码
	    SelectItem selM_SparePartClassCode = (SelectItem)dataInfo.getFieldInfoByName("m_SparePartClassCode").generFormField();
	    selM_SparePartClassCode.setHint(requiredHint);
	    selM_SparePartClassCode.setRequired(true);
	    selM_SparePartClassCode.setDefaultToFirstOption(true);
	    selM_SparePartClassCode.setWidth(200);
	    itemList.add(selM_SparePartClassCode);
	    
	    //静电敏感性指示
	    RadioGroupItem radioIsElectrostaticSensitive = new RadioGroupItem("electrostaticSensitiveDeviceIndicator");  
	    radioIsElectrostaticSensitive.setValueMap(boolValueMap);
	    radioIsElectrostaticSensitive.setVertical(false);
	    radioIsElectrostaticSensitive.setWidth(150);
	    radioIsElectrostaticSensitive.setDefaultValue("false");
	    itemList.add(radioIsElectrostaticSensitive);
	    
	    //设备类型
	    SelectItem selEquipmentType = (SelectItem)dataInfo.getFieldInfoByName("m_EquipmentType").generFormField();
	    selEquipmentType.setWidth(200);
	    selEquipmentType.setDefaultToFirstOption(true);
	    itemList.add(selEquipmentType);
	    
	  //PMA标识
	    RadioGroupItem rgiPMAPartCaused = new RadioGroupItem("m_PMAPartCausedDefectIndicator");
	    LinkedHashMap<String, String> pmaValueMap = new LinkedHashMap<String, String>();
	    pmaValueMap.put("Y",ConstantsUtil.commonConstants.choiceYes());
	    pmaValueMap.put("N",ConstantsUtil.commonConstants.choiceNo());
	    rgiPMAPartCaused.setValueMap(pmaValueMap);
	    rgiPMAPartCaused.setDefaultValue("N");
	    rgiPMAPartCaused.setWidth(150);
	    rgiPMAPartCaused.setVertical(false);
	    itemList.add(rgiPMAPartCaused);

	    //计量代码
	    SelectItem selM_UnitOfMeasureCode = (SelectItem)dataInfo.getFieldInfoByName("m_UnitOfMeasureCode").generFormField();
	    selM_UnitOfMeasureCode.setWidth(200);
	    selM_UnitOfMeasureCode.setDefaultToFirstOption(true);
	    itemList.add(selM_UnitOfMeasureCode);
	    
	    //是否发动机件
	    RadioGroupItem rgiIsEngine = new RadioGroupItem("engine");
	    rgiIsEngine.setValueMap(boolValueMap);
	    rgiIsEngine.setVertical(false);
	    rgiIsEngine.setWidth(150);
	    rgiIsEngine.setDefaultValue("false");
	    itemList.add(rgiIsEngine);
	    	   
	    //数据来源
	    TextItem textDataSource = new TextItem("dataSource");
	    textDataSource.setWidth(200);
	    textDataSource.setValidators(ValidateUtils.isIntValidator());
	    textDataSource.setValidateOnExit(true);
	    itemList.add(textDataSource);
	  
	    //是否序号控制
	    RadioGroupItem rgiIsSerial = new RadioGroupItem("serial");
	    rgiIsSerial.setValueMap(boolValueMap);
	    rgiIsSerial.setVertical(false);
	    rgiIsSerial.setWidth(150);
	    rgiIsSerial.setDefaultValue("false");
	    itemList.add(rgiIsSerial);
	    
	    //备注文本
	    TextAreaItem textRemarkText = new TextAreaItem("remarkText");
	    textRemarkText.setWidth(280);  
	    textRemarkText.setRowSpan(2);
	    textRemarkText.setHeight(60);
	    itemList.add(textRemarkText);

	    //BFE/SFE
	    RadioGroupItem radioBuyerFurnishedE = new RadioGroupItem("m_BuyerFurnishedEquipmentIndicator");
	    LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
	    valueMap.put("BFE",ConstantsUtil.partCatalogConstants.choiceBFE());
	    valueMap.put("SFE",ConstantsUtil.partCatalogConstants.choiceSFE());
	    radioBuyerFurnishedE.setValueMap(valueMap);
	    radioBuyerFurnishedE.setDefaultValue("SFE");
	    radioBuyerFurnishedE.setWidth(150);
	    radioBuyerFurnishedE.setVertical(false);
	    itemList.add(radioBuyerFurnishedE);

	    
	    //危险品提示信息
	    HeaderItem dangerHeader = new HeaderItem();
	    dangerHeader.setDefaultValue(ConstantsUtil.partCatalogConstants.dangerInfo());
	    dangerHeader.setColSpan(3);
	    itemList.add(dangerHeader);
	    
	    //是否危险材料
	    RadioGroupItem radioIsHazardousMaterial = new RadioGroupItem("hazardousMaterial");
	    radioIsHazardousMaterial.setHint(requiredHint);
	    radioIsHazardousMaterial.setRequired(true);
	    radioIsHazardousMaterial.setValueMap(boolValueMap);
	    radioIsHazardousMaterial.setVertical(false);
	    radioIsHazardousMaterial.setWidth(150);
	    radioIsHazardousMaterial.setColSpan(3);
	    radioIsHazardousMaterial.setDefaultValue("false");
	    itemList.add(radioIsHazardousMaterial);
	    radioIsHazardousMaterial.addChangedHandler(new ChangedHandler() {
			@Override
			public void onChanged(ChangedEvent event) {
				boolean isHaz = Boolean.valueOf((String)event.getValue());
				resetHazardousItem(isHaz);
			}
		});

	    //危险品分类
	    SelectItem selM_DangerCategory = (SelectItem)dataInfo.getFieldInfoByName("m_DangerCategory").generFormField();
	    selM_DangerCategory.setWidth(150);
	    selM_DangerCategory.setAllowEmptyValue(true);
	    itemList.add(selM_DangerCategory);
	    
	    //危险品材料描述
	    TextItem textDesc = new TextItem("hazardousMaterialDescription");
	    textDesc.setWidth(200);
	    itemList.add(textDesc);
	    
	    //ATA提示信息
	    HeaderItem ataHeader = new HeaderItem();
	    ataHeader.setDefaultValue(ConstantsUtil.partCatalogConstants.ataDataInfo());
	    ataHeader.setColSpan(3);
	    itemList.add(ataHeader);
	    
	    //ATA章节号（飞机系统代码）
	    SelectItem selAtaNumber = new SelectItem("ataNumber");
	    EnumTool.setValueMap("com.skynet.spms.persistence.entity.csdd.a.AircraftSystemCode",selAtaNumber);
	    selAtaNumber.setHint(requiredHint);
	    selAtaNumber.setRequired(true);
	    selAtaNumber.setMultiple(true);
	    selAtaNumber.setMultipleAppearance(MultipleAppearance.PICKLIST);
	    selAtaNumber.setWidth(200);
	    selAtaNumber.setColSpan(3);
	    itemList.add(selAtaNumber);
	    
	    //技术引用号
	    TextItem textReferenceNumber = new TextItem("m_technicalPublishRecord.referenceNumber");
	    textReferenceNumber.setHint(requiredHint);
	    textReferenceNumber.setRequired(true);
	    itemList.add(textReferenceNumber);
	    textReferenceNumber.setWidth(200);
	     
	    //技术出版物
	    SelectItem selM_TechnicalPublishType = (SelectItem)dataInfo.getFieldInfoByName("m_technicalPublishRecord.m_TechnicalPublishType").generFormField(); 
	    selM_TechnicalPublishType.setWidth(200);
	    selM_TechnicalPublishType.setAllowEmptyValue(true);
	    itemList.add(selM_TechnicalPublishType);
	    
	    //COMAC专利件提示信息
	    HeaderItem patentHeader = new HeaderItem();
	    patentHeader.setDefaultValue(ConstantsUtil.partCatalogConstants.comacPatent());
	    patentHeader.setColSpan(3);
	    itemList.add(patentHeader);
	   
	    //是否COMAC专利件
	    RadioGroupItem radioIsPatent = new RadioGroupItem("comacPatent");  
	    radioIsPatent.setValueMap(boolValueMap);
	    radioIsPatent.setVertical(false);
	    radioIsPatent.setWidth(150);
	    itemList.add(radioIsPatent);
	   /* radioIsHazardousMaterial.addChangedHandler(new ChangedHandler() {
			@Override
			public void onChanged(ChangedEvent event) {
				boolean isHaz = Boolean.valueOf((String)event.getValue());
				resetComacPatent(isHaz);
			}
		});*/
	    
	    //专利权说明
	    TextAreaItem txaPatentDesc = new TextAreaItem("patentDescription"); 
	    txaPatentDesc.setWidth(280);
	    txaPatentDesc.setRowSpan(2);
	    txaPatentDesc.setHeight(60);
	    itemList.add(txaPatentDesc);
	    
		FormItem[] items = new FormItem[itemList.size()];
        itemList.toArray(items);     
        form.setFields(items); 
	    this.addMember(form);
		
		buttonPanel = new BtnsHLayout();
		buttonPanel.setHeight(30);
		IButton saveButton = new IButton(ConstantsUtil.buttonConstants.saveButton());
		saveButton.setIcon("icons/save.png");
		saveButton.addClickHandler(new ClickHandler() {	
			@Override
			public void onClick(ClickEvent event) {
				if(form.getItem("id").getValue() == null){
					Window.alert(ConstantsUtil.partCatalogConstants.alertForSelectIndex());
					return ;
				}
				form.validate();
				form.saveData(new DSCallback() {	
					@Override
					public void execute(DSResponse response, Object rawData, DSRequest request) {
						
						selectedRecord = response.getData()[0];
						String models = selectedRecord.getAttribute("suitableAircraftModel");
						if(models != null){
							String[] modelArr = models.split(",");
							((SelectItem)form.getItem("suitableAircraftModel")).setValues(modelArr);
						}
						String ataNumbers = selectedRecord.getAttribute("ataNumber");
						if(ataNumbers != null){
							String[] ataNumberArr = ataNumbers.split(",");
							((SelectItem)form.getItem("ataNumber")).setValues(ataNumberArr);
						}
						form.rememberValues();
						Window.alert(ConstantsUtil.commonConstants.alertForSaveSuccess());
					}
				});
			}
		});	
		IButton cancelButton = new IButton(ConstantsUtil.buttonConstants.cancelButton());
		cancelButton.setIcon("icons/remove.png");
		cancelButton.addClickHandler(new ClickHandler() {	
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
		buttonPanel.addMember(saveButton);
		buttonPanel.addMember(cancelButton);
		buttonPanel.addMember(helpButton);	
		
		HLayout wrapper = new HLayout();
		wrapper.setWidth100();
		wrapper.setHeight100();
		this.addMember(wrapper); 
		
	    this.addMember(buttonPanel);       
	}
	
	private void resetHazardousItem(boolean isHazardousMaterial){
		boolean disabled = true;
		if(isHazardousMaterial){
			disabled = false;
		}
		form.getItem("m_DangerCategory").setDisabled(disabled);
		form.getItem("hazardousMaterialDescription").setDisabled(disabled);
		if(disabled){
			form.getItem("m_DangerCategory").clearValue();
			form.getItem("hazardousMaterialDescription").clearValue();
		}else{
			if(selectedRecord != null){
				form.getItem("m_DangerCategory").setValue(selectedRecord.getAttribute("m_DangerCategory"));
				form.getItem("hazardousMaterialDescription").setValue(selectedRecord.getAttribute("hazardousMaterialDescription"));
			}
		}
	}
	
	/*//选择非专利件时，无需说明专利权
	private void resetComacPatent(boolean isComacPatent){
		boolean disabled = true;
		if(isComacPatent){
			disabled=false;
		}
		
		form.getItem("patentDescription").setDisabled(disabled);
	}*/
}
