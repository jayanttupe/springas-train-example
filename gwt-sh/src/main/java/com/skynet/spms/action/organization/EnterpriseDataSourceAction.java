package com.skynet.spms.action.organization;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.BeanPropUtil;
import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.organization.EnterpriseManager;
import com.skynet.spms.manager.organization.UserManager;
import com.skynet.spms.persistence.entity.csdd.c.CAGECode;
import com.skynet.spms.persistence.entity.csdd.c.CarrierName;
import com.skynet.spms.persistence.entity.csdd.c.CustomerIdentificationCode;
import com.skynet.spms.persistence.entity.csdd.m.ManufacturerCode;
import com.skynet.spms.persistence.entity.csdd.r.RepairShopCode;
import com.skynet.spms.persistence.entity.csdd.s.SupplierCode;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.COMACSC.COMACSC;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.baseEnterpriseInformation.BaseEnterpriseInformation;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.carriers.Carrier;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.clearanceAgencys.ClearanceAgency;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.customers.Customer;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.manufacturers.Manufacturer;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.repairAgencys.RepairAgency;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.suppliers.Supplier;
import com.skynet.spms.persistence.entity.spmsdd.ClearanceAgent;
import com.skynet.spms.web.form.EnterpriseForm;

@Component
public class EnterpriseDataSourceAction implements DataSourceAction<EnterpriseForm> {
	
	private Logger log=LoggerFactory.getLogger(EnterpriseDataSourceAction.class);
	
	@Autowired
	EnterpriseManager enterpriseManager;
	
	private static Map<String , Class> dsCls;
	
	static{
		dsCls = new HashMap<String, Class>();
		dsCls.put("baseEnterprise_dataSource", BaseEnterpriseInformation.class);
		dsCls.put("COMACSC_dataSource", COMACSC.class);
		dsCls.put("customer_dataSource", Customer.class);
		dsCls.put("supplier_dataSource", Supplier.class);
		dsCls.put("manufacturer_dataSource", Manufacturer.class);
		dsCls.put("clearanceAgency_dataSource", ClearanceAgency.class);
		dsCls.put("carrier_dataSource", Carrier.class);
		dsCls.put("repairAgency_dataSource", RepairAgency.class);
	}
	
	@Override
	public String[] getBindDsName() {
		String[] dsName = new String[dsCls.size()];
		dsCls.keySet().toArray(dsName);
		return dsName;
	}

	@Override
	public void insert(EnterpriseForm item) {
		log.info("***********EnterpriseDataSourceAction.insert()");
		log.info("id : " + item.getId());
		log.info("parentId : " + item.getParentId());
		log.info("parentCompany : " + item.getParentCompany());
		log.info("isAppoint : " + item.isAppoint());
		BaseEnterpriseInformation enterpriseInfo = transfer(item);
		enterpriseManager.insertCascade(enterpriseInfo);
		item.setId(enterpriseInfo.getId());
		if(enterpriseInfo.getM_CAGECode()!= null){
			item.setCageCode(enterpriseInfo.getM_CAGECode().getCode());
		}
		
		item.setM_VAT(enterpriseInfo.getM_VAT());
		fillSubProps(enterpriseInfo, item);
	}

	@Override
	public EnterpriseForm update(Map<String, Object> newValues,String itemID) {
		log.info("***********EnterpriseDataSourceAction.update()");
		for(Map.Entry<String, Object> entry : newValues.entrySet()){
			log.info(entry.getKey() + " : " + entry.getValue());
		}
		log.info("itemID : " + itemID);
		
		String parentId = (String)newValues.get("parentId");
		if(parentId == null){
			newValues.put("parentId", "");
			newValues.put("parentCompany", "");
		}
		
		BaseEnterpriseInformation enterpriseInfo = enterpriseManager.updateEntityCascade(newValues, itemID, getCurrentClass());
		log.info("Entity class : " + enterpriseInfo.getClass());
		EnterpriseForm form = transfer(enterpriseInfo);
		log.info("****************************************end");
		return form;
	}

	@Override
	public void delete(String itemID) {
		log.info("***********EnterpriseDataSourceAction.delete()");
		log.info("delete itemID : " + itemID);
		enterpriseManager.deleteCascade(itemID, getCurrentClass());
	}

	@Override
	public List<EnterpriseForm> doQuery(Map<String, Object> values,
			int startRow, int endRow) {
		log.info("===============EnterpriseDataSourceAction.doQuery()");
		for(Map.Entry<String, Object> entry : values.entrySet()){
			log.info(entry.getKey() + " : " + entry.getValue());
		}
		
		String username = (String)values.remove("username");
		String enterpriseId = (String)values.remove("enterpriseId");
		String parentId = (String)values.remove("parentId");
		List<BaseEnterpriseInformation> eiList = null;
		
		
		if(enterpriseId != null){ 
			eiList = enterpriseManager.queryAllWithSubById(enterpriseId);
			log.info("for enterpriseId");
		}
		//由客户平台客户信息模块查询客户企业树发送的查询条件，根据用户名查找所属企业及其所属子企业
		else if(username != null && parentId == null){
			eiList = enterpriseManager.queryAllWithSubByUsername(username);
			log.info("for username");
		}
		else{
			eiList = enterpriseManager.queryTree(parentId,getCurrentClass());
			log.info("for parentId");
		}
		
		log.info("***********记录数：" + eiList.size());
		log.info("================================================end");
		List<EnterpriseForm> formList = new ArrayList<EnterpriseForm>();
		for(BaseEnterpriseInformation ei: eiList){
			EnterpriseForm form = transfer(ei);
			formList.add(form);
		}
		return formList;
	}

	@Override
	public List<EnterpriseForm> getList(int startRow, int endRow) {
		log.info("=============EnterpriseDataSourceAction.getList()");
		List<BaseEnterpriseInformation> eiList = enterpriseManager.list(startRow, endRow, getCurrentClass());
		log.info("***********记录数：" + eiList.size());
		log.info("==============================================end");
		List<EnterpriseForm> formList = new ArrayList<EnterpriseForm>();
		for(BaseEnterpriseInformation ei: eiList){
			EnterpriseForm form = transfer(ei);
			formList.add(form);
		}
		return formList;
	}
	
	private Class getCurrentClass(){
		return dsCls.get(BeanPropUtil.getDsName());
	}
	
	private EnterpriseForm transfer(BaseEnterpriseInformation enterpriseInfo){
		EnterpriseForm form = new EnterpriseForm();
		form.setAbbreviation(enterpriseInfo.getAbbreviation());
		form.setAddress_en(enterpriseInfo.getAddress_en());
		form.setAddress_zh(enterpriseInfo.getAddress_zh());
		form.setCreateBy(enterpriseInfo.getCreateBy());
		form.setCreateDate(enterpriseInfo.getCreateDate());
		form.setDeleted(enterpriseInfo.isDeleted());
		form.setEmail(enterpriseInfo.getEmail());
		form.setEnterpriseHomepageURL(enterpriseInfo.getEnterpriseHomepageURL());
		form.setEnterpriseName_en(enterpriseInfo.getEnterpriseName_en());
		form.setEnterpriseName_zh(enterpriseInfo.getEnterpriseName_zh());
		form.setFax(enterpriseInfo.getFax());
		form.setId(enterpriseInfo.getId());
		form.setKeyword(enterpriseInfo.getKeyword());
		form.setLinkman(enterpriseInfo.getLinkman());
		form.setLogoURL(enterpriseInfo.getLogoURL());
		if(enterpriseInfo.getM_CAGECode()!= null){
			form.setCageCode(enterpriseInfo.getM_CAGECode().getCode());//CAGECode value
		}
		
		form.setM_CountryCode(enterpriseInfo.getM_CountryCode());
		form.setM_VAT(enterpriseInfo.getM_VAT());
		form.setMainEnterprise(enterpriseInfo.isMainEnterprise());
		form.setParentCompany(enterpriseInfo.getParentCompany());
		form.setParentId(enterpriseInfo.getParentId());
		form.setPostCode(enterpriseInfo.getPostCode());
		form.setRunning(enterpriseInfo.isRunning());
		form.setTelephone(enterpriseInfo.getTelephone());
		form.setVersion(enterpriseInfo.getVersion());
		fillSubProps(enterpriseInfo, form);
		return form;
	}
	private BaseEnterpriseInformation transfer(EnterpriseForm enterpriseForm){
		Class cls = getCurrentClass();
		BaseEnterpriseInformation enterpriseInfo = null;
		try {
			enterpriseInfo = (BaseEnterpriseInformation)cls.newInstance();
			enterpriseInfo.setAbbreviation(enterpriseForm.getAbbreviation());
			enterpriseInfo.setAddress_en(enterpriseForm.getAddress_en());
			enterpriseInfo.setAddress_zh(enterpriseForm.getAddress_zh());
			enterpriseInfo.setCreateBy(enterpriseForm.getCreateBy());
			enterpriseInfo.setCreateDate(enterpriseForm.getCreateDate());
			enterpriseInfo.setDeleted(enterpriseForm.isDeleted());
			enterpriseInfo.setEmail(enterpriseForm.getEmail());
			enterpriseInfo.setEnterpriseHomepageURL(enterpriseForm.getEnterpriseHomepageURL());
			enterpriseInfo.setEnterpriseName_en(enterpriseForm.getEnterpriseName_en());
			enterpriseInfo.setEnterpriseName_zh(enterpriseForm.getEnterpriseName_zh());
			enterpriseInfo.setFax(enterpriseForm.getFax());
			enterpriseInfo.setId(enterpriseForm.getId());
			enterpriseInfo.setKeyword(enterpriseForm.getKeyword());
			enterpriseInfo.setLinkman(enterpriseForm.getLinkman());
			enterpriseInfo.setLogoURL(enterpriseForm.getLogoURL());
			enterpriseInfo.setM_CAGECode(enterpriseForm.getM_CAGECode());
			enterpriseInfo.setM_CountryCode(enterpriseForm.getM_CountryCode());
			enterpriseInfo.setM_VAT(enterpriseForm.getM_VAT());
			enterpriseInfo.setMainEnterprise(enterpriseForm.isMainEnterprise());
			enterpriseInfo.setParentCompany(enterpriseForm.getParentCompany());
			enterpriseInfo.setParentId(enterpriseForm.getParentId());
			enterpriseInfo.setPostCode(enterpriseForm.getPostCode());
			enterpriseInfo.setRunning(enterpriseForm.isRunning());
			enterpriseInfo.setTelephone(enterpriseForm.getTelephone());
			enterpriseInfo.setVersion(enterpriseForm.getVersion());
			
			String cageCode = enterpriseForm.getCageCode();
			if(cageCode != null && !"".equals(cageCode)){
				CAGECode newCAGECode = new CAGECode();
				newCAGECode.setCode(cageCode);
				enterpriseInfo.setM_CAGECode(newCAGECode);
			}
			
			if(cls == Customer.class){
				CustomerIdentificationCode ciCode = new CustomerIdentificationCode();
				ciCode.setCode(enterpriseForm.getCustomerIdentificationCode());
				((Customer)enterpriseInfo).setM_CustomerIdentificationCode(ciCode);
				((Customer)enterpriseInfo).setCustomerCategoryCode(enterpriseForm.getCustomerCategoryCode());
			}else if(cls == Supplier.class){
				SupplierCode spCode = new SupplierCode();
				spCode.setCode(enterpriseForm.getSupplierCode());
				((Supplier)enterpriseInfo).setM_SupplierCode(spCode);
				((Supplier)enterpriseInfo).setAppoint(enterpriseForm.isAppoint());
			}else if(cls == Manufacturer.class){
				ManufacturerCode mnCode = new ManufacturerCode();
				mnCode.setCode(enterpriseForm.getManufacturerCode());
				((Manufacturer)enterpriseInfo).setM_ManufacturerCode(mnCode);
				((Manufacturer)enterpriseInfo).setAppoint(enterpriseForm.isAppoint());
			}else if(cls == ClearanceAgency.class){
				ClearanceAgent caCode = new ClearanceAgent();
				caCode.setCode(enterpriseForm.getClearanceAgent());
				((ClearanceAgency)enterpriseInfo).setM_ClearanceAgent(caCode);
				((ClearanceAgency)enterpriseInfo).setAppoint(enterpriseForm.isAppoint());
			}else if(cls == Carrier.class){
				CarrierName cnCode = new CarrierName();
				cnCode.setCode(enterpriseForm.getCarrierName());
				((Carrier)enterpriseInfo).setM_CarrierName(cnCode);
				((Carrier)enterpriseInfo).setAppoint(enterpriseForm.isAppoint());
			}else if(cls == RepairAgency.class){
				RepairShopCode rsCode = new RepairShopCode();
				rsCode.setCode(enterpriseForm.getRepairShopCode());
				((RepairAgency)enterpriseInfo).setM_RepairShopCode(rsCode);
				((RepairAgency)enterpriseInfo).setAppoint(enterpriseForm.isAppoint());
			}
		} catch (Exception e) {
			log.info("Error: Cannot transfer EnterpriseForm. to BaseEnterpriseInformation ");
		}
		return enterpriseInfo;
	}
	
	private void fillSubProps(BaseEnterpriseInformation enterpriseInfo, EnterpriseForm item){
		Class cls = enterpriseInfo.getClass();
		if(cls == Customer.class){
			item.setCustomerIdentificationCode(((Customer)enterpriseInfo).getM_CustomerIdentificationCode().getCode());
			item.setCustomerCategoryCode(((Customer)enterpriseInfo).getCustomerCategoryCode());
			//log.info("CustomerIdentificationCode : " + item.getCustomerIdentificationCode());
		}else if(cls == Supplier.class){
			item.setSupplierCode(((Supplier)enterpriseInfo).getM_SupplierCode().getCode());
			item.setAppoint(((Supplier)enterpriseInfo).isAppoint());
			//log.info("SupplierCode : " + item.getSupplierCode());
		}else if(cls == Manufacturer.class){
			item.setManufacturerCode(((Manufacturer)enterpriseInfo).getM_ManufacturerCode().getCode());
			item.setAppoint(((Manufacturer)enterpriseInfo).isAppoint());
			//log.info("ManufacturerCode : " + item.getManufacturerCode());
		}else if(cls == ClearanceAgency.class){
			item.setClearanceAgent(((ClearanceAgency)enterpriseInfo).getM_ClearanceAgent().getCode());
			item.setAppoint(((ClearanceAgency)enterpriseInfo).isAppoint());
			//log.info("ClearanceAgent : " + item.getClearanceAgent());
		}else if(cls == Carrier.class){
			item.setCarrierName(((Carrier)enterpriseInfo).getM_CarrierName().getCode());
			item.setAppoint(((Carrier)enterpriseInfo).isAppoint());
			//log.info("CarrierName : " + item.getCarrierName());
		}else if(cls == RepairAgency.class){
			item.setRepairShopCode(((RepairAgency)enterpriseInfo).getM_RepairShopCode().getCode());
			item.setAppoint(((RepairAgency)enterpriseInfo).isAppoint());
			//log.info("RepairShopCode : " + item.getRepairShopCode());
		}else if(cls == COMACSC.class){
			log.info("Comacsc Abbreviation : " + item.getAbbreviation());
		}
	}
}
