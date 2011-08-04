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

import java.util.logging.Logger;

import com.google.gwt.event.shared.HandlerManager;
import com.skynet.spms.client.data.PanelMapFactrory;
import com.skynet.spms.client.event.UserLogoutEvent;
import com.skynet.spms.client.event.UserLogoutEventHandler;
import com.smartgwt.client.SmartGwtEntryPoint;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * 入口点类    <code>onModuleLoad()</code>.
 */
public class SpmsMain extends SmartGwtEntryPoint {
    
	private Logger log = Logger.getLogger("main entry");

	//声明内容面板
    private MainTabPanel tabPanel;

    //声明左侧导航栏树状目录面板
	private NviTreePanel nviTreePanel;
	
	//声明顶部工具条面板
	private ToolBarPanel toolbarPanel;

	private void registerGlobeEvent(HandlerManager eventBus) {
		//用户注销事件处理
		eventBus.addHandler(UserLogoutEvent.HANDLER,
				new UserLogoutEventHandler() {
					public void onUserLogout(UserLogoutEvent event) {
						com.google.gwt.user.client.Window.open(
								"./j_spring_security_logout",
								"_self", null);
					}

				});

		PanelMapFactrory.initPanelMap(eventBus);

		tabPanel = new MainTabPanel(eventBus);

		log.info("start nvi tree");
		
		nviTreePanel = new NviTreePanel(eventBus);
		
		log.info("start toolbar tree");
		toolbarPanel=new ToolBarPanel(eventBus);
	}

	//程序入口点
	@Override
	public void onModuleLoad() {
		//实例化全局事件管理器
		HandlerManager eventBus = new HandlerManager(null);
		//注册全局事件
		registerGlobeEvent(eventBus);

		//实例化垂直布局器，即主布局器
		VLayout mainlayout = new VLayout();

		mainlayout.setWidth("99%");
		mainlayout.setHeight("99%");
		mainlayout.setLayoutMargin(0);
		mainlayout.setStyleName("tabSetContainer");

		//主布局器内加载顶层工具条面板
		mainlayout.addMember( toolbarPanel.getToolStrip());

		//实例化水平布局器，即内容布局器
		HLayout contentlayout = new HLayout();
		contentlayout.setWidth100();
		contentlayout.setHeight100();

		//实例化垂直布局器，加载左侧导航栏目录树布局器
		VLayout sideNavLayout = nviTreePanel.generTreeLayout();
		sideNavLayout.setWidth("0%");		
		
		//内容布局器内加载左侧导航栏目录树布局器
		contentlayout.addMember(sideNavLayout);

		Canvas canvas = tabPanel.generRoadLayout();
		contentlayout.addMember(canvas);	
        
		//主布局器内加载内容布局器
		mainlayout.addMember(contentlayout);
		//绘制主布局器
		mainlayout.draw();

	}	

}
