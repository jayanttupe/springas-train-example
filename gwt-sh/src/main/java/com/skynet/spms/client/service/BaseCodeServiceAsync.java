package com.skynet.spms.client.service;

import java.util.LinkedHashMap;
import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.skynet.spms.client.vo.AddressInfoVO;
import com.skynet.spms.client.vo.AddressVO;
import com.skynet.spms.client.vo.BaseCode;
import com.skynet.spms.client.vo.CarrierVO;
import com.skynet.spms.client.vo.ContractState;
import com.skynet.spms.client.vo.CustomerContact;
import com.skynet.spms.client.vo.DiscountVO;
import com.skynet.spms.client.vo.RepairAgencyVO;
import com.skynet.spms.client.vo.SupplierVO;
import com.skynet.spms.client.vo.PartInfoVO;
public interface BaseCodeServiceAsync {

	void getListCode(String key, AsyncCallback<List<BaseCode>> callback);

	void getCustomerContact(String codeId,
			AsyncCallback<CustomerContact> callback);

	/***
	 * 通过供应商ids获得多个供应商数据
	 * 
	 * @param ids
	 * @param callback
	 */
	void getSupplierCodeList(String ids, AsyncCallback<List<BaseCode>> callback);

	/**
	 * 根据codeid获得供应商相关信息
	 * 
	 * @param id
	 * @param callback
	 */
	void getSupplierVO(String id, AsyncCallback<SupplierVO> callback);

	/**
	 * 根据codeid获得运代商相关信息
	 * 
	 * @param id
	 * @param callback
	 */
	void getCarrierVO(String id, AsyncCallback<CarrierVO> asyncCallback);

	/**
	 * 根据承修商codeID获取详细信息
	 * 
	 * @param codeID
	 *            承修商codeID
	 * @param asyncCallback
	 *            异步回调接口
	 */
	void getRepairAgencyVO(String codeID,
			AsyncCallback<RepairAgencyVO> asyncCallback);

	/**
	 * 获得适用机型数组列表
	 * 
	 * @param asyncCallback
	 */
	void getModelofApplicabilityCode(AsyncCallback<String[]> asyncCallback);

	/**
	 * 获得随件资料数组列表
	 * 
	 * @param asyncCallback
	 */
	void getCertificateType(AsyncCallback<String[]> asyncCallback);

	/**
	 * 发货地址信息
	 * 
	 * @param enterpriseId
	 * @param asyncCallback
	 */
	void getShippingAddressByEnterId(String enterpriseId, String businessType,
			AsyncCallback<LinkedHashMap<String, AddressVO>> asyncCallback);

	/**
	 * 获得发票相关信息
	 * 
	 * @param enterpriseId
	 * @param asyncCallback
	 */
	void getInvocieAddressByEnterId(String enterpriseId, String businessType,
			AsyncCallback<LinkedHashMap<String, AddressVO>> asyncCallback);

	/**
	 * 获得收货相关地址
	 * 
	 * @param enterpriseId
	 * @param asyncCallback
	 */
	void getConsigneeAddressByEnterId(String enterpriseId, String businessType,
			AsyncCallback<LinkedHashMap<String, AddressVO>> asyncCallback);

	void getRepairContractState(String contractID,
			AsyncCallback<ContractState> callback);

	/**
	 * 获得折扣信息
	 * 
	 * @param customerCodeId
	 * @param partNumber
	 * @param item
	 */
	void getByCustomerIdandPartSaleReleaseId(String customerCodeId,
			String partNumber, AsyncCallback<List<DiscountVO>> asyncCallback);

	/**
	 * 根据codeid获得code值
	 * 
	 * @param id
	 * @param asyncCallback
	 */
	void getCodeById(String id, AsyncCallback<String> asyncCallback);

	void updateSupplierRepairContractAmount(String contractID, Float amount,
			AsyncCallback<Void> asyncCallback);
	
	void updateCustomerRepairContractAmount(String contractID,Float amount,AsyncCallback<Void> asyncCallback);
	

	/**
	 * 根据销售订单明细id查询制造商信息
	 * 
	 * @param id
	 * @param asyncCallback
	 */
	void getmanufacturerCodeBySalesRequisitionSheetItemId(String id,
			AsyncCallback<BaseCode> asyncCallback);


	/**
	 * 根据件号获得件的信息
	 * @param partNumber
	 * @param asyncCallback
	 */
	void getPartInfoByPartNumber(String partNumber,AsyncCallback<PartInfoVO> asyncCallback);
		
	

	void getAddressInfoByUUID(String uuid,AsyncCallback<AddressInfoVO> asyncCallback);
	
	/**
	 * 
	 * 修改业务状态
	 *
	 * @param   className 类名
	 * @param   id 	               主键
	 * @param   state     新状态
	 * @return void  
	 * @throws
	 */
	void updateBussinessState(String className, String id, String state,AsyncCallback<Void> asyncCallback) ;

}
