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
import com.skynet.spms.client.service.PrintMatrixService;
import com.skynet.spms.manager.stockServiceBusiness.base.partEntityBusiness.PartEntityManager;
import com.skynet.spms.manager.stockServiceBusiness.inStockRoomBusiness.InStockRecordManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.base.partEntity.PartEntity;
import com.skynet.spms.persistence.entity.stockServiceBusiness.inStockRoomBusiness.inStockRecord.InStockRecordForPrint;
import com.skynet.spms.service.PrinterEnum;
import com.skynet.spms.service.PrinterService;
import com.skynet.spms.service.UUIDGeneral;

@Controller
@GwtRpcEndPoint
public class PrintMatrixAction implements PrintMatrixService {

	@Autowired
	private PrinterService printerService;

	@Autowired
	private InStockRecordManager inStockRecordManage;

	@Autowired
	private UUIDGeneral uUIDGeneral;

	@Autowired
	private PartEntityManager partEntityManager;

	@Override
	public void print(String[] inStockRecordIDs, String ip, int port,
			String lableid) throws Exception {
		String[][] content = new String[inStockRecordIDs.length][30];
		try {
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String strDate = sdf.format(date).toString();

			List<InStockRecordForPrint> inStockRecordList = inStockRecordManage
					.getInStockRecordForPrint(inStockRecordIDs);
			for (int i = 0; i < inStockRecordList.size(); i++) {
				// 条码唯一编号
				String printSequence = uUIDGeneral.getPrintSequence();
				InStockRecordForPrint inStockRecordForPrint = inStockRecordList
						.get(i);
				// PNR 件号
				content[i][0] = inStockRecordForPrint.getPartNumber();
				// MFR 制造商代码
				if (inStockRecordForPrint.getManufacturerCode() != null) {
					content[i][1] = inStockRecordForPrint.getManufacturerCode();
				} else {
					content[i][1] = "";
				}
				// SER 部件序列编号
				content[i][2] = inStockRecordForPrint.getPartSerialNumber();
				// CER 备件证件电子扫描档案号
				content[i][3] = "";
				// STK 库房编号
				content[i][4] = inStockRecordForPrint.getStockRoomNumber();
				// SIN 入库检验单编号
				content[i][5] = inStockRecordForPrint
						.getCheckAndAcceptSheetNumber();
				// EXP 有效期
				content[i][6] = strDate;
				// DMF 制造日期
				content[i][7] = strDate;
				// SHQ 数量
				content[i][8] = String.valueOf(inStockRecordForPrint
						.getQuantity());
				// UNT 单位
				content[i][9] = inStockRecordForPrint.getUnit();
				// SLC 货架寿命代码
				content[i][10] = "";
				content[i][11] = printSequence;

				// 更新备件实体的二维标签序列号
				PartEntity partEntity = new PartEntity();
				partEntity.setPartNumber(inStockRecordForPrint.getPartNumber());
				partEntity.setPartSerialNumber(inStockRecordForPrint.getPartSerialNumber());
				partEntity.setBarcodeTagUUID(printSequence);
				partEntityManager.updateBarcodeTagUUID(partEntity);
			}

			String command = createSTKCommand(content);
			Future<String> f = printerService.doPrint(PrinterEnum.A, command);
			String result = f.get(120, TimeUnit.SECONDS);
			System.out.println("info" + result);
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

	private String createSTKCommand(String[][] lebelcontent) {
		StringBuilder commarry = new StringBuilder();

		int sum = lebelcontent.length;
		commarry.append("NEW" + "\n" + "10 ON ERROR GOTO 10000\n"
				+ "20 LET LX%=380\n" + "30 LET LY%=360\n" + "40 LET CEL%=60\n"
				+ "70 LET SUM%=" + sum + "\n" + "80 DIM LABELARRY1$(SUM%,6)"
				+ "\n" + "90 DIM LABELARRY2$(SUM%,6)" + "\n");

		int line = 90;
		for (int i = 0; i < sum; i++) {
			for (int j = 0; j < 6; j++) {
				line = line + 10;
				commarry.append(String.valueOf(line) + " LABELARRY1$(" + i
						+ "," + j + ")=\"" + lebelcontent[i][j] + "\"\n");
			}

			for (int j = 0; j < 6; j++) {
				line = line + 10;
				commarry.append(String.valueOf(line) + " LABELARRY2$(" + i
						+ "," + j + ")=\"" + lebelcontent[i][j + 6] + "\"\n");
			}
		}
		// 按照锐见要求在二维码最后添加\@\#结束符
		commarry.append("1000 FOR I%=0 TO SUM%-1 STEP 1"
				+ "\n"
				+ "1010 PRPOS LX%-230,LY%-410"
				+ "\n"
				+ "1020 DIR 1"
				+ "\n"
				// 设置图像文件名称
				+ "1100 PRIMAGE \"MATRIX_E.PCX\""
				+ "\n"
				+ "2020 PRPOS LX%-200,LY%-50"
				+ "\n"
				+ "2030 BARMAG 4"
				+ "\n"
				// 设置打印的方位
				+ "2040 DIR 1"
				+ "\n"
				+ "2050 BARSET \"DATAMATRIX\""
				+ "\n"
				+ "2060 BARFONT OFF,0"
				+ "\n"
				+ "3010 LET PNR$=\"PNR\"+space$(1)+LABELARRY1$(I%,0)"
				+ "\n"
				+ "3020 LET MFR$=\"MFR\"+space$(1)+LABELARRY1$(I%,1)"
				+ "\n"
				+ "3030 LET SER$=\"SER\"+space$(1)+LABELARRY1$(I%,2)"
				+ "\n"
				+ "3040 LET CER$=\"CER\"+space$(1)+LABELARRY1$(I%,3)"
				+ "\n"
				+ "3050 LET STK$=\"STK\"+space$(1)+LABELARRY1$(I%,4)"
				+ "\n"
				+ "3060 LET SIN$=\"SIN\"+space$(1)+LABELARRY1$(I%,5)"
				+ "\n"
				+ "3070 LET EXP$=\"EXP\"+space$(1)+LABELARRY2$(I%,0)"
				+ "\n"
				+ "3080 LET DMF$=\"DMF\"+space$(1)+LABELARRY2$(I%,1)"
				+ "\n"
				+ "3090 LET SHQ$=\"SHQ\"+space$(1)+LABELARRY2$(I%,2)"
				+ "\n"
				+ "3100 LET UNT$=\"UNT\"+space$(1)+LABELARRY2$(I%,3)"
				+ "\n"
				+ "3110 LET SLC$=\"SLC\"+space$(1)+LABELARRY2$(I%,4)"
				+ "\n"
				+ "3120 LET BSR$=\"BSR\"+space$(1)+LABELARRY2$(I%,5)"
				+ "\n"
				+ "3130 PRBAR PNR$+\"/\"+MFR$+\"/\"+SER$+\"/\"+CER$+\"/\"+STK$+\"/\"+SIN$+\"/\"+EXP$+\"/\"+DMF$+\"/\"+SHQ$+\"/\"+UNT$+\"/\"+SLC$+\"/\"+BSR$+\"\\@\"+\"\\#\""
				+ "\n"
				// 设置字体
				+ "4010 FONT \"Swiss 721 BT\",10"
				+ "\n"
				// PNR
				+ "5160 PRPOS LX%,LY%+80"
				+ "\n"
				+ "5170 PRTXT PNR$"
				+ "\n"
				// MFR
				+ "5140 PRPOS LX%,LY%+30"
				+ "\n"
				+ "5150 PRTXT MFR$"
				+ "\n"
				// SER
				+ "5120 PRPOS LX%,LY%-20"
				+ "\n"
				+ "5130 PRTXT SER$"
				+ "\n"
				// CER
				+ "5100 PRPOS LX%,LY%-70"
				+ "\n"
				+ "5110 PRTXT CER$"
				+ "\n"
				// STK
				+ "5080 PRPOS LX%,LY%-120"
				+ "\n"
				+ "5090 PRTXT STK$"
				+ "\n"
				// SIN
				+ "5060 PRPOS LX%,LY%-170"
				+ "\n"
				+ "5070 PRTXT SIN$"
				+ "\n"
				// EXP
				+ "5040 PRPOS LX%,LY%-220"
				+ "\n"
				+ "5050 PRTXT EXP$"
				+ "\n"
				// DMF
				+ "5020 PRPOS LX%,LY%-270"
				+ "\n"
				+ "5030 PRTXT DMF$"
				+ "\n"
				// SHQ
				+ "4040 PRPOS LX%+200,LY%+30"
				+ "\n"
				+ "4050 PRTXT SPACE$(20)+SHQ$\n"
				// UNT
				+ "4120 PRPOS LX%+200,LY%-20"
				+ "\n"
				+ "4130 PRTXT SPACE$(20)+UNT$\n"
				// SLC
				+ "4020 PRPOS LX%+200,LY%-70" + "\n"
				+ "4030 PRTXT SPACE$(20)+SLC$\n"

				+ "5300 PRINTFEED" + "\n" + "5310 NEXT" + "\n" + "5320 END"
				+ "\n"
				+ "10000 PRINT \"Undefined error, execution terminated\""
				+ "\n" + "10010 END" + "\n" + "RUN" + "\n");
		System.out.println(commarry);
		return commarry.toString();
	}
}
