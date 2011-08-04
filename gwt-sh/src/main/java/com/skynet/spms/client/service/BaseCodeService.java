package com.skynet.spms.client.service;

import java.util.LinkedHashMap;
import java.util.List;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
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

@RemoteServiceRelativePath("baseCodeAction.form")
public interface BaseCodeService extends RemoteService {

	public List<BaseCode> getListCode(String key);

	public CustomerContact getCustomerContact(String codeId);

	public SupplierVO getSupplierVO(String codeId);

	public CarrierVO getCarrierVO(String codeId);
	
	public RepairAgencyVO getRepairAgencyVO(String codeID);

	String[] getCertificateType();

	String[] getModelofApplicabilityCode();
	
	

	/**
	 * 发货地址信息
	 * 
	 * @param enterpriseId
	 */
	LinkedHashMap<String, AddressVO> getShippingAddressByEnterId(
			String enterpriseId, String businessType);

	/**
	 * 获得发票相关信息
	 * 
	 * @param enterpriseId
	 */
	LinkedHashMap<String, AddressVO> getInvocieAddressByEnterId(
			String enterpriseId, String businessType);

	/**
	 * 获得收货相关地址
	 * 
	 * @param enterpriseId
	 */
	LinkedHashMap<String, AddressVO> getConsigneeAddressByEnterId(
			String enterpriseId, String businessType);

	public List<DiscountVO> getByCustomerIdandPartSaleReleaseId(
			String customerCodeId, String partNumber);

	/**
	 * 获取送修合同状态
	 * 
	 * @param contractID
	 * @return
	 */
	public ContractState getRepairContractState(String contractID);

	/***
	 * 通过供应商ids获得多个供应商数据
	 * 
	 * @param ids
	 * @return
	 */
	public List<BaseCode> getSupplierCodeList(String ids);

	/**
	 * 根据codeid获得code
	 * 
	 * @param id
	 * @return
	 */
	public String getCodeById(String id);
	
	/**
	 * 更新供应商送修合同的总金额
	 * @param contractID
	 * @param amount
	 */
	public void updateSupplierRepairContractAmount(String contractID,Float amount);
	
	/**
	 * 更新客户送修合同总金额
	 * @param contractID
	 * @param amount
	 */
	public void updateCustomerRepairContractAmount(String contractID,Float amount);
	
	
	/**
	 * 根据销售订单明细id查询制造商信息
	 * @param id
	 */
	public BaseCode getmanufacturerCodeBySalesRequisitionSheetItemId(String id);

	/**
	 * 件号获得备件信息
	 * @param partNumber
	 * @return
	 */
	public PartInfoVO getPartInfoByPartNumber(String partNumber);
	
	/**
	 * 根据业务编号获取地址信息
	 * @param uuid
	 * @return
	 */
	public AddressInfoVO getAddressInfoByUUID(String uuid);

	
	/**
	 * 
	 * 更改实体业务状态
	 *
	 * @param   className 类名
	 * @param   id        主键
	 * @param   state     新业务状态
	 * @return void  
	 * @throws
	 */
	public void updateBussinessState(String className,String id,String state);

}
