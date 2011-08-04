package com.skynet.spms.client.gui.customerService.repairService.sheet.view;

import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.Utils;
import com.skynet.spms.client.gui.customerService.common.DSKey;
import com.skynet.spms.client.gui.customerService.repairService.sheet.SheetModelLocator;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.DateItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * 客户部件送修明细项拆换数据
 * 
 * @author tqc
 * 
 */
public class PartRepairDisassembleDataViewForm extends VLayout {

	private DynamicForm form = new DynamicForm();

	private SheetModelLocator model = SheetModelLocator.getInstance();

	public PartRepairDisassembleDataViewForm() {
		form.setNumCols(4);
		final DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(DSKey.C_REPAIRREQUISITIONSHEET,
				DSKey.CUSTOMERPARTREPAIRDISASSEMBLEDATA_DATASOURCE,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						form.setDataSource(dataSource);
						Criteria criteria = new Criteria();
						criteria.setAttribute(
								"getByItemID",
								model.repairRequisitionListGrid
										.getSelectedRecord()
										.getAttribute(
												"repairRequisitionSheetItem.id"));
						form.fetchData(criteria);
						buildForm();
					}
				});
		Utils.setReadOnlyForm(form);
	}

	public void buildForm() {

		DateItem disassembleDateItem = new DateItem();
		disassembleDateItem.setName("disassembleDate");
		disassembleDateItem.setUseTextField(true);
		disassembleDateItem
				.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);

		TextItem disassembleTypeItem = new TextItem();
		disassembleTypeItem.setName("disassembleType");

		TextItem fromRegistrationNumberItem = new TextItem();
		fromRegistrationNumberItem.setName("fromRegistrationNumber");

		TextItem partPostionItem = new TextItem();
		partPostionItem.setName("partPostion");

		TextItem tsnItem = new TextItem();
		tsnItem.setName("m_TSN");
		//tsnItem.setTitle("TSN(飞行小时)");

		TextItem tsoItem = new TextItem();
		tsoItem.setName("m_TSO");
		//tsoItem.setTitle("TSO(飞行小时)");

		TextItem csoItem = new TextItem();
		csoItem.setName("m_CSO");
		//csoItem.setTitle("CSO(循环小时)");

		TextItem csnItem = new TextItem();
		csnItem.setName("m_CSN");
		//csnItem.setTitle("CSN(循环小时)");
		
		
		TextAreaItem reasonForRemovalItem = new TextAreaItem();
		reasonForRemovalItem.setName("reasonForRemoval");

		TextAreaItem fixWorkText = new TextAreaItem();
		fixWorkText.setName("fixWorkText");

		form.setFields(disassembleDateItem, disassembleTypeItem,
				fromRegistrationNumberItem, partPostionItem, tsnItem, tsoItem,
				csoItem, csnItem, reasonForRemovalItem, fixWorkText);
		
		Utils.setReadOnlyForm(form);

		this.addMember(form);
		HLayout btnGroup = new HLayout();
		btnGroup.setMembersMargin(3);
		this.addMember(btnGroup);
	}
}
