package com.skynet.spms.client.vo.customerService.salesInquirySheet;

import java.util.ArrayList;
import java.util.List;
import com.google.gwt.user.client.rpc.IsSerializable;

public class SalesInquirySheetVO implements IsSerializable {
	
	private String id;
	
	/** 询价单编号 **/
	private String inquirySheetNumber;

	/** 询价单备注 **/
	private String remark;

	/** 如果是非系统登录用户，可以填写联系人姓名，做为联系方式 **/
	private String linkman;

	/** 客户的联系人员的联系方式 **/
	private String contactInformation;

	/** 对应询价单明细项 **/
	private List<SalesInquirySheetItemVO> m_SalesInquirySheetItem = new ArrayList<SalesInquirySheetItemVO>();

	/** 客户识别代码 **/
	private String m_CustomerIdentificationCode;

	public SalesInquirySheetVO() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<SalesInquirySheetItemVO> getM_SalesInquirySheetItem() {
		return m_SalesInquirySheetItem;
	}

	public String getInquirySheetNumber() {
		return inquirySheetNumber;
	}

	public void setInquirySheetNumber(String inquirySheetNumber) {
		this.inquirySheetNumber = inquirySheetNumber;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getLinkman() {
		return linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

	public String getContactInformation() {
		return contactInformation;
	}

	public void setContactInformation(String contactInformation) {
		this.contactInformation = contactInformation;
	}

	public void setM_SalesInquirySheetItem(
			List<SalesInquirySheetItemVO> m_SalesInquirySheetItem) {
		this.m_SalesInquirySheetItem = m_SalesInquirySheetItem;
	}

	public String getM_CustomerIdentificationCode() {
		return m_CustomerIdentificationCode;
	}

	public void setM_CustomerIdentificationCode(
			String m_CustomerIdentificationCode) {
		this.m_CustomerIdentificationCode = m_CustomerIdentificationCode;
	}

}
