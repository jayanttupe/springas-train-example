package com.skynet.spms.client.gui.basedatamanager.organization.enterpriseInfo;

import java.util.HashMap;
import java.util.Map;

public class ClassifyHelper {

	private static Map<String,String> props = new HashMap<String,String>();
	
	static{
		props.put("organization.enterprise.customer.customer_dataSource",					"customerIdentificationCode");
		props.put("organization.enterprise.supplier.supplier_dataSource", 					"supplierCode");
		props.put("organization.enterprise.manufacturer.manufacturer_dataSource",			"manufacturerCode");
		props.put("organization.enterprise.clearanceAgency.clearanceAgency_dataSource", 	"clearanceAgent");
		props.put("organization.enterprise.carrier.carrier_dataSource", 					"carrierName");
		props.put("organization.enterprise.repairAgency.repairAgency_dataSource", 			"repairShopCode");

	}
	
	public static String getPropByDataSource(String dsName){
		return props.get(dsName);
	}
	
	public static boolean isNotMainEnterprise(String dsName){
		return props.containsKey(dsName);
	}
	
	public static boolean isCustomer(String dsName){
		if(props.containsKey(dsName) && dsName.equals("organization.enterprise.customer.customer_dataSource")){
			return true;
		}
		return false;
	}
	
	public static boolean canAppoint(String dsName){
		if(props.containsKey(dsName) && !dsName.equals("organization.enterprise.customer.customer_dataSource")){
			return true;
		}
		return false;
	}
}
