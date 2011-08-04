package com.skynet.spms.web.form;

import com.skynet.spms.datasource.annotation.ViewFormAnno;

@ViewFormAnno(value="repairShopCodeId")
public class RepairShopCodeForm {

	private String repairShopCodeId;
	private String repairShopCode;
	private String repairShopName;
	public String getRepairShopCodeId() {
		return repairShopCodeId;
	}
	public void setRepairShopCodeId(String repairShopCodeId) {
		this.repairShopCodeId = repairShopCodeId;
	}
	public String getRepairShopCode() {
		return repairShopCode;
	}
	public void setRepairShopCode(String repairShopCode) {
		this.repairShopCode = repairShopCode;
	}
	public String getRepairShopName() {
		return repairShopName;
	}
	public void setRepairShopName(String repairShopName) {
		this.repairShopName = repairShopName;
	}

}
