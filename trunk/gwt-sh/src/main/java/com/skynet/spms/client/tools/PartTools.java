package com.skynet.spms.client.tools;
import com.skynet.spms.client.vo.PartInfoVO;
import com.skynet.spms.client.gui.base.CodeRPCTool;
public class PartTools {
	private static PartInfoVO partInfoVO;

	public static PartInfoVO getPartInfoVO() {
		return partInfoVO;
	}

	public static void setPartInfoVO(PartInfoVO partInfoVO) {
		PartTools.partInfoVO = partInfoVO;
	}


	
	public static PartInfoVO getPartInfoVOByPartNumber(String partNumber){
		CodeRPCTool.getPartInfoByPartNumber(partNumber);
		return partInfoVO;
	}
	
}
