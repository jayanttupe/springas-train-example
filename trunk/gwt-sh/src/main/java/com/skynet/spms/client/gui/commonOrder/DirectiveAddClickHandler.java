package com.skynet.spms.client.gui.commonOrder;

import com.google.gwt.core.client.GWT;
import com.skynet.spms.client.gui.base.OperationMode;
import com.skynet.spms.client.gui.base.i18n.CommonOrderModuleConst;
import com.skynet.spms.client.gui.commonOrder.delivery.model.DataModelLocator;
import com.skynet.spms.client.gui.commonOrder.delivery.ui.add.DeliveryOrderAddWindow;
import com.skynet.spms.client.gui.commonOrder.pickup.ui.add.PickUpOrderAddWindow;
import com.skynet.spms.client.gui.customerService.salesService.salesContract.SaleContractGrid;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.toolbar.ToolStripMenuButton;

/**
 * 指令按钮点击处理类
 * 
 * @author zhangqiang
 * 
 */
public class DirectiveAddClickHandler {
	
	CommonOrderModuleConst ui=GWT.create(CommonOrderModuleConst.class);

	/** 指令标识 1：发货/ 2：提货 */
	private DirectiveType directiveType;
	/** 合同编号key值 */
	private String contractNumberKey = "id";
	/** 合同grid */
	private ListGrid listGrid;
	/** 合同类型 */
	private String contractType;

	/** 审批结果 **/
	@SuppressWarnings("unused")
	private static String AUDITSTATUS = "auditStatus";

	public DirectiveAddClickHandler(DirectiveType directiveType,
			ListGrid listGrid, String contractType, String businessType) {
		this.directiveType = directiveType;
		this.listGrid = listGrid;
		this.contractType = contractType;
		DataModelLocator.getInstance().businessType = businessType;
	}

	public DirectiveAddClickHandler(DirectiveType directiveType,
			String contractNumberKey, SaleContractGrid listGrid,
			String contractType, String businessType) {
		this.directiveType = directiveType;
		this.contractNumberKey = contractNumberKey;
		this.listGrid = listGrid;
		this.contractType = contractType;
		DataModelLocator.getInstance().businessType = businessType;
	}

	public void pop(ToolStripMenuButton button, String businessType) {
		DataModelLocator.getInstance().businessType = businessType;
		com.skynet.spms.client.gui.commonOrder.pickup.model.DataModelLocator
				.getInstance().businessType = businessType;
		ListGridRecord record = listGrid.getSelectedRecord();
		String contractNumber = ""; // 合同编号

		if (null == record) {
			SC.say(ui.msgOpSeletedData());
			return;
		}

		contractNumber = record.getAttribute(contractNumberKey);

		if (null == directiveType) {
			//SC.say("系统异常", "传入handler类中的指令类型不能为空");
			return;
		}

		//如果是AOG类型，提交审批后直接可以建立提发货指令，其他类型必须审批通过后,才可以建立
		String priority = record.getAttribute("m_Priority");

		String auditStatus = record.getAttribute("auditStatus");
		
		if ("AOG".equals(priority) && "approvaling".equals(auditStatus)) {

			showWindow(button, contractNumber);

		} else {
			if ("pass".equals(auditStatus)) {

				showWindow(button, contractNumber);

			}else{
				SC.say(ui.msg_aog_warn());
			}

		}

	}

	private void showWindow(ToolStripMenuButton button, String contractNumber) {
		Window win;
		if (directiveType.toString().equals("delivery")) {
			win = new DeliveryOrderAddWindow(ui.create_pickup_order(), true,
					button.getPageRect(), null, "", OperationMode.add,
					contractNumber, contractType);
		} else {
			win = new PickUpOrderAddWindow(ui.create_order(), true,
					button.getPageRect(), listGrid, null, contractNumber,
					contractType);
		}

		ShowWindowTools.showWindow(button.getPageRect(), win, 200);
	}

}
