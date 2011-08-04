package com.skynet.spms.manager;

import java.util.LinkedHashMap;
import java.util.List;

import com.skynet.spms.client.vo.AddressInfoVO;
import com.skynet.spms.client.vo.AddressVO;
import com.skynet.spms.client.vo.BaseCode;
import com.skynet.spms.client.vo.CarrierVO;
import com.skynet.spms.client.vo.CustomerContact;
import com.skynet.spms.client.vo.DiscountVO;
import com.skynet.spms.client.vo.PartInfoVO;
import com.skynet.spms.client.vo.RepairAgencyVO;
import com.skynet.spms.client.vo.SupplierVO;

public interface CodeManager {

	List<BaseCode> getList(String className);

	/***
	 * 通过供应商ids获得多个供应商数据
	 * 
	 * @param ids
	 * @return
	 */
	List<BaseCode> getSupplierCodeList(String ids);

	CustomerContact getCustomerContact(String codeId);

	/**
	 * 根据codeid获得供应商相关信息
	 * 
	 * @param codeId
	 * @return
	 */
	SupplierVO getSupplierInfo(String codeId);

	/**
	 * 根据codeid获得运代商相关信息
	 * 
	 * @param codeId
	 * @return
	 */
	CarrierVO getCarrierVO(String codeId);
	
	/**
	 * 根据承修商codeID获取详细信息
	 * @param codeID
	 * @return
	 */
	RepairAgencyVO getRepairAgencyVO(String codeID);

	/**
	 * 获得随件资料数组列表
	 * 
	 * @param asyncCallback
	 */
	String[] getCertificateType();

	/**
	 * 获得适用机型数组列表
	 * 
	 * @param asyncCallback
	 */
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

	/**
	 * 根据客户codeid和件号 获得折扣信息
	 * 
	 * @param customerCodeId
	 * @param partNumber
	 * @return
	 */
	List<DiscountVO> getByCustomerIdandPartSaleReleaseId(String customerCodeId,
			String partNumber);
	/**
	 * 根据codeid获得code
	 * @param id
	 * @return
	 */
	public String getCodeById(String id);
	/**
	 * 根据销售订单明细id查询制造商信息
	 * @param id
	 */
	public BaseCode getmanufacturerCodeBySalesRequisitionSheetItemId(String id);

	
	/**
	 * 根据件号 查询件号信息
	 */
	public PartInfoVO getPartInfoByPartNumber(String partNumber);
	

	
	public AddressInfoVO getAddressInfo(String uuid);
	
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

	/**
	 * 
	 * 新建时修改业务状态为 “处理中”
	 *
	 * @param  @param className 类名
	 * @param  @param id   		主键
	 * @return void  
	 * @throws
	 */
	public void updateBussinessStateByAdd(String className,String id);
	/**
	 * 
	 * 删除时修改业务状态为 “已提交”
	 *
	 * @param  @param className 类名
	 * @param  @param id   		主键
	 * @return void  
	 * @throws
	 */
	public void updateBussinessStateByDel(String className,String id);
	/**
	 * 
	 * 发布时修改业务状态为 “已确认”
	 *
	 * @param  @param className 类名
	 * @param  @param id   		主键
	 * @return void  
	 * @throws
	 */
	public void updateBussinessStateByPublic(String className,String id);
	/**
	 * 
	 * 取消发布时修改业务状态为 “已提交”
	 *
	 * @param  @param className 类名
	 * @param  @param id   		主键
	 * @return void  
	 * @throws
	 */
	public void updateBussinessStateByCancelPublic(String className,String id);
	
}
