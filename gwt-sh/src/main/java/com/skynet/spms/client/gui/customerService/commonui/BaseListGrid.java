package com.skynet.spms.client.gui.customerService.commonui;

import com.skynet.spms.client.gui.base.Utils;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

/**
 * 基础表格，子类必须实现drawGrid方法，绑定数据源之后需调用drawGrid方法
 * 
 * @author fl
 * 
 */
public abstract class BaseListGrid extends ListGrid {
	public BaseListGrid() {
		this.setWidth100();
		this.setCellHeight(22);
		this.setHeight100();
		this.setMargin(5);
		this.setAlign(Alignment.CENTER);
		// 设置显示筛选栏
		this.setShowFilterEditor(true);
		this.setShowAllRecords(false);
		this.setFilterOnKeypress(true);
//		this.addFilterEditorSubmitHandler(new FilterEditorSubmitHandler() {
//			
//			@Override
//			public void onFilterEditorSubmit(FilterEditorSubmitEvent event) {
//				Criteria criteria=event.getCriteria();
//				BaseListGrid grid=(BaseListGrid) event.getSource();
//				grid.fetchData(criteria);
//			}
//		});
		
		// 启动可渲染
		this.setShowRecordComponents(true);
		this.setShowRecordComponentsByCell(true);
		this.setShowRowNumbers(true);
		this.setCanEdit(false);
		Utils.setListGridHeight(this);
	}

	/**
	 * 绘制表格，主要是对表列当中的每列进行的定义
	 */
	public abstract void drawGrid();

	/**
	 * 设置所有列可以或不可以筛选
	 * 
	 * @param b
	 */
	protected void setAllFieldCanFilter(boolean b) {
		ListGridField[] fields = this.getAllFields();
		for (ListGridField listGridField : fields) {
			listGridField.setCanFilter(b);
		}
	}
}
