package com.skynet.spms.client.gui.partcatalog.common;

import java.util.Map;

import com.skynet.spms.client.PanelFactory;
import com.skynet.spms.client.gui.base.ModulePlug;
import com.skynet.spms.client.gui.basedatamanager.organization.department.DepartmentInfoPanel;
import com.skynet.spms.client.gui.partcatalog.aircraftConfigCatalog.AircraftConfigCatalogPanel;
import com.skynet.spms.client.gui.partcatalog.repairableCatalog.RepairableCatalogPanel;
import com.skynet.spms.client.gui.partcatalog.salesCatalog.PartSaleRelease.SalesCatalogPanel;
import com.skynet.spms.client.gui.partcatalog.supplierPriceCatalog.partSupplierPrice.PartSupplierPricePanel;
import com.skynet.spms.client.gui.partcatalog.technicalCatalog.indexInfomation.IndexInfoPanel;
import com.skynet.spms.client.gui.partcatalog.technicalDocuments.TechnicalDocumentPanel;

/**
 * @author 曹宏炜
 * 技术目录映射插件类
 */
public class PartcatalogPlugin implements ModulePlug {

	@Override
	public void plug(Map<String, PanelFactory> map) {
		map.put("partCatalog.technical", new IndexInfoPanel.Factory());


		//map.put("partCatalog.supplierPrice", new PartSupplierPricePanel.Factory());

		map.put("partCatalog.supplierPrice", new PartSupplierPricePanel.Factory());

		map.put("partCatalog.sales", new SalesCatalogPanel.Factory());
		map.put("partCatalog.aircraftConfig", new AircraftConfigCatalogPanel.Factory());
		map.put("partCatalog.repairable", new RepairableCatalogPanel.Factory());
		map.put("partCatalog.technicalDocuments", new TechnicalDocumentPanel.Factory());
				
		map.put("organization.sales", new DepartmentInfoPanel.Factory());
	}

}
