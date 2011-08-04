package com.skynet.spms.client.gui.contractManagement.tag;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * 合同标签
 * 
 * @author tqc
 * 
 */
public class Tag implements IsSerializable {

	/** 标签编号 **/
	private Integer tagID;

	/** 标签类别 **/
	private TagEnum tagType;

	/** 标签名 **/
	private String tagName;

	/** 标签key **/
	private String tagKey;

	public Tag() {
	}

	public Tag(String tagName, String tagKey) {
		this.tagName = tagName;
		this.tagKey = tagKey;
	}

	public Tag(Integer tagID, TagEnum tagType, String tagName, String tagKey) {
		this.tagID = tagID;
		this.tagType = tagType;
		this.tagName = tagName;
		this.tagKey = tagKey;
	}

	public Integer getTagID() {
		return tagID;
	}

	public void setTagID(Integer tagID) {
		this.tagID = tagID;
	}

	public TagEnum getTagType() {
		return tagType;
	}

	public void setTagType(TagEnum tagType) {
		this.tagType = tagType;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public String getTagKey() {
		return tagKey;
	}

	public void setTagKey(String tagKey) {
		this.tagKey = tagKey;
	}

}
