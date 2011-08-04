package com.skynet.spms.web.control;

import java.io.IOException;
import java.net.ConnectException;
import java.nio.channels.ClosedChannelException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.skynet.common.gwt.GwtRpcEndPoint;
import com.skynet.spms.client.service.PrintCargoService;
import com.skynet.spms.manager.stockServiceBusiness.warehouseManageBusiness.CargoSpaceManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.warehouseManageBusiness.cargoSpaceManage.CargoSpace;
import com.skynet.spms.service.PrinterEnum;
import com.skynet.spms.service.PrinterProService;
import com.skynet.spms.service.UUIDGeneral;

@Controller
@GwtRpcEndPoint
public class PrintCargoAction implements PrintCargoService {

	@Autowired
	private PrinterProService printerProService;

	@Autowired
	private CargoSpaceManager cargoSpaceManager;

	@Autowired
	private UUIDGeneral uUIDGeneral;

	@Override
	public String print(String[] cargoSpaceIDs, String ip, int port,
			String lableid) throws Exception {
		String cbString = null;
		String[][] content = new String[cargoSpaceIDs.length][25];

		try {
			PrinterEnum printer = null;
			String printerType = "";
			if (port == 1) {
				// 远程打印机
				printer = PrinterEnum.B;
				printerType = "printer";
			} else if (port == 2) {
				// 远程发卡器
				printer = PrinterEnum.C;
				printerType = "cardSender";
			}

			Map map = new HashMap();
			map.put("id", cargoSpaceIDs[0]);
			List<CargoSpace> cargoSpaceList = cargoSpaceManager
					.getAllCargoSpace(map, 0, -1);
			for (int i = 0; i < cargoSpaceList.size(); i++) {
				CargoSpace cargoSpace = cargoSpaceList.get(i);
				// 打印机发卡
				content[i][0] = printerType;
				// 标签容量
				content[i][1] = "size512bit";
				// 国别码
				content[i][2] = "156";
				// 版本区
				content[i][3] = "1";
				// 一级类别
				content[i][4] = "location";
				// 二级类别
				content[i][5] = "RO";
				// 策略类别
				content[i][6] = "serial";
				// 控制序列号
				content[i][7] = uUIDGeneral.getPrintSequence();
				// 货位编号
				content[i][8] = cargoSpace.getCargoSpaceNumber().toString();
				// 1
				content[i][9] = "";
				// 2
				content[i][10] = "0";
				// 3
				content[i][11] = "";
				// 4
				content[i][12] = "0";
				// 5
				content[i][13] = "";
				// 6
				content[i][14] = "0";
				// 7
				content[i][15] = "";
				// 8
				content[i][16] = "0";
				// 9
				content[i][17] = "";
				// 10
				content[i][18] = "0";
				// 11
				content[i][19] = "";
				// 12
				content[i][20] = "0";
				// 13
				content[i][21] = "";
				// 14
				content[i][22] = "0";
				// 15
				content[i][23] = "";
				// 16
				content[i][24] = "0";

			}

			String command = createSTKCommand(content);
			Future<String> f = printerProService.doPrint(printer, command);
			String result = f.get(120, TimeUnit.SECONDS);
			System.out.println(result);
			cbString = result;
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

		return cbString;
	}

	private String createSTKCommand(String[][] content) {
		StringBuilder commarry = new StringBuilder();
		int sum = content.length;
		for (int i = 0; i < sum; i++) {
			for (int j = 0; j < 25; j++) {
				commarry.append(content[i][j] + "\n");
			}
		}

		System.out.println(commarry);
		return commarry.toString();
	}
}
