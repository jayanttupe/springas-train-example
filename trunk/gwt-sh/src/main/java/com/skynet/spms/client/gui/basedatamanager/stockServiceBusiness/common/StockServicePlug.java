package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.common;

import java.util.Map;

import com.skynet.spms.client.PanelFactory;
import com.skynet.spms.client.gui.base.ModulePlug;
import com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.allocationBillBusiness.AllocationBillBusinessPanel;
import com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.base.partEntityBusiness.partLifetimeInformation.PartLifeTimePanel;
import com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.bondedWarehouseBusiness.bondedWarehouseInStock.BondedWarehouseInStockPanel;
import com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.bondedWarehouseBusiness.bondedWarehouseInventory.BondedWarehouseInventoryPanel;
import com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.bondedWarehouseBusiness.bondedWarehouseOutStock.BondedWarehouseOutStockPanel;
import com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.bondedWarehouseBusiness.businessScopeAccountBook.BusinessScopeAccountBookPanel;
import com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.bondedWarehouseBusiness.clearanceAccountBook.ClearanceAccountBookPanel;
import com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.bondedWarehouseBusiness.customsSupervision.bondedWarehouseInStockByIsCustoms.BondedWarehouseInStockByIsCustomsPanel;
import com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.bondedWarehouseBusiness.customsSupervision.bondedWarehouseInventoryByIsCustoms.BondedWarehouseInventoryByIsCustomsPanel;
import com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.bondedWarehouseBusiness.customsSupervision.bondedWarehouseOutStockByIsCustoms.BondedWarehouseOutStockByIsCustomsPanel;
import com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.checkAndAcceptBusiness.checkAndAcceptSheet.CheckAndAcceptSheetPanel;
import com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.checkAndAcceptBusiness.credentials.CredentialsPanel;
import com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.checkAndAcceptBusiness.credentialsRecord.CredentialsRecordPanel;
import com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.checkAndAcceptBusiness.nonconformingReport.NonconformingReportPanel;
import com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.checkAndAcceptBusiness.waitCheckAndAcceptSheet.WaitCheckAndAcceptSheetPanel;
import com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.containerBusiness.ContainerPanel;
import com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.discardServiceBusiness.DiscardServiceBusinessPanel;
import com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.inStockRoomBusiness.inStockRecord.InStockRecordPanel;
import com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.inStockRoomBusiness.inStockRecordManage.InStockRecordManagePanel;
import com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.inStockRoomBusiness.receivingSheet.ReceivingSheetPanel;
import com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.inStockRoomBusiness.reparePartRegister.ReparePartRegisterPanel;
import com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.inStockRoomBusiness.waitReceivingSheet.WaitReceivingSheetPanel;
import com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.outStockRoomBusiness.outStockRoomRecord.OutStockRoomRecordPanel;
import com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.outStockRoomBusiness.packingList.PackingListPanel;
import com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.outStockRoomBusiness.picking.PickingPanel;
import com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.outStockRoomBusiness.pickingList.PickingListPanel;
import com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.outStockRoomBusiness.pickingRecord.PickingRecordPanel;
import com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.outStockRoomBusiness.waitPickingList.WaitPickingPanel;
import com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.partsInventory.freezeRecord.FreezeRecordPanel;
import com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.partsInventory.partsInventoryRecord.PartsInventoryRecordPanel;
import com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.partsInventory.timeControlPartBusiness.TimeControlPartBusinessPanel;
import com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.repairCodeBusiness.RepairCodePanel;
import com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.stockCheckBusiness.stockCheck.StockCheckPanel;
import com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.stockCheckBusiness.stockCheckResult.StockCheckResultPanel;
import com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.stockCheckBusiness.stockCheckResultTrack.StockCheckResultTrackPanel;
import com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.stockManageTool.stockPolicy.StockPolicyPanel;
import com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.stockMoveBusiness.stockMoveApply.StockMoveApplyPanel;
import com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.stockMoveBusiness.stockMoveApproval.StockMoveApprovalPanel;
import com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.stockMoveBusiness.stockMoveIn.StockMoveInRecordPanel;
import com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.stockMoveBusiness.stockMoveOut.StockMoveOutRecordPanel;
import com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.stockTask.packingTask.PackingTaskPanel;
import com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.stockTask.pickingTask.PickingTaskPanel;
import com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.stockTask.repairCodeTask.RepairCodeTaskPanel;
import com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.stockTask.sendCardTask.SendCardTaskPanel;
import com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.stockTask.shelvingTask.ShelvingTaskPanel;
import com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.stockTask.stockCheckTask.StockCheckTaskPanel;
import com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.warehouseManageBusiness.cargoSpaceManage.CargoSpaceManagerPanel;
import com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.warehouseManageBusiness.stockRoomManage.StockRoomPanel;
import com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.warehouseManageBusiness.storageRacks.StorageRacksPanel;

/**
 * 仓储模块加载器
 * 
 * @author tqc
 * 
 */
public class StockServicePlug implements ModulePlug {

	public void plug(Map<String, PanelFactory> map) {
		// 库房管理
		map.put("stockServiceBusiness.basicData.stockRoom"
						, new StockRoomPanel.Factory());
		// 货位管理
		map.put("stockServiceBusiness.basicData.cargoSpace"
						, new CargoSpaceManagerPanel.Factory());
		// 货架管理
		map.put("stockServiceBusiness.basicData.storageRacks"
						, new StorageRacksPanel.Factory());
		// 库存策略管理
		map.put("stockServiceBusiness.basicData.stockPolicy"
						, new StockPolicyPanel.Factory());
		// 货位策略管理
		//map.put("stockServiceBusiness.basicData.cargoSpaceManagePolicy"
		//				, new CargoSpaceManagePolicyPanel.Factory());
		// 容器管理
		map.put("stockServiceBusiness.basicData.container"
						, new ContainerPanel.Factory());
		// 补码管理
		map.put("stockServiceBusiness.basicData.repairCode"
						, new RepairCodePanel.Factory());
		// 航材收料入库
		// 待收料航材
		map.put("stockServiceBusiness.inStockRoomBusiness.waitReceivingSheet"
		        		,new WaitReceivingSheetPanel.Factory());
		// 航材收料单
		map.put("stockServiceBusiness.inStockRoomBusiness.receivingSheet"
						, new ReceivingSheetPanel.Factory());
		// 合格待入库
		map.put("stockServiceBusiness.inStockRoomBusiness.inStockRecord"
						, new InStockRecordPanel.Factory());
		// 入库记录管理
		map.put("stockServiceBusiness.inStockRoomBusiness.inStockRecordManage"
						, new InStockRecordManagePanel.Factory());
		// 送修件登记
		map.put("stockServiceBusiness.inStockRoomBusiness.reparePartRegister"
						, new ReparePartRegisterPanel.Factory());
		// 航材检验管理
		// 航材检验单
		map.put("stockServiceBusiness.checkAndAcceptBusiness.checkAndAcceptSheet"
						, new CheckAndAcceptSheetPanel.Factory());
		//待检验航材
		map.put("stockServiceBusiness.checkAndAcceptBusiness.waitCheckAndAcceptSheet"
				, new WaitCheckAndAcceptSheetPanel.Factory());
		//航材证书管理
		map.put("stockServiceBusiness.checkAndAcceptBusiness.credentialsRecord", 
				new CredentialsRecordPanel.Factory());
		// 不合格品登记记录
		map.put("stockServiceBusiness.checkAndAcceptBusiness.nonconformingReport"
						, new NonconformingReportPanel.Factory());
		// 证书存档位置管理
		map.put("stockServiceBusiness.checkAndAcceptBusiness.credentials"
						, new CredentialsPanel.Factory());
		// 航材发料出库
		// 待发料航材
		map.put("stockServiceBusiness.outStockRoomBusiness.waitDeliveryOrder"
						, new WaitPickingPanel.Factory());
		// 航材发料单
		map.put("stockServiceBusiness.outStockRoomBusiness.pickingList"
						, new PickingListPanel.Factory());
		// 拣货指令管理
		map.put("stockServiceBusiness.outStockRoomBusiness.picking"
						, new PickingPanel.Factory());
		// 已拣货记录管理
		map.put("stockServiceBusiness.outStockRoomBusiness.pickingRecord"
						, new PickingRecordPanel.Factory());
		//出库记录管理
		map.put("stockServiceBusiness.outStockRoomBusiness.outStockRoomRecord", 
				new OutStockRoomRecordPanel.Factory());
		// 装箱管理				
		map.put("stockServiceBusiness.outStockRoomBusiness.packingList"        		
						, new PackingListPanel.Factory());
		//出库记录管理
		map.put("stockServiceBusiness.outStockRoomBusiness.outStockRoomRecord", 
				new OutStockRoomRecordPanel.Factory());
		// 航材库存日常业务				
		// 备件库存查询
		map.put("stockServiceBusiness.partsInventory.partsInventoryRecord"
						, new PartsInventoryRecordPanel.Factory()); 
		// 库存盘点管理
		 // 盘点项目管理
		map.put("stockServiceBusiness.partsInventory.stockCheckBusiness.stockCheckManager"
						, new StockCheckPanel.Factory());
		// 盘点结果管理
		map.put("stockServiceBusiness.partsInventory.stockCheckBusiness.stockCheckResult"
						, new StockCheckResultPanel.Factory());
		// 盘点结果跟踪
		map.put("stockServiceBusiness.partsInventory.stockCheckBusiness.stockCheckResultTrack"
						, new StockCheckResultTrackPanel.Factory());
		//移库申请
		map.put("stockServiceBusiness.partsInventory.stockMoveBusiness.stockMoveApply"
				, new StockMoveApplyPanel.Factory());
		//移库审批
		map.put("stockServiceBusiness.partsInventory.stockMoveBusiness.stockMoveApproval"
				, new StockMoveApprovalPanel.Factory());
		// 航材移出记录管理				
		map.put("stockServiceBusiness.partsInventory.stockMoveBusiness.stockMoveOutRecord"
						, new StockMoveOutRecordPanel.Factory());
		// 航材移入记录管理				
		map.put("stockServiceBusiness.partsInventory.stockMoveBusiness.stockMoveInRecord"
						, new StockMoveInRecordPanel.Factory());
		// 库存冻结管理				
		map.put("stockServiceBusiness.partsInventory.freezeRecord"
						, new FreezeRecordPanel.Factory());
		// 库存时控件管理
		map.put("stockServiceBusiness.partsInventory.timeControlPartBusiness"
						, new TimeControlPartBusinessPanel.Factory());
		// 库存寿命件管理
		map.put("stockServiceBusiness.partsInventory.partLifeTime"
						, new PartLifeTimePanel.Factory());
		// 航材报废管理
		map.put("stockServiceBusiness.partsInventory.discardServiceBusiness"
						, new DiscardServiceBusinessPanel.Factory());
		// 航材调拨管理
		map.put("stockServiceBusiness.partsInventory.allocationBillBusiness"
						, new AllocationBillBusinessPanel.Factory());
		// 任务管理
		// 发卡任务管理
		map.put("stockServiceBusiness.stockTask.sendCardTask"
						, new SendCardTaskPanel.Factory());
		// 上架任务管理
		map.put("stockServiceBusiness.stockTask.shelvingTask"
						, new ShelvingTaskPanel.Factory());
		// 拣货任务管理
		map.put("stockServiceBusiness.stockTask.pickingTask"
						, new PickingTaskPanel.Factory());
		// 装箱任务管理
		map.put("stockServiceBusiness.stockTask.packingTask"
						, new PackingTaskPanel.Factory());
		// 盘点任务管理
		map.put("stockServiceBusiness.stockTask.stockCheckTask"
						, new StockCheckTaskPanel.Factory());
		// 补码任务管理
		map.put("stockServiceBusiness.stockTask.repairCodeTask"
						, new RepairCodeTaskPanel.Factory());
		
		// 保税库业务
		// 经营范围电子账册
		map.put("stockServiceBusiness.bondedWarehouseBusiness.businessScopeAccountBook"        		
						, new BusinessScopeAccountBookPanel.Factory());
		// 通关电子账册
		map.put("stockServiceBusiness.bondedWarehouseBusiness.clearanceAccountBook"
		        		,new ClearanceAccountBookPanel.Factory());
		// 保税库入库记录
		map.put("stockServiceBusiness.bondedWarehouseBusiness.bondedWarehouseInStock"
		        		,new BondedWarehouseInStockPanel.Factory());
		// 保税库出库记录
		map.put("stockServiceBusiness.bondedWarehouseBusiness.bondedWarehouseOutStock"
		        		,new BondedWarehouseOutStockPanel.Factory());
		// 保税库在库记录
		map.put("stockServiceBusiness.bondedWarehouseBusiness.bondedWarehouseInventoryStock"
		        		,new BondedWarehouseInventoryPanel.Factory());
		// 海关监管
		// 海保税库入库记录
		map.put("stockServiceBusiness.bondedWarehouseBusiness.customsSupervision.bondedWarehouseInStockByIsCustoms"
		        		,new BondedWarehouseInStockByIsCustomsPanel.Factory());
		// 海保税库出库记录
		map.put("stockServiceBusiness.bondedWarehouseBusiness.customsSupervision.bondedWarehouseOutStockByIsCustoms"
		        		,new BondedWarehouseOutStockByIsCustomsPanel.Factory());
		// 海保税库在库记录
		map.put("stockServiceBusiness.bondedWarehouseBusiness.customsSupervision.bondedWarehouseInventoryStockByIsCustoms"
		        		,new BondedWarehouseInventoryByIsCustomsPanel.Factory());
	}
}
