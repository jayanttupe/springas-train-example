package com.skynet.spms.web.servlet;

import java.util.Locale;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.isomorphic.datasource.DSRequest;
import com.isomorphic.datasource.DataSource;
import com.isomorphic.datasource.DynamicDSGenerator;
import com.isomorphic.servlet.IDACall;
import com.skynet.common.gwt.GwtActionHelper;
import com.skynet.spms.service.DSConfigService;

public class CustomIDCallServlet extends IDACall {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7682388794111269609L;


	private ApplicationContext getContext(ServletContext context) {
		WebApplicationContext ctx = WebApplicationContextUtils
				.getWebApplicationContext(context);
		return ctx;
	}
	
	public CustomIDCallServlet(){
		super();
		DataSource.addDynamicDSGenerator(new DynamicDSGenerator(){

			@Override
			public DataSource getDataSource(String s, DSRequest dsRequest) {
				String dsName=s;
				try{
				
					GwtActionHelper.init(dsRequest.getHttpServletRequest());
					
					Locale locale=GwtActionHelper.getLocale();
					String rule=GwtActionHelper.getCurrRule();
					
					DSConfigService service=getContext(dsRequest.getServletContext())
						.getBean(DSConfigService.class);
					
					return service.getDataSourceByName(dsName, rule,locale);
				}catch(Exception e){
					return null;
				} 
			}
			
		});
	}
		
//	public DSResponse tempHandleDSRequest(DSRequest dsRequest, RPCManager rpc,
//			RequestContext context) throws Exception {
//		try {
//						
//			
//			String dsName=dsRequest.getDataSourceName();
//			
//			Reader reader=getCustomDs(dsName,context);
//			dsRequest.setBeenThroughDMI(true);
//
//			DataSource ds = DataSource.fromXML(reader,dsRequest);
//			
//			return ds.execute(dsRequest);
//			
//			
//		} catch (Exception e) {
//			RequestContext _tmp = context;
//			RequestContext.staticLog.warn("dsRequest.execute() failed: ", e);
//			DSResponse dsResponse = new DSResponse(
//					dsRequest != null ? dsRequest.getDataSource()
//							: (DataSource) null);
//			if (e instanceof UpdateWithoutPKException)
//				dsResponse.setStatus(-9);
//			else
//				dsResponse.setStatus(DSResponse.STATUS_FAILURE);
//			dsResponse.setData(e.getMessage());
//			return dsResponse;
//		}
//	}

}
