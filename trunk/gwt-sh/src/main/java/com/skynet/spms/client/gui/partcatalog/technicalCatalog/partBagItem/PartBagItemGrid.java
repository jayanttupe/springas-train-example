/**
 * partBagItemGrid.java
 * com.skynet.spms.client.gui.partcatalog.technicalCatalog.partBagItem
 *
 * Description: TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2011-7-25 		Administrator
 *
 * Copyright (c) 2011, TNT All Rights Reserved.
*/

package com.skynet.spms.client.gui.partcatalog.technicalCatalog.partBagItem;
/**
 * Description: TODO
 *
 * @author   Administrator
 * @version  Ver 1.0
 * @Date	 2011-7-25
 *
 */
/*public class partBagItemGrid {
	
package com.skynet.spms.client.gui.partcatalog.technicalCatalog.optionalData;
*/
import java.util.ArrayList;
import java.util.List;

import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class PartBagItemGrid extends ListGrid {

	private DataInfo dataInfo;
	private String partIndexId;
	
	public void setPartIndexId(String partIndexId) {
		this.partIndexId = partIndexId;
	}
	public String getPartIndexId() {
		return partIndexId;
	}
	public DataInfo getDataInfo() {
		return dataInfo;
	} 
	public PartBagItemGrid(final boolean editable) {
		DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource("partCatalog.technical","partBagItem_dataSource", new PostDataSourceInit() {
			
			@Override
			public void doPostOper(DataSource dataSource, DataInfo dataInfo) {
				PartBagItemGrid.this.dataInfo = dataInfo;
				drawPartBagItemListGrid(dataSource,editable);
				
				/*OptionalDataListGrid.this.dataInfo = dataInfo;
				drawOptionalDataListGrid(dataSource,editable);
				*/
			}
		});
	}
	public void drawPartBagItemListGrid(DataSource dataSource,boolean editable) {  
        setDataSource(dataSource);;
		setSelectionType(SelectionStyle.SIMPLE);
		setSelectionAppearance(SelectionAppearance.CHECKBOX);
		setCellHeight(22); 
		setCanEdit(editable);
		if(editable){
			setAutoSaveEdits(false);
		}
		
		final List<ListGridField> fieldList = new ArrayList<ListGridField>();
		
		//组包航材  
	    ListGridField itemPartIndex = new ListGridField("itemPartIndex.partNumber");
	    fieldList.add(itemPartIndex);
         
	    //组包航材所需数量
	    ListGridField requireCount = new ListGridField("requireCount");
	    fieldList.add(requireCount);
     
	    ListGridField[] fields = new ListGridField[fieldList.size()];
        fieldList.toArray(fields);
        setFields(fields);  

    }
}





