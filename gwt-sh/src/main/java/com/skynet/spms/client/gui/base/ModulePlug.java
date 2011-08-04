package com.skynet.spms.client.gui.base;

import java.util.Map;
import com.skynet.spms.client.PanelFactory;

public interface ModulePlug {

	public void plug(Map<String,PanelFactory> map);
}
