package com.skynet.spms.client.gui.base;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.skynet.spms.client.gui.customerService.commonui.BaseAddressModel;
import com.skynet.spms.client.service.BaseCodeService;
import com.skynet.spms.client.service.BaseCodeServiceAsync;
import com.skynet.spms.client.vo.AddressVO;
import com.skynet.spms.client.vo.BaseCode;
import com.skynet.spms.client.vo.CarrierVO;
import com.skynet.spms.client.vo.CustomerContact;
import com.skynet.spms.client.vo.DiscountVO;
import com.skynet.spms.client.vo.SupplierVO;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.skynet.spms.client.vo.PartInfoVO;
import com.skynet.spms.client.tools.PartTools;
/**
 * 针对BaseCode数据源工具类封装
 * 
 * @author tqc
 * 
 */
public class CodeRPCTool {
	private static final BaseAddressModel model = BaseAddressModel
			.getInstance();
	/** 制造商代码 **/
	public static final String MANUFACTURERCODE = "com.skynet.spms.persistence.entity.csdd.m.ManufacturerCode";
	/** 客户代码 **/
	public static final String CUSTOMERIDENTIFICATIONCODE = "com.skynet.spms.persistence.entity.csdd.c.CustomerIdentificationCode";
	/** 运代商 */
	public static final String CARRIERNAMECODE = "com.skynet.spms.persistence.entity.csdd.c.CarrierName";
	/** 运代商代码 **/
	public static final String CARRIERNAME = "com.skynet.spms.persistence.entity.csdd.c.CarrierName";

	public static final String CLEARANCEAGENT = "com.skynet.spms.persistence.entity.spmsdd.ClearanceAgent";

	public static final String REPAIRSHOPCODE = "com.skynet.spms.persistence.entity.csdd.r.RepairShopCode";

	/** 供应商 **/
	public static final String SUPPLIERCODE = "com.skynet.spms.persistence.entity.csdd.s.SupplierCode";

	/** 企业政府 **/
	public static final String CAGECODE = "com.skynet.spms.persistence.entity.csdd.c.CAGECode";

	private static BaseCodeServiceAsync service = GWT
			.create(BaseCodeService.class);

	public CodeRPCTool() {
	}

	/**
	 * 为下来列表绑定数据源
	 * 
	 * @param className
	 *            类名
	 * @param selectItem
	 *            下拉列表对象
	 */
	public static void bindData(String className, final SelectItem selectItem) {
		service.getListCode(className, new AsyncCallback<List<BaseCode>>() {
			public void onSuccess(List<BaseCode> code) {
				final LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
				for (BaseCode baseCode : code) {
					map.put(baseCode.getId(), baseCode.getCode());
				}
				selectItem.setValueMap(map);
				
				if(selectItem.getMultiple()){
					String value=selectItem.getValueAsString();
					selectItem.setValues(value.split(","));
				}
			}

			public void onFailure(Throwable arg0) {
				SC.warn("code loaded failure.");
			}
		});
	}

	/**
	 * 获得客户相关信息，如：联系人和联系方式
	 * 
	 * @param id
	 * @param formItems
	 */
	public static void bindCustomerData(String id, final FormItem... formItems) {
		service.getCustomerContact(id, new AsyncCallback<CustomerContact>() {
			public void onFailure(Throwable arg0) {
				System.out.println("失败");
			}

			public void onSuccess(CustomerContact vo) {
				if (vo != null) {
					String name = "";
					for (FormItem formItem : formItems) {
						name = formItem.getName();
						if (name.equals("linkman") || name.equals("linkMan")) {
							formItem.setValue(vo.getLinkman());
						}
						if (name.equals("contactInformation")
								|| name.equals("contactWay")
								|| name.equals("linkWay")) {
							formItem.setValue(vo.getAddress());
						}
						if (name.equals("enterpriseIds")) {
							LinkedHashMap<String, String> gta = vo.getGtaInfo();
							popularGta(gta, formItem);
						}
					}
				}

			}

		});
	}

	/**
	 * 获得供应商相关信息，如：联系人和联系方式
	 * 
	 * @param id
	 * @param formItems
	 */
	public static void bindSuppliercodeData(String id,
			final FormItem... formItems) {
		service.getSupplierVO(id, new AsyncCallback<SupplierVO>() {
			public void onFailure(Throwable arg0) {
				System.out.println("失败");
			}

			public void onSuccess(SupplierVO vo) {
				if (vo != null) {
					String name = "";
					for (FormItem formItem : formItems) {
						name = formItem.getName();
						// 联系人
						if (name.equals("sellerContactMan")
								|| name.equals("linkman")) {
							formItem.setValue(vo.getLinkMan());
						}
						// 联系方式
						if (name.equals("contactWay")
								|| name.equals("m_CarrierContact")
								|| name.equals("contactInformation")) {
							formItem.setValue(vo.getLinkType());
						}
						// GTA协议
						if (name.equals("gta") || name.equals("enterpriseIds")) {
							LinkedHashMap<String, String> gta = vo.getGtas();
							popularGta(gta, formItem);
						}

					}
				}

			}

		});
	}

	/**
	 * 组装GTA信息
	 * 
	 * @param gta
	 * @param formItem
	 */
	private static void popularGta(LinkedHashMap<String, String> gta,
			FormItem formItem) {
		SelectItem selectItem = (SelectItem) formItem;
		Iterator<String> iterator = gta.keySet().iterator();
		String[] gtaValues = new String[gta.size()];
		int index = 0;
		while (iterator.hasNext()) {
			gtaValues[index] = gta.get(iterator.next());
			index++;
		}
		selectItem.setValueMap(gtaValues);
		Object oldValues = selectItem.getValue();
		if (oldValues == null || "".equals(oldValues)) {
			selectItem.setValues(gtaValues);
		} else {
			String[] strOldValues = oldValues.toString().split(",");
			selectItem.setValues(strOldValues);
		}
	}

	/**
	 * 获得运代商相关信息，如：联系人和联系方式
	 * 
	 * @param id
	 * @param formItems
	 */
	public static void bindCarrierData(String id, final FormItem... formItems) {
		service.getCarrierVO(id, new AsyncCallback<CarrierVO>() {
			public void onFailure(Throwable arg0) {
				System.out.println("失败");
			}

			public void onSuccess(CarrierVO carrier) {
				if (carrier != null) {
					String name = "";
					for (FormItem formItem : formItems) {
						name = formItem.getName();
						// 联系人
						if (name.equals("appointForwarderLinkman")||name.equals("carrierLinkman")) {
							formItem.setValue(carrier.getLinkMan());
						}
						// 联系方式
						if (name.equals("appointForwarderContact")||name.equals("carrierContactInformation")) {
							formItem.setValue(carrier.getLinkType());
						}
						// GTA协议
						if (name.equals("enterpriseIds")) {
							LinkedHashMap<String, String> gta = carrier
									.getGtaInfo();
							popularGta(gta, formItem);
						}
					}
				}
			}

		});
	}

	/**
	 * 为下拉列表绑定数据源
	 * 
	 * @param className
	 *            类名
	 * @param listGrid
	 *            表格对象
	 */
	public static void bindData(String className, final ListGrid listGrid) {
		service.getListCode(className, new AsyncCallback<List<BaseCode>>() {
			public void onSuccess(List<BaseCode> code) {
				if (code != null) {
					Record[] records = new Record[code.size()];
					for (int i = 0; i <= records.length; i++) {
						Record record = new Record();
						record.setAttribute("id", code.get(i).getId());
						record.setAttribute("code", code.get(i).getCode());
						records[i] = record;
					}
					listGrid.setData(records);
				}

			}

			public void onFailure(Throwable arg0) {
				SC.warn("code loaded failure.");
			}
		});

	}

	/**
	 * 为适用机型绑定下拉列表
	 * 
	 * @param item
	 */
	public static void bindModelofApplicabilityCode(final SelectItem item) {
		service.getModelofApplicabilityCode(new AsyncCallback<String[]>() {
			@Override
			public void onFailure(Throwable codes) {

			}

			@Override
			public void onSuccess(String[] codes) {
				item.setValueMap(codes);
			}
		});

	}

	/**
	 * 为随件资料绑定下拉列表
	 * 
	 * @param item
	 */
	public static void bindCertificateType(final SelectItem item) {
		service.getCertificateType(new AsyncCallback<String[]>() {
			@Override
			public void onFailure(Throwable codes) {

			}

			@Override
			public void onSuccess(String[] codes) {
				item.setValueMap(codes);
			}
		});
	}

	/**
	 * 构建发货地址下拉列表
	 * 
	 * @param id
	 *            关联的业务编号,如何客户编号
	 * @return
	 */
	public static void bindShippingAddressByEnterId(String enterpriseId,
			String businessType, final ComboBoxItem item) {
		service.getShippingAddressByEnterId(enterpriseId, businessType,
				new AsyncCallback<LinkedHashMap<String, AddressVO>>() {

					@Override
					public void onFailure(Throwable arg0) {

					}

					@Override
					public void onSuccess(
							LinkedHashMap<String, AddressVO> addrMap) {
						LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
						for (Map.Entry<String, AddressVO> address : addrMap
								.entrySet()) {
							AddressVO addressVO = address.getValue();
							valueMap.put(addressVO.getUuid(), addressVO
									.getAddress());
						}
						item.setValueMap(valueMap);
						model.shippingAddrMap = addrMap;
					}

				});
	}

	
	
	
	/**
	 * 构建收发票地址下拉列表
	 * 
	 * @param id
	 *            关联的业务编号,如何客户编号
	 * @return
	 */
	public static void bindInvocieAddressByEnterId(String enterpriseId,
			String businessType, final ComboBoxItem item) {
		service.getInvocieAddressByEnterId(enterpriseId, businessType,
				new AsyncCallback<LinkedHashMap<String, AddressVO>>() {

					@Override
					public void onFailure(Throwable arg0) {

					}

					@Override
					public void onSuccess(
							LinkedHashMap<String, AddressVO> addrMap) {
						LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
						for (Map.Entry<String, AddressVO> address : addrMap
								.entrySet()) {
							AddressVO addressVO = address.getValue();
							valueMap.put(addressVO.getUuid(), addressVO
									.getAddress());
						}
						item.setValueMap(valueMap);
						model.invocieAddrMap = addrMap;
					}

				});
	}

	/**
	 * 构建收货地址下拉列表
	 * 
	 * @param id
	 *            关联的业务编号,如何客户编号
	 * @return
	 */
	public static void bindConsigneeAddressByEnterId(String enterpriseId,
			String businessType, final ComboBoxItem item) {
		service.getConsigneeAddressByEnterId(enterpriseId, businessType,
				new AsyncCallback<LinkedHashMap<String, AddressVO>>() {

					@Override
					public void onFailure(Throwable arg0) {

					}

					@Override
					public void onSuccess(
							LinkedHashMap<String, AddressVO> addrMap) {
						LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
						for (Map.Entry<String, AddressVO> address : addrMap
								.entrySet()) {
							AddressVO addressVO = address.getValue();
							valueMap.put(addressVO.getUuid(), addressVO
									.getAddress());
						}
						item.setValueMap(valueMap);
						model.consigneeAddrMap = addrMap;
					}

				});
	}

	/**
	 * 为下拉列表绑定折扣信息
	 * 
	 * @param customerCodeId
	 * @param partNumber
	 * @param item
	 */
	public static void bindDiscountItem(String customerCodeId,
			String partNumber, final ComboBoxItem item) {
		service.getByCustomerIdandPartSaleReleaseId(customerCodeId, partNumber,
				new AsyncCallback<List<DiscountVO>>() {
					@Override
					public void onFailure(Throwable arg0) {

					}

					@Override
					public void onSuccess(List<DiscountVO> voList) {
						String[] discounts = new String[voList.size()];
						for (int i = 0; i < voList.size(); i++) {
							discounts[i] = String.valueOf(voList.get(i)
									.getM_DiscountPercentCode());
						}
						item.setValueMap(discounts);
					}

				});
	}

	/***
	 * 通过供应商ids获得多个供应商数据源
	 * 
	 * @param ids
	 */
	public static void bindSuppliercodeDataByIds(String ids,
			final SelectItem selectItem) {
		service.getSupplierCodeList(ids, new AsyncCallback<List<BaseCode>>() {
			public void onSuccess(List<BaseCode> code) {
				final LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
				for (BaseCode baseCode : code) {
					map.put(baseCode.getId(), baseCode.getCode());
				}
				selectItem.setValueMap(map);
			}

			public void onFailure(Throwable arg0) {
				SC.warn("code loaded failure.");
			}
		});
	}
	/**
	 * 根据codeid获得code值
	 * @param id
	 * @param asyncCallback
	 */
	public static void getCodeById(String id,
			final FormItem codeItem) {
		service.getCodeById(id, new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable arg0) {

			}

			@Override
			public void onSuccess(String code) {
				codeItem.setValue(code);
			}

		});
	}
	
	public static void getmanufacturerCodeBySalesRequisitionSheetItemId(String id,
			final FormItem codeItem,final FormItem idItem) {
		service.getmanufacturerCodeBySalesRequisitionSheetItemId(id, new AsyncCallback<BaseCode>() {

			@Override
			public void onFailure(Throwable arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(BaseCode codeVo) {
				codeItem.setValue(codeVo.getCode());
				idItem.setValue(codeVo.getId());
			}


		});
	}
	
	public static void getPartInfoByPartNumber(String partNumber){
		service.getPartInfoByPartNumber(partNumber,new AsyncCallback<PartInfoVO>() {

			@Override
			public void onFailure(Throwable arg0) {
				// TODO Auto-generated method stub
				SC.say("失败");
			}

			@Override
			public void onSuccess(PartInfoVO partInfoVO) {
				// TODO Auto-generated method stub
				PartTools.setPartInfoVO(partInfoVO);
			}
		});
	}
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
	public static void updateBussinessState(String className, String id, String state) {
		service.updateBussinessState(className, id, state, new AsyncCallback<Void>() {

			@Override
			public void onFailure(Throwable arg0) {
				
				// TODO Auto-generated method stub
				SC.say("修改业务状态失败");
			}

			@Override
			public void onSuccess(Void arg0) {
				
				// TODO Auto-generated method stub
				//SC.say("修改业务状态成功");
			}
			
		});
	}
	/**
	 * 
	 * 新建业务时更改上个业务的业务状态为“处理中”
	 *
	 * @param   className 类名
	 * @param   id        主键
	 * @return void  
	 * @throws
	 */
	public static void updateBussinessStateByAdd(String className, String id) {
		updateBussinessState(className,id,"accepted");
	}
}
