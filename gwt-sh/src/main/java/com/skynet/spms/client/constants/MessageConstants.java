package com.skynet.spms.client.constants;

import com.google.gwt.i18n.client.Messages;

public interface MessageConstants extends Messages {

	String emailError();
	
	String floatRangeError(float from,float to);
	
	String intRangeError(int from,int to);
	
	String stringLenRangeError(int start,int end);
	
    String stringLenError(int len);

	String intError();

	String stringError();

	String floatError();

	String containStringError();

	String zipCodeError();
	
	String pwdConfirmError();
	
	String mobileError();
	
	String telephoneError();
	
	String faxError();

	String urlError();

	String capitalError();
	
	String logicError();
}
