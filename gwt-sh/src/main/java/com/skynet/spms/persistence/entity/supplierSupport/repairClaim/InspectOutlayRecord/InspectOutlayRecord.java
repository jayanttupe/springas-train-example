package com.skynet.spms.persistence.entity.supplierSupport.repairClaim.InspectOutlayRecord;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import com.skynet.spms.persistence.entity.base.baseOutlayRecordEntity.BaseOutlayRecord;

/**
 * 供应商检验费用处理记录
 * 
 * @author 曹宏炜
 * @version 1.0
 * @created 07-五月-2011 10:33:21
 */
@Entity(name = "SupplierSupportInspectOutlayRecord")
@Table(name = "SPMS_SUPPINSPECTOUTLAYRECORD")
public class InspectOutlayRecord extends BaseOutlayRecord {

	/** 检测单位 **/
	private String detectionUnit;

	/** 检验报告描述 **/
	private String inspectionDescription;

	/** 检测日期 **/
	private Date detectionDate;


	@Column(name = "DETECTIONUNIT")
	public String getDetectionUnit() {
		return detectionUnit;
	}

	public void setDetectionUnit(String detectionUnit) {
		this.detectionUnit = detectionUnit;
	}

	@Column(name = "INSPECTIONDES")
	public String getInspectionDescription() {
		return inspectionDescription;
	}

	public void setInspectionDescription(String inspectionDescription) {
		this.inspectionDescription = inspectionDescription;
	}

	@Column(name = "DETECTIONDATE")
	public Date getDetectionDate() {
		return detectionDate;
	}

	public void setDetectionDate(Date detectionDate) {
		this.detectionDate = detectionDate;
	}


}