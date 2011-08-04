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

import com.google.gwt.user.rebind.rpc.ProblemReport.Priority;
import com.skynet.spms.client.vo.contractManagement.BankInformation;
import com.skynet.spms.client.vo.contractManagement.ConsigneeAddress;
import com.skynet.spms.client.vo.contractManagement.Contract;
import com.skynet.spms.client.vo.contractManagement.ContractItem;
import com.skynet.spms.client.vo.contractManagement.RecipeInvoiceAddress;
import com.skynet.spms.client.vo.contractManagement.ShippingAddress;
import com.skynet.spms.manager.contractManagement.template.IRuoterManager;
import com.skynet.spms.persistence.entity.base.AddressInfo;
import com.skynet.spms.persistence.entity.base.dataDictionary.EntityStatusMonitor;
import com.skynet.spms.persistence.entity.contractManagement.template.SalesContractTemplate.SalesContractTemplate;
import com.skynet.spms.persistence.entity.customerService.sales.salesContract.SalesContractItem;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.baseEnterpriseInformation.BaseEnterpriseInformation;
import com.skynet.spms.persistence.entity.spmsdd.AuditStatus;
import com.skynet.spms.persistence.entity.spmsdd.DeliveryType;
import com.skynet.spms.persistence.entity.spmsdd.TradeMethods;
import com.skynet.spms.persistence.entity.spmsdd.TransportationCode;
import com.skynet.spms.tools.EnumUtil;

@Service(value = "SalesContractTemplateForOrder")
@Transactional
public class SalesContractTemplateManager extends HibernateDaoSupport
		implements IRuoterManager {

	private Logger log=LoggerFactory.getLogger(SalesContractTemplateManager.class);
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
		SalesContractTemplate obj = getHibernateTemplate().get(
				SalesContractTemplate.class, id);
		contract.setId(obj.getId());
		contract.setContractNumber(obj.getContractNumber());
		if(obj.getM_DeliveryType()!=null){
			contract.setM_DeliveryType(obj.getM_DeliveryType().name());
		}
		if(obj.getM_TradeMethods()!=null){
			contract.setM_TradeMethods(obj.getM_TradeMethods().name());
		}
		if(obj.getM_Priority()!=null){
			contract.setM_Priority(obj.getM_Priority().name());
		}
		if(obj.getM_TransportationCode()!=null){
			contract.setM_TransportationCode(obj.getM_TransportationCode().name());
		}
		contract.setIsAppointFreightAgent(obj.getBuyerFreightAgent());
		if(null!=obj.getCarrierName()){
			contract.setAppointForwarder(obj.getCarrierName().getCode());
		}
		contract.setAppointForwarderLinkman(obj.getAppointForwarderLinkman());
		contract.setAppointForwarderContact(obj.getAppointForwarderContact());
		
		String hql="select o from SalesContractItem o where o.salesTemplate.id=? and o.deleted=false ";
		Query query=getSession().createQuery(hql);
		query.setParameter(0, id);
		@SuppressWarnings("unchecked")
		List<SalesContractItem> items=query.list();
		for (SalesContractItem item : items) {
			ContractItem contractItem = new ContractItem();
			contractItem.setId(item.getId());
			contractItem.setPartNumber(item.getPartNumber());
			if(null!=item.getQuantity()){
				contractItem.setQuantity(item.getQuantity().intValue());
			}
			if(null!=item.getUnitPrice()){
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
		List<Contract> contractsList = new ArrayList();
		List<SalesContractTemplate> salesContractsList = new ArrayList();
		String hql = null;
		 if("wanghai".endsWith(userName))
			 hql="from SalesContractTemplate rcct " +
				" where rcct.createBy='" +userName+
				"' and rcct.auditStatus='" +AuditStatus.pass+
				"' and rcct.m_BussinessStatusEntity.status<>'" +EntityStatusMonitor.closed+
				"' and rcct.deleted=false" +
				" order by rcct.createDate desc"; 
		 else
			 hql="from SalesContractTemplate rcct " +
				" where rcct.auditStatus='" +AuditStatus.pass+
				"' and rcct.m_BussinessStatusEntity.status<>'" +EntityStatusMonitor.closed+
				"' and rcct.deleted=false" +
				" order by rcct.createDate desc"; 
		 
		log.info("hql:"+hql);
		salesContractsList = getHibernateTemplate().find(hql);
		for(SalesContractTemplate obj:salesContractsList){
			Contract contract = new Contract();
			ContractItem contractItem = new ContractItem();
			List<ContractItem> contractItems = new ArrayList<ContractItem>();
			contract.setId(obj.getId());
			contract.setContractNumber(obj.getContractNumber());
			//合同总金额
			log.info("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!totalAmount="+obj.getExtendedValueTotalAmount());
			contract.setContractTotalAmount(obj.getExtendedValueTotalAmount());
			//总项数
			contract.setTotalCount(obj.getTotalCount());
			String itemsHql="select o from SalesContractItem o where o.salesTemplate.id=? and o.deleted=false ";
			Query query=getSession().createQuery(itemsHql);
			query.setParameter(0, obj.getId());
			@SuppressWarnings("unchecked")
			List<SalesContractItem> items=query.list();
			for (SalesContractItem item : items) {
				contractItem.setId(item.getId());
				contractItem.setPartNumber(item.getPartNumber());
				if(null!=item.getQuantity()){
					contractItem.setQuantity(item.getQuantity().intValue());
				}
				if(null!=item.getUnitPrice()){
					contractItem.setUnitPriceAmount(item.getUnitPrice());
				}
				contractItems.add(contractItem);
			}
			contract.setContractItems(contractItems);
			
			String addressInfoHql = "from AddressInfo ai where ai.uuid='" +obj.getId()+"'";
			List addressInfoList = getHibernateTemplate().find(addressInfoHql);
			AddressInfo addressInfo =  null;
			if(addressInfoList!=null&&addressInfoList.size()>0)
			{
				addressInfo = (AddressInfo)addressInfoList.get(0);
	
				//收发票信息
				RecipeInvoiceAddress clientRecipeInvoiceAddress = new RecipeInvoiceAddress();
				clientRecipeInvoiceAddress.setPostCode(addressInfo.getInvocieZipCode());
				clientRecipeInvoiceAddress.setRecipient(addressInfo.getInvocieMan());
				clientRecipeInvoiceAddress.setAddress(addressInfo.getInvocieAddr());
				contract.setRecipeInvoiceAddress(clientRecipeInvoiceAddress);
				
				//发货地址
				ShippingAddress clientShippingAddress = new ShippingAddress();
				clientShippingAddress.setAddress(addressInfo.getShippingAddr());
				clientShippingAddress.setConsignor(addressInfo.getShippingMan());
				clientShippingAddress.setPostCode(addressInfo.getShippingZipCode());
				contract.setShippingAddress(clientShippingAddress);
				
				//收货地址
				ConsigneeAddress clientConsigneeAddress = new ConsigneeAddress();
				clientConsigneeAddress.setAddress(addressInfo.getConsigneeAddr());
				clientConsigneeAddress.setRecipient(addressInfo.getConsigneeMan());
				clientConsigneeAddress.setPostCode(addressInfo.getConsigneeZipCode());
				contract.setConsigneeAddress(clientConsigneeAddress);
			}

			
			//银行信息
			String enterHql = "from Customer where m_CustomerIdentificationCode=?";
			Query codeQuery = getSession().createQuery(enterHql);
			codeQuery.setParameter(0, obj.getCustomerIdenty());
			List bankList = codeQuery.list();
			if(bankList!=null&&bankList.size()>0){
				BaseEnterpriseInformation bsEnterpriseInfo = (BaseEnterpriseInformation)bankList.get(0);
				contract.setRecieveName(bsEnterpriseInfo.getAbbreviation());
				 com.skynet.spms.persistence.entity.organization.enterpriseInformation.baseEnterpriseInformation.BankInformation serBankInfomation =null;
				List bankInfoList = bsEnterpriseInfo.getM_BankInformation();
				if(bankInfoList!=null&&bankInfoList.size()>0)
				{
					serBankInfomation = ( com.skynet.spms.persistence.entity.organization.enterpriseInformation.baseEnterpriseInformation.BankInformation)bankInfoList.get(0);
					BankInformation clientBankInfo = new BankInformation();
					clientBankInfo.setBankAccount(serBankInfomation.getBankAccount());
					clientBankInfo.setBankAddress(serBankInfomation.getBankAddress());
					clientBankInfo.setBankName(serBankInfomation.getBankName());
					contract.setBankInformation(clientBankInfo);
					
					
				}
			}
			contractsList.add(contract);
		}
		return contractsList;
	}

}
