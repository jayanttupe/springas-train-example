package com.skynet.spms.client.gui.portal;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.skynet.spms.client.constants.PortalConstants;
import com.skynet.spms.client.event.PortalLeaveSeleEvent;
import com.skynet.spms.client.event.PortalRefreshEvent;
import com.skynet.spms.client.gui.portal.LayoutManager.MemberFactory;
import com.skynet.spms.client.gui.portal.PortalConfig.ReDrawPortal;
import com.skynet.spms.client.service.ModuleInfoService;
import com.skynet.spms.client.service.ModuleInfoServiceAsync;
import com.skynet.spms.client.service.PollService;
import com.skynet.spms.client.service.PollServiceAsync;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.Layout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.menu.Menu;
import com.smartgwt.client.widgets.menu.MenuItem;
import com.smartgwt.client.widgets.menu.events.ClickHandler;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;
import com.smartgwt.client.widgets.tab.events.TabDeselectedEvent;
import com.smartgwt.client.widgets.tab.events.TabDeselectedHandler;
import com.smartgwt.client.widgets.tab.events.TabSelectedEvent;
import com.smartgwt.client.widgets.tab.events.TabSelectedHandler;

import edu.emory.mathcs.backport.java.util.Arrays;

public class PortalPanel {

	private static final String PORTAL_ID = "portal_tab_id";

	private Logger log = Logger.getLogger("Portal panel");

	private PortalConstants navConst = GWT.create(PortalConstants.class);

	private PollServiceAsync pollService = GWT.create(PollService.class);


	private final HandlerManager eventBus;

	private final Timer timer;

	private LayoutManager layoutMang;

	private MainFactory factory = new MainFactory();

	private Tab tab = new Tab();

	private MemberStore members;


	public PortalPanel(HandlerManager eventBusParam) {
		this.eventBus = eventBusParam;

		members = new MemberStore(eventBusParam);
		
		layoutMang=new LayoutManager();
		
		tab.setCanClose(false);

		tab.setID(PORTAL_ID);
		tab.setTitle(navConst.protalTab());

		tab.addTabSelectedHandler(new TabSelectedHandler() {

			@Override
			public void onTabSelected(TabSelectedEvent event) {
				log.info("portal tab sele");
				eventBus.fireEvent(new PortalRefreshEvent());

			}

		});
		tab.addTabDeselectedHandler(new TabDeselectedHandler() {

			@Override
			public void onTabDeselected(TabDeselectedEvent event) {
				log.info("portal tab de sele");
				tab.setTitle(navConst.protalTab());
				eventBus.fireEvent(new PortalLeaveSeleEvent());
			}

		});
		

		MenuItem item = new MenuItem();
		item.setTitle("门户配置");

		item.addClickHandler(new DisplayConfigHandler(layoutMang));

		Menu contextMenu = new Menu();
		contextMenu.addItem(item);
		tab.setContextMenu(contextMenu);

		timer = new Timer() {

			@Override
			public void run() {
				pollService.checkIsMsgReceive(new AsyncCallback<Boolean>() {

					@Override
					public void onFailure(Throwable caught) {
						log.warning(caught.getMessage());

					}

					@Override
					public void onSuccess(Boolean result) {
						log.info("is msg receive:" + result);
						if (result) {

							tab.setTitle(navConst.protaTabNewMsg());
						}

					}

				});
			}

		};
		// timer.scheduleRepeating(1000*60);

	}

	public Tab getTab() {

		tab.setPane(layoutMang.getLayout(factory));
		return tab;
	}

	private class DisplayConfigHandler implements ClickHandler {

		private final LayoutManager layoutMang;

		public DisplayConfigHandler(LayoutManager layoutMang2) {
			this.layoutMang=layoutMang2;
		}

		@Override
		public void onClick(MenuItemClickEvent event) {
			PortalConfig config=layoutMang.getConfig();
			
			config.displayWin(layoutMang, new ReDrawPortal() {

				@Override
				public void doReDraw(LayoutManager layoutMang) {
					TabSet tabSet = tab.getTabSet();

					tabSet.updateTab(tab, layoutMang.getLayout(factory));

					log.info("update portal tab");
				}
			});

		}

	};

	public class MainFactory implements MemberFactory {

		@Override
		public Canvas getMember(String name) {
			PortalMember portalMember = members.getMemberByName(name);
			Canvas canvas = portalMember.getCanvas();

			Window win = new Window();
			win.addItem(canvas);
			win.setWidth100();
			win.setShowTitle(true);
			win.setTitle(factory.getDescription(name));
			win.setShowEdges(true);
			win.setMargin(1);
			return win;
		}

		@Override
		public String getDescription(String name) {
			PortalMember portalMember = members.getMemberByName(name);
			return portalMember.getDescription();
		}

		@Override
		public HLayout getHLayout() {
			return new HLayout();
		}

		@Override
		public VLayout getVLayout() {
			return new VLayout();
		}

	}

	public static interface PortalMember {
		Canvas getCanvas();

		String getName();

		String getDescription();
	}

}
