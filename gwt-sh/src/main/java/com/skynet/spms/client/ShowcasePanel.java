/*
 * Isomorphic SmartGWT web presentation layer
 * Copyright 2000 and beyond Isomorphic Software, Inc.
 *
 * OWNERSHIP NOTICE
 * Isomorphic Software owns and reserves all rights not expressly granted in this source code,
 * including all intellectual property rights to the structure, sequence, and format of this code
 * and to all designs, interfaces, algorithms, schema, protocols, and inventions expressed herein.
 *
 *  If you have any questions, please email <sourcecode@isomorphic.com>.
 *
 *  This entire comment must accompany any portion of Isomorphic Software source code that is
 *  copied or moved from this file.
 */

package com.skynet.spms.client;

import com.google.gwt.event.shared.HandlerManager;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.Layout;
import com.smartgwt.client.widgets.layout.VLayout;

public abstract class ShowcasePanel extends VLayout {

	protected String dataSourceName;
	protected String moduleName;
	protected HandlerManager eventBus;

	public ShowcasePanel() {
		this("", "", null);
	}

	public ShowcasePanel(String moduleName, String dataSourceName,
			HandlerManager eventBus) {
		this.moduleName = moduleName;
		this.dataSourceName = dataSourceName;
		this.eventBus = eventBus;
		
		setWidth("99%");
		setHeight("99%");		
		
		boolean topIntro = isTopIntro();
		Layout layout = topIntro ? new VLayout() : new HLayout();
//
		layout.setWidth100();
		layout.setMargin(1);
		layout.setMembersMargin(2);

		Canvas panel = getViewPanel();
		
		HLayout wrapper = new HLayout();
		wrapper.setWidth100();
		wrapper.addMember(panel);

		layout.addMember(wrapper);
		addMember(layout);
	}

	protected boolean isTopIntro() {
		return false;
	}

	

	public abstract Canvas getViewPanel();

}
