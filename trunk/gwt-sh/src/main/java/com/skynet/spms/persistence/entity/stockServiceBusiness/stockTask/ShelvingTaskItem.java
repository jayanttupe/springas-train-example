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
@Table(name = "SPMS_SHELVING_TASK_ITEM")
public class ShelvingTaskItem extends BaseStockTaskItem{

	/**
	 * 实际货位
	 */
	private String cargoSpaceNumber;

	/**
	 * 推荐货位
	 */
	private String recCargoSpaceNumber;

	@Column(name = "CARGO_SPACE_NUMBER")
	public String getCargoSpaceNumber() {
		return cargoSpaceNumber;
	}

	public void setCargoSpaceNumber(String cargoSpaceNumber) {
		this.cargoSpaceNumber = cargoSpaceNumber;
	}

	@Column(name = "REC_CARGO_SPACE_NUMBER")
	public String getRecCargoSpaceNumber() {
		return recCargoSpaceNumber;
	}

	public void setRecCargoSpaceNumber(String recCargoSpaceNumber) {
		this.recCargoSpaceNumber = recCargoSpaceNumber;
	}
}