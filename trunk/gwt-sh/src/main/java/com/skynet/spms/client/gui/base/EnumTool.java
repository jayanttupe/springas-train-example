package com.skynet.spms.client.gui.base;

import java.util.LinkedHashMap;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.skynet.spms.client.service.EnumDicService;
import com.skynet.spms.client.service.EnumDicServiceAsync;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.form.fields.FormItem;

/**
 * 枚举字典加载
 * 
 * @author tqc
 * 
 */
public class EnumTool {

	private static EnumDicServiceAsync service = GWT
			.create(EnumDicService.class);

	public static LinkedHashMap<String, LinkedHashMap<String, String>> localMap = new LinkedHashMap<String, LinkedHashMap<String, String>>();

	/** 特殊运输方式 **/
	public static final String SPECIFIEDSHIPPINGMETHODCODE = "com.skynet.spms.persistence.entity.csdd.s.SpecifiedShippingMethodCode";

	/** 发货方式 **/
	public static final String DELIVERYTYPE = "com.skynet.spms.persistence.entity.spmsdd.DeliveryType";

	/** 优先级 **/
	public static final String PRIORITY = "com.skynet.spms.persistence.entity.spmsdd.Priority";

	/** 运输方式 **/
	public static final String TRANSPORTATIONCODE = "com.skynet.spms.persistence.entity.spmsdd.TransportationCode";

	/** 贸易方式 **/
	public static final String TRADEMETHODS = "com.skynet.spms.persistence.entity.spmsdd.TradeMethods";

	/** 国际货币代码 **/
	public static final String INTERNATIONALCURRENCYCODE = "com.skynet.spms.persistence.entity.csdd.i.InternationalCurrencyCode";

	/** 计量单位代码 **/
	public static final String UNITOFMEASURECODE = "com.skynet.spms.persistence.entity.csdd.u.UnitOfMeasureCode";

	/** 证书类型 */
	public static final String CERTIFICATETYPE = "com.skynet.spms.persistence.entity.csdd.c.CertificateType";
	
	/**业务类型**/
	public static final String BUSINESSTYPE="com.skynet.spms.persistence.entity.csdd.b.BusinessType";
	

	public EnumTool() {
	}

	public static void setValueMap(String className, final FormItem item) {
		service.get(className,
				new AsyncCallback<LinkedHashMap<String, String>>() {
					public void onSuccess(LinkedHashMap<String, String> map) {
						item.setValueMap(map);
					}

					public void onFailure(Throwable arg0) {
						SC.warn("加载枚举列表失败:" + arg0);
					}
				});
	}

	/**
	 * 初始化国际化到本地
	 * 
	 * @param className
	 */
	public static void initValueMap(final String className) {
		service.get(className,
				new AsyncCallback<LinkedHashMap<String, String>>() {
					public void onSuccess(LinkedHashMap<String, String> map) {
						localMap.put(className, map);
					}

					public void onFailure(Throwable arg0) {
						SC.warn("加载失败:" + arg0);
					}
				});
	}

	/**
	 * 获得枚举国际化后的值(前提InitLocal被实例化过)
	 * 
	 * @param className
	 * @param enumValue
	 * @return
	 */
	public static String getIn18Value(final String className, String enumValue) {
		return EnumTool.localMap.get(className).get(enumValue);
	}

}
