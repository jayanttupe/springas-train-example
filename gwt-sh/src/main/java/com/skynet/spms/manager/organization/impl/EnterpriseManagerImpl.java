package com.skynet.spms.manager.organization.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.datasource.BeanPropUtil;
import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.manager.organization.EnterpriseManager;
import com.skynet.spms.persistence.entity.csdd.c.CAGECode;
import com.skynet.spms.persistence.entity.csdd.c.CarrierName;
import com.skynet.spms.persistence.entity.csdd.c.CustomerIdentificationCode;
import com.skynet.spms.persistence.entity.csdd.m.ManufacturerCode;
import com.skynet.spms.persistence.entity.csdd.r.RepairShopCode;
import com.skynet.spms.persistence.entity.csdd.s.SupplierCode;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.baseEnterpriseInformation.BaseEnterpriseInformation;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.baseEnterpriseInformation.VAT;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.carriers.Carrier;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.clearanceAgencys.ClearanceAgency;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.customers.Customer;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.manufacturers.Manufacturer;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.repairAgencys.RepairAgency;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.suppliers.Supplier;
import com.skynet.spms.persistence.entity.organization.userInformation.User;
import com.skynet.spms.persistence.entity.spmsdd.ClearanceAgent;

@Service
@Transactional
public class EnterpriseManagerImpl extends CommonManagerImpl<BaseEnterpriseInformation> implements EnterpriseManager{

	private Logger log=LoggerFactory.getLogger(EnterpriseManagerImpl.class);
	@Override
	public List<BaseEnterpriseInformation> queryTree(String parentId, Class cls) {
		Criteria criteria = getSession().createCriteria(cls);
		if(parentId != null)
			criteria.add(Restrictions.eq("parentId", parentId));
		List<BaseEnterpriseInformation> list = criteria.list();
		//临时代码，用于防止一对一关联表数据被删除造成运行错误，实际运行时需要删除
		for(BaseEnterpriseInformation be : list){
			if(be.getM_VAT() == null){
				be.setM_VAT(new VAT());
				getSession().saveOrUpdate(be);
			}
			if(be.getM_CAGECode() == null){
				CAGECode cage = new CAGECode();
				cage.setCode("cagecode");
				be.setM_CAGECode(cage);
				getSession().saveOrUpdate(be);
			}
		}
		//结束
		return list;
	}

	@Override
	public BaseEnterpriseInformation updateEntityCascade(Map<String, Object> props, String entityId, Class cls) {
		BaseEnterpriseInformation entity = (BaseEnterpriseInformation)getSession().get(cls, entityId);
		String cageCode = (String)props.remove("cageCode");
		entity.getM_CAGECode().setCode(cageCode);
		String baseCode = null;
		if(cls == Customer.class){
			baseCode = (String)props.remove("customerIdentificationCode");
			((Customer)entity).getM_CustomerIdentificationCode().setCode(baseCode);
		}else if(cls == Supplier.class){
			baseCode = (String)props.remove("supplierCode");
			((Supplier)entity).getM_SupplierCode().setCode(baseCode);
		}else if(cls == Manufacturer.class){
			baseCode = (String)props.remove("manufacturerCode");
			((Manufacturer)entity).getM_ManufacturerCode().setCode(baseCode);
		}else if(cls == ClearanceAgency.class){
			baseCode = (String)props.remove("clearenceAgent");
			((ClearanceAgency)entity).getM_ClearanceAgent().setCode(baseCode);
		}else if(cls == Carrier.class){
			baseCode = (String)props.remove("carrierName");
			((Carrier)entity).getM_CarrierName().setCode(baseCode);
		}else if(cls == RepairAgency.class){
			baseCode = (String)props.remove("repairShopCode");
			((RepairAgency)entity).getM_RepairShopCode().setCode(baseCode);
		}

		BeanPropUtil.fillEntityWithMap(entity, props);
		getSession().persist(entity);
		return entity;
	}

	/*@Override
	public BaseEnterpriseInformation saveVATCascade(BaseEnterpriseInformation entity) {
		BaseEnterpriseInformation entity = (BaseEnterpriseInformation)getSession().get(BaseEnterpriseInformation.class, id);
		if(vat.getId() == null){
			
		}
		getSession().update(entity);
		return entity;
	}*/

	@Override
	public void deleteCascade(String enterpriseId,Class cls) {
		BaseEnterpriseInformation entity = (BaseEnterpriseInformation)getSession().get(cls, enterpriseId);
		//删除增值税信息
		VAT vat = entity.getM_VAT();
		entity.setM_VAT(null);
		getSession().delete(vat);
		
		//删除企业代码
		CAGECode cageCode = entity.getM_CAGECode();
		entity.setM_CAGECode(null);
		getSession().delete(cageCode);
		
		//取消上级企业关联
		entity.setParentId(null);
		
		
		//删除各类型企业识别码
		if(cls == Customer.class){
			Customer customer = (Customer)entity;
			CustomerIdentificationCode ciCode = customer.getM_CustomerIdentificationCode();
			customer.setM_CustomerIdentificationCode(null);
			getSession().delete(ciCode);
			
		}else if(cls == Supplier.class){
			Supplier supplier = (Supplier)entity;
			SupplierCode scode = supplier.getM_SupplierCode();
			supplier.setM_SupplierCode(null);
			getSession().delete(scode);
			
		}else if(cls == Manufacturer.class){
			Manufacturer manufacturer = (Manufacturer)entity;
			ManufacturerCode mcode = manufacturer.getM_ManufacturerCode();
			manufacturer.setM_ManufacturerCode(null);
			getSession().delete(mcode);
			
		}else if(cls == ClearanceAgency.class){
			ClearanceAgency clearanceAgency = (ClearanceAgency)entity;
			ClearanceAgent caCode = clearanceAgency.getM_ClearanceAgent();
			clearanceAgency.setM_ClearanceAgent(null);
			getSession().delete(caCode);
			
		}else if(cls == Carrier.class){
			Carrier carrier = (Carrier)entity;
			CarrierName cnCode = carrier.getM_CarrierName();
			carrier.setM_CarrierName(null);
			getSession().delete(cnCode);
			
		}else if(cls == RepairAgency.class){
			RepairAgency repairAgency = (RepairAgency)entity;
			RepairShopCode rsCode = repairAgency.getM_RepairShopCode();
			repairAgency.setM_RepairShopCode(null);
			getSession().delete(rsCode);
			
		}
		entity.setDeleted(true);
		getSession().update(entity);
	}

	@Override
	public List<BaseEnterpriseInformation> queryByFilter(
			Map<String, Object> props, Class cls) {
		Criteria criteria = getSession().createCriteria(cls);
		for(Map.Entry<String, Object> entry : props.entrySet()){
			String key = entry.getKey();
			String value = (String)entry.getValue();
			String[] propName = key.split("\\.");
			if(propName.length > 1){
				criteria.createCriteria(propName[0], Criteria.INNER_JOIN);	
			}
			criteria.add(Restrictions.like(key, value));
		}
		return criteria.list();
	}

	@Override
	public void insertCascade(BaseEnterpriseInformation entity) {
		VAT vat = new VAT();
		entity.setM_VAT(vat);
		getSession().saveOrUpdate(entity);
	}

	@Override
	public List<BaseEnterpriseInformation> queryByCode(String propName,String codeValue, Class subCls) {
		Criteria criteria = getSession().createCriteria(subCls);
		criteria.createCriteria(propName, "als", Criteria.INNER_JOIN);
		criteria.add(Restrictions.eq("als.code", codeValue));
		return criteria.list();
	}
	
	//根据企业ID查找企业及其所有子企业
	@Override
	public List<BaseEnterpriseInformation> queryAllWithSubById(String enterpriseId) {
		BaseEnterpriseInformation ei = (BaseEnterpriseInformation)getSession().get(BaseEnterpriseInformation.class, enterpriseId);
		List<BaseEnterpriseInformation> eiList = new ArrayList<BaseEnterpriseInformation>();
		if(ei != null){
			fillSubEnterprise(ei,eiList);
		}
		return eiList;
	}
	private void fillSubEnterprise(BaseEnterpriseInformation ei,List<BaseEnterpriseInformation> eiList){
		eiList.add(ei);
		if(ei.getM_ChildEnterpriseInformation().size() > 0){
			for(BaseEnterpriseInformation bei : ei.getM_ChildEnterpriseInformation()){
				fillSubEnterprise(bei,eiList);
			}
		}
	}
	@Override
	public List<BaseEnterpriseInformation> queryAllWithSubByUsername(String username) {
		Criteria criteria = getSession().createCriteria(User.class);
		criteria.add(Restrictions.eq("username", username));
		User user = (User)criteria.uniqueResult();
		List<BaseEnterpriseInformation> eiList = new ArrayList<BaseEnterpriseInformation>();
		if(user != null){
			BaseEnterpriseInformation ei = user.getM_BaseEnterpriseInformation();
			if(ei != null){
				fillSubEnterprise(ei,eiList);
			}
		}
		return eiList;
	}
	
}
