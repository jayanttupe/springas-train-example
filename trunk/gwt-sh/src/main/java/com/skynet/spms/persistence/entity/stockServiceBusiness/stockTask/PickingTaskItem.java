package com.skynet.spms.persistence.entity.stockServiceBusiness.stockTask;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.skynet.spms.datasource.annotation.ViewFormAnno;

/**
 * @author 曹宏炜
 * @version 1.0
 * @created 15-三月-2011 12:33:17
 */
@ViewFormAnno(value="id")
@Entity
@Table(name = "SPMS_PICKING_TASK_ITEM")
public class PickingTaskItem extends BaseStockTaskItem{

	/**
	 * 货位编号
	 */
	private String cargoSpaceNumber;

	@Column(name = "CARGO_SPACE_NUMBER")
	public String getCargoSpaceNumber() {
		return cargoSpaceNumber;
	}

	public void setCargoSpaceNumber(String cargoSpaceNumber) {
		this.cargoSpaceNumber = cargoSpaceNumber;
	}
}