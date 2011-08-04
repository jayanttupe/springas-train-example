package com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.customsDeclaration.importCustomsDeclaration.importCustomsTariffRecord;
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
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.base.baseCustomsDeclaration.baseCustomsTariffRecord.BaseCustomsTaiffRecord;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.customsDeclaration.importCustomsDeclaration.importCustomsDeclaration.ImportCustomsDeclarationItems;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.pickupDeliveryOrder.PickupDeliveryOrder;
/**
 * @author FANYX
 * @version 1.0  进口关税记录
 * @created 
 */
@ViewFormAnno(value="id")
@Entity
@Table(name = "SPMS_IMPORT_TARI_RECORD")
public class ImportCustomsTariffRecord extends BaseCustomsTaiffRecord{

	/**
	 * 进口报关备件项
	 */
	private List<ImportCustomsDeclarationItems> importCustomsDeclarationItems;
	/**
     * 基础提发货指令
     */
	private PickupDeliveryOrder pickupDeliveryOrder;

	@Transient
	public List<ImportCustomsDeclarationItems> getImportCustomsDeclarationItems() {
		return importCustomsDeclarationItems;
	}
	public void setImportCustomsDeclarationItems(
			List<ImportCustomsDeclarationItems> importCustomsDeclarationItems) {
		this.importCustomsDeclarationItems = importCustomsDeclarationItems;
	}
	@Transient
	public PickupDeliveryOrder getPickupDeliveryOrder() {
		return pickupDeliveryOrder;
	}
	public void setPickupDeliveryOrder(PickupDeliveryOrder pickupDeliveryOrder) {
		this.pickupDeliveryOrder = pickupDeliveryOrder;
	}
	
	
	
}