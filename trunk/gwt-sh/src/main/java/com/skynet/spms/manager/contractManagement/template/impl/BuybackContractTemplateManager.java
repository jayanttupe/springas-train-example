package com.skynet.spms.manager.contractManagement.template.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.client.vo.contractManagement.Contract;
import com.skynet.spms.client.vo.contractManagement.ContractItem;
import com.skynet.spms.manager.contractManagement.template.IRuoterManager;
import com.skynet.spms.persistence.entity.contractManagement.template.CustomerContactTemplate.BuybackContractTemplate.BuybackContractTemplate;
import com.skynet.spms.persistence.entity.customerService.BuybackService.BuyBackPact.BuybackPactItem;
import com.skynet.spms.tools.EnumUtil;

@Service(value = "BuybackContractTemplateManagerForOrder")
@Transactional
public class BuybackContractTemplateManager extends HibernateDaoSupport
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
		BuybackContractTemplate obj = getHibernateTemplate().get(
				BuybackContractTemplate.class, id);
		contract.setId(obj.getId());
		if(obj.getM_DeliveryType()!=null){
			contract.setM_DeliveryType(obj.getM_DeliveryType().name());
		}
		if(obj.getM_TradeMethods()!=null){
			contract.setM_TradeMethods(obj.getM_TradeMethods().name());
		}
		contract.setContractNumber(obj.getContractNumber());
		contract.setM_Priority("routine");
		if(obj.getM_TransportationCode()!=null){
			contract.setM_TransportationCode(obj.getM_TransportationCode().name());
		}
		contract.setIsAppointFreightAgent(obj.getBuyerFreightAgent());
		if(null!=obj.getCarrierName()){
			contract.setAppointForwarder(obj.getCarrierName().getCode());
		}
		contract.setAppointForwarderLinkman(obj.getAppointForwarderLinkman());
		contract.setAppointForwarderContact(obj.getAppointForwarderContact());
		String hql = "select o from BuybackPactItem o where o.template.id=? and o.deleted=false ";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, id);
		@SuppressWarnings("unchecked")
		List<BuybackPactItem> m_LeaseContractItem = query.list();
		for (BuybackPactItem item : m_LeaseContractItem) {
			ContractItem contractItem = new ContractItem();
			contractItem.setId(item.getId());
			contractItem.setPartNumber(item.getPartNumber());
			if (null != item.getQuantity()) {
				contractItem.setQuantity(item.getQuantity().intValue());
			}
			if (null != item.getUnitPrice()) {
				contractItem.setUnitPriceAmount(item.getUnitPrice());
			}
			if(null!=item.getM_UnitOfMeasureCode()){
				contractItem.setUnit(item.getM_UnitOfMeasureCode().name());
			}
			if(null!=item.getCurrency()){
				contractItem.setCurrency(item.getCurrency().name());
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
