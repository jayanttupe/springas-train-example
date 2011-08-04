package com.skynet.spms.persistence.entity.contractManagement.template.SalesContractTemplate;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.skynet.spms.persistence.entity.contractManagement.template.CustomerContactTemplate.baseCustomerContractTemplate.BaseCustomerContract;
import com.skynet.spms.persistence.entity.csdd.c.CustomerIdentificationCode;
import com.skynet.spms.persistence.entity.csdd.u.UnitOfMeasureCode;
import com.skynet.spms.persistence.entity.customerService.sales.salesRequisitionSheet.SalesRequisitionSheet;
import com.skynet.spms.persistence.entity.spmsdd.Priority;

/**
 * (客户)销售合同信息
 * 
 * @author songb
 * @version 1.0
 * @created 05-五月-2011 11:15:44
 */
@Entity
@Table(name = "SPMS_SALESCONTRACTTEMPLATE")
public class SalesContractTemplate extends BaseCustomerContract {

	/** 唛头 **/
	private String shippingMark;
	/**
	 * 交货日期指定后，无需再设定交货期天数。同样设定交货期天数，则也无需设定交货日期。
	 */
	private Date dateOfDelivery;

	/** 交货期(天) **/
	private Integer deliveryPeriod;

	/** 合同总金额 **/
	private float extendedValueTotalAmount;

	/** 是否分期付款 **/
	private boolean installment;
	/**
	 * 如果是买方指定运代，如果运代在字典中不存在需要进行维护运代的企业信息
	 */
	private Boolean buyerFreightAgent;

	/** 客户识别代码 **/
	private CustomerIdentificationCode customerIdenty;

	/** 优先级 **/
	private Priority m_Priority;

	/** 关联销售单信息 */
	private SalesRequisitionSheet saleSheet;
	/**
	 * 联系人
	 * 
	 * @return
	 */
	private String linkMan;
	/**
	 * 联系方式
	 * 
	 * @return
	 */
	private String linkWay;
	/**
	 * 单位
	 */
	private UnitOfMeasureCode m_UnitOfMeasureCode;
	/**
	 * 特殊条款
	 */
	private String extraProvision;

	/**
	 * 项数总计
	 */
	public Integer totalCount;

	@Column(name = "TOTALCOUNT")
	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	/**
	 * 联系方式
	 * 
	 * @return
	 */
	@Column(name = "LINKWAY")
	public String getLinkWay() {
		return linkWay;
	}

	public void setLinkWay(String linkWay) {
		this.linkWay = linkWay;
	}

	@Column(name = "EXTRAPROVISION")
	public String getExtraProvision() {
		return extraProvision;
	}

	public void setExtraProvision(String extraProvision) {
		this.extraProvision = extraProvision;
	}

	@Column(name = "LINKMAN")
	public String getLinkMan() {
		return linkMan;
	}

	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
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

	@Enumerated(EnumType.STRING)
	@Column(name = "M_UNITOFMEASURECODE")
	public UnitOfMeasureCode getM_UnitOfMeasureCode() {
		return m_UnitOfMeasureCode;
	}

	public void setM_UnitOfMeasureCode(UnitOfMeasureCode m_UnitOfMeasureCode) {
		this.m_UnitOfMeasureCode = m_UnitOfMeasureCode;
	}

	@OneToOne
	@JoinColumn(name = "SALESHEET_ID")
	public SalesRequisitionSheet getSaleSheet() {
		return saleSheet;
	}

	public void setSaleSheet(SalesRequisitionSheet saleSheet) {
		this.saleSheet = saleSheet;
	}

	@Column(name = "SHIPPINGMARK")
	public String getShippingMark() {
		return shippingMark;
	}

	public void setShippingMark(String shippingMark) {
		this.shippingMark = shippingMark;
	}

	@Column(name = "DATEOFDELIVERY")
	public Date getDateOfDelivery() {
		return dateOfDelivery;
	}

	public void setDateOfDelivery(Date dateOfDelivery) {
		this.dateOfDelivery = dateOfDelivery;
	}

	@Column(name = "DELIVERYPERIOD")
	public Integer getDeliveryPeriod() {
		return deliveryPeriod;
	}

	public void setDeliveryPeriod(Integer deliveryPeriod) {
		this.deliveryPeriod = deliveryPeriod;
	}

	@Column(name = "EXTENDEDAMOUNT")
	public float getExtendedValueTotalAmount() {
		return extendedValueTotalAmount;
	}

	public void setExtendedValueTotalAmount(float extendedValueTotalAmount) {
		this.extendedValueTotalAmount = extendedValueTotalAmount;
	}

	@Column(name = "ISINSTALLMENT")
	public boolean isInstallment() {
		return installment;
	}

	public void setInstallment(boolean installment) {
		this.installment = installment;
	}

	@ManyToOne
	@JoinColumn(name = "C_CUSTOMER_IDENTTION_CODE")
	public CustomerIdentificationCode getCustomerIdenty() {
		return customerIdenty;
	}

	public void setCustomerIdenty(CustomerIdentificationCode customerIdenty) {
		this.customerIdenty = customerIdenty;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "M_PRIORITY")
	public Priority getM_Priority() {
		return m_Priority;
	}

	public void setM_Priority(Priority m_Priority) {
		this.m_Priority = m_Priority;
	}
}