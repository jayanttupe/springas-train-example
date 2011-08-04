package com.skynet.spms.persistence.entity.stockServiceBusiness.base.tagEntity.barCodeEntity.base1DBarCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.skynet.spms.datasource.annotation.ViewFormAnno;

/**
 * 一维条码采用Code 128
 * 采用定长方式设计
 * 
 * 
 * 条码编码内容由以下业务属性组成（参见证书存档管理实体域模型）
 * <ol>
 * 	<li>备件中心位置</li>
 * 	<li>证书存放位置代码</li>
 * 	<li>证书存档编号</li>
 * </ol>
 * @author FANYX
 * @version 1.0
 * @created 13-六月-2011 11:08:57
 */
@ViewFormAnno(value="id")
@Entity
@Table(name = "SPMS_CREDENTIAL_1DBARCODE")
public class Credential1DBarCode extends Base1DBarCode {

	/**
	 * 打印出来的证书条形码
	 */
	private String credentialsCodeID;

	@Column(name = "CREDENTIALS_CODE_ID")
	public String getCredentialsCodeID() {
		return credentialsCodeID;
	}

	public void setCredentialsCodeID(String credentialsCodeID) {
		this.credentialsCodeID = credentialsCodeID;
	}	
}