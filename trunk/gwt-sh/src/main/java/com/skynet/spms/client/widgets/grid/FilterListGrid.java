package com.skynet.spms.client.widgets.grid;

import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
/**
 * 此Gird用于父Grid,以及所有列可Filter
 * @author Tony FANG
 *
 */
public class FilterListGrid extends ListGrid {

	public FilterListGrid() {
		super();
		//默认可Filter
		this.setShowFilterEditor(true);
		this.setCanEdit(false);
		this.setWidth100();
		this.setMargin(5);
		
		this.setFilterOnKeypress(true);
		
		// 启动可渲染
		this.setShowRecordComponents(true);
		this.setShowRecordComponentsByCell(true);
		this.setShowRowNumbers(true);
	}

	/**
	 * 设置所有Grid列可Filter
	 */
//	@Override
//	public void setFields(ListGridField... fields) {
//		// TODO Auto-generated method stub
//		super.setFields(fields);
//		for (ListGridField listGridField : fields) {
//			listGridField.setCanFilter(true);
//		}
//	}


	
}
