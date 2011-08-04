package com.skynet.spms.service;

import java.io.IOException;
import java.net.SocketException;
import java.util.concurrent.Future;

public interface PrinterService {

	public Future<String>  doPrint(PrinterEnum printer,String printOperStr) throws IOException;


}
