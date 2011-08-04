package com.skynet.spms.client.gui.contractManagement.template;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.skynet.spms.client.PanelFactory;
import com.skynet.spms.client.ShowcasePanel;
import com.skynet.spms.client.gui.base.i18n.ContractModuleConst;
import com.skynet.spms.client.gui.base.i18n.I18n;
import com.skynet.spms.client.gui.contractManagement.common.ContractSelectDS;
import com.skynet.spms.client.gui.contractManagement.common.ContractTreeDS;
import com.skynet.spms.client.gui.contractManagement.common.TagModelLocator;
import com.skynet.spms.client.gui.contractManagement.tag.TagEnum;
import com.skynet.spms.client.gui.contractManagement.tag.TagWindow;
import com.skynet.spms.client.service.contractManagement.provision.ContractProvisionService;
import com.skynet.spms.client.service.contractManagement.provision.ContractProvisionServiceAsync;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.skynet.spms.client.tools.UserTools;
import com.skynet.spms.client.vo.contractManagement.Provision;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.menu.Menu;
import com.smartgwt.client.widgets.menu.MenuItem;
import com.smartgwt.client.widgets.menu.events.ClickHandler;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;
import com.smartgwt.client.widgets.tree.TreeGrid;
import com.smartgwt.client.widgets.tree.TreeGridField;

/**
 * 合同模板条款管理
 * 
 * @author tqc
 * 
 */
public class ContractProvisionPanel extends ShowcasePanel {

	private static I18n ui = new I18n();

	public static class Factory implements PanelFactory {
		private String DESCRIPTION = ui.getM().mod_contractProvision_name();
		private String id;

		public Canvas create() {
			ContractProvisionPanel panel = new ContractProvisionPanel();
			id = panel.getID();
			return panel;
		}

		public String getID() {
			return id;
		}

		public String getDescription() {
			return DESCRIPTION;
		}
	}

	public Canvas getViewPanel() {
		
		final ContractProvisionServiceAsync service = GWT
		.create(ContractProvisionService.class);
		// 容器层
		VLayout wrap = new VLayout();
		// 头部容器
		HLayout topWrap = new HLayout();
		topWrap.setHeight("5%");
		// 左侧树
		final TreeGrid treeGrid = new TreeGrid();
		treeGrid.setLoadDataOnDemand(false);
		treeGrid.setWidth100();
		treeGrid.setHeight100();
		treeGrid.setNodeIcon("icons/16/person.png");
		treeGrid.setFolderIcon("icons/16/person.png");
		treeGrid.setShowOpenIcons(false);
		treeGrid.setShowDropIcons(false);
		treeGrid.setClosedIconSuffix("");
		treeGrid.setCanFreezeFields(true);
		treeGrid.setCanReparentNodes(true);
		TreeGridField idField = new TreeGridField("id", 150);
		TreeGridField nameField = new TreeGridField("title_en", 150);
		TreeGridField parentField = new TreeGridField("parentId", 150);
		treeGrid.setFields(idField, nameField,parentField);

		// 右键菜单
		final Menu contextMenu = new Menu();
		// 删除菜单
		final MenuItem delItem = new MenuItem(ui.getB().btnRemove());
		contextMenu.addItem(delItem);
		// 将右键菜单绑定在tree上
		treeGrid.setContextMenu(contextMenu);
		
		final ContractModuleConst uii18=GWT.create(ContractModuleConst.class);

		// 合同类型数据源
		ContractSelectDS ds = new ContractSelectDS(System.currentTimeMillis()
				+ "");
		final ComboBoxItem selectItem = new ComboBoxItem();
		selectItem.setTitle(uii18.tempType());
		selectItem.setDisplayField("typeAlias_zh");
		selectItem.setOptionDataSource(ds);
		selectItem.addChangedHandler(new ChangedHandler() {
			public void onChanged(ChangedEvent event) {
				refreshTree(treeGrid, selectItem);
			}
		});

		final IButton addBtn = new IButton(uii18.createprovition());
		addBtn.addClickHandler(new com.smartgwt.client.widgets.events.ClickHandler() {
			public void onClick(ClickEvent event) {
				if (selectItem.getSelectedRecord() != null) {
					ProvisionAddWindow addWin = new ProvisionAddWindow(
							treeGrid, selectItem);
					ShowWindowTools.showWondow(addBtn.getPageRect(), addWin,
							500);
				} else {
					SC.say(uii18.msg_select_data());
				}

			}
		});

		// 右键删除
		delItem.addClickHandler(new ClickHandler() {
			public void onClick(MenuItemClickEvent event) {
				String id = treeGrid.getSelectedRecord().getAttribute("id");
				if (id != null && !"".equals(id)) {
					
					service.deleteProvision(id, selectItem.getSelectedRecord()
							.getAttributeAsString("fileName"),
							new AsyncCallback<Void>() {
								public void onSuccess(Void arg0) {
									refreshTree(treeGrid, selectItem);
								}

								public void onFailure(Throwable arg0) {
									SC.say("fail");
								}
							});
				}
			}
		});

		DynamicForm form = new DynamicForm();
		form.setWidth(500);
		form.setNumCols(5);
		form.setFields(selectItem);

		topWrap.addMember(addBtn);
		topWrap.addMember(form);

		wrap.addMember(topWrap);

		HLayout bottomWrap = new HLayout();
		bottomWrap.setHeight("95%");

		VLayout bottomLeft = new VLayout();
		bottomLeft.setWidth("30%");
		bottomLeft.setBorder("1px solid #E8E8E8");
		bottomLeft.setShowResizeBar(true);
		bottomLeft.addMember(treeGrid);
		/**
		 * 修改form
		 */
		final DynamicForm contentForm = new DynamicForm();

		final TextItem titleEnItem = new TextItem();
		titleEnItem.setName("title_en");
		titleEnItem.setTitle(uii18.title_en());
		titleEnItem.setRequired(true);
		titleEnItem.setWidth("100%");

		final TextItem titleZhItem = new TextItem();
		titleZhItem.setName("title_zh");
		titleZhItem.setTitle(uii18.title_zh());
		titleZhItem.setRequired(true);
		titleZhItem.setWidth("100%");

		final TextAreaItem contentEnItem = new TextAreaItem();
		contentEnItem.setName("content_en");
		contentEnItem.setTitle(uii18.content_en());
		contentEnItem.setRequired(true);
		contentEnItem.setWidth("100%");
		TagModelLocator.getInstance().enItem=contentEnItem;

		ButtonItem tagEnBtn = new ButtonItem();
		tagEnBtn.setTitle(uii18.insertTag());
		
		ButtonItem tagZhBtn = new ButtonItem();
		tagZhBtn.setTitle(uii18.insertTag());

		final TextAreaItem contentZhItem = new TextAreaItem();
		contentZhItem.setName("content_zh");
		contentZhItem.setTitle(uii18.content_zh());
		contentZhItem.setRequired(true);
		contentZhItem.setWidth("100%");
		TagModelLocator.getInstance().zhItem=contentZhItem;
		
		contentForm.setFields(titleEnItem, titleZhItem, contentEnItem, tagEnBtn,
				contentZhItem, tagZhBtn);

		HLayout btnWrap = new HLayout();
		final IButton saveModifyBtn = new IButton();
		saveModifyBtn.setTitle(uii18.btnSave());
		saveModifyBtn.setDisabled(true);
		btnWrap.addMember(saveModifyBtn);

		VLayout bottomRight = new VLayout();
		bottomRight.setBorder("1px solid #E8E8E8");
		bottomRight.setWidth("70%");
		bottomRight.addMember(contentForm);
		bottomRight.addMember(btnWrap);

		bottomWrap.addMember(bottomLeft);
		bottomWrap.addMember(bottomRight);
		wrap.addMember(bottomWrap);

		// 点击树查看详情
		treeGrid.addRecordClickHandler(new RecordClickHandler() {
			public void onRecordClick(RecordClickEvent event) {
				contentForm.editRecord(treeGrid.getSelectedRecord());
				saveModifyBtn.setDisabled(false);
			}
		});

		// 保存修改
		saveModifyBtn
				.addClickHandler(new com.smartgwt.client.widgets.events.ClickHandler() {
					@SuppressWarnings("deprecation")
					public void onClick(ClickEvent event) {
						if(null!=treeGrid.getSelectedRecord()){
							Provision p=new Provision();
							Record record = treeGrid.getSelectedRecord();
							if(null!=record.getAttribute("parentId")&&!"".equals(record.getAttribute("parentId"))){
								p.setParentId(record.getAttribute("parentId"));
							}else{
								p.setParentId("root");
							}
							p.setTempType(selectItem.getSelectedRecord()
									.getAttributeAsString("fileName"));
							p.setId(record.getAttribute("id"));
							p.setTitle_en(titleEnItem.getValueAsString());
							p.setTitle_zh(titleZhItem.getValueAsString());
							p.setContent_en(contentEnItem.getValueAsString());
							p.setContent_zh(contentZhItem.getValueAsString());
							p.setCreateBy(UserTools.getCurrentUserName());
							p.setCreateDate(new Date().toLocaleString());
							p.setDeleted("false");
							p.setParentId(record.getAttributeAsString("parentId"));
							p.setKeywordkey("key");
							p.setItemNumber("1");
							p.setVersion(record.getAttributeAsString("version")+1);
							service.updateProvision(p,new AsyncCallback<Void>() {
								@Override
								public void onSuccess(Void arg0) {
									SC.say(uii18.msgModifyOpSuccess());
									refreshTree(treeGrid, selectItem);
								}
								
								@Override
								public void onFailure(Throwable arg0) {
									SC.warn("error"+arg0);
								}
							});
						}
					}
				});

		// 插入标签
		tagZhBtn.addClickHandler(new com.smartgwt.client.widgets.form.fields.events.ClickHandler() {
			public void onClick(
					com.smartgwt.client.widgets.form.fields.events.ClickEvent event) {
				// 获得标签类别
				String tagType = selectItem.getSelectedRecord()
						.getAttributeAsString("name");
				if (null != tagType && !"".equals(tagType)) {
					TagEnum[] tagTypes = TagEnum.values();
					TagEnum type = null;
					for (int i = 0; i < tagTypes.length; i++) {
						if (tagTypes[i].name().equals(tagType)) {
							type = tagTypes[i];
							break;
						}
					}
					if (type != null) {
						TagWindow win = new TagWindow(type,"zh");
						win.show();
					}

				}

			}
		});
		
		// 插入标签
		tagEnBtn.addClickHandler(new com.smartgwt.client.widgets.form.fields.events.ClickHandler() {
			public void onClick(
					com.smartgwt.client.widgets.form.fields.events.ClickEvent event) {
				// 获得标签类别
				String tagType = selectItem.getSelectedRecord()
						.getAttributeAsString("name");
				if (null != tagType && !"".equals(tagType)) {
					TagEnum[] tagTypes = TagEnum.values();
					TagEnum type = null;
					for (int i = 0; i < tagTypes.length; i++) {
						if (tagTypes[i].name().equals(tagType)) {
							type = tagTypes[i];
							break;
						}
					}
					if (type != null) {
						TagWindow win = new TagWindow(type,"en");
						win.show();
					}

				}

			}
		});
		
		

		return wrap;

	}

	/**
	 * 刷新树
	 * 
	 * @param treeGrid
	 * @param selectItem
	 */
	private void refreshTree(final TreeGrid treeGrid,
			final ComboBoxItem selectItem) {
		ContractTreeDS treeDs = new ContractTreeDS(selectItem
				.getSelectedRecord().getAttributeAsString("fileName"),
				System.currentTimeMillis() + "");
		treeGrid.setDataSource(treeDs);
		treeGrid.fetchData();
		treeDs.destroy();
	}

	public String getIntro() {
		return ui.getM().mod_contractProvision_name();
	}
}