package com.skynet.spms.client.gui.customerService.commonui;

import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.CodeRPCTool;
import com.skynet.spms.client.gui.base.Utils;
import com.skynet.spms.client.gui.base.i18n.I18n;
import com.skynet.spms.client.gui.customerService.common.DSKey;
import com.skynet.spms.client.vo.AddressVO;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.form.fields.HiddenItem;
import com.smartgwt.client.widgets.form.fields.SectionItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class BaseAddressForm extends VLayout {

	public enum Type {
		view, modify
	}

	private I18n ui = new I18n();

	private BaseAddressModel model = BaseAddressModel.getInstance();
	public DynamicForm form;

	/**
	 * 实例一个地址信息表单，
	 * 
	 * @param sheetId
	 *            关联的表单主键
	 */
	public BaseAddressForm(final Type type) {
		form = new DynamicForm();
		/** 初始化地址添加数据源 * */
		final DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(DSKey.C_SALESCONTRACT,
				DSKey.C_SALESADDRESS_DS, new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						form.setDataSource(dataSource);
						build(type);
						if (model.addr_sheetId != null) {
							Criteria criteria = new Criteria();
							StringBuilder sb = new StringBuilder();
							sb.append(model.addr_sheetId);
							criteria.setAttribute("uuid", sb.toString());
							form.fetchData(criteria, new DSCallback() {
								@Override
								public void execute(DSResponse response,
										Object rawData, DSRequest request) {
									shippingAddr.setValue(shippingAddr_item
											.getValue());
									consigneeAddr.setValue(consigneeAddr_item
											.getValue());
									invocieAddr.setValue(invocieAddr_item
											.getValue());
								}
							});
						}
					}
				});

	}

	/**
	 * 发货地址下拉列表
	 */
	private ComboBoxItem shippingAddr;
	/**
	 * 发货地址 将被保存到数据库
	 */
	private HiddenItem shippingAddr_item;

	/**
	 * 发货联系人
	 */
	private TextItem shippingMan;
	/**
	 * 发货联系方式
	 */
	private TextItem shippingLinkType;
	/**
	 * 发货单位
	 */
	private TextItem shippingUnit;

	/**
	 * 发票收取地址
	 */
	private ComboBoxItem invocieAddr;
	/**
	 * 发票收取地址 将被保存到数据库
	 */
	private HiddenItem invocieAddr_item;
	/**
	 * 发票收取人
	 */
	private TextItem invocieMan;

	/**
	 * 发票收取单位
	 */
	private TextItem invocieUnit;

	/**
	 * 收货地址
	 */
	private ComboBoxItem consigneeAddr;
	/**
	 * 收货地址 将被保存到数据库
	 */
	private HiddenItem consigneeAddr_item;
	/**
	 * 收货联系人
	 */
	private TextItem consigneeMan;
	/**
	 * 收货联系方式
	 */
	private TextItem consigneeLinkType;

	/**
	 * 收货单位
	 */
	private TextItem consigneeUnit;

	public IButton btnSave;

	protected void build(Type type) {
		final HiddenItem contractId = new HiddenItem();
		contractId.setName("uuid");

		shippingAddr = new ComboBoxItem();
		shippingAddr.setTitle("发货地址");
		shippingAddr.setWidth("100%");
		// shippingAddr
		// .addClickHandler(new
		// com.smartgwt.client.widgets.form.fields.events.ClickHandler() {
		//
		// @Override
		// public void onClick(
		// com.smartgwt.client.widgets.form.fields.events.ClickEvent event) {
		// if (model.enterpriseId == null) {
		// return;
		// }
		// LinkedHashMap<String, AddressVO> addrMap = model.shippingAddrMap;
		// LinkedHashMap<String, String> valueMap = new LinkedHashMap<String,
		// String>();
		// String str="";
		// for (Map.Entry<String, AddressVO> address : addrMap
		// .entrySet()) {
		// AddressVO addressVO = address.getValue();
		// str+=addressVO.getEnterpriseId()+"--";
		// if (addressVO.getEnterpriseId().equals(
		// model.enterpriseId)) {
		// valueMap.put(addressVO.getUuid(),
		// addressVO.getAddress());
		// }
		// }
		// shippingLinkType.setValueMap(valueMap+"-----"+str);
		// SC.say(String.valueOf(valueMap.size()));
		// }
		// });
		CodeRPCTool.bindShippingAddressByEnterId(model.enterpriseId,
				model.businessType, shippingAddr);
		shippingAddr.addChangedHandler(new ChangedHandler() {
			@Override
			public void onChanged(ChangedEvent event) {
				String key = shippingAddr.getValueAsString();
				AddressVO vo = model.shippingAddrMap.get(key);
				shippingLinkType.setValue(vo.getLinkType());
				shippingMan.setValue(vo.getLinkMan());
				shippingUnit.setValue(vo.getUnit());
				shippingAddr_item.setValue(shippingAddr.getDisplayValue());
			}
		});
		shippingAddr_item = new HiddenItem();
		shippingAddr_item.setName("shippingAddr");
		shippingAddr_item.setVisible(false);

		shippingLinkType = new TextItem();
		shippingLinkType.setName("shippingLinkType");
		shippingLinkType.setWidth("100%");

		shippingMan = new TextItem();
		shippingMan.setName("shippingMan");
		shippingMan.setWidth("100%");

		shippingUnit = new TextItem();
		shippingUnit.setName("shippingUnit");
		shippingUnit.setWidth("100%");

		invocieAddr = new ComboBoxItem();
		invocieAddr.setTitle("收发票地址");
		invocieAddr.setWidth("100%");
		CodeRPCTool.bindInvocieAddressByEnterId(model.enterpriseId,
				model.businessType, invocieAddr);
		invocieAddr.addChangedHandler(new ChangedHandler() {

			@Override
			public void onChanged(ChangedEvent event) {
				String key = invocieAddr.getValueAsString();
				AddressVO vo = model.invocieAddrMap.get(key);
				invocieMan.setValue(vo.getLinkMan());
				invocieUnit.setValue(vo.getUnit());
				invocieAddr_item.setValue(invocieAddr.getDisplayValue());
			}
		});
		invocieAddr_item = new HiddenItem();
		invocieAddr_item.setName("invocieAddr");
		invocieAddr_item.setVisible(false);

		invocieMan = new TextItem();
		invocieMan.setName("invocieMan");
		invocieMan.setWidth("100%");

		invocieUnit = new TextItem();
		invocieUnit.setName("invocieUnit");
		invocieUnit.setWidth("100%");

		consigneeAddr = new ComboBoxItem();
		consigneeAddr.setTitle("收货地址");
		consigneeAddr.setWidth("100%");
		CodeRPCTool.bindConsigneeAddressByEnterId(model.enterpriseId,
				model.businessType, consigneeAddr);
		consigneeAddr.addChangedHandler(new ChangedHandler() {
			@Override
			public void onChanged(ChangedEvent event) {
				String key = consigneeAddr.getValueAsString();
				AddressVO vo = model.consigneeAddrMap.get(key);
				consigneeLinkType.setValue(vo.getLinkType());
				consigneeMan.setValue(vo.getLinkMan());
				consigneeUnit.setValue(vo.getUnit());
				consigneeAddr_item.setValue(consigneeAddr.getDisplayValue());
			}
		});

		consigneeAddr_item = new HiddenItem();
		consigneeAddr_item.setName("consigneeAddr");
		consigneeAddr_item.setVisible(false);

		consigneeLinkType = new TextItem();
		consigneeLinkType.setName("consigneeLinkType");
		consigneeLinkType.setWidth("100%");

		consigneeMan = new TextItem();
		consigneeMan.setName("consigneeMan");
		consigneeMan.setWidth("100%");

		consigneeUnit = new TextItem();
		consigneeUnit.setName("consigneeUnit");
		consigneeUnit.setWidth("100%");

		SectionItem shippingSection = new SectionItem();
		shippingSection.setTop(5);
		shippingSection.setDefaultValue("发货地址");
		shippingSection.setSectionExpanded(true);
		shippingSection.setItemIds("shippingAddr", "shippingLinkType",
				"shippingMan", "shippingUnit");

		SectionItem invocieSection = new SectionItem();
		invocieSection.setTop(5);
		invocieSection.setDefaultValue("收发票地址");
		invocieSection.setSectionExpanded(true);
		invocieSection.setItemIds("invocieAddr", "invocieMan", "invocieUnit");

		SectionItem consigneeSection = new SectionItem();
		consigneeSection.setTop(5);
		consigneeSection.setDefaultValue("收货地址");
		consigneeSection.setSectionExpanded(true);
		consigneeSection.setItemIds("consigneeAddr", "consigneeLinkType",
				"consigneeMan", "consigneeUnit");

		form.setFields(shippingSection, contractId, shippingUnit, shippingAddr,
				shippingAddr_item, shippingLinkType, shippingMan,
				invocieSection, invocieUnit, invocieAddr, invocieAddr_item,
				invocieMan, consigneeSection, consigneeUnit, consigneeAddr,
				consigneeAddr_item, consigneeLinkType, consigneeMan);
		this.addMember(form);

		HLayout btnGroup = new HLayout();
		btnGroup.setMembersMargin(3);
		btnGroup.setMargin(5);
		// add by tqc
		if (type.name().equals("view")) {
			Utils.setReadOnlyForm(form);
		} else {
			btnSave = new IButton(ui.getB().btnSaveAdd());
			btnSave.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					if (model.addr_sheetId != null) {
						contractId.setValue(model.addr_sheetId);
						shippingAddr_item.setValue(shippingAddr
								.getDisplayValue());
						consigneeAddr_item.setValue(consigneeAddr
								.getDisplayValue());
						invocieAddr_item
								.setValue(invocieAddr.getDisplayValue());
						form.saveData(new DSCallback() {
							public void execute(DSResponse response,
									Object rawData, DSRequest request) {
								SC.say(ui.getB().msgAddOpSuccess());
							}
						});
					} else {
						SC.warn("请先保存基本信息");
					}

				}
			});
			btnGroup.addMember(btnSave);
		}

		this.addMember(btnGroup);
	}

	public ComboBoxItem getShippingAddr() {
		return shippingAddr;
	}

	public void setShippingAddr(ComboBoxItem shippingAddr) {
		this.shippingAddr = shippingAddr;
	}

	public TextItem getShippingMan() {
		return shippingMan;
	}

	public void setShippingMan(TextItem shippingMan) {
		this.shippingMan = shippingMan;
	}

	public TextItem getShippingLinkType() {
		return shippingLinkType;
	}

	public void setShippingLinkType(TextItem shippingLinkType) {
		this.shippingLinkType = shippingLinkType;
	}

	public TextItem getShippingUnit() {
		return shippingUnit;
	}

	public void setShippingUnit(TextItem shippingUnit) {
		this.shippingUnit = shippingUnit;
	}

	public ComboBoxItem getInvocieAddr() {
		return invocieAddr;
	}

	public void setInvocieAddr(ComboBoxItem invocieAddr) {
		this.invocieAddr = invocieAddr;
	}

	public TextItem getInvocieUnit() {
		return invocieUnit;
	}

	public void setInvocieUnit(TextItem invocieUnit) {
		this.invocieUnit = invocieUnit;
	}

	public ComboBoxItem getConsigneeAddr() {
		return consigneeAddr;
	}

	public void setConsigneeAddr(ComboBoxItem consigneeAddr) {
		this.consigneeAddr = consigneeAddr;
	}

	public TextItem getConsigneeMan() {
		return consigneeMan;
	}

	public void setConsigneeMan(TextItem consigneeMan) {
		this.consigneeMan = consigneeMan;
	}

	public TextItem getConsigneeLinkType() {
		return consigneeLinkType;
	}

	public void setConsigneeLinkType(TextItem consigneeLinkType) {
		this.consigneeLinkType = consigneeLinkType;
	}

	public TextItem getConsigneeUnit() {
		return consigneeUnit;
	}

	public void setConsigneeUnit(TextItem consigneeUnit) {
		this.consigneeUnit = consigneeUnit;
	}

}
