package com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.customsDeclaration.importCustomsDeclaration.importSecurityDeposit;
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
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.customsDeclaration.importCustomsDeclaration.importCustomsDeclaration.ImportCustomsDeclarationItems;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.pickupDeliveryOrder.PickupDeliveryOrder;

/**
 * @author FANYX
 * @version 1.0   进口报关保证金
 * @created 
 */
@ViewFormAnno(value="id")
@Entity
@Table(name = "SPMS_IMPORT_SECURITY_DEPOSIT")
public class ImportSecurityDeposit extends BaseSecurityDeposit {
	
	/**
	 * 进口日期
	 */
	private Date importDate;
    /**
	 * 进口报关明细
	 */
	private List<ImportCustomsDeclarationItems> importCustomsDeclarationItems;
	
	@Column(name = "IMPORT_DATE")
	public Date getImportDate() {
		return importDate;
	}
	public void setImportDate(Date importDate) {
		this.importDate = importDate;
	}
	
	@Transient
	public List<ImportCustomsDeclarationItems> getImportCustomsDeclarationItems() {
		return importCustomsDeclarationItems;
	}
	public void setImportCustomsDeclarationItems(
			List<ImportCustomsDeclarationItems> importCustomsDeclarationItems) {
		this.importCustomsDeclarationItems = importCustomsDeclarationItems;
	}
}