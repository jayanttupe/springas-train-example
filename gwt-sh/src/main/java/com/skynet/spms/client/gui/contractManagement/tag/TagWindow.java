package com.skynet.spms.client.gui.contractManagement.tag;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.skynet.spms.client.gui.base.SuperWindow;
import com.skynet.spms.client.gui.base.ValidateUtil;
import com.skynet.spms.client.gui.base.i18n.ContractModuleConst;
import com.skynet.spms.client.gui.contractManagement.common.TagModelLocator;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.VLayout;

public class TagWindow extends SuperWindow {

	/** 标签 类别 **/
	private TagEnum tagType;

	/** 标签 **/
	private List<Tag> tags;

	private String lang;

	private ListGrid tagListGrid = new ListGrid();

	public TagWindow(TagEnum tagType, String lang) {
		this.tagType = tagType;
		this.lang = lang;
		addMemberToLeft(viewInit());
	}

	private VLayout viewInit() {
		ContractModuleConst ui=GWT.create(ContractModuleConst.class);
		this.setTitle(ui.tag());
		this.setWidth(600);
		this.setHeight(500);
		this.setShowMinimizeButton(false);
		this.centerInPage();
		// 容器层
		VLayout vmain = new VLayout();
		vmain.setWidth100();
		vmain.setHeight100();
		vmain.setLayoutMargin(5);
		vmain.setLayoutAlign(VerticalAlignment.BOTTOM);
		// grid构建
		ListGridField pkField = new ListGridField("tagID");
		pkField.setHidden(true);
		ListGridField tagName = new ListGridField("tagName", ui.tagName());
		ListGridField tagKey = new ListGridField("tagKey", ui.tag());
		tagListGrid.setFields(pkField, tagName, tagKey);
		vmain.addMember(tagListGrid);

		// 数据渲染
		tags = TagManager.getInstance().getTags(tagType);
		ListGridRecord[] records = new ListGridRecord[tags.size()];
		for (int i = 0; i < tags.size(); i++) {
			Tag tag = tags.get(i);
			ListGridRecord record = new ListGridRecord();
			record.setAttribute("tagID", tag.getTagID());
			record.setAttribute("tagName", tag.getTagName());
			record.setAttribute("tagKey", tag.getTagKey());
			records[i] = record;
		}
		tagListGrid.setData(records);

		// 插入按钮
		IButton insertBtn = new IButton(ui.insert());
		insertBtn.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (ValidateUtil.isRecordSelected(tagListGrid)) {
					String tagkey = tagListGrid.getSelectedRecord()
							.getAttribute("tagKey");
					TextAreaItem zhItem = TagModelLocator.getInstance().zhItem;
					TextAreaItem enItem = TagModelLocator.getInstance().enItem;
					if ("en".equals(lang)) {
						if(null==enItem.getValue()){
							enItem.setValue("");
						}
						enItem.setValue(enItem.getValue() + " {" + tagkey
								+ "} ");
					} else if ("zh".equals(lang)) {
						if(null==zhItem.getValue()){
							zhItem.setValue("");
						}
						zhItem.setValue(zhItem.getValue() + " {" + tagkey
								+ "} ");
					}
				}
			}
		});
		vmain.addMember(insertBtn);
		return vmain;
	}

}
