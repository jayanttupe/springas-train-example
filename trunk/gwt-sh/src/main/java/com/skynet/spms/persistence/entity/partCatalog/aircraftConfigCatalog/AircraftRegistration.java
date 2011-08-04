package com.skynet.spms.persistence.entity.partCatalog.aircraftConfigCatalog;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.FilterDefs;
import org.hibernate.annotations.ParamDef;

import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.csdd.a.AircraftModelIdentifier;
import com.skynet.spms.persistence.entity.csdd.c.CountryCode;

/**
 * Aircraft Registration
 * The resolving entity between aircraft and registration, showing the current
 * registration of an aircraft.
 * @author 曹宏炜
 * @version 1.0
 * @created 18-四月-2011 14:14:20
 */
@Entity
@FilterDefs({
	@FilterDef(name="active",
			defaultCondition="IS_DELETED = :isDele",
			parameters=@ParamDef(name="isDele",type="boolean"))		
})
@Filter(name="active")
@Table(name = "SPMS_AIRCRAFT_REGISTRATION")
public class AircraftRegistration extends BaseEntity{
	/*
	 * 适用机型
	 */
	private AircraftModelIdentifier m_AircraftModelIdentifier;
	/*
	 * 飞机尾号
	 */
	private String aircraftTailNumber;
	/*
	 * 飞机运营人
	 */
	private String operator;
	/*
	 * 飞机所有人
	 */
	private String owner;
	/*
	 * 注册时间
	 */
	private Date registrationDate;
	
	/*
	 * 出厂时间
	 */
	private Date factoryDate;
	/*
	 * 飞机注册号
	 */
	public String aircraftRegistrationNumber;
	/*
	 * 国籍
	 */
	private CountryCode m_CountryCode;
	
	@Column(name = "AIRCRAFT_TAIL_NUMBER")
	public String getAircraftTailNumber() {
		return aircraftTailNumber;
	}
	public void setAircraftTailNumber(String aircraftTailNumber) {
		this.aircraftTailNumber = aircraftTailNumber;
	}
	@Column(name = "FACTORY_DATE")
	public Date getFactoryDate() {
		return factoryDate;
	}
	public void setFactoryDate(Date factoryDate) {
		this.factoryDate = factoryDate;
	}
    @Enumerated(EnumType.STRING)
    @Column(name = "COUNTRY_CODE")
	public CountryCode getM_CountryCode() {
		return m_CountryCode;
	}
	public void setM_CountryCode(CountryCode m_CountryCode) {
		this.m_CountryCode = m_CountryCode;
	}
	@Column(name = "OPERATOR")
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	@Column(name = "OWNER")
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	@Column(name = "REGISTRATION_DATE")
	public java.util.Date getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(java.util.Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	@Column(name = "AIR_REGI_NUM")
    public String getAircraftRegistrationNumber() {
		return aircraftRegistrationNumber;
	}
	public void setAircraftRegistrationNumber(String aircraftRegistrationNumber) {
		this.aircraftRegistrationNumber = aircraftRegistrationNumber;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(name = "AIR_MODEL_IDENTIFIER")
	public AircraftModelIdentifier getM_AircraftModelIdentifier() {
		return m_AircraftModelIdentifier;
	}
	public void setM_AircraftModelIdentifier(
			AircraftModelIdentifier m_AircraftModelIdentifier) {
		this.m_AircraftModelIdentifier = m_AircraftModelIdentifier;
	}


}