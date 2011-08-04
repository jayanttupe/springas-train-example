package com.skynet.spms.client.gui.customerService.repairService.repairContract.plugin;

import com.smartgwt.client.widgets.form.fields.DateItem;
import com.smartgwt.client.widgets.form.fields.SpinnerItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;

public class RepairMockView extends MockView {

	public RepairMockView() {
	}

	private DateItem checkDateItem;

	private SpinnerItem checkCompanyItem;

	private TextAreaItem inspectDescriptionItem;

	public DateItem getCheckDateItem() {
		return checkDateItem;
	}

	public void setCheckDateItem(DateItem checkDateItem) {
		this.checkDateItem = checkDateItem;
	}

	public TextItem getCheckCompanyItem() {
		return checkCompanyItem;
	}

	public void setCheckCompanyItem(SpinnerItem checkCompanyItem) {
		this.checkCompanyItem = checkCompanyItem;
	}

	public TextAreaItem getInspectDescriptionItem() {
		return inspectDescriptionItem;
	}

	public void setInspectDescriptionItem(TextAreaItem inspectDescriptionItem) {
		this.inspectDescriptionItem = inspectDescriptionItem;
	}

}
