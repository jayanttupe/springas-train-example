package com.skynet.spms.web.control;


import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ChartDataSourceAction {

	
	@RequestMapping(value="/chart/dataSource/{dsName}",method = RequestMethod.GET)
	public void generXml(@PathVariable("dsName") String dsName,HttpServletRequest request,HttpServletResponse  response) throws IOException{
		
		
		Document doc=DocumentHelper.createDocument();
		
		Element elem=doc.addElement("chart");
		elem.addAttribute("palette", "2");
		elem.addAttribute("caption","monthly Sales Summory");
		elem.addAttribute("subcaption","For the year 2010");
		elem.addAttribute("xAxisName","month");
		elem.addAttribute("yAxisMinValue","15000");
		
		elem.addAttribute("yAxisName","Sales");
		elem.addAttribute("numberPrefix","$");
		elem.addAttribute("showValues","0");
		
		int[] values={17400,18100,21800,23800,29600,
				27600,31800,39700,37900,21900,32900,31900};
		String[] titles={"Jan","Feb","Mar","Apr","May","Jun",
				"Jul","Aug","Sep","Oct","Nov","Dec"};
		
		for(int i=0;i<values.length;i++){
			Element setElem=elem.addElement("set");
			setElem.addAttribute("label", titles[i]);
			setElem.addAttribute("value",String.valueOf(values[i]));
		}
		response.setContentType(MediaType.TEXT_XML.toString());
		response.getWriter().write(doc.asXML());
		
	}
}	
		/*
		 * <chart palette='2' 
		 * caption='Monthly Sales Summary' 
		 * subcaption='For the year 2006'
		 *  xAxisName='Month' 
		 *  yAxisMinValue='15000' 
		 *  yAxisName='Sales' 
		 *  numberPrefix='$' 
		 *  showValues='0'>
	<set label='Jan' value='17400' />
	<set label='Feb' value='18100' />
	<set label='Mar' value='21800' />
	<set label='Apr' value='23800' />
	<set label='May' value='29600' />
	<set label='Jun' value='27600' />
	<set label='Jul' value='31800' />
	<set label='Aug' value='39700' />
	<set label='Sep' value='37800' />
	<set label='Oct' value='21900' />
	<set label='Nov' value='32900' />
	<set label='Dec' value='39800' />

	<styles>
		<definition>
			<style name='Anim1' type='animation' param='_xscale' start='0' duration='1' />
			<style name='Anim2' type='animation' param='_alpha' start='0' duration='1' />
			<style name='DataShadow' type='Shadow' alpha='20'/>
		</definition>
		<application>
			<apply toObject='DIVLINES' styles='Anim1' />
			<apply toObject='HGRID' styles='Anim2' />
			<apply toObject='DATALABELS' styles='DataShadow,Anim2' />
	</application>	
	</styles>

</chart> 
		 */
		

