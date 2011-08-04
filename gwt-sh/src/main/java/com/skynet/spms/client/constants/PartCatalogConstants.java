package com.skynet.spms.client.constants;

import com.google.gwt.i18n.client.Constants;

public interface PartCatalogConstants extends Constants{

	String addApplicationData();
	String modifyApplicationData();
    
	String addBasicInfoData();
	String modifyBasicInfoData();
	
	String addIndexInfo();
	String modifyIndexInfo();
	
	String addTechnicalData();
    String modifyTechnicalData();

    String editCustomsClearanceData();


	String modifyAircraftConfigCatalog();
	String addAircraftConfigCatalog();
	
	String addSalesCatalog();
	String modifySalesCatalog();

	String choiceBFE();
	String choiceSFE();
	
	String dangerInfo();
	String ataDataInfo();
	String comacPatent();
	String reliabilityReferenceValue();
	
	String addPartSupplierPrice();
	
	String modifyPartSupplierPrice();
	
	String addRepairData();
	String modifyRepairData();
	
	String repairShopCode();
	
	String addTechnicalDocument();
	String modifyTechnicalDocument();
	
	String alertForSelectIndex();
	String alertForSelectSalePriceRelease();
	String alertForSelectSupplierPriceIndex();
	
	//飞机构型字段国际化
	String aircraftModelIdentifier();
	String aircraftTailNumber();
	String aircraftRegistrationNumber();
	String countryCode();
	String operator();
	String owner();
	String factoryDate();
	String registrationDate();
	
	String basicInfo();
	String applicationData();
	String technicalData();
	String customsClearanceData();
	String optionalData();
	String aircraftConfiguration();
	String timeCyclesData();
	String singlePartQuery();
	String partBagItem();
}