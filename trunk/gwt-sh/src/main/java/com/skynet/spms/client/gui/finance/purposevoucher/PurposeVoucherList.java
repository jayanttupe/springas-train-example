package com.skynet.spms.client.gui.finance.purposevoucher;

import java.util.logging.Logger;

import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.ExpansionMode;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class PurposeVoucherList extends ListGrid {

	private Logger log = Logger.getLogger("PurposeVoucherList");
	private DataInfo purposeVoucherDataInfo;
	private SelectItem sltSubject;
	public PurposeVoucherList() {
		sltSubject = new SelectItem("id");
		sltSubject.setDisplayField("subjectName");
		ListGridField nameField = new ListGridField("subjectName");
		ListGridField codeField = new ListGridField("subjectCode");
		sltSubject.setPickListFields(nameField,codeField);
		sltSubject.setPickListWidth(200);
		DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource("account.certificateManager", "finance_subjects_dataSource", new PostDataSourceInit() {
			@Override
			public void doPostOper(DataSource dataSource, DataInfo dataInfo) {
				sltSubject.setOptionDataSource(dataSource);
			}
		});
	}
	public SelectItem getSubjectSelectItem(){
		return sltSubject;
	}

	public DataInfo getPurposeVoucherDataInfo() {
		return purposeVoucherDataInfo;
	}

	public void setPurposeVoucherDataInfo(DataInfo purposeVoucherDataInfo) {
		this.purposeVoucherDataInfo = purposeVoucherDataInfo;
	}

	public void drawPerchaseInvoiceList() {
		// TODO Auto-generated method stub
		setExpansionMode(ExpansionMode.DETAILS);
		setCanRemoveRecords(true);
		setRemoveFieldTitle("删除");

		setSelectionType(SelectionStyle.SIMPLE);
		setSelectionAppearance(SelectionAppearance.CHECKBOX);

		ListGridField id = new ListGridField("id");
		id.setHidden(true);

		// 凭证编号
		ListGridField certificateNumberField = new ListGridField(
				"certificateNumber");
		certificateNumberField.setCanEdit(false);
		certificateNumberField.setCanFilter(true);
		// certificateNumberField.setWidth("15%");

		// 凭证类型
		ListGridField purposeVoucherTypeField = purposeVoucherDataInfo
				.getFieldInfoByName("purposeVoucherType").generGridField();
		purposeVoucherTypeField.setCanEdit(false);
		purposeVoucherTypeField.setCanFilter(true);
		purposeVoucherTypeField.setWidth("10%");

/*		// 总金额
		ListGridField totalAmountField = new ListGridField("totalAmount");
		totalAmountField.setCanEdit(false);
		totalAmountField.setCanFilter(true);*/

		// 制单人
		ListGridField createCertificateUserField = new ListGridField(
				"createCertificateUser");
		createCertificateUserField.setCanEdit(false);
		createCertificateUserField.setCanFilter(true);
		// createCertificateUserField.setWidth("15%");

		// 凭证日期
		ListGridField certificateDateField = new ListGridField(
				"certificateDate");
		certificateDateField.setType(ListGridFieldType.DATE);
		createCertificateUserField.setCanEdit(false);
		createCertificateUserField.setCanFilter(true);
		// createCertificateUserField.setWidth("15%");


		setFields(id,certificateNumberField, purposeVoucherTypeField,
				 createCertificateUserField,
				certificateDateField);

	}

}
