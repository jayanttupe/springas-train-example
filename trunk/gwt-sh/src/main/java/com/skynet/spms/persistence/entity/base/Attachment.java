package com.skynet.spms.persistence.entity.base;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.FilterDefs;
import org.hibernate.annotations.ParamDef;

import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseIDEntity;

/**
 * 附件文件是一种可以在业务实体上以附件文件形式出现的实体，附件文件可以是各种类型的电子文件，不过电子文件的大小应该限定在每个文件10M以下，这样有利于文件的传输及
 * 管理。
 * @author 曹宏炜
 * @version 1.0
 * @created 10-三月-2011 11:10:32
 */

/*************************************************************************
 *Update by : Huhf    2011-4-27
 *CHECKED BY: Shanyf  2011-4-27
 *Confirm by: 
 *Update list:
 * 
************************************************************************ */
@Entity
@FilterDefs({
	@FilterDef(name="active",
			defaultCondition="IS_DELETED = :isDele",
			parameters=@ParamDef(name="isDele",type="boolean"))		
})
@Filter(name="active")
@Table(name="SPMS_ATTACHMENT")
public class Attachment extends BaseEntity{

	/**
	 * 项号用于对附件文件显示的排序
	 */
	private String title;
	private String fileName;
	private String fileType;
	private String description;
	private int fileSize;
	private Date modifyDate;
	private String operator;
	private String relatedBussinessId;
	private int itemNumber;
	
	/**业务编号外键**/
	private String uuid;
	
	@Column(name="BUSSINESS_UUID")
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	@Column(name="ITEM_NUMBER")
	public int getItemNumber() {
		return itemNumber;
	}
	public void setItemNumber(int itemNumber) {
		this.itemNumber = itemNumber;
	}
	@Column(name="TITLE")
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Column(name="FILE_NAME")
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	@Column(name="FILE_TYPE")
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	@Column(name="DESCRIPTION")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name="FILE_SIZE")
	public int getFileSize() {
		return fileSize;
	}
	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="MODIFY_DATE")
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	@Column(name="OPERATOR")
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	@Column(name="RELATED_BUSSINESS_ID")
	public String getRelatedBussinessId() {
		return relatedBussinessId;
	}
	public void setRelatedBussinessId(String relatedBussinessId) {
		this.relatedBussinessId = relatedBussinessId;
	}
	
	@Transient
	public Map<String,Object> getJsonFileInfo(){

		Map<String,Object> fileInfo=new HashMap<String,Object>();

		fileInfo.put("fileName", fileName);
		fileInfo.put("index", getId());
		fileInfo.put("size", new Long(fileSize));
		fileInfo.put("type", fileType);
		try {
			fileInfo.put("fileNameUrl", URLEncoder.encode(fileName, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return fileInfo;
	}
}