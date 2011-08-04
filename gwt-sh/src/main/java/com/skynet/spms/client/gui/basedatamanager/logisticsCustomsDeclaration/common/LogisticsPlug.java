package com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.common;

import java.util.Map;

import com.skynet.spms.client.PanelFactory;
import com.skynet.spms.client.gui.base.ModulePlug;
import com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.customsDeclaration.exportCustomsDeclaration.exportCustomsDeclaration.ExportCustomsDeclarationPanel;
import com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.customsDeclaration.exportCustomsDeclaration.exportCustomsTariffRecord.ExportCustomsTariffRecordPanel;
import com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.customsDeclaration.exportCustomsDeclaration.exportSecurityDeposit.ExportSecurityDepositPanel;
import com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.customsDeclaration.importCustomsDeclaration.importCustomsDeclaration.ImportCustomsDeclarationPanel;
import com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.customsDeclaration.importCustomsDeclaration.importCustomsTariffRecord.ImportCustomsTariffRecordPanel;
import com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.customsDeclaration.importCustomsDeclaration.importSecurityDeposit.ImportSecurityDepositPanel;
import com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.dispatchLogisticsTask.arrivalOfGoodsJob.ArrivalOfGoodsJobPanel;
import com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.dispatchLogisticsTask.bookingJob.BookingJobPanel;
import com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.dispatchLogisticsTask.deliverTheGoodsJob.DeliverTheGoodsJobPanel;
import com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.dispatchLogisticsTask.dispatchTask.DeliveryDispatchTaskPanel;
import com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.dispatchLogisticsTask.dispatchTask.PickupDispatchTaskPanel;
import com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.dispatchLogisticsTask.shippingJob.ShippingJobPanel;
import com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.dispatchLogisticsTask.takeDeliveryOfJob.TakeDeliveryOfJobPanel;
import com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.documentRecords.DocumentRecordsPanel;
import com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.logisticsOutlayRecordManage.LogisticsOutlayRecordPanel;
import com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.pickupDeliveryOrder.deliveryOrder.DeliveryOrderPanel;
import com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.pickupDeliveryOrder.pickupOrder.PickupOrderPanel;

/**
 * 物流模块加载器
 * 
 * @author tqc
 * 
 */
public class LogisticsPlug implements ModulePlug {

	public void plug(Map<String, PanelFactory> map) {
        // 处理提货指令
		map.put("logisticsCustomsDeclaration.pickupDeliveryBusiness.pickupOrder", 
        		new PickupOrderPanel.Factory());
		// 处理发货指令
		map.put("logisticsCustomsDeclaration.pickupDeliveryBusiness.deliveryOrder", 
        		new DeliveryOrderPanel.Factory());
        // 提货任务分派管理
        map.put("logisticsCustomsDeclaration.dispatchLogisticsTask.pickupDispatchTask", 
        		new PickupDispatchTaskPanel.Factory());
        // 发货任务分派管理
        map.put("logisticsCustomsDeclaration.dispatchLogisticsTask.deliveryDispatchTask", 
        		new DeliveryDispatchTaskPanel.Factory());
        // 订舱工作
        map.put("logisticsCustomsDeclaration.dispatchLogisticsTask.bookingJob", 
        		new BookingJobPanel.Factory());
        // 取货工作
        map.put("logisticsCustomsDeclaration.dispatchLogisticsTask.takeDeliveryOfJob", 
        		new TakeDeliveryOfJobPanel.Factory());
        // 起运工作
        map.put("logisticsCustomsDeclaration.dispatchLogisticsTask.shippingJob", 
        		new ShippingJobPanel.Factory());
        // 到达工作
        map.put("logisticsCustomsDeclaration.dispatchLogisticsTask.arrivalOfGoodsJob", 
        		new ArrivalOfGoodsJobPanel.Factory());
        // 交货工作
        map.put("logisticsCustomsDeclaration.dispatchLogisticsTask.deliverTheGoodsJob", 
        		new DeliverTheGoodsJobPanel.Factory());
        // 报关业务
        // 进口报关单记录
        map.put("logisticsCustomsDeclaration.dispatchLogisticsTask.customsClearanceJob.importCustomsDeclaration.importCustomsDeclaration",
        		new ImportCustomsDeclarationPanel.Factory());
        // 进口报关保证金记录
        map.put("logisticsCustomsDeclaration.dispatchLogisticsTask.customsClearanceJob.importCustomsDeclaration.importSecurityDeposit", 
        		new ImportSecurityDepositPanel.Factory());
        // 进口报关关税记录
        map.put("logisticsCustomsDeclaration.dispatchLogisticsTask.customsClearanceJob.importCustomsDeclaration.importCustomsTariffRecord", 
        		new ImportCustomsTariffRecordPanel.Factory());
        // 出口报关单记录
        map.put("logisticsCustomsDeclaration.dispatchLogisticsTask.customsClearanceJob.exportCustomsDeclaration.exportCustomsDeclaration",
        		new ExportCustomsDeclarationPanel.Factory());
        // 出口报关保证金记录
        map.put("logisticsCustomsDeclaration.dispatchLogisticsTask.customsClearanceJob.exportCustomsDeclaration.exportSecurityDeposit", 
        		new ExportSecurityDepositPanel.Factory());
        // 出口报关关税记录
        map.put("logisticsCustomsDeclaration.dispatchLogisticsTask.customsClearanceJob.exportCustomsDeclaration.exportCustomsTariffRecord", 
        		new ExportCustomsTariffRecordPanel.Factory());
        
        // 单证记录管理
        map.put("logisticsCustomsDeclaration.documentRecords", 
        		new DocumentRecordsPanel.Factory());
        // 费用记录管理
        map.put("logisticsCustomsDeclaration.logisticsOutlayRecordManage", 
        		new LogisticsOutlayRecordPanel.Factory());
	}
}
