package com.skynet.spms.persistence.entity.base.remarkEntity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.skynet.spms.persistence.entity.base.baseEntity.BaseIDEntity;

/**
 * @author 曹宏炜
 * @version 1.0
 * @created 05-五月-2011 11:15:41
 */
@Entity
@Table(name="SPMS_REMARK_TEXT_ENTITY")
public class RemarkTextEntity extends BaseIDEntity{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1958335765915369228L;

	private String remarkText;
	
	private RelEntityInfo relInfo;
	
	private String fromUser;
	
	private Date createDate;
	
	
	@Embedded
	public RelEntityInfo getRelInfo() {
		return relInfo;
	}

	public void setRelInfo(RelEntityInfo relInfo) {
		this.relInfo = relInfo;
	}

	@Column(name="FROM_USER")
	public String getFromUser() {
		return fromUser;
	}

	public void setFromUser(String fromUser) {
		this.fromUser = fromUser;
	}

	@Column(name="REMARK_TEXT")
	public String getRemarkText() {
		return remarkText;
	}

	public void setRemarkText(String remarkText) {
		this.remarkText = remarkText;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_DATE")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
}