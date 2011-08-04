package com.skynet.spms.client.gui.supplierSupport.customerRepairInsOrder;

import com.skynet.spms.client.gui.base.BaseButtonToolStript;
import com.skynet.spms.client.gui.supplierSupport.common.DSKey;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

/**
 * 面板工具栏
 * 
 * @author tqc
 * 
 */
public class CustomerRepairInsOrderToolStrip extends BaseButtonToolStript {

	private CustomerRepairInsOrderListGrid listgrid;
	
	Business business=new Business();

	public CustomerRepairInsOrderToolStrip(
			final CustomerRepairInsOrderListGrid listgrid) {
		super(DSKey.C_USTOMERREPAIRINSORDER);
		this.setWidth100();
		this.setHeight(30);
		this.setLayoutLeftMargin(5);
		this.setLayoutRightMargin(5);
		this.listgrid = listgrid;
	}

	protected void showWindow(String buttonName, ToolStripButton button) {
		if ("VIEW".equals(buttonName)) {// 查看
			business.viewSheetDetail(listgrid, button);
		}else if("ADDCONTRACT".equals(buttonName)){//新建合同
			business.addContract(listgrid, button);
		}else if("REFRESH".equals(buttonName)){
			business.refreshSheet(listgrid);
		}

	}

}
