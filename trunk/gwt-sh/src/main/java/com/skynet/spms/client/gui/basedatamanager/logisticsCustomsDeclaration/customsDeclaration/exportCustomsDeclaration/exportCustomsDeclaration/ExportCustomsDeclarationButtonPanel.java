/**
 * 
 */
package com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.customsDeclaration.exportCustomsDeclaration.exportCustomsDeclaration;

import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.BaseButtonToolStript;
import com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.customsDeclaration.exportCustomsDeclaration.exportCustomsTariffRecord.ExportCustomsTariffRecordDetailWindow;
import com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.customsDeclaration.exportCustomsDeclaration.exportSecurityDeposit.ExportSecurityDepositDetailWindow;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

/**
 * @author Administrator
 */
public class ExportCustomsDeclarationButtonPanel extends BaseButtonToolStript {
	private ExportCustomsDeclarationListgrid exportCustomsDeclarationListgrid;
	private ExportCustomsDeclarationItemsListgrid exportCustomsDeclarationItemsListgrid;

	public ExportCustomsDeclarationButtonPanel(
			final ExportCustomsDeclarationListgrid exportCustomsDeclarationListgrid,
			final ExportCustomsDeclarationItemsListgrid exportCustomsDeclarationItemsListgrid) {
		super("logisticsCustomsDeclaration.dispatchLogisticsTask.customsClearanceJob.exportCustomsDeclaration.exportCustomsDeclaration");
		this.exportCustomsDeclarationListgrid = exportCustomsDeclarationListgrid;
		this.exportCustomsDeclarationItemsListgrid = exportCustomsDeclarationItemsListgrid;
	}

	protected void showWindow(String buttonName, final ToolStripButton objButton) {
		Window objWindow = null;
		Rectangle rect = objButton.getPageRect();
		
		if ("editcustoms".equalsIgnoreCase(buttonName)) {
			if (exportCustomsDeclarationListgrid.getSelection().length == 1) {
				objWindow = new ExportCustomsDeclarationDetailWindow("修改出口报关记录",
						false,
						rect,
						exportCustomsDeclarationListgrid,
						"showwindow/logistics_modify_01.png",
						true,
						false);
				ShowWindowTools.showWondow(rect, objWindow, -1);
				return;
			} else {
				SC.say("请选择一条记录进行修改！");
			}
		} else if ("view".equalsIgnoreCase(buttonName)) {
			if (exportCustomsDeclarationListgrid.getSelection().length == 1) {
				objWindow = new ExportCustomsDeclarationDetailWindow("查看出口报关记录",
						false,
						rect,
						exportCustomsDeclarationListgrid,
						"showwindow/logistics_modify_01.png",
						true,
						true);
				ShowWindowTools.showWondow(rect, objWindow, -1);
				return;
			} else {
				SC.say("请选择一条记录进行查看！");
			}
		} else if ("add_security_deposit".equalsIgnoreCase(buttonName)) {
			if (exportCustomsDeclarationListgrid.getSelection().length == 1) {
				String securityOrCustomsTariffName =
					exportCustomsDeclarationListgrid.getSelectedRecord().getAttribute("securityOrCustomsTariffName");

				if (securityOrCustomsTariffName != null && !securityOrCustomsTariffName.equals("")) {
					SC.say("已经添加过关税，不能再添加保证金！");
					return;
				} else {
					// 构造一个DataSource
					DataSourceTool headDST = new DataSourceTool();
					String headModeName = "logisticsCustomsDeclaration.dispatchLogisticsTask.customsClearanceJob.exportCustomsDeclaration.exportSecurityDeposit";
					String headDSName = "exportSecurityDeposit_dataSource";
					headDST.onCreateDataSource(headModeName, headDSName,
						new PostDataSourceInit() {
							public void doPostOper(DataSource dataSource,
									DataInfo dataInfo) {
								Rectangle rect = objButton.getPageRect();
								Window newWindow = new ExportSecurityDepositDetailWindow("添加保证金",
																				false,
																				rect,
																				exportCustomsDeclarationListgrid,
																				dataSource,
																				"showwindow/logistics_add_02.png",
																				true,
																				true,
																				false);
								ShowWindowTools.showWondow(rect, newWindow, -1);
								return;
							}
					});
				}
			} else {
				SC.say("请选择一条报关记录进行添加保证金！");
			}
		} else if ("add_customs_tariff".equalsIgnoreCase(buttonName)){
			if (exportCustomsDeclarationListgrid.getSelection().length == 1) {
				String securityOrCustomsTariffName =
					exportCustomsDeclarationListgrid.getSelectedRecord().getAttribute("securityOrCustomsTariffName");

				if (securityOrCustomsTariffName != null && !securityOrCustomsTariffName.equals("")) {
					SC.say("已经添加过保证金，不能再添加关税！");
					return;
				} else {
					// 构造一个DataSource
					DataSourceTool headDST = new DataSourceTool();
					String headModeName = "logisticsCustomsDeclaration.dispatchLogisticsTask.customsClearanceJob.exportCustomsDeclaration.exportCustomsTariffRecord";
					String headDSName = "exportCustomsTariffRecord_dataSource";
					headDST.onCreateDataSource(headModeName, headDSName,
						new PostDataSourceInit() {
							public void doPostOper(DataSource dataSource,
									DataInfo dataInfo) {
								Rectangle rect = objButton.getPageRect();
								Window newWindow = new ExportCustomsTariffRecordDetailWindow("添加关税",
																				false,
																				rect,
																				exportCustomsDeclarationListgrid,
																				dataSource,
																				"showwindow/logistics_add_02.png",
																				true,
																				true,
																				false);
								ShowWindowTools.showWondow(rect, newWindow, -1);
								return;
							}
					});
				}
			} else {
				SC.say("请选择一条报关记录进行添加关税！");
			}
		} else if ("finish_work".equalsIgnoreCase(buttonName)) {
			if (exportCustomsDeclarationListgrid.getSelection().length == 1) {
				Record record = new Record();
				// 关闭工作处理
				record.setAttribute("setStatus", "exportCustomsStatus");
				record.setAttribute("orderID", exportCustomsDeclarationListgrid.getSelectedRecord().getAttribute("orderID"));
				exportCustomsDeclarationListgrid.updateData(record, new DSCallback() {
					@Override
					public void execute(DSResponse response, Object rawData, DSRequest request) {
						SC.say("已经关闭当前工作！");

						Criteria criteria = new Criteria();
						criteria.addCriteria("temp", String.valueOf(Math.random()));
						criteria.addCriteria("workStatus", "2");
						exportCustomsDeclarationListgrid.fetchData(criteria);

						exportCustomsDeclarationItemsListgrid.setData(new ListGridRecord[]{});
					}
				});
			} else {
				SC.say("请选择一条记录进行处理！");
			}
		}
	}
}
