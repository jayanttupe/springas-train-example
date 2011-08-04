package com.skynet.spms.persistence.entity.supplierSupport.procurement.priceComparisonsReport;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.base.businessStatusEntity.BussinessStatusEntity;
import com.skynet.spms.persistence.entity.base.remarkEntity.RemarkTextEntity;

/**
 * 采购报价比价单，内部聚合了采购报价单，采购报价单组合了采购报价单明细项，比价单由业务人员选取报价单之后系统生成。通常的比价模式是一张二维表样式，纵向是比价的各个
 * 比值项，横向为各个供应商，建议将性价比最高的供应商放置于表格的左侧第一排，其余可依据性价比此次排放。通过比价还能查看供应商的发布价格报价情况。
 * @author 曹宏炜
 * @version 1.0
 * @created 05-五月-2011 11:15:37
 */
@Entity
@Table(name = "SPMS_PRICECOMPARISONSSHEET")
public class PriceComparisonsSheetReport extends BaseEntity {

	/**比价单编号*/
	private String priceComparisonNumber;
	/**
	 * 设定多个报价单编号中首选采购的报价单编号
	 */
	private String firstChoiceQuotation;
	
	/**备注*/
	public String remark;
	
	/**业务状态*/
	public BussinessStatusEntity m_BussinessStatusEntity;
	
	@Column(name="PRICECOMPARISONNUMBER")
	public String getPriceComparisonNumber() {
		return priceComparisonNumber;
	}
	public void setPriceComparisonNumber(String priceComparisonNumber) {
		this.priceComparisonNumber = priceComparisonNumber;
	}
	
	@Column(name="FIRSTCHOICEQUOTATION")
	public String getFirstChoiceQuotation() {
		return firstChoiceQuotation;
	}
	public void setFirstChoiceQuotation(String firstChoiceQuotation) {
		this.firstChoiceQuotation = firstChoiceQuotation;
	}
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "STATUSENTITY_ID")
	public BussinessStatusEntity getM_BussinessStatusEntity() {
		return m_BussinessStatusEntity;
	}
	public void setM_BussinessStatusEntity(
			BussinessStatusEntity bussinessStatusEntity) {
		m_BussinessStatusEntity = bussinessStatusEntity;
	}
	
	@Column(name="REMARK")
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}