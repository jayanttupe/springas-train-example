package com.skynet.spms.client.vo;

import com.google.gwt.user.client.rpc.IsSerializable;

public class DepartmentVo  implements IsSerializable  {

	private String department;
	private String remark;
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
