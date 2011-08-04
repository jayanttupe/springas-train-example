package com.skynet.spms.web.control;

import java.util.LinkedHashMap;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import com.skynet.common.gwt.GwtRpcEndPoint;
import com.skynet.spms.client.service.BaseCodeService;
import com.skynet.spms.client.vo.AddressInfoVO;
import com.skynet.spms.client.vo.AddressVO;
import com.skynet.spms.client.vo.BaseCode;
import com.skynet.spms.client.vo.CarrierVO;
import com.skynet.spms.client.vo.ContractState;
import com.skynet.spms.client.vo.CustomerContact;
import com.skynet.spms.client.vo.DiscountVO;
import com.skynet.spms.client.vo.PartInfoVO;
import com.skynet.spms.client.vo.RepairAgencyVO;
import com.skynet.spms.client.vo.SupplierVO;
import com.skynet.spms.manager.CodeManager;
import com.skynet.spms.manager.contractManagement.customerContactTemplate.repaireContractTemplate.IRepairContractTemplateManager;
import com.skynet.spms.manager.contractManagement.supplierContactTemplate.repairInspectClaimContractTemplate.IRepairInspectClaimContractManager;

/**
 * Bind the code table datasource.
 * 
 * @author tqc
 * 
 */
@Controller
@GwtRpcEndPoint
public class BaseCodeAction extends BaseAction implements BaseCodeService {

	@Resource
	CodeManager manager;

	@Resource
	IRepairContractTemplateManager repairContractMananger;
	
	@Resource
	IRepairInspectClaimContractManager repairInspectClaimContractMang;
	
	
	/**
	 * Response code to the client.
	 */
	public List<BaseCode> getListCode(String className) {
		return manager.getList(className);
	}

	@Override
	public CustomerContact getCustomerContact(String codeId) {
		return manager.getCustomerContact(codeId);
	}

	/**
	 * 根据codeid获得供应商相关信息
	 */
	@Override
	public SupplierVO getSupplierVO(String codeId) {
		return manager.getSupplierInfo(codeId);
	}

	/**
	 * 根据codeid获得运代商相关信息
	 */
	@Override
	public CarrierVO getCarrierVO(String codeId) {
		return manager.getCarrierVO(codeId);
	}

	/**
	 * 获得随件资料数组列表
	 * 
	 * @param asyncCallback
	 */
	public String[] getCertificateType() {
		return manager.getCertificateType();
	}

	/**
	 * 获得适用机型数组列表
	 * 
	 * @param asyncCallback
	 */
	public String[] getModelofApplicabilityCode() {
		return manager.getModelofApplicabilityCode();
	}

	/**
	 * 发货地址信息
	 */
	@Override
	public LinkedHashMap<String, AddressVO> getShippingAddressByEnterId(
			String enterpriseId, String businessType) {
		return manager.getShippingAddressByEnterId(enterpriseId, businessType);
	}

	/**
	 * 获得发票相关信息
	 */
	@Override
	public LinkedHashMap<String, AddressVO> getInvocieAddressByEnterId(
			String enterpriseId, String businessType) {
		return manager.getInvocieAddressByEnterId(enterpriseId, businessType);
	}

	/**
	 * 获得收货相关地址
	 */
	@Override
	public LinkedHashMap<String, AddressVO> getConsigneeAddressByEnterId(
			String enterpriseId, String businessType) {
		return manager.getConsigneeAddressByEnterId(enterpriseId, businessType);
	}

	@Override
	public ContractState getRepairContractState(String contractID) {
		return repairContractMananger.getContractState(contractID);
	}

	/**
	 * 获得折扣信息
	 */
	public List<DiscountVO> getByCustomerIdandPartSaleReleaseId(
			String customerCodeId, String partNumber) {
		return manager.getByCustomerIdandPartSaleReleaseId(customerCodeId,
				partNumber);
	}

	/**
	 * 通过供应商ids获得多个供应商数据
	 * 
	 * @param ids
	 * @return
	 */
	public List<BaseCode> getSupplierCodeList(String ids) {
		return manager.getSupplierCodeList(ids);
	}

	@Override
	public RepairAgencyVO getRepairAgencyVO(String codeID) {
		return manager.getRepairAgencyVO(codeID);
	}

	@Override
	public String getCodeById(String id) {
		return manager.getCodeById(id);
	}
	/**
	 * 根据销售订单明细id查询制造商信息
	 * @param id
	 */
	public BaseCode getmanufacturerCodeBySalesRequisitionSheetItemId(String id) {
		return manager.getmanufacturerCodeBySalesRequisitionSheetItemId(id);
	}
	
	@Override
	public void updateSupplierRepairContractAmount(String contractID,
			Float amount) {
		repairInspectClaimContractMang.updateContractAmount(contractID, amount);
	}


	/**
	 * 根据件号 查询件号信息
	 */
	@Override
	public PartInfoVO getPartInfoByPartNumber(String partNumber) {
		// TODO Auto-generated method stub
		return manager.getPartInfoByPartNumber(partNumber);
	}
	


	@Override
	public AddressInfoVO getAddressInfoByUUID(String uuid) {
		return manager.getAddressInfo(uuid);
	}

	@Override
	public void updateCustomerRepairContractAmount(String contractID,
			Float amount) {
		repairContractMananger.updateContractAmount(contractID, amount);
	}

	@Override
	public void updateBussinessState(String className, String id, String state) {
		
		manager.updateBussinessState(className, id, state);
		
	}

}
