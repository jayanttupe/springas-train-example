package com.skynet.spms.client.gui.customerService.commonui;

/**
 * 继承BaseListGrid 增加 表格的自带删除功能，即行右侧的删除，该删除功能没有提示
 * 
 * @author fl
 * 
 */
public abstract class BaseRightListGrid extends BaseListGrid {
	public BaseRightListGrid() {
		super();
		setCanRemoveRecords(true);
	}
}
