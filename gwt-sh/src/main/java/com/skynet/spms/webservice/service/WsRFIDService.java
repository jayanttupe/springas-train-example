package com.skynet.spms.webservice.service;

import com.skynet.spms.webservice.entity.Check4DoorControlInputParameters;
import com.skynet.spms.webservice.entity.FaultResponse;
import com.skynet.spms.webservice.entity.GetRFIDSerialInputParameters;
import com.skynet.spms.webservice.entity.GetRFIDSerialOutputParameters;
import com.skynet.spms.webservice.entity.QueryLocationOutParameters;
import com.skynet.spms.webservice.entity.QueryLocationParameters;
import com.skynet.spms.webservice.entity.VisualLocationInputParameters;
import com.skynet.spms.webservice.entity.VisualLocationOutputParameters;

public interface WsRFIDService {

	public VisualLocationOutputParameters getVirtualLocaltion(

	VisualLocationInputParameters request) throws FaultResponse;

	public QueryLocationOutParameters getLocationInfo(

	QueryLocationParameters request) throws FaultResponse;

	public boolean check4DoorControl(Check4DoorControlInputParameters request)
			throws FaultResponse;

	public GetRFIDSerialOutputParameters getRFIDSerial(
			GetRFIDSerialInputParameters request) throws FaultResponse;

}
