package com.skynet.spms.manager.contractManagement.template.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.client.vo.contractManagement.Contract;
import com.skynet.spms.client.vo.contractManagement.ContractItem;
import com.skynet.spms.manager.contractManagement.template.IRuoterManager;
import com.skynet.spms.persistence.entity.contractManagement.template.supplierContactTemplate.leaseContractTemplate.SSLeaseContractTemplate;
import com.skynet.spms.persistence.entity.supplierSupport.lease.leaseContract.SSLeaseContractItem;
import com.skynet.spms.tools.EnumUtil;

@Service(value = "SSLeaseContractTemplateManagerForOrder")
@Transactional
public class SSLeaseContractTemplateManager extends HibernateDaoSupport
		implements IRuoterManager {

	@Resource
	EnumUtil enumUtil;

	@Resource(name = "sessionFactory")
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	public Contract getContract(String id) {
		// 客户端vo
		Contract contract = new Contract();

		List<ContractItem> contractItems = new ArrayList<ContractItem>();
		// 查询合同信息
		SSLeaseContractTemplate obj = getHibernateTemplate().get(
				SSLeaseContractTemplate.class, id);
		contract.setId(obj.getId());
		contract.setContractNumber(obj.getContractNumber());

		if (obj.getDeliveryType() != null) {
			contract.setM_DeliveryType(obj.getDeliveryType().name());
		}
		if (obj.getM_TradeMethods() != null) {
			contract.setM_TradeMethods(obj.getM_TradeMethods().name());
		}
		if (obj.getM_Priority() != null) {
			contract.setM_Priority(obj.getM_Priority().name());
		}
		if (obj.getM_TransportationCode() != null) {
			contract.setM_TransportationCode(obj.getM_TransportationCode()
					.name());
		}
		contract.setIsAppointFreightAgent(obj.getSellerFreightAgent());
		if (null != obj.getM_CarrierName()) {
			contract.setAppointForwarder(obj.getM_CarrierName().getCode());
		}
		contract.setAppointForwarderLinkman(obj.getAppointForwarderLinkman());
		contract.setAppointForwarderContact(obj.getAppointForwarderContact());

		List<SSLeaseContractItem> m_LeaseContractItem = obj
				.getM_LeaseContractItem();
		for (SSLeaseContractItem item : m_LeaseContractItem) {
			ContractItem contractItem = new ContractItem();
			contractItem.setId(item.getId());
			contractItem.setPartNumber(item.getPartNumber());
			contractItem.setQuantity(item.getQuantity());
			contractItem.setUnitPriceAmount(item.getUnitPrice());
			if (null != item.getM_UnitOfMeasureCode()) {
				contractItem.setUnit(item.getM_UnitOfMeasureCode().name());
			}
			if (null != item.getM_InternationalCurrencyCode()) {
				contractItem.setCurrency(item.getM_InternationalCurrencyCode()
						.name());
			}

			contractItems.add(contractItem);
		}
		contract.setContractItems(contractItems);
		return contract;
	}

	@Override
	public List<Contract> getContracts(String userName) {
		return null;
	}

}
