package com.skynet.spms.client.constants;

import com.google.gwt.core.client.GWT;

public class ConstantsUtil {

	public static final CommonConstants commonConstants = GWT.create(CommonConstants.class);
	public static final ButtonConstants buttonConstants = GWT.create(ButtonConstants.class);
	public static final MessageConstants messageConstants = GWT.create(MessageConstants.class);
	public static final NavigationConst navigationConstants = GWT.create(NavigationConst.class);
	public static final OrganizationConstants organizationConstants = GWT.create(OrganizationConstants.class);
	public static final PartCatalogConstants partCatalogConstants = GWT.create(PartCatalogConstants.class); 
	public static final StockConstants stockConstants = GWT.create(StockConstants.class);
	private ConstantsUtil(){}
}
