package com.skynet.spms.client.gui.customerplatform.repairsheet.add;

import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.i18n.I18n;
import com.skynet.spms.client.gui.customerService.common.DSKey;
import com.skynet.spms.client.gui.customerplatform.repairsheet.SheetModelLocator;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
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
public class PartRepairDisassembleDataAddForm extends VLayout {

	private I18n ui = new I18n();

	private DynamicForm form = new DynamicForm();

	private SheetModelLocator model = SheetModelLocator.getInstance();

	public PartRepairDisassembleDataAddForm() {
		form.setNumCols(4);
		final DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(DSKey.C_REPAIRREQUISITIONSHEET,
				DSKey.CUSTOMERPARTREPAIRDISASSEMBLEDATA_DATASOURCE,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						form.setDataSource(dataSource);
						buildForm();
					}
				});
	}

	public void buildForm() {

		final TextItem pkItemIdItem = new TextItem();
		pkItemIdItem.setName("repairRequisitionSheetItemID");
		pkItemIdItem.setVisible(false);

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
		tsnItem.setTitle("TSN(飞行小时)");

		TextItem tsoItem = new TextItem();
		tsoItem.setName("m_TSO");
		tsoItem.setTitle("TSO(飞行小时)");

		TextItem csoItem = new TextItem();
		csoItem.setName("m_CSO");
		csoItem.setTitle("CSO(循环小时)");

		TextItem csnItem = new TextItem();
		csnItem.setName("m_CSN");
		csnItem.setTitle("CSN(循环小时)");

		TextAreaItem reasonForRemovalItem = new TextAreaItem();
		reasonForRemovalItem.setName("reasonForRemoval");

		TextAreaItem fixWorkText = new TextAreaItem();
		fixWorkText.setName("fixWorkText");

		form.setFields(pkItemIdItem, disassembleDateItem, disassembleTypeItem,
				fromRegistrationNumberItem, partPostionItem, tsnItem, tsoItem,
				csoItem, csnItem, reasonForRemovalItem, fixWorkText);

		this.addMember(form);
		HLayout btnGroup = new HLayout();
		btnGroup.setMembersMargin(3);
		IButton btnSave = new IButton(ui.getB().btnSaveAdd());
		btnSave.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if (model.repairSheetId == null
						|| model.repairSheetItemId == null) {
					SC.say("请先保存基本信息");
					return;
				}
				pkItemIdItem.setValue(model.repairSheetItemId);

				form.saveData(new DSCallback() {
					public void execute(DSResponse response, Object rawData,
							DSRequest request) {
						SC.say("保存成功");
					}
				});
			}
		});
		btnGroup.addMember(btnSave);
		this.addMember(btnGroup);
	}
}
