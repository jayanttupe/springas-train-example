package com.skynet.spms.persistence.entity.base.messageEntity;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.FilterDefs;
import org.hibernate.annotations.ParamDef;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.base.remarkEntity.RelEntityInfo;

/**
 * 消息留言的实体信息。
 * @author 曹宏炜
 * @version 1.0
 * @created 01-四月-2011 15:38:15
 */

@Entity
@Filter(name="active")
@Table(name = "SPMS_MESSAGE")
public class MessageEntity extends BaseEntity {
	
	private RelEntityInfo relInfo;
	

	private String message;

	@Field(index=Index.TOKENIZED, store=Store.NO)
	@Column(name = "MESSAGE")
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Embedded
	public RelEntityInfo getRelInfo() {
		return relInfo;
	}

	public void setRelInfo(RelEntityInfo relInfo) {
		this.relInfo = relInfo;
	}

	
	
	
}