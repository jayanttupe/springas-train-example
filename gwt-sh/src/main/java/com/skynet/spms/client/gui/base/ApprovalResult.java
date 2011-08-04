package com.skynet.spms.client.gui.base;

/**
 * 审批结果
 * @author zhangqiang
 *
 */
public enum ApprovalResult {
	pass("agree","通过"),
	fail("refuse","不通过");
	
	private String key = "";
	private String value = "";
	
	private ApprovalResult(String key,String value){
		this.key = key ;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}

}
