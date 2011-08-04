package com.skynet.spms.client.gui.portal;

import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.skynet.spms.client.service.ModuleInfoService;
import com.skynet.spms.client.service.ModuleInfoServiceAsync;
import com.smartgwt.client.util.JSOHelper;
import com.smartgwt.client.util.JSON;
import com.smartgwt.client.util.Offline;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.Layout;
import com.smartgwt.client.widgets.layout.VLayout;

public class LayoutManager {
	private static final String SPMS_PROTAL_LAYOUT = "spms.protal.layout";

	private static final Logger log = Logger.getLogger("Portal config");

	private String[][] layoutArray={
			{"70","TaskList","Chart"},
			{"30","Comment"}
		};
	

	
	private ModuleInfoServiceAsync moduleService = GWT
	.create(ModuleInfoService.class);
	
	public LayoutManager(){
		
					
	}

	private PortalConfig config;
	public PortalConfig getConfig(){
		return config;
	}
	public LayoutManager(Set<String> filterSet){
		
		String jsnString = (String) Offline.get(SPMS_PROTAL_LAYOUT);
		
		if(jsnString==null){						
			return;
		}
		
		try{
			JavaScriptObject arrayJsn=JSON.decode(jsnString);
			JavaScriptObject[] jsnArray=JSOHelper.toArray(arrayJsn);
			
			String[][] strArray=new String[jsnArray.length][];
			
			for(int i=0;i<jsnArray.length;i++){
				JavaScriptObject lineJsn=jsnArray[i];
				log.info("line json:"+lineJsn);
				Object[] objLine=JSOHelper.convertToArray(lineJsn);
				String[] lineArray=new String[objLine.length];
				
				for(int j=0;j<objLine.length;j++){
					lineArray[j]=(String)objLine[j];				
				}
				strArray[i]=lineArray;
			}
			
			layoutArray=strArray;
		}catch(Exception e){
			log.warning(e.getMessage());
		}
	}
	
	public HLayout getLayout(final MemberFactory factory){
		final HLayout mainLayout=factory.getHLayout();
		mainLayout.setWidth100();
		mainLayout.setHeight100();

		moduleService.getPortalMembers(new AsyncCallback<Set<String>>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onSuccess(final Set<String> filterSet) {
				config=new PortalConfig(filterSet);
				for(int i=0;i<layoutArray.length;i++){
					String wid=layoutArray[i][0];
					Layout canvas=getChildLayout(i,factory,filterSet);
					canvas.setWidth(wid+"%");			
					mainLayout.addMember(canvas);
				}
			}
		});
		
		
		return mainLayout;
	}
	
	public void setLayoutInfoList(List<List<String>> infoList){
		
		for(int idx=0;idx<infoList.size();idx++){
			List<String> subList=infoList.get(idx);
			String[] lineArray=subList.toArray(new String[0]);
			for(int itemIdx=0;itemIdx<lineArray.length;itemIdx++){
				String name=lineArray[itemIdx];
				layoutArray[idx][itemIdx+1]=name;
			}
		}
		
		
		JavaScriptObject[] jsonArray=new JavaScriptObject[layoutArray.length];
		
		for(int i=0;i<layoutArray.length;i++){
			jsonArray[i]=JSOHelper.arrayConvert(layoutArray[i]);
		}		
		JavaScriptObject arrayObj=JSOHelper.arrayConvert(jsonArray);
		
		String jsnString=JSON.encode(arrayObj);
		log.info("layout json:"+jsnString);
		Offline.put(SPMS_PROTAL_LAYOUT, jsnString);
				
	}
	
	
	private Layout getChildLayout(int num,MemberFactory factory,Set<String> filterSet){
		
		String[] lineArray=layoutArray[num];
		
		int pre=100/lineArray.length;
		
		VLayout lineLayout=factory.getVLayout();		
		for(int idx=1;idx<lineArray.length;idx++){
			String name=lineArray[idx];
			log.info("add module "+name+" to panel");
			if(!filterSet.contains(name)){
				continue;
			}
			if("blank".equals(name)){
				Canvas canvas=new Canvas();
				canvas.setWidth100();
				canvas.setHeight(pre+"%");
				lineLayout.addMember(canvas);
				continue;
			}
			Canvas canvas=factory.getMember(name);
			if(canvas==null){
				continue;
			}
			
			canvas.setHeight(pre+"%");
			lineLayout.addMember(canvas,idx-1);
			
		}		
		return lineLayout;
		
	}
	
	public interface MemberFactory {
		Canvas getMember(String name);
		
		String getDescription(String name);
		
		HLayout getHLayout();
		
		VLayout getVLayout();
	}
}
