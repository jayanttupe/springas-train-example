package com.skynet.spms.persistence.entity.supplierSupport.repairClaim.InspectOutlayRecord;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import com.skynet.spms.persistence.entity.base.baseOutlayRecordEntity.baseOutlayItem.BaseOutlayItem;

/**
 * 供应商检验费用处理记录明细
 * @author 曹宏炜
 * @version 1.0
 * @created 07-五月-2011 10:33:21
 */
@Entity(name="SupplierSupportInspectOutlayRecordItem")
@Table(name="SPMS_SUPPINSOUTRECORD_ITEM")
public class InspectOutlayRecordItem extends BaseOutlayItem {
	
	/**处理记录编号**/
	private String inspectOutlayRecordId;

	@Column(name="INSPECTOUTLAYRECORDID")
	public String getInspectOutlayRecordId() {
		return inspectOutlayRecordId;
	}

	public void setInspectOutlayRecordId(String inspectOutlayRecordId) {
		this.inspectOutlayRecordId = inspectOutlayRecordId;
	}
	
	

}