package com.skynet.spms.persistence.entity.financeService.purposeVoucher;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.FilterDefs;
import org.hibernate.annotations.ParamDef;

import com.skynet.spms.datasource.annotation.ViewFormAnno;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseIDEntity;



/**
 * @author 黄赟
 * @version 1.0
 * @created 21-四月-2011 19:57:55
 */
@ViewFormAnno(value="id")
@Entity
@Table(name="SPMS_SUBJECTS")
public class Subjects extends BaseIDEntity{
	
	private String subjectCode;
	
	private String subjectName;

	@Column(name="SUBJECT_CODE")
	public String getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}

	@Column(name="SUBJECT_NAME")
	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	
	
	
	
	

}