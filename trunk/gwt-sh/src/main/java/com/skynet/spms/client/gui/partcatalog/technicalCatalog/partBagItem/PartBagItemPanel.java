/**
 * PartBagItemPanel.java
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
	import java.util.LinkedHashMap;
import java.util.Map;

import com.skynet.spms.client.constants.ConstantsUtil;
import com.skynet.spms.client.gui.base.BtnsHLayout;
import com.skynet.spms.client.gui.base.CustomSelectItem;
import com.smartgwt.client.core.Function;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
	
	public class PartBagItemPanel extends VLayout {
		
		private PartBagItemGrid partBagItemGrid;
		private HLayout buttonPanel;
		
		public PartBagItemPanel(final boolean editable) {
			
			partBagItemGrid = new PartBagItemGrid(editable);
			this.addMember(partBagItemGrid);
			
			partBagItemGrid.addRecordClickHandler(new RecordClickHandler() {	
				@Override
				public void onRecordClick(RecordClickEvent event) {
							
				}
			});
			
	     	buttonPanel = new BtnsHLayout();
	     	buttonPanel.setHeight(30);
	     	
	     	final IButton newButton = new IButton(ConstantsUtil.buttonConstants.newButton());	
			newButton.setIcon("icons/add_list.png");
			newButton.addClickHandler(new ClickHandler() {	
				@Override
				public void onClick(ClickEvent event) {
					if(partBagItemGrid.getPartIndexId() == null){
						SC.say(ConstantsUtil.commonConstants.alertSelectFortoolbar());
						return;
					}
					
					//partBagItemGrid.getField("itemPartIndex").setEditorType(sltPartNumber);
					Map<String, String> map = new LinkedHashMap<String, String>();
					map.put("partIndexId", partBagItemGrid.getPartIndexId());
					partBagItemGrid.startEditingNew(map);	
										
				};
			});
					
			IButton saveButton = new IButton(ConstantsUtil.buttonConstants.saveButton());
			saveButton.setIcon("icons/save.png");
			saveButton.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
				if(partBagItemGrid.getPartIndexId() == null){
					SC.say(ConstantsUtil.commonConstants.alertSelectFortoolbar());
				}else{
					partBagItemGrid.saveAllEdits(new Function(){

						@Override
						public void execute() {
							SC.say(ConstantsUtil.commonConstants.alertForSaveSuccess());
							
						}
					});
				}	
			}
		});
			IButton cancelButton = new IButton(ConstantsUtil.buttonConstants.cancelButton());
			cancelButton.setIcon("icons/remove.png");
			cancelButton.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					partBagItemGrid.discardAllEdits();				
				}
			});
			
			final IButton deleteButton = new IButton(ConstantsUtil.buttonConstants.deleteButton());
			deleteButton.setIcon("icons/delete_list.png");
			deleteButton.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					if(partBagItemGrid.getSelection().length == 0){
						SC.say(ConstantsUtil.commonConstants.alertSelectFortoolbar());
					}else{
						SC.confirm(ConstantsUtil.commonConstants.ConfirmForDelete(), new BooleanCallback() {  
		                    public void execute(Boolean value) {  
		                        if (value != null && value) {  
		                           SC.say(ConstantsUtil.commonConstants.alertForsuccessDelete());  
		                           partBagItemGrid.removeSelectedData();
		                           //删除表单
		                        } else {  
		                           // labelAnswer.setContents("Cancel");  
		                        }  
		                    }  
		                }); 
									}	
				}
			});

			buttonPanel.addMember(newButton);  
			buttonPanel.addMember(saveButton);
			buttonPanel.addMember(cancelButton);
			buttonPanel.addMember(deleteButton);
			//this.addMember(buttonPanel);
			
			if(!editable){
				buttonPanel.setVisible(false);
			}
		}
		

		public void fetchData(String partIndexId){
			partBagItemGrid.setPartIndexId(partIndexId);
			Criteria criteria = new Criteria("partIndexId",partIndexId);
			partBagItemGrid.fetchData(criteria);
			
		}
		public void clearData(){
			partBagItemGrid.setPartIndexId(null);
			partBagItemGrid.setData(new Record[0]);
		}

	}
