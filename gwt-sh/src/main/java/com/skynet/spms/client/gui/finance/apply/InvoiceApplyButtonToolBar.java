package com.skynet.spms.client.gui.finance.apply;

import com.skynet.spms.client.gui.base.BaseButtonToolStript;
import com.skynet.spms.client.gui.basedatamanager.organization.user.TestWindow;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

public class InvoiceApplyButtonToolBar extends BaseButtonToolStript {

	private ListGrid listGrid;
	
	public InvoiceApplyButtonToolBar(ListGrid listGrid) {
		// TODO Auto-generated constructor stub
		
		super("account.applyManager.invoiceApplyManager");
		this.listGrid = listGrid;
	}

	@Override
	protected void showWindow(String buttonName, ToolStripButton button) {
		// TODO Auto-generated method stub
		Rectangle srcRect = null;
		if (button != null)
			srcRect = button.getPageRect();
		if ("add".equalsIgnoreCase(buttonName)) {
			useWindow = new InvoiceApplyAddWindow("新建开票申请书", false,srcRect, listGrid, "showwindow/finance_add_01.png");
		} else if ("modify".equalsIgnoreCase(buttonName)) {
			if (listGrid.getSelection().length == 1) {
				Record[] rdd = listGrid.getSelection();
				for(Record rd:rdd){
					if(rd.getAttribute("check").equals("1"))
					{
						SC.warn("记录审核通过，不允许修改！");
						return;
					}	
				}
				 useWindow = new InvoiceApplyModifyWindow("修改开票申请书", false,srcRect, listGrid, "showwindow/finance_modify_01.png");
			} else {
				SC.warn("请选择一条记录进行修改！");
				return;
			}

		} else if ("check".equalsIgnoreCase(buttonName)) {
			if (listGrid.getSelection().length == 1) {
				Record[] rdd = listGrid.getSelection();
				for(Record rd:rdd){
					if(!rd.getAttribute("check").equals("0")&&!rd.getAttribute("check").equals("3"))
					{
						SC.warn("记录审核通过，不允许重复审核！");
						return;
					}	
					if(rd.getAttribute("check").equals("0")){
						SC.warn("记录未提交审核！");
						return;
					}
					
				}
				 useWindow = new CheckInvoiceApplyWindow("审核开票申请书", false,srcRect, listGrid, "showwindow/finance_modify_01.png");
			} else {
				SC.warn("请选择一条记录进行查看！");
				return;
			}
		} else if ("delete".equalsIgnoreCase(buttonName)) {
			if (listGrid.getSelection().length != 0) {
				Record[] rdd = listGrid.getSelection();
				for(Record rd:rdd){
					if(!rd.getAttribute("check").equals("0")&&!rd.getAttribute("check").equals("3"))
					{
						SC.warn("有记录审核过，不允许删除！");
						return;
					}	
				
				}
			SC.confirm("删除确认", "是否要删除选中的记录！", new BooleanCallback() {
					
					@Override
					public void execute(Boolean value) {
						// TODO Auto-generated method stub
						if (value) {
							listGrid.removeSelectedData();
						}
						return;
					}
				});
			}else{
				SC.warn("请选择要删除的记录！");
				return;
			}
		}	else if("commitcheck".equalsIgnoreCase(buttonName)){
			Record record = listGrid.getSelectedRecord();
			if(record.getAttribute("check").equals("0")){
				record.setAttribute("check", "3");
				listGrid.updateData(record);
			
			}
			else{
				SC.warn("记录已经提交审核或已审核，不能重复提交审核！");
			}
			return;
		} 
		else {
			useWindow = new TestWindow("TEST", true, srcRect, listGrid,
					"icons/add.gif");
		}
		if (button != null&&!"delete".equalsIgnoreCase(buttonName))
			ShowWindowTools.showWondow(srcRect, useWindow, -1);

	}

}
