package com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementInquirySheet;

import com.skynet.spms.client.gui.base.BaseButtonToolStript;
import com.skynet.spms.client.gui.base.Utils;
import com.skynet.spms.client.gui.supplierSupport.common.DSKey;
import com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementInquirySheet.add.ProcurementQuotationSheetAddWindow;
import com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementInquirySheet.business.ProcurementInquirySheetBusiness;
import com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementInquirySheet.modity.ProcurementInquirySheetModityWindow;
import com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementInquirySheet.view.ProcurementInquirySheetViewWindow;
import com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementOrder.model.ProcurementOrderModelLocator;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

/**
 * 主订单操作按钮
 * 
 * @author Tony FANG
 * 
 */
public class ProcurementInquirySheetToolStrip extends BaseButtonToolStript {

	private ProcurementInquirySheetBusiness pisiBusiness;

	private ProcurementInquirySheetListGrid proInquiryListgrid;

	public ProcurementInquirySheetToolStrip(
			ProcurementInquirySheetListGrid listGrid) {
		super(DSKey.S_PROCUREMENTINQUIRYSHEET);
		this.proInquiryListgrid = listGrid;
		this.setWidth100();
		this.setHeight(30);
		this.setLayoutLeftMargin(5);
		this.setLayoutRightMargin(5);

	}


	@Override
	protected void showWindow(String buttonName, final ToolStripButton button) {
		pisiBusiness= new ProcurementInquirySheetBusiness();
		if ("ADD".equals(buttonName)) {
			
		} else if ("MODIFY".equals(buttonName)) {
			
			if (pisiBusiness.canModifiedSheet(proInquiryListgrid)) {
				ProcurementInquirySheetModityWindow ppa = new ProcurementInquirySheetModityWindow(
						"修改采购询价单", true, button.getPageRect(), null, "");
				ShowWindowTools.showWindow(button.getPageRect(), ppa, 200);
			}

		} else if ("ADDPROCUREMENTQUOTATION".equals(buttonName)) {
			
			if(pisiBusiness.canDoNextBusiness(proInquiryListgrid)){
				ProcurementQuotationSheetAddWindow win = new ProcurementQuotationSheetAddWindow(
						"新建采购报价单", true, null, null, null);
				ShowWindowTools.showWindow(button.getPageRect(), win, 200);
			}
			

		} else if ("DELETE".equals(buttonName)) {
			pisiBusiness.deleteSheet(proInquiryListgrid);
		} else if ("PUBLISH".equals(buttonName)) {
			pisiBusiness.publishSheet(proInquiryListgrid);
		} else if ("CANCELPUBLISH".equals(buttonName)) {
			pisiBusiness.cancelPublishSheet(proInquiryListgrid);
		} else if("VIEW".equals(buttonName)){
				ProcurementInquirySheetViewWindow win = new ProcurementInquirySheetViewWindow(
						"采购报价单信息", true, null, null, null);
				ShowWindowTools.showWindow(button.getPageRect(), win, 200);
		}else if("REFRESH".equals(buttonName)){
			pisiBusiness.refreshSheet(proInquiryListgrid);
		}
	}
}
