package com.skynet.spms.datasource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.ModelAndView;

import com.isomorphic.datasource.DSRequest;
import com.isomorphic.datasource.DSResponse;
import com.isomorphic.datasource.DataSource;
import com.isomorphic.rpc.ClientMustResubmitException;
import com.isomorphic.rpc.RPCManager;
import com.isomorphic.rpc.RPCRequest;
import com.skynet.common.gwt.GwtActionHelper;
import com.skynet.spms.datasource.annotation.DataSourceAdapteAnno;

public class DataSourceAnnotationHandlerAdapter implements HandlerAdapter {

	private Logger log = LoggerFactory
			.getLogger(DataSourceAnnotationHandlerAdapter.class);

	private static final long serialVersionUID = -7421136737990135393L;

	public long getLastModified(HttpServletRequest request, Object handler) {
		return -1;
	}

	
	@Override
	public ModelAndView handle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		GwtActionHelper.init(request);
		
		RPCManager rpc;
		try {
			rpc = new RPCManager(request, response);
		} catch (ClientMustResubmitException e) {
			log.error("rpc manager create fail", e);
			return null;
		}
		
		

		for (Object req : rpc.getRequests()) {

			if (req instanceof RPCRequest) {
				throw new Exception("This adapet expects only DSRequests");
			}

			DSRequest dsRequest = (DSRequest) req;

			
//			String reqDsName = dsRequest.getDataSourceName();
//			log.info("req ds name:" + reqDsName);
	
			DSResponse dsResponse = new DSResponse();
			// assume the operation will be successful. If there's a failure,
			// we'll override this with
			// an error code and provide the problem report as the data so the
			// client can log it.
			dsResponse.setSuccess();

			String operId=dsRequest.getOperationId();

			DataSourceAdapte adapte = (DataSourceAdapte) handler;

			String operation = dsRequest.getOperationType();
			log.info("do " + operation + " oper");
			if (operation.equals(DataSource.OP_FETCH)) {
				adapte.fetch(dsRequest, dsResponse);
			} else if (operation.equals(DataSource.OP_ADD)) {
				adapte.insert(dsRequest, dsResponse);
			} else if (operation.equals(DataSource.OP_UPDATE)) {
				adapte.update(dsRequest, dsResponse);
			} else if (operation.equals(DataSource.OP_REMOVE)) {
				adapte.delete(dsRequest, dsResponse);
			} else {
				adapte.custom(dsRequest, dsResponse,operation);
			}

			// match the response to the request
			rpc.send(dsRequest, dsResponse);

		} // for(requests)

		return null;
	}

	@Override
	public boolean supports(Object handler) {

		return handler instanceof DataSourceAdapte
				&& handler.getClass().isAnnotationPresent(
						DataSourceAdapteAnno.class);

	}



}
