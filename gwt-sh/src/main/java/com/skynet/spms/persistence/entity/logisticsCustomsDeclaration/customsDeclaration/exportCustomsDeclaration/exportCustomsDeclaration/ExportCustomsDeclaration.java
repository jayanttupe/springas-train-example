/**
 * 
 */
package com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.customsDeclaration.exportCustomsDeclaration.exportCustomsDeclaration;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.skynet.spms.datasource.annotation.ViewFormAnno;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.base.baseCustomsDeclaration.baseCustomsDeclarationRecord.BaseCustomsDeclarationRecord;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.customsDeclaration.importCustomsDeclaration.importCustomsDeclaration.ImportCustomsDeclarationItems;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.pickupDeliveryOrder.PickupDeliveryOrder;

/**
 * @author FANYX
 * @version 1.0  出口报关单记录
 */
@ViewFormAnno(value="id")
@Entity
@Table(name = "SPMS_EXPORT_CUS_DECLARATION")
public class ExportCustomsDeclaration extends BaseCustomsDeclarationRecord{

	/**
	 * 运抵国
	 */
	private String countryOfArrived;
	/**
	 * 境内货源地 
	 */
	private String domesticSupplyOfGoods;
	/**
	 * 出口日期
	 */
	private Date exportDate;
	/**
	 * 出口口岸
	 */
	private String exportPort;
	/**
	 * 生产厂家
	 */
	private String manufacturer;
	/**
	 * 发货单位
	 */
	private String sendOutGoodsUnit;
	/**
	 * 结汇方式
	 */
	private String settlementWay;
	/**
	 * 卸货港
	 */
	private String unloadingPort;
	/**
	 * 出口报关明细
	 */
	private List<ExportCustomsDeclarationItems> exportCustomsDeclarationItems;
	
	@Column(name="COUNTRY_OF_ARRIVED")
	public String getCountryOfArrived() {
		return countryOfArrived;
	}
	public void setCountryOfArrived(String countryOfArrived) {
		this.countryOfArrived = countryOfArrived;
	}
	@Column(name="DOMESTIC_SUPPLY_OF_GOODS")
	public String getDomesticSupplyOfGoods() {
		return domesticSupplyOfGoods;
	}
	public void setDomesticSupplyOfGoods(String domesticSupplyOfGoods) {
		this.domesticSupplyOfGoods = domesticSupplyOfGoods;
	}
	@Column(name="EXPORT_DATE")
	public Date getExportDate() {
		return exportDate;
	}
	public void setExportDate(Date exportDate) {
		this.exportDate = exportDate;
	}
	@Column(name="EXPORT_PORT")
	public String getExportPort() {
		return exportPort;
	}
	public void setExportPort(String exportPort) {
		this.exportPort = exportPort;
	}
	@Column(name="MANUFACTURER")
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	@Column(name="SEND_OUT_GOODS_UNIT")
	public String getSendOutGoodsUnit() {
		return sendOutGoodsUnit;
	}
	public void setSendOutGoodsUnit(String sendOutGoodsUnit) {
		this.sendOutGoodsUnit = sendOutGoodsUnit;
	}
	@Column(name="SETTLEMENT_WAY")
	public String getSettlementWay() {
		return settlementWay;
	}
	public void setSettlementWay(String settlementWay) {
		this.settlementWay = settlementWay;
	}
	@Column(name="UNLOADING_PORT")
	public String getUnloadingPort() {
		return unloadingPort;
	}
	public void setUnloadingPort(String unloadingPort) {
		this.unloadingPort = unloadingPort;
	}
	@Transient
	public List<ExportCustomsDeclarationItems> getExportCustomsDeclarationItems() {
		return exportCustomsDeclarationItems;
	}
	public void setExportCustomsDeclarationItems(
			List<ExportCustomsDeclarationItems> exportCustomsDeclarationItems) {
		this.exportCustomsDeclarationItems = exportCustomsDeclarationItems;
	}
}
