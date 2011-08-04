package com.skynet.spms.persistence.entity.supplierSupport.procurement.procurementTransferSheet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.skynet.spms.persistence.entity.contractManagement.template.TransferSheetTemplate.BaseTransferSheetTemplate.BaseTransferSheetItem;

/**
 * @author 曹宏炜
 * @version 1.0
 * @created 05-五月-2011 11:15:38
 */

@Entity
@Table(name = "SPMS_PRO_TRANSFER_ITEM")
public class ProcurementTransferSheetItem extends BaseTransferSheetItem {

	/**调拨单**/
	private ProcurementTransferSheet procurementTransferSheet;

	@ManyToOne
	@JoinColumn(name = "PROCUREMENT_TRANSFER_ID")
	public ProcurementTransferSheet getProcurementTransferSheet() {
		return procurementTransferSheet;
	}

	public void setProcurementTransferSheet(
			ProcurementTransferSheet procurementTransferSheet) {
		this.procurementTransferSheet = procurementTransferSheet;
	}

}