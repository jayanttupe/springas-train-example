package com.skynet.spms.tools;

public class ConvertCodeName {
	
	public static String GetWorkName(String workType)
	{
		if (workType == null) {
			return "";
		}
		
		String strResult = "";
		if (workType.equals("1")) {
			strResult = "订舱";
		} else if (workType.equals("2")) {
			strResult = "取货";			
		} else if (workType.equals("3")) {
			strResult = "起运";			
		} else if (workType.equals("4")) {
			strResult = "到达";			
		} else if (workType.equals("5")) {
			strResult = "清关";			
		} else if (workType.equals("6")) {
			strResult = "交货";			
		}
		return strResult;
	}
	
	public static String GetWorkStatus(String workStatusCode)
	{
		if (workStatusCode == null) {
			return "";
		}
		
		String strResult = "";
		if (workStatusCode.equals("1")) {
			strResult = "未分配";
		} else if (workStatusCode.equals("2")) {
			strResult = "处理中";			
		} else if (workStatusCode.equals("3")) {
			strResult = "处理结束";			
		}
		return strResult;
	}
	
	public static String GetWorkStatusCode(String workStatus)
	{
		if (workStatus == null) {
			return "";
		}
		
		String strResult = "";
		if (workStatus.equals("未分配")) {
			strResult = "1";
		} else if (workStatus.equals("处理中")) {
			strResult = "2";			
		} else if (workStatus.equals("处理结束")) {
			strResult = "3";			
		}
		return strResult;
	}
	
	public static String GetPickupDeliveryStatusName(String statusCode)
	{
		if (statusCode == null) {
			return "";
		}
		
		String strResult = "";
		if (statusCode.equals("1")) {
			strResult = "未处理";
		} else if (statusCode.equals("2")) {
			strResult = "处理中";
		} else if (statusCode.equals("3")) {
			strResult = "已处理";
		}
		return strResult;
	}
	
	public static String GetOrderStatusName(String statusCode)
	{
		if (statusCode == null) {
			return "";
		}
		
		String strResult = "";
		if (statusCode.equals("1")) {
			strResult = "未下达";
		} else if (statusCode.equals("2")) {
			strResult = "已下达";
		}
		return strResult;
	}
}
