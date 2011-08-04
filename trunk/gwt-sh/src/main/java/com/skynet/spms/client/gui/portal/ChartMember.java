package com.skynet.spms.client.gui.portal;

import com.skynet.spms.client.chart.ChartType;
import com.skynet.spms.client.chart.FusionChart;
import com.skynet.spms.client.gui.portal.PortalPanel.PortalMember;
import com.smartgwt.client.widgets.Canvas;

public class ChartMember implements PortalMember{


	private FusionChart chart=new FusionChart();	
	
	@Override
	public Canvas getCanvas() {
		
		 	chart.setDataSource("demo");
			chart.setChartType(ChartType.Column3D);
			chart.setWidth100();
			chart.setHeight100();
			
			return chart;
	}

	@Override
	public String getName() {
		return "Chart";
	}

	@Override
	public String getDescription() {
		return "Chart";
	}

	
	
}
