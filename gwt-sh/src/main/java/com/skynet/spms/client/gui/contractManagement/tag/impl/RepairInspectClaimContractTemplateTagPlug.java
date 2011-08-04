package com.skynet.spms.client.gui.contractManagement.tag.impl;

import java.util.List;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.skynet.spms.client.gui.base.i18n.ContractModuleConst;
import com.skynet.spms.client.gui.contractManagement.tag.BaseTagPlug;
import com.skynet.spms.client.gui.contractManagement.tag.Tag;
import com.skynet.spms.client.gui.contractManagement.tag.TagEnum;

public class RepairInspectClaimContractTemplateTagPlug extends BaseTagPlug {
	
	private ContractModuleConst ui=GWT.create(ContractModuleConst.class);

	public RepairInspectClaimContractTemplateTagPlug() {
		tagType = TagEnum.RepairInspectClaimContractTemplate;
	}

	@Override
	public void plug(Map<TagEnum, List<Tag>> tags) {
		// 合同编号
		Tag contractNumberTag = new Tag(ui.contractNumber(), "contractNumber");
		// 优先级
		Tag priorityTag = new Tag(ui.priority(), "m_Priority");
		// 飞机机尾号
		Tag airIdentificationNumberTag = new Tag(ui.airIdentificationNumber(),
				"airIdentificationNumber");
		// 是否指定运代
		Tag buyerFreightAgentTag = new Tag(ui.buyerFreightAgent(), "buyerFreightAgent");
		// 运代商
		Tag carrierNameTag = new Tag(ui.carrierName(), "carrierName");
		// 运代商联系人
		Tag carrierLinkManTag = new Tag(ui.carrierLinkMan(), "carrierLinkMan");
		// 运代商联系方式
		Tag carrierLinkWayTag = new Tag(ui.carrierLinkWay(), "carrierLinkWay");
		// 交货方式
		Tag deliveryTypeTag = new Tag(ui.deliveryType(), "m_DeliveryType");
		// 运输方式
		Tag transportationCodeTag = new Tag(ui.transportationCode(), "m_TransportationCode");
		// 贸易方式
		Tag tradeMethodsTag = new Tag(ui.tradeMethods(), "m_TradeMethods");
		// GTA协议
		Tag enterpriseIdsTag = new Tag(ui.enterpriseIds(), "enterpriseIds");

		// 批次加入
		addModuleTag(contractNumberTag, priorityTag,
				airIdentificationNumberTag, buyerFreightAgentTag,
				carrierNameTag, carrierLinkManTag, carrierLinkWayTag,
				deliveryTypeTag, transportationCodeTag, tradeMethodsTag,
				enterpriseIdsTag);

		// 加入tag Map
		tags.put(tagType, moduleTags);
	}

}
