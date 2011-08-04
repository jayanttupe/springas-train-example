package com.skynet.spms.client.gui.base;

import com.skynet.spms.client.gui.base.i18n.I18n;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.grid.ListGrid;

public class ValidateUtil {
	private static I18n ui = new I18n();

	public static boolean isRecordSelected(ListGrid grid) {
		if (grid.getSelection().length == 0) {
			SC.warn(ui.getB().msgOpSeletedData());
			return false;
		} else {
			return true;
		}
	}
}
