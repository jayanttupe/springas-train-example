package com.skynet.spms.manager.imp;

import java.lang.reflect.InvocationTargetException;
import com.skynet.spms.persistence.entity.base.businessStatusEntity.BussinessStatusEntity;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.skynet.spms.client.gui.base.OrganizationType;
import com.skynet.spms.client.vo.AddressInfoVO;
import com.skynet.spms.client.vo.AddressVO;
import com.skynet.spms.client.vo.BaseCode;
import com.skynet.spms.client.vo.CarrierVO;
import com.skynet.spms.client.vo.CustomerContact;
import com.skynet.spms.client.vo.DiscountVO;
import com.skynet.spms.client.vo.PartInfoVO;
import com.skynet.spms.client.vo.RepairAgencyVO;
import com.skynet.spms.client.vo.SupplierVO;
import com.skynet.spms.manager.CodeManager;
import com.skynet.spms.persistence.entity.base.AddressInfo;
import com.skynet.spms.persistence.entity.base.baseCodeEntity.BaseCodeEntity;
import com.skynet.spms.persistence.entity.base.dataDictionary.EntityStatusMonitor;
import com.skynet.spms.persistence.entity.csdd.c.CertificateType;
import com.skynet.spms.persistence.entity.csdd.c.CustomerCategoryCode;
import com.skynet.spms.persistence.entity.csdd.m.ManufacturerCode;
import com.skynet.spms.persistence.entity.csdd.m.ModelofApplicabilityCode;
import com.skynet.spms.persistence.entity.customerService.sales.salesRequisitionSheet.SalesRequisitionSheetItem;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.GTAManage.EnterpriseGTA;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.baseEnterpriseInformation.BaseEnterpriseInformation;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.baseEnterpriseInformation.ConsigneeAddress;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.baseEnterpriseInformation.RecipeInvoiceAddress;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.baseEnterpriseInformation.ShippingAddress;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.carriers.Carrier;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.customers.Customer;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.repairAgencys.RepairAgency;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.suppliers.Supplier;
import com.skynet.spms.persistence.entity.partCatalog.salesCatalog.PartSaleRelease;
import com.skynet.spms.persistence.entity.partCatalog.salesCatalog.discountMatrix.DiscountItem;
import com.skynet.spms.persistence.entity.partCatalog.technicalCatalog.indexInfomation.PartIndex;
import com.skynet.spms.persistence.entity.partCatalog.technicalCatalog.basicInformation.BasicInformation;

@Service
@Transactional
public class CodeManagerImpl implements CodeManager {

	@Autowired
	private SessionFactory factory;

	@SuppressWarnings( { "unchecked", "rawtypes" })
	public List<BaseCode> getList(String className) {
		List<BaseCode> responseList = new ArrayList<BaseCode>();
		try {
			Class classTarget = Class.forName(className);
			List<BaseCodeEntity> codeList = factory.getCurrentSession()
					.createCriteria(classTarget).list();
			for (BaseCodeEntity code : codeList) {
				BaseCode responseCode = new BaseCode();
				BeanUtils.copyProperties(responseCode, code);
				responseList.add(responseCode);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseList;
	}

	public CustomerContact getCustomerContact(String codeId) {
		String hql = "from Customer o left join fetch o.m_EnterpriseGTA where o.m_CustomerIdentificationCode.id=?";
		Query query = factory.getCurrentSession().createQuery(hql);
		query.setParameter(0, codeId);
		Customer customer = (Customer) query.uniqueResult();
		CustomerContact contact = null;
		if (customer != null) {
			contact = new CustomerContact();
			contact.setId(customer.getId());
			contact.setLinkman(customer.getLinkman());
			contact.setAddress(customer.getAddress_zh());
			contact.setTelephone(customer.getTelephone());
			contact.setPostCode(customer.getPostCode());
			List<EnterpriseGTA> gtas = customer.getM_EnterpriseGTA();
			LinkedHashMap<String, String> gtaInfo = new LinkedHashMap<String, String>();
			for (EnterpriseGTA enterpriseGTA : gtas) {
				gtaInfo.put(enterpriseGTA.getId(), enterpriseGTA.getTitle());
			}
			contact.setGtaInfo(gtaInfo);
		}
		return contact;
	}

	@Override
	public SupplierVO getSupplierInfo(String codeId) {
		String hql = "from Supplier o left join fetch o.m_EnterpriseGTA  where o.m_SupplierCode.id=?";
		Query query = factory.getCurrentSession().createQuery(hql);
		query.setParameter(0, codeId);
		Object obj = query.uniqueResult();
		Supplier supplier = (Supplier) obj;
		SupplierVO supplierVO = new SupplierVO();
		if (supplier != null) {
			supplierVO.setLinkMan(supplier.getLinkman());
			supplierVO.setLinkType(supplier.getAddress_zh() + " "
					+ supplier.getPostCode() + " " + supplier.getTelephone());
			List<EnterpriseGTA> gtas = supplier.getM_EnterpriseGTA();
			LinkedHashMap<String, String> gtaInfo = new LinkedHashMap<String, String>();
			for (EnterpriseGTA enterpriseGTA : gtas) {
				gtaInfo.put(enterpriseGTA.getId(), enterpriseGTA.getTitle());
			}
			supplierVO.setGtas(gtaInfo);
		}
		return supplierVO;
	}

	@Override
	public CarrierVO getCarrierVO(String codeId) {
		String hql = "from Carrier o left join fetch o.m_EnterpriseGTA  where o.m_CarrierName.id=?";
		Query query = factory.getCurrentSession().createQuery(hql);
		query.setParameter(0, codeId);
		Object obj = query.uniqueResult();
		Carrier carrier = (Carrier) obj;
		CarrierVO carrierVO = new CarrierVO();
		if (carrier != null) {
			carrierVO.setLinkMan(carrier.getLinkman());
			carrierVO.setLinkType(carrier.getAddress_zh() + " "
					+ carrier.getPostCode() + " " + carrier.getTelephone());
			List<EnterpriseGTA> gtas = carrier.getM_EnterpriseGTA();
			LinkedHashMap<String, String> gtaInfo = new LinkedHashMap<String, String>();
			for (EnterpriseGTA enterpriseGTA : gtas) {
				gtaInfo.put(enterpriseGTA.getId(), enterpriseGTA.getTitle());
			}
			carrierVO.setGtaInfo(gtaInfo);
		}
		return carrierVO;
	}

	@Override
	public String[] getCertificateType() {
		CertificateType[] codes = CertificateType.values();
		String[] strCodes = new String[codes.length];
		for (int i = 0; i < codes.length; i++) {
			strCodes[i] = codes[i].toString();
		}
		return strCodes;
	}

	@Override
	public String[] getModelofApplicabilityCode() {
		ModelofApplicabilityCode[] codes = ModelofApplicabilityCode.values();
		String[] strCodes = new String[codes.length];
		for (int i = 0; i < codes.length; i++) {
			strCodes[i] = codes[i].toString();
		}
		return strCodes;
	}

	@SuppressWarnings("unchecked")
	@Override
	public LinkedHashMap<String, AddressVO> getShippingAddressByEnterId(
			String enterpriseCodeId, String businessType) {
		String enterpriseId = "-1";
		String enterpriseName_zh = "";
		BaseEnterpriseInformation information = getEnterpriseInfomation(
				enterpriseCodeId, businessType);
		List<ShippingAddress> addresses = null;
		if (information != null) {
			enterpriseId = information.getId();
			enterpriseName_zh = information.getEnterpriseName_zh();
			String hql = "from ShippingAddress o where o.deleted=false and o.enterpriseId=?";
			Query query = factory.getCurrentSession().createQuery(hql);
			query.setParameter(0, enterpriseId);
			addresses = query.list();
		} else {
			String hql = "from ShippingAddress o where o.deleted=false";
			Query query = factory.getCurrentSession().createQuery(hql);
			addresses = query.list();
		}
		LinkedHashMap<String, AddressVO> addrMap = new LinkedHashMap<String, AddressVO>();
		for (ShippingAddress shippingAddress : addresses) {
			AddressVO addressVO = new AddressVO();
			addressVO.setLinkMan(shippingAddress.getContactMan());
			addressVO.setLinkType(shippingAddress.getContactWay());
			addressVO.setUuid(shippingAddress.getId());
			addressVO.setAddress(shippingAddress.getAddress());
			addressVO.setUnit(enterpriseName_zh);
			addressVO.setEnterpriseId(shippingAddress.getEnterpriseId());
			addrMap.put(shippingAddress.getId(), addressVO);
		}
		return addrMap;
	}

	private BaseEnterpriseInformation getEnterpriseInfomation(
			String enterpriseCodeId, String businessType) {

		String hql = null;

		BaseEnterpriseInformation information = null;

		if (OrganizationType.CUSTOMER.equals(businessType)) {// 客户

			hql = "from Customer o where o.deleted=false and o.m_CustomerIdentificationCode.id=?";

		} else if (OrganizationType.SUPPLIER.equals(businessType)) {// 供应商

			hql = "from Supplier o where o.deleted=false and o.m_SupplierCode.id=?";

		} else if (OrganizationType.REPAIRAGENCY.equals(businessType)) {// 承修商

			hql = "select o from RepairAgency o where o.deleted=false and o.m_RepairShopCode.id=?";

		}

		if (hql == null) {
			return null;
		}

		Query query = factory.getCurrentSession().createQuery(hql);

		query.setParameter(0, enterpriseCodeId);

		information = (BaseEnterpriseInformation) query.uniqueResult();

		return information;
	}

	@SuppressWarnings("unchecked")
	@Override
	public LinkedHashMap<String, AddressVO> getInvocieAddressByEnterId(
			String enterpriseCodeId, String businessType) {
		String enterpriseId = "-1";
		String enterpriseName_zh = "";
		BaseEnterpriseInformation information = getEnterpriseInfomation(
				enterpriseCodeId, businessType);
		List<RecipeInvoiceAddress> addresses = null;
		if (information != null) {
			enterpriseId = information.getId();
			enterpriseName_zh = information.getEnterpriseName_zh();
			String hql = "from RecipeInvoiceAddress o where o.deleted=false and o.enterpriseId=?";
			Query query = factory.getCurrentSession().createQuery(hql);
			query.setParameter(0, enterpriseId);
			addresses = query.list();
		} else {
			String hql = "from RecipeInvoiceAddress o where o.deleted=false";
			Query query = factory.getCurrentSession().createQuery(hql);
			addresses = query.list();
		}
		LinkedHashMap<String, AddressVO> addrMap = new LinkedHashMap<String, AddressVO>();
		for (RecipeInvoiceAddress addr : addresses) {
			AddressVO addressVO = new AddressVO();
			addressVO.setUuid(addr.getId());
			addressVO.setAddress(addr.getAddress());
			addressVO.setUnit(enterpriseName_zh);
			addressVO.setLinkMan(addr.getRecipient());
			addressVO.setEnterpriseId(addr.getEnterpriseId());
			addrMap.put(addr.getId(), addressVO);
		}
		return addrMap;
	}

	@SuppressWarnings("unchecked")
	@Override
	public LinkedHashMap<String, AddressVO> getConsigneeAddressByEnterId(
			String enterpriseCodeId, String businessType) {
		String enterpriseId = "-1";
		String enterpriseName_zh = "";
		BaseEnterpriseInformation information = getEnterpriseInfomation(
				enterpriseCodeId, businessType);
		List<ConsigneeAddress> addresses = null;
		if (information != null) {
			enterpriseId = information.getId();
			enterpriseName_zh = information.getEnterpriseName_zh();
			String hql = "from ConsigneeAddress o where o.deleted=false and o.enterpriseId=?";
			Query query = factory.getCurrentSession().createQuery(hql);
			query.setParameter(0, enterpriseId);
			addresses = query.list();
		} else {
			String hql = "from ConsigneeAddress o where o.deleted=false";
			Query query = factory.getCurrentSession().createQuery(hql);
			addresses = query.list();
		}
		LinkedHashMap<String, AddressVO> addrMap = new LinkedHashMap<String, AddressVO>();
		for (ConsigneeAddress addr : addresses) {
			AddressVO addressVO = new AddressVO();
			addressVO.setLinkMan(addr.getContactMan());
			addressVO.setLinkType(addr.getContactWay());
			addressVO.setUuid(addr.getId());
			addressVO.setAddress(addr.getAddress());
			addressVO.setUnit(enterpriseName_zh);
			addressVO.setEnterpriseId(addr.getEnterpriseId());
			addrMap.put(addr.getId(), addressVO);
		}
		return addrMap;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DiscountVO> getByCustomerIdandPartSaleReleaseId(
			String customerCodeId, String partNumber) {
		String hql = "from Customer o where o.deleted=false and o.m_CustomerIdentificationCode.id=?";
		Query query = factory.getCurrentSession().createQuery(hql);
		query.setParameter(0, customerCodeId);
		Customer customer = (Customer) query.uniqueResult();
		if (customer == null) {
			return null;
		}
		String customerCategoryCode = String.valueOf(customer
				.getCustomerCategoryCode());
		hql = "from PartSaleRelease o where o.deleted=false and o.m_PartIndex.partNumber=? and o.m_BussinessPublishStatusEntity.m_PublishStatus='published'";
		query = factory.getCurrentSession().createQuery(hql);
		query.setParameter(0, partNumber);
		PartSaleRelease release = (PartSaleRelease) query.uniqueResult();
		if (release == null) {
			return null;
		}
		String partSaleReleaseId = release.getId();
		hql = "from DiscountItem o where o.m_CustomerCategoryCode=? and o.partSaleReleaseId=?";
		query = factory.getCurrentSession().createQuery(hql);
		query.setParameter(0, CustomerCategoryCode
				.valueOf(customerCategoryCode));
		query.setParameter(1, partSaleReleaseId);
		List<DiscountItem> list = query.list();
		List<DiscountVO> voList = new ArrayList<DiscountVO>();
		for (DiscountItem discountItem : list) {
			DiscountVO vo = new DiscountVO();
			vo
					.setM_DiscountPercentCode(discountItem
							.getM_DiscountPercentCode());
			voList.add(vo);
		}
		return voList;
	}

	@Override
	public List<BaseCode> getSupplierCodeList(String ids) {
		String[] idArray = ids.split(",");
		int lenght = idArray.length;
		List<BaseCode> bcList = new ArrayList<BaseCode>();
		BaseCodeEntity code;
		try {
			for (int i = 0; i < lenght; i++) {
				code = (BaseCodeEntity) factory.getCurrentSession().get(
						BaseCodeEntity.class, idArray[i]);
				BaseCode responseCode = new BaseCode();
				BeanUtils.copyProperties(responseCode, code);
				bcList.add(responseCode);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bcList;
	}

	@Override
	public RepairAgencyVO getRepairAgencyVO(String codeID) {
		String hql = "select o from RepairAgency o left outer join fetch o.m_EnterpriseGTA  where o.m_RepairShopCode.id=?";
		Query query = factory.getCurrentSession().createQuery(hql);
		query.setParameter(0, codeID);
		RepairAgency obj = (RepairAgency) query.uniqueResult();
		RepairAgencyVO vo = new RepairAgencyVO();
		if (obj != null) {
			vo.setLinkMan(obj.getLinkman());
			vo.setLinkType(obj.getAddress_zh() + " " + obj.getPostCode() + " "
					+ obj.getTelephone());
			List<EnterpriseGTA> gtas = obj.getM_EnterpriseGTA();
			LinkedHashMap<String, String> gtaInfo = new LinkedHashMap<String, String>();
			for (EnterpriseGTA enterpriseGTA : gtas) {
				gtaInfo.put(enterpriseGTA.getId(), enterpriseGTA.getTitle());
			}
			vo.setGtas(gtaInfo);
		}
		return vo;
	}

	@Override
	public String getCodeById(String id) {
		String hql = "from BaseCodeEntity where id=?";
		Query query = factory.getCurrentSession().createQuery(hql);
		query.setParameter(0, id);
		BaseCodeEntity codeEntity = (BaseCodeEntity) query.uniqueResult();
		if (codeEntity != null) {
			return codeEntity.getCode();
		}
		return null;
	}

	@Override
	public BaseCode getmanufacturerCodeBySalesRequisitionSheetItemId(String id) {
		String hql = "select o from SalesRequisitionSheetItem o where o.id=?";
		Query query = factory.getCurrentSession().createQuery(hql);
		query.setParameter(0, id);
		SalesRequisitionSheetItem item = (SalesRequisitionSheetItem) query
				.uniqueResult();
		BaseCode baseCode = new BaseCode();
		if (item != null) {
			ManufacturerCode code = item.getM_ManufacturerCode();
			baseCode.setCode(code.getCode());
			baseCode.setId(code.getId());
		}
		return baseCode;
	}

	/**
	 * 根据件号 查询件号信息
	 */
	@Override
	public PartInfoVO getPartInfoByPartNumber(String partNumber) {
		// String
		// hql="select o from PartIndex o left  join fetch o.m_ManufacturerCode left  join fetch o.m_BasicInformation where o.partNumber=?";
		String hql = "select o from PartIndex o where o.partNumber=?";
		Query query = factory.getCurrentSession().createQuery(hql);
		query.setParameter(0, partNumber);
		PartIndex partIndex = new PartIndex();
		PartInfoVO partInfoVO = new PartInfoVO();
		List<PartIndex> partIndexList = query.list();
		if (partIndexList != null) {
			partIndex = partIndexList.get(0);

			partInfoVO.setManufacturerCodeId(partIndex.getM_ManufacturerCode()
					.getId());
			partInfoVO.setManufacturerCode(partIndex.getM_ManufacturerCode()
					.getCode());
			BasicInformation information = partIndex.getM_BasicInformation();
			if (information != null) {
				partInfoVO.setKeyword(information.getKeyword_zh());
				partInfoVO.setAtaNumber(information.getAtaNumber());
				partInfoVO.setDescription(information.getPartName_zh());
				partInfoVO.setUnitOfMeasureCode(information
						.getM_UnitOfMeasureCode()
						+ "");
			}
		}
		return partInfoVO;
	}

	@Override
	public AddressInfoVO getAddressInfo(String uuid) {
		AddressInfo address;
		AddressInfoVO addressVO = new AddressInfoVO();
		String hql = "select o from AddressInfo o where o.uuid=?";
		Query query = factory.getCurrentSession().createQuery(hql);
		query.setParameter(0, uuid);
		Object obj = query.uniqueResult();
		if (obj != null) {
			address = (AddressInfo) obj;
			try {
				BeanUtils.copyProperties(addressVO, address);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		return addressVO;
	}

	/**
	 * 
	 * 更改实体业务状态
	 * 
	 * @param className
	 *            类名
	 * @param id
	 *            主键
	 * @param state
	 *            新业务状态
	 * @return void
	 * @throws
	 */
	@Override
	public void updateBussinessState(String className, String id, String state) {
		
		String hql="select o.m_BussinessStatusEntity.id  from "+className+" o where o.id=?";
		Query query = factory.getCurrentSession().createQuery(hql);
		query.setParameter(0, id);
		String stateId=(String) query.uniqueResult();
		
		
		String hql2 = "update BussinessStatusEntity set status=? where id=? ";
		query =factory.getCurrentSession().createQuery(hql2);
		if(state!=null){
			try {
				EntityStatusMonitor statusEnum=EntityStatusMonitor.valueOf(state);
				query.setParameter(0, statusEnum);
				query.setParameter(1, stateId);
				query.executeUpdate();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void updateBussinessStateByAdd(String className, String id) {
		
		updateBussinessState(className, id, EntityStatusMonitor.accepted.toString());
	}

	@Override
	public void updateBussinessStateByDel(String className, String id) {
		
		updateBussinessState(className, id, EntityStatusMonitor.submited.toString());
	}

	@Override
	public void updateBussinessStateByPublic(String className, String id) {
		
		updateBussinessState(className, id, EntityStatusMonitor.processed.toString());
		
	}
	
	@Override
	public void updateBussinessStateByCancelPublic(String className, String id) {
		
		updateBussinessState(className, id, EntityStatusMonitor.accepted.toString());
		
	}
}
