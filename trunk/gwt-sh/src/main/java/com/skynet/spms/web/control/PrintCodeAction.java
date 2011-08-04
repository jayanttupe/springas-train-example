package com.skynet.spms.web.control;

import java.io.IOException;
import java.net.ConnectException;
import java.nio.channels.ClosedChannelException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.skynet.common.gwt.GwtRpcEndPoint;
import com.skynet.spms.client.service.PrintCodeService;
import com.skynet.spms.service.PrinterEnum;
import com.skynet.spms.service.PrinterService;

@Controller
@GwtRpcEndPoint
public class PrintCodeAction implements PrintCodeService {

	@Autowired
	private PrinterService printerService;

	@Override
	public void print(String[] countent, String ip, int port, String lableid)
			throws Exception {
		if (countent.length <= 0) {
			return;
		}

		String command = createSTKCommand(countent, lableid);

		try {
			Future<String> f = printerService.doPrint(PrinterEnum.D, command);
			f.get(120, TimeUnit.SECONDS);
			System.out.println("INFO\n" + f.get().toString());
		} catch (TimeoutException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ConnectException e) {
			e.printStackTrace();
			e.getMessage();
			throw e;
		} catch (ClosedChannelException e) {
			e.printStackTrace();
			e.getMessage();
			throw e;
		} catch (ExecutionException e) {
			e.printStackTrace();
			e.getMessage();
			throw e;
		} catch (IOException e) {
			e.printStackTrace();
			e.getMessage();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			e.getMessage();
			throw e;
		}

	}

	private static String createSTKCommand(String[] lebelcontent, String lableID) {
		int sum = lebelcontent.length;
		String command = "NEW\n";
		command += "10 ON ERROR GOTO 10000\n";
		command += "20 LET SUM%=" + sum + "\n";
		command += "30 DIM LEBSUM$(SUM%)" + "\n";
		for (int i = 1; i <= sum; i++) {
			command += String.valueOf(30 + i * 10) + " LEBSUM$(" + i + ")=\""
					+ lebelcontent[i - 1] + "\"\n";
		}
		if (lableID == null || "".equals(lableID)
				|| "NO".equals(lableID.toUpperCase())) {
			// 货位源码
			command += "1000 FOR I%=1 TO SUM% STEP 1" + "\n"
					+ "1010 LET STK$=LEBSUM$(I%)" + "\n"
					+ "1020 BARFONT \"Swiss 721 BT\",12,0,5,1,1,100,2,0 ON" + "\n"
					+ "1030 PRPOS 30,5" + "\n"
					+ "1040 BARSET \"CODE128\",2,2,3,160" + "\n"
					+ "1050 PRBAR STK$" + "\n" + "1060 PRINTFEED" + "\n"
					+ "1070 NEXT" + "\n" + "1080 CLEAR" + "\n" + "1090 END" + "\n"
					+ "10000 PRINT \"Undefined error, execution terminated\"" + "\n"
					+ "10015 CLEAR" + "\n" + "10010 END" + "\n"
					+ "RUN" + "\n";
		} else if ("2".equals(lableID)) {
			// 证书编号
			command += "1000 FOR I%=1 TO SUM% STEP 1" + "\n"
					+ "1010 LET STK$=LEBSUM$(I%)" + "\n"
					+ "1020 BARFONT \"Swiss 721 BT\",12,0,5,1,1,100,2,0 ON"
					+ "\n" + "1030 PRPOS 125,0" + "\n"
					+ "1040 BARSET \"CODE128\",2,2,3,200" + "\n"
					+ "1050 PRBAR STK$" + "\n" + "1060 PRINTFEED" + "\n"
					+ "1070 NEXT" + "\n" + "1080 CLEAR" + "\n" + "1090 END"
					+ "\n"
					+ "10000 PRINT \"Undefined error, execution terminated\""
					+ "\n" + "10015 CLEAR" + "\n" + "10010 END" + "\n" + "RUN"
					+ "\n";
		} else if ("3".equals(lableID)) {
			// 收料标签
			command += "1000 FOR I%=1 TO SUM% STEP 1" + "\n"
					+ "1010 LET STK$=LEBSUM$(I%)" + "\n"
					+ "1020 BARFONT \"Swiss 721 BT\",12,0,5,1,1,100,2,0 ON"
					+ "\n" + "1030 PRPOS 125,0" + "\n"
					+ "1040 BARSET \"CODE128\",1,1,3,160" + "\n"
					+ "1050 PRBAR STK$" + "\n" + "1060 PRINTFEED" + "\n"
					+ "1070 NEXT" + "\n" + "1080 CLEAR" + "\n" + "1090 END"
					+ "\n"
					+ "10000 PRINT \"Undefined error, execution terminated\""
					+ "\n" + "10015 CLEAR" + "\n" + "10010 END" + "\n" + "RUN"
					+ "\n";
		}
		System.out.println(command);
		return command.toString();
	}
}
