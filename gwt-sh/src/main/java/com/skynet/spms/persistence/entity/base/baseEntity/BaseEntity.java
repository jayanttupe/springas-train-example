package com.skynet.spms.persistence.entity.base.baseEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.FilterDefs;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.ParamDef;

import com.skynet.spms.datasource.annotation.Reference;
import com.skynet.spms.persistence.entity.base.logEntity.GeneralOperateLogEntity;

/**
 * 抽象层的实体，封装了基本的实体信息，本系统内的其他业务实体可以继承BaseEntity来扩展出新的业务实体。该实体实现了Serializable接口，
 * 因此继承这 个实体的业务实体都能实现远程传输。
 * 
 * @author 曹宏炜
 * @version 1.0
 * @created 10-三月-2011 11:10:33
 */
@MappedSuperclass
@FilterDefs({
	@FilterDef(name="active",
			defaultCondition="IS_DELETED = :isDele",
			parameters=@ParamDef(name="isDele",type="boolean"))		
})
@Filter(name="active")
public class BaseEntity extends BaseIDEntity {

	private String createBy;
	
	private Date createDate;
	
	private String keyword;
	
	private int version;
	
	private boolean deleted=false;
	
	private int dbVersion=0;
	
	public List<GeneralOperateLogEntity> m_GeneralOperateLogEntity = new ArrayList<GeneralOperateLogEntity>();
	
	@Column(name = "CREATE_BY")
	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_DATE")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "KEYWORD")
	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

//	@Reference(itemCls=GeneralOperateLogEntity.class)
//	@OneToMany()
//	@JoinColumn(name = "RECORD_ID")
//	public List<GeneralOperateLogEntity> getM_GeneralOperateLogEntity() {
//		return m_GeneralOperateLogEntity;
//	}
//	public void setM_GeneralOperateLogEntity(
//			List<GeneralOperateLogEntity> m_GeneralOperateLogEntity) {
//		this.m_GeneralOperateLogEntity = m_GeneralOperateLogEntity;
//	}

	@Column(name = "VERSION")
	public int getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@Column(name = "IS_DELETED")
	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
	public void doDelete(){
		this.deleted=true;
	}
	
	@Version
	@Column(name="LOCK_VER")
	public int getLockVersion(){
		return dbVersion;
	}
	
	public void setLockVersion(int ver){
		this.dbVersion=ver;
	}
}