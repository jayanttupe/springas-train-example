package com.skynet.spms.persistence.entity.organization.userInformation;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.FilterDefs;
import org.hibernate.annotations.ParamDef;
import org.hibernate.search.annotations.DateBridge;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Resolution;
import org.hibernate.search.annotations.Store;

import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.base.messageEntity.MessageEntity;

/**
 * 实现针对每个用户的消息处理记录信息。
 * @author 曹宏炜
 * @version 1.0
 * @created 01-四月-2011 15:38:06
 */
@Entity
@FilterDefs({
	@FilterDef(name="active",
			defaultCondition="IS_DELETED = :isDele",
			parameters=@ParamDef(name="isDele",type="boolean"))		
})
@Filter(name="active")
@Indexed
@Table(name = "SPMS_USER_MESSAGE_RECORD")
public class UserMessageRecord extends BaseEntity{

	private boolean isReaded;
	private java.util.Date readDatetime;
	private String userId;
	private MessageEntity m_MessageEntity;
	
	@Column(name = "IS_READED")
	public boolean isReaded() {
		return isReaded;
	}
	public void setReaded(boolean isReaded) {
		this.isReaded = isReaded;
	}
	
	@Field(index = Index.UN_TOKENIZED, store = Store.YES)
	@DateBridge(resolution = Resolution.DAY)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "READ_DATETIME")
	public java.util.Date getReadDatetime() {
		return readDatetime;
	}
	public void setReadDatetime(java.util.Date readDatetime) {
		this.readDatetime = readDatetime;
	}
	
	@Column(name = "USER_ID")
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@IndexedEmbedded
	@OneToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="MESSAGE_ID")
	public MessageEntity getM_MessageEntity() {
		return m_MessageEntity;
	}
	public void setM_MessageEntity(MessageEntity m_MessageEntity) {
		this.m_MessageEntity = m_MessageEntity;
	}
	
}