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

import com.smartgwt.client.widgets.Canvas;

/**
 * @author 曹宏炜
 * 面板工厂接口类
 *
 */
public interface PanelFactory {

	/**
	 * @return 接口函数定义返回一个Canvas类，该类抽象了DHTML浏览器页面
	 */
	Canvas create();

	String getID();

	String getDescription();

}
