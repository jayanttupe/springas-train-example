package com.skynet.spms.persistence.entity.contractManagement.template.CustomerContactTemplate.BuybackContractTemplate;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.skynet.spms.persistence.entity.contractManagement.template.CustomerContactTemplate.baseCustomerContractTemplate.BaseCustomerContract;
import com.skynet.spms.persistence.entity.contractManagement.template.baseContractTemplate.ContractProvision;
import com.skynet.spms.persistence.entity.csdd.c.CAGECode;
import com.skynet.spms.persistence.entity.csdd.c.CustomerIdentificationCode;
import com.skynet.spms.persistence.entity.csdd.o.OrderTransactionCode;
import com.skynet.spms.persistence.entity.csdd.u.UnitOfMeasureCode;
import com.skynet.spms.persistence.entity.customerService.BuybackService.BuybackRequisitionSheet.BuybackRequisitionSheet;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.baseEnterpriseInformation.BankInformation;

/**
 * 客户回购合同模板
 * 
 * @author tqc
 * @version 1.0
 * @created 03-四月-2011 11:32:32
 */
@Entity
@Table(name = "SPMS_BUYBACKCONTRACTTEMPLATE")
public class BuybackContractTemplate extends BaseCustomerContract {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6544782047236763218L;
	/**
	 * 外贸中的&ldquo;唛头&rdquo;
	 * 是运输标志另一种称呼，是为了便于识别货物，防止错发货，通常由型号，图形或收货单位简称，目的港，件数或批号等组成
	 * 。通常是由一个简单的几何图形和一些字母、数字及简单
	 * 的文字组成，其作用在于使货物在装卸、运输、保管过程中容易被有关人员识别，以防错发错运。其主要内容包括-
	 * （1）收货人或买方名称字首（2）参照号码；（
	 * 3）目的港（地）名称；（4）件数、批号。此外，有的运输标志还包括原产地、合同号、许可证号和体积与重量等内容。运输标志
	 * 的内容繁简不一，由买卖双方根据商品特点和具体要求商定。
	 */
	private String shippingMark;
	/**
	 * 交货日期指定后，无需再设定交货期天数。同样设定交货期天数，则也无需设定交货日期。
	 */
	private Date dateOfDelivery;
	/**
	 * 回购交货期
	 */
	private int deliveryPeriod;
	/**
	 * 合同总金额
	 */
	private float extendedValueTotalAmount;
	/**
	 * 是否分期付款
	 */
	private Boolean installment;
	/**
	 * 如果是买方指定运代，如果运代在字典中不存在需要进行维护运代的企业信息
	 */
	private Boolean buyerFreightAgent;
	/**
	 * 合同条款
	 */
	public ContractProvision specialProvision;
	/**
	 * 客户回购提货指令
	 */
	public List m_CustomerBuybackPickupOrder;
	/**
	 * 客户回购申请单明细项
	 */
	public List m_BuybackRequisitionSheetItem;
	/**
	 * 合同明细
	 */
	public List m_BuybackPactItems;

	/**
	 * 银行账户信息
	 */
	public BankInformation m_BankInformation;
	/**
	 * 指定客户订单的类型、种类以及条件。
	 */
	public OrderTransactionCode m_OrderTransactionCode;
	/** 客户识别代码 **/
	public CustomerIdentificationCode customerIdentificationCode;
	/** 联系人 **/
	private String linkMan;

	/** 联系方式 **/
	private String linkWay;

	/**
	 * 企业或政府代码
	 */
	public CAGECode buyer;
	/**
	 * 数量总计
	 */
	private float totalCount;
	/**
	 * 数量总计的单位
	 */
	private UnitOfMeasureCode m_UnitOfMeasureCode;

	/** 特殊条款 */
	private String extraProvision;
	/** 关联的申请单信息 */
	private BuybackRequisitionSheet sheet;

	@OneToOne
	@JoinColumn(name = "BUYBACKSHEET_ID")
	public BuybackRequisitionSheet getSheet() {
		return sheet;
	}

	public void setSheet(BuybackRequisitionSheet sheet) {
		this.sheet = sheet;
	}

	/** 联系人 **/
	@Column(name = "LINKMAN")
	public String getLinkMan() {
		return linkMan;
	}

	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}

	/** 联系方式 **/
	@Column(name = "LINKWAY")
	public String getLinkWay() {
		return linkWay;
	}

	public void setLinkWay(String linkWay) {
		this.linkWay = linkWay;
	}

	/** 特殊条款 */
	@Column(name = "EXTRAPROVISION")
	public String getExtraProvision() {
		return extraProvision;
	}

	public void setExtraProvision(String extraProvision) {
		this.extraProvision = extraProvision;
	}

	/**
	 * 数量总计
	 */
	@Column(name = "TOTALCOUNT")
	public float getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(float totalCount) {
		this.totalCount = totalCount;
	}

	/**
	 * 数量总计的单位
	 */
	@Enumerated(EnumType.STRING)
	@Column(name = "TOTALCOUNTUNIT")
	public UnitOfMeasureCode getM_UnitOfMeasureCode() {
		return m_UnitOfMeasureCode;
	}

	public void setM_UnitOfMeasureCode(UnitOfMeasureCode m_UnitOfMeasureCode) {
		this.m_UnitOfMeasureCode = m_UnitOfMeasureCode;
	}

	/**
	 * 唛头
	 * 
	 * @return
	 */
	@Column(name = "SHIPPINGMARK")
	public String getShippingMark() {
		return shippingMark;
	}

	public void setShippingMark(String shippingMark) {
		this.shippingMark = shippingMark;
	}

	/**
	 * 交货日期
	 * 
	 * @return
	 */
	@Column(name = "DATEOFDELIVERY")
	public Date getDateOfDelivery() {
		return dateOfDelivery;
	}

	public void setDateOfDelivery(Date dateOfDelivery) {
		this.dateOfDelivery = dateOfDelivery;
	}

	/**
	 * 交货期
	 * 
	 * @return
	 */
	@Column(name = "DELIVERYPERIOD")
	public int getDeliveryPeriod() {
		return deliveryPeriod;
	}

	public void setDeliveryPeriod(int deliveryPeriod) {
		this.deliveryPeriod = deliveryPeriod;
	}

	/**
	 * 合同总金额
	 * 
	 * @return
	 */
	@Column(name = "EXTENDEDVALUETOTALAMOUNT")
	public float getExtendedValueTotalAmount() {
		return extendedValueTotalAmount;
	}

	public void setExtendedValueTotalAmount(float extendedValueTotalAmount) {
		this.extendedValueTotalAmount = extendedValueTotalAmount;
	}

	/**
	 * 是否分期付款
	 * 
	 * @return
	 */
	@Column(name = "ISINSTALLMENT")
	public Boolean getInstallment() {
		return installment;
	}

	public void setInstallment(Boolean installment) {
		this.installment = installment;
	}

	/**
	 * 是否指定运代商
	 * 
	 * @return
	 */
	@Column(name = "ISBUYERFREIGHTAGENT")
	public Boolean getBuyerFreightAgent() {
		return buyerFreightAgent;
	}

	public void setBuyerFreightAgent(Boolean buyerFreightAgent) {
		this.buyerFreightAgent = buyerFreightAgent;
	}

	/**
	 * 合同条款
	 * 
	 * @return
	 */
	@OneToOne
	@JoinColumn(name = "SPECIALPROVISION")
	public ContractProvision getSpecialProvision() {
		return specialProvision;
	}

	public void setSpecialProvision(ContractProvision specialProvision) {
		this.specialProvision = specialProvision;
	}

	@Transient
	public List getM_CustomerBuybackPickupOrder() {
		return m_CustomerBuybackPickupOrder;
	}

	public void setM_CustomerBuybackPickupOrder(
			List m_CustomerBuybackPickupOrder) {
		this.m_CustomerBuybackPickupOrder = m_CustomerBuybackPickupOrder;
	}

	@Transient
	public List getM_BuybackRequisitionSheetItem() {
		return m_BuybackRequisitionSheetItem;
	}

	public void setM_BuybackRequisitionSheetItem(
			List m_BuybackRequisitionSheetItem) {
		this.m_BuybackRequisitionSheetItem = m_BuybackRequisitionSheetItem;
	}

	@Transient
	public List getM_BuybackPactItems() {
		return m_BuybackPactItems;
	}

	public void setM_BuybackPactItems(List m_BuybackPactItems) {
		this.m_BuybackPactItems = m_BuybackPactItems;
	}

	/**
	 * 银行账户信息
	 * 
	 * @return
	 */
	@ManyToOne
	@JoinColumn(name = "M_BANKINFORMATION")
	public BankInformation getM_BankInformation() {
		return m_BankInformation;
	}

	public void setM_BankInformation(BankInformation m_BankInformation) {
		this.m_BankInformation = m_BankInformation;
	}

	/**
	 * 指定客户订单的类型、种类以及条件
	 * 
	 * @return
	 */
	@Enumerated(EnumType.STRING)
	@Column(name = "M_ORDERTRANSACTIONCODE")
	public OrderTransactionCode getM_OrderTransactionCode() {
		return m_OrderTransactionCode;
	}

	public void setM_OrderTransactionCode(
			OrderTransactionCode m_OrderTransactionCode) {
		this.m_OrderTransactionCode = m_OrderTransactionCode;
	}

	/**
	 * 客户识别码
	 * 
	 * @return
	 */
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "CIC_ID")
	public CustomerIdentificationCode getCustomerIdentificationCode() {
		return customerIdentificationCode;
	}

	public void setCustomerIdentificationCode(
			CustomerIdentificationCode customerIdentificationCode) {
		this.customerIdentificationCode = customerIdentificationCode;
	}

	/**
	 * 政府企业代码
	 * 
	 * @return
	 */
	@Enumerated(EnumType.STRING)
	@Column(name = "BUYER")
	public CAGECode getBuyer() {
		return buyer;
	}

	public void setBuyer(CAGECode buyer) {
		this.buyer = buyer;
	}
}