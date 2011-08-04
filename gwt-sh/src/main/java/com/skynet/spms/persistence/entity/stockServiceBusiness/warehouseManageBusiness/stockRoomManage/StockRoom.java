package com.skynet.spms.persistence.entity.stockServiceBusiness.warehouseManageBusiness.stockRoomManage;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.FilterDefs;
import org.hibernate.annotations.ParamDef;

import com.skynet.spms.datasource.annotation.ViewFormAnno;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.stockServiceBusiness.spmsdd.StockRoomFloorCode;
import com.skynet.spms.persistence.entity.stockServiceBusiness.spmsdd.StockRoomType;
import com.skynet.spms.persistence.entity.stockServiceBusiness.warehouseManageBusiness.logicStockManage.LogicStock;

/**
 * @author 黄帝君
 * @version 1.1
 * @created 2011-3-30
 */
@ViewFormAnno(value="id")
@Entity
@FilterDefs({
	@FilterDef(name="active",
			defaultCondition="IS_DELETED = :isDele",
			parameters=@ParamDef(name="isDele",type="boolean"))		
})
@Filter(name="active")
@Table(name = "SPMS_STOCK_ROOM")
public class StockRoom extends BaseEntity {
	
	/**
	 * 库房编号
	 */
	private String stockRoomNumber;

	/**
	 * 备件中心位置代码
	 */
	private String partCentreLocation;

	/**
	 * 库房中文名称
	 */
	private String stockRoomChineseName;

	/**
	 * 库房英文名称
	 */
	private String stockRoomEngilshName;

	/**
	 * 库房类型
	 */
	private StockRoomType stockRoomType;
	/**
	 * 库房楼层
	 */
    private StockRoomFloorCode stockRoomFloor;
	/**
	 * 库房地址
	 */
	private String address;

	/**
	 * 电话
	 */
	private String telephone;

	/**
	 * 邮编
	 */
	private String postCode;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 库房区域
	 */
	private List<StockArea> stockArea;

	/**
	 * 逻辑库
	 */
	private List<LogicStock> logicStock;

	public StockRoom() {

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

	@Column(name = "STOCK_ROOM_NUMBER")
	public String getStockRoomNumber() {
		return stockRoomNumber;
	}

	public void setStockRoomNumber(String stockRoomNumber) {
		this.stockRoomNumber = stockRoomNumber;
	}

	@Column(name = "PART_CENTRE_LOCATION")
	public String getPartCentreLocation() {
		return partCentreLocation;
	}

	public void setPartCentreLocation(String partCentreLocation) {
		this.partCentreLocation = partCentreLocation;
	}
	
	@Column(name = "STOCK_ROOM_CHINESE_NAME")
	public String getStockRoomChineseName() {
		return stockRoomChineseName;
	}

	public void setStockRoomChineseName(String stockRoomChineseName) {
		this.stockRoomChineseName = stockRoomChineseName;
	}

	@Column(name = "STOCK_ROOM_ENGILSH_NAME")
	public String getStockRoomEngilshName() {
		return stockRoomEngilshName;
	}

	public void setStockRoomEngilshName(String stockRoomEngilshName) {
		this.stockRoomEngilshName = stockRoomEngilshName;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "STOCK_ROOM_TYPE")
	public StockRoomType getStockRoomType() {
		return stockRoomType;
	}

	public void setStockRoomType(StockRoomType stockRoomType) {
		this.stockRoomType = stockRoomType;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(name = "STOCK_ROOM_FLOOR")
	public StockRoomFloorCode getStockRoomFloor() {
		return stockRoomFloor;
	}

	public void setStockRoomFloor(StockRoomFloorCode stockRoomFloor) {
		this.stockRoomFloor = stockRoomFloor;
	}

	@Column(name = "ADDRESS")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "TELEPHONE")
	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@Column(name = "POST_CODE")
	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	
	@Column(name = "REMARK")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@OneToMany(targetEntity= StockArea.class,cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="STOCK_ROOM_ID")
	public List<StockArea> getStockArea() {
		return stockArea;
	}

	public void setStockArea(List<StockArea> stockArea) {
		this.stockArea = stockArea;
	}

	@OneToMany(targetEntity= LogicStock.class,cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="STOCK_ROOM_ID")
	public List<LogicStock> getLogicStock() {
		return logicStock;
	}

	public void setLogicStock(List<LogicStock> logicStock) {
		this.logicStock = logicStock;
	}
}