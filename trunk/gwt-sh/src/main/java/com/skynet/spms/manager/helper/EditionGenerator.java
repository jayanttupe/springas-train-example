package com.skynet.spms.manager.helper;


public class EditionGenerator {

	public static String getNewEditionNumber(){
		return "0001";
	}
	public synchronized static String getNextEditionNumber(String prevNumber){
		int n = Integer.parseInt(prevNumber);
		String sn = String.valueOf(n+1);
		int len = sn.length();
		for(int i=0; i<4-len; i++){
			sn = "0" + sn;
		}
		
		return sn;
	}
}
