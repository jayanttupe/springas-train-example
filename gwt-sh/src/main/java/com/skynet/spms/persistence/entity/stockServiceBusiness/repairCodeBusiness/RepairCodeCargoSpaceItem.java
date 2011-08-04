package com.skynet.spms.persistence.entity.stockServiceBusiness.repairCodeBusiness;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.FilterDefs;
import org.hibernate.annotations.ParamDef;

import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.stockServiceBusiness.warehouseManageBusiness.cargoSpaceManage.CargoSpace;

/**
 * @author wangyx
 * @version 1.1
 * @created 2011-5-9
 */
@Entity
@Table(name="SPMS_REPAIR_CODE_ITEM_CS")
@FilterDefs({
	@FilterDef(name="active",
			defaultCondition="IS_DELETED = :isDele",
			parameters=@ParamDef(name="isDele",type="boolean"))		
})
@Filter(name="active")
public class RepairCodeCargoSpaceItem extends BaseEntity {
	/**
	 * 补码ID
	 */
	private String repairCodeId;
	/**
	 * 货位
	 */
	private CargoSpace cargoSpace;
	
	@Column(name = "REPAIR_CODE_ID")
	public String getRepairCodeId() {
		return repairCodeId;
	}
	public void setRepairCodeId(String repairCodeId) {
		this.repairCodeId = repairCodeId;
	}
	@ManyToOne
	@JoinColumn(name="CARGO_SPACE_ID")
	public CargoSpace getCargoSpace() {
		return cargoSpace;
	}
	public void setCargoSpace(CargoSpace cargoSpace) {
		this.cargoSpace = cargoSpace;
	}
}
