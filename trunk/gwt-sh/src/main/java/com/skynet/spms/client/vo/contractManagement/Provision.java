package com.skynet.spms.client.vo.contractManagement;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * dto for xml client
 * 
 * @author tqc
 * 
 */
public class Provision implements IsSerializable{
	private String id;
	private String parentId;
	private String createBy;
	private String createDate;
	private String keywordkey;
	private String version;
	private String deleted;
	private String itemNumber;
	private String title_en;
	private String title_zh;
	private String content_en;
	private String content_zh;
	private String tempType;

	public Provision() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getKeywordkey() {
		return keywordkey;
	}

	public void setKeywordkey(String keywordkey) {
		this.keywordkey = keywordkey;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getDeleted() {
		return deleted;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}

	public String getItemNumber() {
		return itemNumber;
	}

	public void setItemNumber(String itemNumber) {
		this.itemNumber = itemNumber;
	}

	public String getTitle_en() {
		return title_en;
	}

	public void setTitle_en(String title_en) {
		this.title_en = title_en;
	}

	public String getTitle_zh() {
		return title_zh;
	}

	public void setTitle_zh(String title_zh) {
		this.title_zh = title_zh;
	}

	public String getContent_en() {
		return content_en;
	}

	public void setContent_en(String content_en) {
		this.content_en = content_en;
	}

	public String getContent_zh() {
		return content_zh;
	}

	public void setContent_zh(String content_zh) {
		this.content_zh = content_zh;
	}

	public String getTempType() {
		return tempType;
	}

	public void setTempType(String tempType) {
		this.tempType = tempType;
	}

}
