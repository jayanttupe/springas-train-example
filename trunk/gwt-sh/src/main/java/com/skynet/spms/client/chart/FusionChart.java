package com.skynet.spms.client.chart;

import java.util.logging.Logger;

import com.google.gwt.core.client.JavaScriptException;
import com.google.gwt.event.shared.HandlerRegistration;
import com.smartgwt.client.core.Function;
import com.smartgwt.client.util.Page;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.events.ResizedEvent;
import com.smartgwt.client.widgets.events.ResizedHandler;

public class FusionChart extends Canvas{

	private Logger log = Logger.getLogger("FusionChart");

	private String chartName;

		
	public void setChartType(ChartType type){
		chartName=Page.getAppDir()+"FusionCharts/"+type.name()+".swf";
	}
		
	private String  dataUrl;
	
	public void setDataSource(String name){
		
		dataUrl=Page.getAppDir()+"chart/dataSource/"+name+".ds";

	}	
		

	public FusionChart(){
		this.doOnRender(new Function(){

			@Override
			public void execute() {
				drawChart();
			}
			
		});
	}
	
	@Override
    public HandlerRegistration addResizedHandler(ResizedHandler handler){
        ResizedHandler newHandler=new ResizedHandler(){

			@Override
			public void onResized(ResizedEvent event) {
				markForRedraw();
			}
        	
        };
        
        return doAddHandler(newHandler, ResizedEvent.getType());
	}
	
	 private native void setupResizedEvent() /*-{
	     var obj = null;
	     var selfJ = this;
	     if(this.@com.smartgwt.client.widgets.BaseWidget::isCreated()()) {
	         obj = this.@com.smartgwt.client.widgets.BaseWidget::getJsObj()();
	         obj.addProperties({resized:$entry(function(){
	                     var param = {};
	                     var event = @com.smartgwt.client.widgets.events.ResizedEvent::new(Lcom/google/gwt/core/client/JavaScriptObject;)(param);
	                     selfJ.@com.smartgwt.client.widgets.BaseWidget::fireEvent(Lcom/google/gwt/event/shared/GwtEvent;)(event);
	                 })
	          });
	     } else {
	         obj = this.@com.smartgwt.client.widgets.BaseWidget::getConfig()();
	         obj.resized = $entry(function(){
	                var param = {};
	                var event = @com.smartgwt.client.widgets.events.ResizedEvent::new(Lcom/google/gwt/core/client/JavaScriptObject;)(param);
	                selfJ.@com.smartgwt.client.widgets.BaseWidget::fireEvent(Lcom/google/gwt/event/shared/GwtEvent;)(event);
	            });
	     }
		}-*/;

	
	@Override
    public String getInnerHTML() {

        return "<DIV STYLE='width:100%;height:100%' ID=" + this.getID() + "_widget></DIV>";
    }
	
	private String getDivID(){
		return this.getID() + "_widget";
	}
   
	public native void view2D()
	/*-{
	    var key=this.@com.skynet.spms.client.chart.FusionChart::getDivID()();
	    var chart=$wnd.infosoftglobal.getChartFromId(key)
		chart.view2D(); 	
	}-*/;	
	
	
	public native void view3D()
	/*-{
    var key=this.@com.skynet.spms.client.chart.FusionChart::getDivID()();
    var chart=$wnd.infosoftglobal.getChartFromId(key)
	chart.view3D(); 	
	}-*/;
	
	
	public native void resetView()
	/*-{
    var key=this.@com.skynet.spms.client.chart.FusionChart::getDivID()();
    var chart=$wnd.infosoftglobal.getChartFromId(key)
	chart.resetView(); 	
	}-*/;
	
	public native void rotateView(int x,int y)
	/*-{
    var key=this.@com.skynet.spms.client.chart.FusionChart::getDivID()();
    var chart=$wnd.infosoftglobal.getChartFromId(key)
	chart.rotateView(x,y); 	
	}-*/;
	
//	private int getViewAngles()
	/*-{
    	var key=this.@com.skynet.spms.client.chart.FusionChart::getDivID()();
    	var chart=$wnd.infosoftglobal.getChartFromId(key)
		var point=chart.getViewAngles();
	 	
//	}-*/;

	private void drawChart(){
		if(!this.isVisible()||this.isDisabled()){
			return;
		}
		
		try {
			 embedFusionCharts( this.getID() + "_widget");
		 }catch(JavaScriptException e){
			 log.warning(e.getMessage());
			 log.warning(e.getDescription());
			 log.warning(e.getException().toString());
		 }
	}
	
	private native void embedFusionCharts(String id)
	/*-{
	    var height=this.@com.skynet.spms.client.chart.FusionChart::getHeightAsString()();
	    var width=this.@com.skynet.spms.client.chart.FusionChart::getWidthAsString()();
	    var chartname= this.@com.skynet.spms.client.chart.FusionChart::chartName;
	    var dataurl=this.@com.skynet.spms.client.chart.FusionChart::dataUrl;
	    
	    var chartId=id;
	    	    
	    var div=$doc.getElementById(chartId);
	    
	    
	    
	    var chart = new $wnd.infosoftglobal.FusionCharts(
			chartname,chartId, 
			width, height, 0, 1);
	
		chart.setDataURL(dataurl);
		chart.setTransparent(false);
		chart.render(chartId);
		
	}-*/;
	
	
}
