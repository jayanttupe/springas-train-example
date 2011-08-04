package com.skynet.spms.client.gui.customerService.repairService.customerRepairInspectionOrder;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.CodeRPCTool;
import com.skynet.spms.client.gui.base.i18n.I18n;
import com.skynet.spms.client.gui.customerService.common.DSKey;
import com.skynet.spms.client.gui.customerService.repairService.repairContract.ContractModelLocator;
import com.skynet.spms.client.service.BaseCodeService;
import com.skynet.spms.client.service.BaseCodeServiceAsync;
import com.skynet.spms.client.tools.UserTools;
import com.skynet.spms.client.vo.CustomerContact;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.HiddenItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.layout.HLayout;

/**
 * 客户送修送检指令添加窗体
 * 
 * @author tqc
 * 
 */
public class CustomerRepairInspectionOrderAddWin extends Window {

	private static I18n ui = new I18n();
	
	ContractModelLocator model = ContractModelLocator.getInstance();
	
	private static BaseCodeServiceAsync service = GWT
	.create(BaseCodeService.class);
	
	private Record record;

	public CustomerRepairInspectionOrderAddWin(Record reocrd) {
		this.record=reocrd;
		setWidth(800);
		setHeight(400);
		setShowMinimizeButton(false);
		setIsModal(true);
		setShowModalMask(true);
		centerInPage();
		setAlign(Alignment.CENTER);
		setTitle(ui.getRepairModule().repair_add_titile());
		final DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(DSKey.C_CUSTOMERREPAIRINSORDER,
				DSKey.C_CUSTOMERREPAIRINSORDER_DS, new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						model.customerRepairinsOrderDS = dataSource;
						_init();
					}
				});
	}

	private void _init() {
		final DynamicForm form = new DynamicForm();
		form.setDataSource(model.customerRepairinsOrderDS);
		form.setNumCols(4);

		// 合同主键
		HiddenItem contractItemId = new HiddenItem();
		contractItemId.setName("contractTemplateID");
		contractItemId.setValue(model.selectedContract
				.getAttribute("id"));

		// 指令编号
		TextItem orderNumberItem = new TextItem();
		orderNumberItem.setName("orderNumber");
		orderNumberItem.setDisabled(true);
		orderNumberItem.setValue(ui.getB().msgAutoIdInfo());

		// 送修合同编号
		TextItem contractNumberItem = new TextItem();
		contractNumberItem.setName("contractNumber");
		contractNumberItem.setValue(model.selectedContract
				.getAttribute("contractNumber"));
		contractNumberItem.setDisabled(true);

		
		
		// 客户
		final SelectItem customerCodeItem = new SelectItem();
		customerCodeItem.setName("m_CustomerIdentificationCode.id");
		customerCodeItem.setDefaultValue(record.getAttribute("m_CustomerIdentificationCode.id"));
		CodeRPCTool.bindData(CodeRPCTool.CUSTOMERIDENTIFICATIONCODE,
				customerCodeItem);
		
		
		// 联系人
		final TextItem linkManItem = new TextItem();
		linkManItem.setName("linkMan");
		linkManItem.setValue(record.getAttribute("linkMan"));
		
		// 联系方式
		final TextAreaItem linkWayItem = new TextAreaItem();
		linkWayItem.setName("linkWay");
		linkWayItem.setValue(record.getAttribute("linkWay"));
		
		customerCodeItem.addChangedHandler(new ChangedHandler() {
			@Override
			public void onChanged(ChangedEvent event) {
				service.getCustomerContact(customerCodeItem.getValueAsString(), new AsyncCallback<CustomerContact>() {
					@Override
					public void onSuccess(CustomerContact vo) {
						linkManItem.setValue(vo.getLinkman());
						linkWayItem.setValue("address:"+vo.getAddress()+" postcode:"+vo.getPostCode()+" tel:"+ vo.getTelephone());
					}
					@Override
					public void onFailure(Throwable e) {
						SC.warn("load customer info failed. Error:"+e);
					}
				});
			}
		});
		
		// 指令下达人
		TextItem makeByItem = new TextItem();
		makeByItem.setDisabled(true);
		makeByItem.setValue(UserTools.getCurrentUserName());
		makeByItem.setName("orderedBy");

		// 指令描述
		TextAreaItem desciptionItem = new TextAreaItem();
		desciptionItem.setName("description");

		form.setFields(contractItemId, orderNumberItem, contractNumberItem,
				/*sendProjectItem, */customerCodeItem, linkManItem,
				linkWayItem, makeByItem,desciptionItem);

		HLayout btnGroup = new HLayout();
		btnGroup.setLayoutLeftMargin(10);
		IButton btnSave = new IButton(ui.getB().btnSaveAdd());
		btnSave.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				form.saveData(new DSCallback() {
					public void execute(DSResponse response, Object rawData,
							DSRequest request) {
						SC.say(ui.getB().msgAddOpSuccess());
						//更新合同的状态
						CodeRPCTool.updateBussinessStateByAdd("RepairContractTemplate", model.selectedContract.getAttribute("id"));
					}
				});
			}
		});

		IButton btnClose = new IButton(ui.getB().btnClose());
		btnClose.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				destroy();
			}
		});
		btnGroup.addMember(btnSave);
		btnGroup.addMember(btnClose);

		addItem(form);
		addItem(btnGroup);
	}

}
