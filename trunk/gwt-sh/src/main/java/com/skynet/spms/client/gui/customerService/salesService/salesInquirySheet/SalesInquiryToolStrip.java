package com.skynet.spms.client.gui.customerService.salesService.salesInquirySheet;

import com.skynet.spms.client.gui.base.BaseButtonToolStript;
import com.skynet.spms.client.gui.base.ValidateUtil;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

public class SalesInquiryToolStrip extends BaseButtonToolStript {

	private SalesInquiryListGrid salesInquiryListgrid;

	public SalesInquiryToolStrip(SalesInquiryListGrid salesInquiryListgrid) {
		super("customerService.salesService.salesInquirySheet", 0, 11);
		this.salesInquiryListgrid = salesInquiryListgrid;
		this.setWidth100();
		this.setHeight(30);
		this.setLayoutLeftMargin(5);
		this.setLayoutRightMargin(5);
	}

	@Override
	protected void showWindow(String buttonName, ToolStripButton button) {
		//ADD,DELETE,MODIFY,PUBLISH,CANCELPUBLISH,ADDSALESQUOTATION,MESSAGE,LOG,PRINT,EXPORT,SENIORSEARCH
		if ("ADD".equals(buttonName)) {
			SalesInquiryAddWindow addWin = new SalesInquiryAddWindow(
					salesInquiryListgrid);
			ShowWindowTools.showWondow(button.getPageRect(), addWin, 500);
		} else if ("MODIFY".equals(buttonName)) {
			if (ValidateUtil.isRecordSelected(salesInquiryListgrid)) {
				SalesInquiryModifyWindow modifyWin = new SalesInquiryModifyWindow(
						salesInquiryListgrid);
				ShowWindowTools
						.showWondow(button.getPageRect(), modifyWin, 500);
			}
		} else if ("DELETE".equals(buttonName)) {

			SC.confirm("确定删除询价单?", new BooleanCallback() {
				public void execute(Boolean value) {
					if (value != null && value) {
						salesInquiryListgrid.removeData(salesInquiryListgrid
								.getSelectedRecord());
					}
				}
			});

		} else if ("PUBLISH".equals(buttonName)) {
			SC.confirm("确定发布此询价单吗？",new BooleanCallback(){
				@Override
				public void execute(Boolean value) {
					// TODO Auto-generated method stub
					ListGridRecord lgr = salesInquiryListgrid.getSelectedRecord();
					lgr.setAttribute("m_BussinessStatusEntity.status", "submited");
					salesInquiryListgrid.updateData(lgr);
				}
				
			});
			
		} else if ("CANCELPUBLISH".equals(buttonName)) {
			SC.confirm("确定取消发布此询价单吗？",new BooleanCallback(){
				@Override
				public void execute(Boolean value) {
					// TODO Auto-generated method stub
					ListGridRecord lgr = salesInquiryListgrid.getSelectedRecord();
					lgr.setAttribute("m_BussinessStatusEntity.status", "refused");
					salesInquiryListgrid.updateData(lgr);
				}
				
			});
		} else if ("ADDSALESQUOTATION".equals(buttonName)) {

		} else if ("MESSAGE".equals(buttonName)) {

		} else if ("LOG".equals(buttonName)) {

		} else if ("PRINT".equals(buttonName)) {

		} else if ("EXPORT".equals(buttonName)) {

		} else if ("SENIORSEARCH".equals(buttonName)) {

		}

	}

}
