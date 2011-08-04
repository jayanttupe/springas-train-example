package com.skynet.spms.client.gui.supplierSupport.leaseService.business;

import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.ValidateUtil;
import com.skynet.spms.client.gui.supplierSupport.common.DSKey;
import com.skynet.spms.client.gui.supplierSupport.leaseService.leaseContract.add.SSLeaseContractAddWindow;
import com.skynet.spms.client.gui.supplierSupport.leaseService.model.SSMainModelLocator;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;
import com.skynet.spms.client.gui.base.BaseBusiness;
/**
 * 供应商租赁合同业务类
 * **/
public class SSLeaseSheetBusiness  extends BaseBusiness{

	public SSMainModelLocator model = SSMainModelLocator.getInstance();

	/**
	 * 刷新ItemListGrid
	 * 
	 * @param grid
	 */
	public void refeshItemListGrid() {
		Criteria c = new Criteria();
		c.setAttribute("key", "reload");
		c.setAttribute("r", String.valueOf(Math.random()));
	}

	/**
	 * 刷新ListGrid
	 * 
	 * @param grid
	 */
	public void refeshListGrid() {

		Criteria c = new Criteria();
		c.setAttribute("key", "reload");
		c.setAttribute("r", String.valueOf(Math.random()));
	}


	/**
	 * 新建供应商租赁合同
	 * **/
	public void addSSLeaseContract(final ListGrid grid,
			final ToolStripButton button) {
		if (ValidateUtil.isRecordSelected(grid)) {// 判断是否发布了
			// final String state = grid.getSelectedRecord().getAttribute(
			// "m_BussinessPublishStatusEntity.m_PublishStatus");

			// 判断是否已经存在合同
			// String contractNumber = grid.getSelectedRecord()
			// .getAttribute("contractNumber").toString();
			// if (contractNumber != null && contractNumber != "") {
			// SC.say("合同已经存在!");
		
			SSLeaseContractAddWindow addWin = new SSLeaseContractAddWindow();
			ShowWindowTools.showWondow(button.getPageRect(), addWin, 200);
		}
		// if ("published".equals(state)) {
		// 初始化合同基本信息数据源数据源, 指定数据源

		// dataSourceTool.onCreateDataSource(DSKey.S_LEASECONTRACT,
		// DSKey.S_LEASECONTRACT_DS, new PostDataSourceInit() {
		// public void doPostOper(DataSource dataSource,
		// DataInfo dataInfo) {
		// model.SSLeaseContractDs = dataSource;
		// }
		// });
		// // 初始化合同明细的数据源
		// dataSourceTool.onCreateDataSource(DSKey.S_LEASECONTRACT,
		// DSKey.S_LEASECONTRACTITEM_DS, new PostDataSourceInit() {
		// public void doPostOper(DataSource dataSource,
		// DataInfo dataInfo) {
		// model.SSleaseContractItemDs = dataSource;
		// }
		// });
		// // 初始化附件数据源
		// dataSourceTool.onCreateDataSource(DSKey.S_LEASECONTRACT,
		// DSKey.S_LEASEADDRESS_DS, new PostDataSourceInit() {
		// public void doPostOper(DataSource dataSource,
		// DataInfo dataInfo) {
		// model.SSattachmentDs = dataSource;
		// }
		// });
		// // 初始化地址添加数据源

		// } else {
		// SC.say("请先发布后在新建合同");
		// }

		// }
	}

}
