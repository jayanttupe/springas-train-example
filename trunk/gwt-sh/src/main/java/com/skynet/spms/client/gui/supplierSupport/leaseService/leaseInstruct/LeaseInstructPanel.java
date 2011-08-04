package com.skynet.spms.client.gui.supplierSupport.leaseService.leaseInstruct;

import com.skynet.spms.client.PanelFactory;
import com.skynet.spms.client.ShowcasePanel;
import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.i18n.I18n;
import com.skynet.spms.client.gui.customerService.common.DSKey;
import com.skynet.spms.client.gui.supplierSupport.leaseService.model.SSMainModelLocator;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;
/**
 * 申请租赁指令
 * Description TODO
 *
 * @author   Tony FANG
 * @version  
 * @Date	 2011	2011-7-21
 */
public class LeaseInstructPanel extends ShowcasePanel {
	private static I18n ui = new I18n();
	private LeaseInstructListGrid leaseInstructListGrid;
	private SSMainModelLocator model = SSMainModelLocator.getInstance();

	public static class Factory implements PanelFactory {
		private String DESCRIPTION = ui.getM().mod_LeaseInstruct_name();
		private String id;

		public Canvas create() {
			LeaseInstructPanel panel = new LeaseInstructPanel();
			id = panel.getID();
			return panel;
		}

		public String getID() {
			return id;
		}

		public String getDescription() {
			return DESCRIPTION;
		}
	}

	@Override
	public Canvas getViewPanel() {
		leaseInstructListGrid = new LeaseInstructListGrid();
		// 加载菜单栏
		VLayout v = new VLayout();
		LeaseInstructToolStrip ts = new LeaseInstructToolStrip(
				leaseInstructListGrid);
		HLayout h = new HLayout();
		h.setHeight(20);
		h.addMember(ts);
		// 创建住容器
		SectionStack s = new SectionStack();
		SectionStackSection s1 = new SectionStackSection(ui.getM()
				.mod_requisitionLeaseInstruct_name());
		s1.setExpanded(true);

		final DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(DSKey.C_LEASEINSTRUCT,
				DSKey.C_LEASEINSTRUCT_DS, new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						leaseInstructListGrid.setDataSource(dataSource);
						Criteria criteria = new Criteria();
						criteria.addCriteria("publishState", "publish");
						leaseInstructListGrid.fetchData(criteria);
						leaseInstructListGrid.drawGrid();
						model.leaseInstructListGrid = leaseInstructListGrid;
					}
				});
		dataSourceTool.onCreateDataSource(DSKey.C_LEASECONTRACT_ITEM,
				DSKey.C_LEASECONTRACT_TIEM_DS, new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						model.leaseContractDs = dataSource;
					}
				});
		s1.addItem(leaseInstructListGrid);
		s.addSection(s1);
		v.addMember(h);
		v.addMember(s);
		return v;
	}
}
