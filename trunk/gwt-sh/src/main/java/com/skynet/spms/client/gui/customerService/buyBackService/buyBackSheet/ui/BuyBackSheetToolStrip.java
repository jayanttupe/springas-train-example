package com.skynet.spms.client.gui.customerService.buyBackService.buyBackSheet.ui;

import com.skynet.spms.client.gui.base.BaseButtonToolStript;
import com.skynet.spms.client.gui.base.OrganizationType;
import com.skynet.spms.client.gui.base.i18n.I18n;
import com.skynet.spms.client.gui.customerService.buyBackService.buyBackPact.business.BuyBackPactBusiness;
import com.skynet.spms.client.gui.customerService.buyBackService.buyBackPact.model.BuybackPactModelLocator;
import com.skynet.spms.client.gui.customerService.buyBackService.buyBackSheet.business.BuyBackSheetBusiness;
import com.skynet.spms.client.gui.customerService.buyBackService.buyBackSheet.model.SheetModelLocator;
import com.skynet.spms.client.gui.customerService.buyBackService.buyBackSheet.ui.view.BuyBackSheetViewWin;
import com.skynet.spms.client.gui.customerService.common.DSKey;
import com.skynet.spms.client.gui.customerService.commonui.BaseAddressModel;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

/**
 * 回购申请单面板工具条
 * 
 * @author fl
 * 
 */
public class BuyBackSheetToolStrip extends BaseButtonToolStript {
	public I18n ui = new I18n();
	private SheetModelLocator model = SheetModelLocator.getInstance();

	private BuybackPactModelLocator modelLocator = BuybackPactModelLocator
			.getInstance();

	private BuyBackSheetBusiness sheetBus = new BuyBackSheetBusiness();

	private BuyBackPactBusiness pactBusiness = new BuyBackPactBusiness();

	private BaseAddressModel addressModel = BaseAddressModel.getInstance();

	public BuyBackSheetToolStrip() {
		super(DSKey.C_BUYBACKSHEET);
		this.setLayoutLeftMargin(5);
		this.setLayoutRightMargin(5);
	}

	@Override
	protected void showWindow(String buttonName, ToolStripButton button) {
		if ("ADD".equals(buttonName)) {// 添加
			sheetBus.addBuyBackSheet(button);
		} else if ("MODIFY".equals(buttonName)) {// 修改
			ListGridRecord record = model.backSheetGrid.getSelectedRecord();
			if (record == null) {
				//"请先选择申请单"
				SC.say(ui.getM().msgOpSelectSheet());
				return;
			}
			if (sheetBus.canModifiedSheet(model.backSheetGrid)) {
				model.sheetID = record.getAttribute("id");
				sheetBus.updateBuyBackSheet(button);
			}
		} else if ("DELETE".equals(buttonName)) {// 删除
			sheetBus.deleteSheet(model.backSheetGrid);
		} else if ("PUBLISH".equals(buttonName)) {// 发布
			sheetBus.publishSheet(model.backSheetGrid);
		} else if ("CANCELPUBLISH".equals(buttonName)) {// 取消发布
			sheetBus.cancelPublishSheet(model.backSheetGrid);
		} else if ("ADDCONTRACT".equals(buttonName)) {// 新建合同
			ListGridRecord record = model.backSheetGrid.getSelectedRecord();
			if (record == null) {
				//请选择要建立合同的申请单
				SC.say(ui.getM().msgOpSelectSheetToContant());
				return;
			}
			if (sheetBus.canDoNextBusiness(model.backSheetGrid)) {
				String isTemptlate = record.getAttribute("isTemptlate");
				if (isTemptlate.equals("true")) {
					//合同已经存在
					SC.say(ui.getM().msgContantExist());
					return;
				}
				modelLocator.linkMan = record.getAttribute("linkman");
				modelLocator.linkType = record.getAttribute("contactWay");
				modelLocator.customerIdentificationCode = record
						.getAttribute("m_CustomerIdentificationCode.id");
				modelLocator.requisitionSheetNumber = record
						.getAttribute("requisitionSheetNumber");
				modelLocator.sheetId = record.getAttribute("id");
				addressModel.enterpriseId = record
						.getAttribute("m_CustomerIdentificationCode.id");
				addressModel.businessType = OrganizationType.CUSTOMER;
				pactBusiness.addBuyBackPact(button);
			}
		} else if ("REFRESH".equals(buttonName)) {
			model.backSheetGrid.invalidateCache();
			Criteria criteria = new Criteria();
			criteria
					.setAttribute(
							"m_BussinessPublishStatusEntity.m_PublishStatus",
							"published");
			model.backSheetGrid.fetchData(criteria);
		} else if ("VIEW".equals(buttonName)) {
			ListGridRecord record = model.backSheetGrid.getSelectedRecord();
			if (record == null) {
				//"请先选择申请单"
				SC.say(ui.getM().msgOpSelectSheet());
				return;
			}
			model.sheetID = record.getAttribute("id");
			BuyBackSheetViewWin win = new BuyBackSheetViewWin("", true,
					button.getPageRect(), null, null);
			model.openenWindow = win;
			ShowWindowTools.showWindow(button.getPageRect(), win, 1);
		}
	}

}
