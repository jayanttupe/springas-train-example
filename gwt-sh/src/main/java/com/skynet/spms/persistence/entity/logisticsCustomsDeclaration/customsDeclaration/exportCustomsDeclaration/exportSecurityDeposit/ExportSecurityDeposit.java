package com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.customsDeclaration.exportCustomsDeclaration.exportSecurityDeposit;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.skynet.spms.datasource.annotation.ViewFormAnno;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.base.baseCustomsDeclaration.baseSecurityDeposit.BaseSecurityDeposit;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.customsDeclaration.exportCustomsDeclaration.exportCustomsDeclaration.ExportCustomsDeclarationItems;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.pickupDeliveryOrder.PickupDeliveryOrder;

/**
 * @author FANYX
 * @version 1.0  出口报关保证金
 * @created 16-三月-2011 17:21:06
 */
@ViewFormAnno(value="id")
@Entity
@Table(name = "SPMS_EXPORT_SECURITY_DEPOSIT")
public class ExportSecurityDeposit extends BaseSecurityDeposit {

	/**
	 * 出口日期
	 */
	private Date exportDate;
	/**
	 * 出口报关明细
	 */
	private List<ExportCustomsDeclarationItems> exportCustomsDeclarationItems;
	
	@Column(name="EXPORT_DATE")
	public Date getExportDate() {
		return exportDate;
	}
	public void setExportDate(Date exportDate) {
		this.exportDate = exportDate;
	}
	
	@Transient
	public List<ExportCustomsDeclarationItems> getExportCustomsDeclarationItems() {
		return exportCustomsDeclarationItems;
	}
	public void setExportCustomsDeclarationItems(
			List<ExportCustomsDeclarationItems> exportCustomsDeclarationItems) {
		this.exportCustomsDeclarationItems = exportCustomsDeclarationItems;
	}
	
}