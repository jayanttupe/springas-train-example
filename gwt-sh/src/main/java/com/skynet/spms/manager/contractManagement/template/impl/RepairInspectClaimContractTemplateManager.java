package com.skynet.spms.manager.contractManagement.template.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.client.vo.contractManagement.BankInformation;
import com.skynet.spms.client.vo.contractManagement.ConsigneeAddress;
import com.skynet.spms.client.vo.contractManagement.Contract;
import com.skynet.spms.client.vo.contractManagement.ContractItem;
import com.skynet.spms.client.vo.contractManagement.RecipeInvoiceAddress;
import com.skynet.spms.client.vo.contractManagement.ShippingAddress;
import com.skynet.spms.manager.contractManagement.template.IRuoterManager;
import com.skynet.spms.persistence.entity.base.AddressInfo;
import com.skynet.spms.persistence.entity.base.dataDictionary.EntityStatusMonitor;
import com.skynet.spms.persistence.entity.contractManagement.template.supplierContactTemplate.repairInspectClaimContractTemplate.RepairInspectClaimContractTemplate;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.baseEnterpriseInformation.BaseEnterpriseInformation;
import com.skynet.spms.persistence.entity.spmsdd.AuditStatus;
import com.skynet.spms.tools.EnumUtil;

@Service(value = "repairInspectClaimContractTemplateManagerForOrder")
@Transactional
public class RepairInspectClaimContractTemplateManager extends
		HibernateDaoSupport implements IRuoterManager {

	private Logger log = LoggerFactory
			.getLogger(RepairInspectClaimContractTemplateManager.class);

	@Resource
	EnumUtil enumUtil;

	@Resource(name = "sessionFactory")
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	public Contract getContract(String id) {
		// 客户端vo
		Contract contract = new Contract();
		ContractItem contractItem = new ContractItem();
		List<ContractItem> contractItems = new ArrayList<ContractItem>();
		// 查询合同信息
		RepairInspectClaimContractTemplate obj = getHibernateTemplate().get(
				RepairInspectClaimContractTemplate.class, id);
		contract.setId(obj.getId());
		contract.setContractNumber(obj.getContractNumber());
		if (obj.getM_DeliveryType() != null) {
			contract.setM_DeliveryType(obj.getM_DeliveryType().name());
		}
		if (obj.getM_TradeMethods() != null) {
			contract.setM_TradeMethods(obj.getM_TradeMethods().name());
		}
		if (obj.getM_Priority() != null) {
			contract.setM_Priority(obj.getM_Priority().name());
		}
		if (obj.getM_TransportationCode() != null) {
			contract.setM_TransportationCode(obj.getM_TransportationCode().name());
		}
		contract.setIsAppointFreightAgent(obj.getBuyerFreightAgent());
		if(null!=obj.getCarrierName()){
			contract.setAppointForwarder(obj.getCarrierName().getCode());
		}
		contract.setAppointForwarderLinkman(obj.getAppointForwarderLinkman());
		contract.setAppointForwarderContact(obj.getAppointForwarderContact());
		
		
		contractItem.setId(obj.getM_RepairContractItem().getId());
		contractItem.setPartNumber(obj.getM_RepairContractItem()
				.getPartNumber());
		contractItem.setQuantity(1);
		contractItem.setUnitPriceAmount(0.0f);
		if (null != obj.getM_RepairContractItem().getM_UnitOfMeasureCode()) {
			contractItem.setUnit(obj.getM_RepairContractItem()
							.getM_UnitOfMeasureCode().name());
		}
		if(null!=obj.getM_RepairContractItem().getCurrency()){
			contractItem.setCurrency(obj.getM_RepairContractItem().getCurrency().name());
		}
		contractItems.add(contractItem);
		contract.setContractItems(contractItems);
		return contract;
	}

	@Override
	public List<Contract> getContracts(String userName) {
		// TODO Auto-generated method stub
		List<Contract> contractsList = new ArrayList();
		List<RepairInspectClaimContractTemplate> repairContractsList = new ArrayList();
		String hql = "from RepairInspectClaimContractTemplate rcct "
				+ " where rcct.makeBy='" + userName
				+ "' and rcct.auditStatus='" + AuditStatus.pass
				+ "' and rcct.m_BussinessStatusEntity.status<>'"
				+ EntityStatusMonitor.closed + "' and rcct.deleted=false"
				+ " order by rcct.createDate desc";

		log.info("hql:" + hql);
		repairContractsList = getHibernateTemplate().find(hql);
		for (int i = 0; i < repairContractsList.size(); i++) {
			RepairInspectClaimContractTemplate obj = repairContractsList.get(i);
			Contract contract = new Contract();
			ContractItem contractItem = new ContractItem();
			List<ContractItem> contractItems = new ArrayList<ContractItem>();
			contract.setId(obj.getId());
			contract.setContractNumber(obj.getContractNumber());
			// 合同总金额
			contract.setContractTotalAmount(obj.getExtendedValueTotalAmount());

			// 银行信息
			String enterHql = "from RepairAgency where m_RepairShopCode=?";
			Query query = getSession().createQuery(enterHql);
			query.setParameter(0, obj.getM_RepairShopCode());
			List bankList = query.list();
			if (bankList != null && bankList.size() > 0) {
				BaseEnterpriseInformation bsEnterpriseInfo = (BaseEnterpriseInformation) bankList
						.get(0);
				contract.setRecieveName(bsEnterpriseInfo.getAbbreviation());
				com.skynet.spms.persistence.entity.organization.enterpriseInformation.baseEnterpriseInformation.BankInformation serBankInfomation = null;
				List bankInfoList = bsEnterpriseInfo.getM_BankInformation();
				if (bankInfoList != null && bankInfoList.size() > 0) {
					serBankInfomation = (com.skynet.spms.persistence.entity.organization.enterpriseInformation.baseEnterpriseInformation.BankInformation) bankInfoList
							.get(0);
					BankInformation clientBankInfo = new BankInformation();
					clientBankInfo.setBankAccount(serBankInfomation
							.getBankAccount());
					clientBankInfo.setBankAddress(serBankInfomation
							.getBankAddress());
					clientBankInfo.setBankName(serBankInfomation.getBankName());
					contract.setBankInformation(clientBankInfo);

				}
			}

			String addressInfoHql = "from AddressInfo ai where ai.uuid='"
					+ obj.getId() + "'";
			List addressInfoList = getHibernateTemplate().find(addressInfoHql);
			AddressInfo addressInfo = null;
			//log.info("size=" + addressInfoList.size());
			if (addressInfoList != null && addressInfoList.size() > 0) {
				addressInfo = (AddressInfo) addressInfoList.get(0);

				// 收发票信息
				RecipeInvoiceAddress clientRecipeInvoiceAddress = new RecipeInvoiceAddress();
				clientRecipeInvoiceAddress.setPostCode(addressInfo
						.getInvocieZipCode());
				clientRecipeInvoiceAddress.setRecipient(addressInfo
						.getInvocieMan());
				clientRecipeInvoiceAddress.setAddress(addressInfo
						.getInvocieAddr());
				contract.setRecipeInvoiceAddress(clientRecipeInvoiceAddress);

				// 发货地址
				ShippingAddress clientShippingAddress = new ShippingAddress();
				clientShippingAddress.setAddress(addressInfo.getShippingAddr());
				clientShippingAddress
						.setConsignor(addressInfo.getShippingMan());
				clientShippingAddress.setPostCode(addressInfo
						.getShippingZipCode());
				contract.setShippingAddress(clientShippingAddress);

				// 收货地址
				ConsigneeAddress clientConsigneeAddress = new ConsigneeAddress();
				clientConsigneeAddress.setAddress(addressInfo
						.getConsigneeAddr());
				clientConsigneeAddress.setRecipient(addressInfo
						.getConsigneeMan());
				clientConsigneeAddress.setPostCode(addressInfo
						.getConsigneeZipCode());
				contract.setConsigneeAddress(clientConsigneeAddress);
			}

			contractItem.setId(obj.getM_RepairContractItem().getId());
			contractItem.setPartNumber(obj.getM_RepairContractItem()
					.getPartNumber());
			contractItem.setQuantity(obj.getM_RepairContractItem()
					.getQuantity());
			contractItem.setUnitPriceAmount(obj.getM_RepairContractItem()
					.getUnitPrice());
			contractItems.add(contractItem);
			contract.setContractItems(contractItems);
			contractsList.add(contract);
		}

		return contractsList;
	}


}
