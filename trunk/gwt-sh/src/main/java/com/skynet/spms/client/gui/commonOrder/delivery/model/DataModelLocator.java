package com.skynet.spms.client.gui.commonOrder.delivery.model;

import com.skynet.spms.client.model.IModelLocator;
import com.smartgwt.client.widgets.form.fields.CheckboxItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGrid;

public class DataModelLocator implements IModelLocator<DataModelLocator> {
	private static DataModelLocator instance;

	private DataModelLocator() {
	}

	public static DataModelLocator getInstance() {
		if (instance == null) {
			instance = new DataModelLocator();
		}
		return instance;
	}

	/** 合同主键 **/
	public TextItem contractIdItem;

	/** 合同号* */
	public TextItem contractNumberText;

	/** 优先级* */
	public TextItem prioritySelect;

	/** 交货方式 * */
	public TextItem deliveryTypeSelect;

	/** 贸易方式 * */
	public TextItem tradeMethodsText;

	/** 运输方式 * */
	public TextItem shippingServiceTypeText;

	/** 是否指定运代商 **/
	public CheckboxItem freightAgentCheckbox;

	/** 运代商 **/
	public TextItem carrierNameText;

	/** 运代商联系人 **/
	public TextItem appointForwarderLinkmanText;

	/** 运代商联系方式 **/
	public TextItem appointForwarderContactText;

	/** 合同明细grid* */
	public ListGrid contractItemGrid;

	/** 发货指令主键 **/
	public String orderID;

	/** 业务类别 **/
	public String businessType;

	/** 修改指令grid **/
	public ListGrid modifyOrderGrid;
	
	
	/**发货单位**/
	public TextItem companyOfShipperItem;
	
	/**发货人**/
	public TextItem shipperItem;
	
	/**发货地址**/
	public TextItem addressOfShipperItem;
	
	/**发货联系方式**/
	public TextAreaItem telephonOfShipperItem;
	
	/**收货单位**/
	public TextItem companyOfConsigneeItem;
	
	/**收货人**/
	public TextItem consigneeItem;
	
	/**收获地址**/
	public TextItem addressOfConsigneeItem;
	
	/**收货人联系方式**/
	public TextAreaItem telephoneOfConsigneeItem;

}
