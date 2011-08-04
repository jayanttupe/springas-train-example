package com.skynet.spms.persistence.entity.stockServiceBusiness.checkAndAcceptBusiness.nonconformingReport;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.skynet.spms.datasource.annotation.ViewFormAnno;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;

/**
 * 不合格品报告
 * @author fanyx
 * @version 1.0
 * @created 24-三月-2011 12:33:14
 */

@ViewFormAnno(value="id")
@Entity
@Table(name = "SPMS_NONCONFORMING_REPORT")
public class NonconformingReport extends BaseEntity {

	/**
	 * 采购实施部门处理描述(验收结论文本)
	 */
	private String acceptanceConclusionText;
	
	/**
	 * 纠正措施和完成时限
	 */
	private String correctiveAction;
	/**
	 * MRB
	 */
	private String mrb;
	/**
	 * 不合格描述(原因分析)
	 */
	private String nonconformingAnalysis;
	/**
	 * 不合格原因
	 */
	private String nonconformingReason;
	/**
	 * 不合格品报告编号
	 */
	private String nonconformingReportNumber;
	/**
	 * 供货单位
	 */
	private String supplyUnit;
	/**
	 * 业务人员
	 */
	private String transactor;
	/**
	 * 检验人
	 */
	private String identifier;
	/**
	 * 检验日期
	 */
    private Date surveyDate; 
	/**
	 * 处理日期
	 */
	private Date treatmentDate;
	/**
	 * 处理意见
	 */
	private String disposeIdea;
	/**
	 * 合同编号
	 */
	private String contratNumber;
	/**
	 * 检验单号
	 */
	private String checkAndAcceptSheetNumber;
	/**
	 * 件号
	 */
	private String partNumber;
	/**
	 * 序号批号
	 */
	private String partSerialNumber;
	/**
	 * 数量
	 */
	private String quantity;
	/**
	 * 状态
	 */
	private String state;
	
	@Column(name = "ACCEPTANCE_CONCLUSION")
	public String getAcceptanceConclusionText() {
		return acceptanceConclusionText;
	}
	
	public void setAcceptanceConclusionText(String acceptanceConclusionText) {
		this.acceptanceConclusionText = acceptanceConclusionText;
	}

    @Column(name = "CORRECTIVE_ACTION") 
	public String getCorrectiveAction() {
		return correctiveAction;
	}

	public void setCorrectiveAction(String correctiveAction) {
		this.correctiveAction = correctiveAction;
	}

	@Column(name = "MRB") 
	public String getMrb() {
		return mrb;
	}

	public void setMrb(String mrb) {
		this.mrb = mrb;
	}

	@Column(name = "NONCONFORMING_ANALYSIS")
	public String getNonconformingAnalysis() {
		return nonconformingAnalysis;
	}

	public void setNonconformingAnalysis(String nonconformingAnalysis) {
		this.nonconformingAnalysis = nonconformingAnalysis;
	}

	@Column(name = "NONCONFORMING_REASON")
	public String getNonconformingReason() {
		return nonconformingReason;
	}

	public void setNonconformingReason(String nonconformingReason) {
		this.nonconformingReason = nonconformingReason;
	}

	@Column(name = "NONCONFORMING_REPORT_NUMBER")
	public String getNonconformingReportNumber() {
		return nonconformingReportNumber;
	}

	public void setNonconformingReportNumber(String nonconformingReportNumber) {
		this.nonconformingReportNumber = nonconformingReportNumber;
	}

	@Column(name = "SUPPLY_UNIT")
	public String getSupplyUnit() {
		return supplyUnit;
	}

	public void setSupplyUnit(String supplyUnit) {
		this.supplyUnit = supplyUnit;
	}	

	@Column(name = "TRANSACTOR")
	public String getTransactor() {
		return transactor;
	}

	public void setTransactor(String transactor) {
		this.transactor = transactor;
	}

	@Column(name = "IDENTIFIER")
	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	@Column(name = "SURVEY_DATE")
	public Date getSurveyDate() {
		return surveyDate;
	}

	public void setSurveyDate(Date surveyDate) {
		this.surveyDate = surveyDate;
	}

	@Column(name = "TREATMENT_DATE")
	public Date getTreatmentDate() {
		return treatmentDate;
	}

	public void setTreatmentDate(Date treatmentDate) {
		this.treatmentDate = treatmentDate;
	}
	
	@Column(name = "DISPOSE_IDEA") 
	public String getDisposeIdea() {
		return disposeIdea;
	}

	public void setDisposeIdea(String disposeIdea) {
		this.disposeIdea = disposeIdea;
	}

	@Column(name = "CONTRACT_NUMBER")
	public String getContratNumber() {
		return contratNumber;
	}

	public void setContratNumber(String contratNumber) {
		this.contratNumber = contratNumber;
	}

	@Column(name = "CHECK_AND_ACCEPT_SHEET_NUMBER") 
	public String getCheckAndAcceptSheetNumber() {
		return checkAndAcceptSheetNumber;
	}

	public void setCheckAndAcceptSheetNumber(String checkAndAcceptSheetNumber) {
		this.checkAndAcceptSheetNumber = checkAndAcceptSheetNumber;
	}

	@Column(name = "PARTNUMBER") 
	public String getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	@Column(name = "PART_SERIAL_NUMBER") 
	public String getPartSerialNumber() {
		return partSerialNumber;
	}

	public void setPartSerialNumber(String partSerialNumber) {
		this.partSerialNumber = partSerialNumber;
	}

   @Column(name = "QUANTITY")
	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	@Column(name = "STATE") 
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}


	public NonconformingReport(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

}