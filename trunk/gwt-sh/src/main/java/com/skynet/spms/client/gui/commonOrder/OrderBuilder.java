package com.skynet.spms.client.gui.commonOrder;

import com.skynet.spms.client.PanelFactory;
import com.skynet.spms.client.gui.commonOrder.delivery.ui.DeliveryOrderPanel;
import com.skynet.spms.client.gui.commonOrder.pickup.ui.PickUpOrderPanel;
import com.smartgwt.client.widgets.Canvas;

/**
 * 通用指令面板构建工具
 * 
 * @author tqc
 * 
 */
public class OrderBuilder {
	

	public static class Factory implements PanelFactory {

		/** 指令类别 1:提货 2:发货 **/
		private String type;

		/** 业务类别 (参考业务类别枚举定义) **/
		private String businessType;
		
		/**模块名**/
		private String modName;

		public Factory(String type, String businessType,String modName) {
			this.type = type;
			this.businessType = businessType;
			this.modName=modName;
		}

		public String getBusinessType() {
			return businessType;
		}

		public void setBusinessType(String businessType) {
			this.businessType = businessType;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}
		

		public String getModName() {
			return modName;
		}

		public void setModName(String modName) {
			this.modName = modName;
		}

		public Canvas create() {
			new InitLocal();//初始化
			if ("2".equals(type)) {
				return new DeliveryOrderPanel(businessType,modName);
			} else {
				return new PickUpOrderPanel(businessType,modName);
			}
		}

		@Override
		public String getID() {
			return null;
		}

		@Override
		public String getDescription() {
			return null;
		}

	}
}
