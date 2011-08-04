package com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.customsDeclaration.importCustomsDeclaration.importCustomsDeclaration;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Transient;

import com.skynet.spms.datasource.annotation.ViewFormAnno;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.base.baseCustomsDeclaration.baseCustomsDeclarationRecord.BaseCustomsDeclarationRecord;

/**
 * @author FANYX
 * @version 1.0  进口报关单记录
 */
@ViewFormAnno(value="id")
@Entity
@Table(name = "SPMS_IMPORT_CUS_DECLARATION")
public class ImportCustomsDeclaration extends BaseCustomsDeclarationRecord{
    /**
     * 起运国(地区)
     */
    private String countryOfOrigin;
    /**
     * 境内目的地
     */
    private String domesticDestination;
    /**
     * 进口日期
     */
    private Date importDate;
    /**
     * 进口口岸
     */
    private String importPorts;
    /**
     * 装货港
     */
    private String loadingPort;
    /**
	 * 进口报关明细
	 */
    private List<ImportCustomsDeclarationItems> importCustomsDeclarationItems;
	
	public ImportCustomsDeclaration (){
		
	}
	public void finalize() throws Throwable {
		super.finalize();
	}
	
	@Column(name="COUNTRY_OF_ORIGIN")
	public String getCountryOfOrigin() {
		return countryOfOrigin;
	}
	public void setCountryOfOrigin(String countryOfOrigin) {
		this.countryOfOrigin = countryOfOrigin;
	}
	@Column(name="DOMESTIC_DESTINATION")
	public String getDomesticDestination() {
		return domesticDestination;
	}
	public void setDomesticDestination(String domesticDestination) {
		this.domesticDestination = domesticDestination;
	}
	@Column(name="IMPORT_DATE")
	public Date getImportDate() {
		return importDate;
	}
	public void setImportDate(Date importDate) {
		this.importDate = importDate;
	}
	@Column(name="IMPORT_PORTS")
	public String getImportPorts() {
		return importPorts;
	}
	public void setImportPorts(String importPorts) {
		this.importPorts = importPorts;
	}
	@Column(name="LOADING_PORT")
	public String getLoadingPort() {
		return loadingPort;
	}
	public void setLoadingPort(String loadingPort) {
		this.loadingPort = loadingPort;
	}
	@Transient
	public List<ImportCustomsDeclarationItems> getImportCustomsDeclarationItems() {
		return importCustomsDeclarationItems;
	}
	public void setImportCustomsDeclarationItems(List<ImportCustomsDeclarationItems> importCustomsDeclarationItems) {
		this.importCustomsDeclarationItems = importCustomsDeclarationItems;
	}
}
