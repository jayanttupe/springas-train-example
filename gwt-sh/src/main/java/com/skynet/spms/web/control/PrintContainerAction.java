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
import com.skynet.spms.client.service.PrintContainerService;
import com.skynet.spms.manager.stockServiceBusiness.containerBusiness.ContainerManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.containerManage.Container;
import com.skynet.spms.service.PrinterEnum;
import com.skynet.spms.service.PrinterProService;
import com.skynet.spms.service.UUIDGeneral;

@Controller
@GwtRpcEndPoint
public class PrintContainerAction implements PrintContainerService {

	@Autowired
	private PrinterProService printerProService;
	
	@Autowired
	private ContainerManager containerManager;

	@Autowired
	private UUIDGeneral uUIDGeneral;

	String[] commarry = new String[4];

	@Override
	public String print(String[] containerIDs, int port)
			throws Exception {
		String result = "";
		String[][] content = new String[containerIDs.length][14];

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
			map.put("id", containerIDs[0]);
			List<Container> containerList = containerManager.getContainer(map, 0, -1);
			for (int i = 0; i < containerList.size(); i++) {
				Container container = containerList.get(i);

				// 打印机发卡 	
				content[i][0] = printerType;
				// 标签容量	 
				content[i][1] = "size512bit";
				// 国别码	
				content[i][2] = "156";
				// 版本区 	
				content[i][3] = "1";
				// 一级类别 	
				content[i][4] ="container";
				// 二级类别
				content[i][5]= "tray";
				// 策略类别
				content[i][6] ="partContainer";
				// 控制序列号
				content[i][7] = uUIDGeneral.getPrintSequence();
				//材质属性
				if (container.getContainerMaterial() != null) {
					content[i][8] = container.getContainerMaterial().toString();	
				} else {
					content[i][8] = "";
				}
				//仓库代码
				if (container.getStockRoomNumber() != null) {
					content[i][9] = container.getStockRoomNumber().toString().replace("-", "");	
				} else {
					content[i][9] = "";
				}
				//目标库房
				content[i][10] ="";
				//装箱单号码
				content[i][11] = "";
				//参考号码
				content[i][12] = "";
				//任务号码
				content[i][13] = "";
			}
			
			String command = createSTKCommand(content);
			Future<String> f = printerProService.doPrint(printer, command);
			result = f.get(120, TimeUnit.SECONDS);
			System.out.println(result);
		}catch (TimeoutException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
		catch (ConnectException e) {
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
	
		return result;
	}

	private String createSTKCommand(String[][] lebelcontent) {
		StringBuilder commarry = new StringBuilder();
		int sum = lebelcontent.length;
		for (int i = 0; i < sum; i++) {
			for (int j = 0; j < 14; j++) {
				commarry.append(lebelcontent[i][j] + "\n");
			}
		}

		System.out.println(commarry);
		return commarry.toString();
	}
}
