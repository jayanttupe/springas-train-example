package com.skynet.spms.client.gui.supplierSupport.procurementOrder.suppliersParity.model;

import com.skynet.spms.client.model.IModelLocator;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.skynet.spms.client.widgets.form.LayoutDynamicForm;
/**
 * 存放采购指令业务数据
 * 
 * @author tqc
 * 
 */
public class SuppliersParityModel implements
		IModelLocator<SuppliersParityModel> {

	private static SuppliersParityModel instance;

	private SuppliersParityModel() {
	}

	public static SuppliersParityModel getInstance() {
		if (instance == null) {
			instance = new SuppliersParityModel();
		}
		return instance;
	}


	
	
	/**采购询价单DS**/
	public DataSource procurementInquiryDS;
	/**采购询价单明细DS**/
	public DataSource procurementInquiryItemDS;
	
	/**采购询价单明细项*/
	public ListGridRecord procurementInquiryLGR;
	
	public ListGridRecord procurementQuotationLGR;
	
	public ListGrid procurementQuotationLG;
	
	public LayoutDynamicForm procurementQuotationForm= new LayoutDynamicForm();
	
	
	public String suppliersParityItemIds="-1";
	public String partNumber="";
	public String contractId;
	
	public DataSource procurementQuotationItemDS;
	

}
