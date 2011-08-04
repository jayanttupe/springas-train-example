package com.skynet.spms.web.control;

import java.io.IOException;
import java.net.ConnectException;
import java.nio.channels.ClosedChannelException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.skynet.common.gwt.GwtRpcEndPoint;
import com.skynet.spms.client.service.PrintCardService;
import com.skynet.spms.manager.stockServiceBusiness.base.partEntityBusiness.PartEntityManager;
import com.skynet.spms.manager.stockServiceBusiness.inStockRoomBusiness.InStockRecordManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.base.partEntity.PartEntity;
import com.skynet.spms.persistence.entity.stockServiceBusiness.inStockRoomBusiness.inStockRecord.InStockRecordForPrint;
import com.skynet.spms.service.PrinterEnum;
import com.skynet.spms.service.PrinterProService;
import com.skynet.spms.service.UUIDGeneral;

@Controller
@GwtRpcEndPoint
public class PrintCardAction implements PrintCardService {

	@Autowired
	private PrinterProService printerProService;

	@Autowired
	private InStockRecordManager inStockRecordManage;

	@Autowired
	private PartEntityManager partEntityManager;

	@Autowired
	private UUIDGeneral uUIDGeneral;

	@Override
	public String print(String[] inStockRecordIDs, String ip, int port,
			String lableid) throws Exception {
		String cbString = null;
		String[][] content = new String[inStockRecordIDs.length][30];

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

			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String strDate = sdf.format(date).toString();

			List<InStockRecordForPrint> inStockRecordList = inStockRecordManage
					.getInStockRecordForPrint(inStockRecordIDs);
			for (int i = 0; i < inStockRecordList.size(); i++) {
				// 获取序列号
				String printSequence = uUIDGeneral.getPrintSequence();

				InStockRecordForPrint inStockRecordForPrint = inStockRecordList.get(i);
				// 打印机发卡
				content[i][0] = printerType;
				// 标签容量
				content[i][1] = "size512bit";
				// 国别码
				content[i][2] = "156";
				// 版本区
				content[i][3] = "1";
				// 一级类别
				content[i][4] = "part";
				// 二级类别
				content[i][5] = "RO";
				// 策略类别
				content[i][6] = "serial";
				// 控制序列号
				content[i][7] = printSequence;
				// 件号
				if (inStockRecordForPrint.getPartNumber() != null) {
					String partNumber = inStockRecordForPrint.getPartNumber();
					if (partNumber.length() > 15) {
						content[i][8] = partNumber.substring(0, 15);
					} else {
						content[i][8] = partNumber;
					}
				} else {
					content[i][8] = "";
				}
				// 序号/批次号
				if (inStockRecordForPrint.getPartSerialNumber() != null) {
					content[i][9] = inStockRecordForPrint.getPartSerialNumber();
				} else {
					content[i][9] = "";
				}
				// 制造商
				if (inStockRecordForPrint.getManufacturerCode() != null) {
					content[i][10] = inStockRecordForPrint.getManufacturerCode();
				} else {
					content[i][10] = "";
				}
				// 货架时寿日期
				content[i][11] = strDate;
				// 数量
				content[i][12] = String.valueOf(inStockRecordForPrint.getQuantity());
				// 单位
				if (inStockRecordForPrint.getUnit() != null) {
					content[i][13] = inStockRecordForPrint.getUnit();
				} else {
					content[i][13] = "";
				}
				// 货位
				content[i][14] = "";
				// 当前操作业务
				content[i][15] = "SC";
				// 航材状态
				content[i][16] = "US";
				// 航材分类
				content[i][17] = "Rotable";
				// 客户订单号
				content[i][18] = "";
				// 箱号
				content[i][19] = "";
				// 修理订单号
				content[i][20] = "";
				// 客户代码
				content[i][21] = "";
				// 供应商代码
				content[i][22] = "";
				// 部件唯一标识
				content[i][23] = "";
				// 备件证书电子扫描档案号
				content[i][24] = "";
				// 库存编号
				if (inStockRecordForPrint.getStockRoomNumber() != null) {
					content[i][25] = inStockRecordForPrint.getStockRoomNumber();
				} else {
					content[i][25] = "";
				}
				// 入库检验单编号
				if (inStockRecordForPrint.getCheckAndAcceptSheetNumber() != null) {
					content[i][26] = inStockRecordForPrint.getCheckAndAcceptSheetNumber();
				} else {
					content[i][26] = "";
				}
				// 货架寿命代码
				content[i][27] = "";
				// 制造日期
				content[i][28] = "";
				// 装箱单号
				content[i][29] = "";

				// 更新备件实体的二维标签序列号
				PartEntity partEntity = new PartEntity();
				partEntity.setPartNumber(inStockRecordForPrint.getPartNumber());
				partEntity.setPartSerialNumber(inStockRecordForPrint.getPartSerialNumber());
				partEntity.setrFIDTagUUID(printSequence);
				partEntityManager.updateRFIDTagUUID(partEntity);
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
			for (int j = 0; j < 30; j++) {
				commarry.append(content[i][j] + "\n");
			}
		}

		System.out.println(commarry);
		return commarry.toString();
	}
}
