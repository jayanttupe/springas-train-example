package com.skynet.spms.client.gui.customerService.repairService.repairContract.plugin;

import com.smartgwt.client.widgets.grid.ListGrid;


public abstract class MockView {

	public ListGrid grid;

	public ListGrid getGrid() {
		return grid;
	}

	public void setGrid(ListGrid grid) {
		this.grid = grid;
	}
	

}
