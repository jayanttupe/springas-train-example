package com.skynet.spms.client;

import java.util.logging.Logger;

import com.google.gwt.core.client.GWT; 
import com.google.gwt.event.shared.HandlerManager;
import com.skynet.spms.client.constants.ConstantsUtil;
import com.skynet.spms.client.constants.NavigationConst;
import com.skynet.spms.client.constants.PortalConstants;
import com.skynet.spms.client.constants.PortalMessage;
import com.skynet.spms.client.data.PanelMapFactrory;
import com.skynet.spms.client.event.RefreshModuleDetailEvent;
import com.skynet.spms.client.event.RefreshModuleDetailEventHandler;
import com.skynet.spms.client.gui.portal.PortalPanel;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;
import com.smartgwt.client.widgets.tab.events.TabSelectedEvent;
import com.smartgwt.client.widgets.tab.events.TabSelectedHandler;
import com.smartgwt.client.widgets.menu.Menu;
import com.smartgwt.client.widgets.menu.MenuItem;
import com.smartgwt.client.widgets.menu.events.ClickHandler;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;
import com.smartgwt.client.core.KeyIdentifier;

/*
 * 主tabset容器类
 */
public class MainTabPanel {


	private Logger log = Logger.getLogger("main tab panel");

	private static final String TAB_TAIL = "_tab";

	//构建tabset容器，用于放置不同业务面板
	private TabSet mainTabSet = new TabSet();

	private Label cusRoadLabel;
	
	private NavigationConst navConst = ConstantsUtil.navigationConstants;

	private PortalConstants portalConst=GWT.create(PortalConstants.class);
	
	private PortalMessage roadMsg=GWT.create(PortalMessage.class);
	
	private Menu contextMenu;
	
	public MainTabPanel(final HandlerManager eventBus) {
		//初始化tabset控件
		initTabSet(eventBus);
		contextMenu = createContextMenu();
		//当前面板路径信息，暂时本版本中不使用
		cusRoadLabel = new Label(navConst.tabIndex());
        
		log.info("finish menu init ");
		eventBus.addHandler(RefreshModuleDetailEvent.TYPE,
				new RefreshModuleDetailEventHandler() {

					@Override
					public void onSeleModuleDetail(
							RefreshModuleDetailEvent event) {
						log.info(event.getSeleModName());
						//依据触发的事件打开tab
						showTab(event);
					}
				});

		mainTabSet.addTabSelectedHandler(new TabSelectedHandler() {
			@Override
			public void onTabSelected(TabSelectedEvent event) {
				Tab tab = event.getTab();
				
				cusRoadLabel.setContents(roadMsg.modPath(tab.getAttributeAsString("path")));
				

			}
		});
	}

	//初始化tabset
	private void initTabSet(HandlerManager eventBus) {

		mainTabSet.setWidth100();
		mainTabSet.setHeight100();
		mainTabSet.setTop(20);
		
		//首先构建门户面板，门户面板将始终在tabset的最前面，并且不能被删除。
		PortalPanel panel=new PortalPanel(eventBus);
		//构建门户的tab容器，并将门户面板放置于其内
		Tab tab=panel.getTab();
		tab.setIcon("silk/house.png");
//		tab.setAttribute("path",portalConst.protalTab());
		//在tabset容器中添加门户的tab子容器
		mainTabSet.addTab(tab);
		log.info("finish main tab init");
	}

	public Canvas generRoadLayout() {
		VLayout canvas = new VLayout();
		canvas.setWidth100();
		canvas.setHeight100();

		cusRoadLabel.setHeight("20px");
//		canvas.addMember(cusRoadLabel);

		canvas.addMember(mainTabSet);
		return canvas;
	}

	//构建打开新建或者已经构建的tab容器
	public void showTab(RefreshModuleDetailEvent event) {
		
		String modName=event.getSeleModName();
		String desc=event.getDesc();

		//依据模块名称，构建业务面板工厂
		PanelFactory factory = PanelMapFactrory.getFactory(modName);
		if (factory == null) {
			return;
		}

		//设置tab的id名称，用于匹配是否已经构建了该tab，如果已经构建则设置该tab为选取状态，否则构建新的tab容器
		String tabID = modName + TAB_TAIL;
		Tab tab = mainTabSet.getTab(tabID);
		if (tab != null) {
			mainTabSet.selectTab(tab);
			return;
		}

		//构建新的tab容器
		tab = new Tab();
        //依据业务面板工厂构建业务面板
		Canvas panel = factory.create();
		//无效代码，可考虑删除
		if(event.getBusinessKey()!=null){}
		//构建业务面板与tab容器的关联关系  
		tab.setPane(panel);

		//tab.setIcon("silk/plugin.png");
		String imgHTML = Canvas.imgHTML("silk/plugin.png");
		tab.setTitle(desc);
		tab.setTitle("<span>" + imgHTML + "&nbsp;" + desc + "</span>");
		tab.setID(modName + TAB_TAIL);
		//新构建的tab容器均允许被关闭
		tab.setCanClose(true);
        //设置当前tab的上下文菜单
		tab.setContextMenu(contextMenu);
		//主tabset容器中放置构架的子tab容器，并设置当前的子tab容器为选定状态
		mainTabSet.addTab(tab);
		mainTabSet.selectTab(tab);
		
		tab.setAttribute("path", event.getPathDesc());
		cusRoadLabel.setContents(roadMsg.modPath(event.getPathDesc()));

        //tab.setContextMenu(contextMenu);
		// mainTabSet.markForRedraw();
		// if (!SC.isIE()) {
		// if (mainTabSet.getNumTabs() == 10) {
		// mainTabSet.removeTabs(new int[] { 1 });
		// }
		// }
		// History.newItem(modName, false);
	}
	
	//构建tab容器的上下文菜单，用于鼠标右键弹出控制菜单，来关闭tab容器
	public Menu createContextMenu() {
        Menu menu = new Menu();
        menu.setWidth(140);
        menu.setShowShadow(true);
        
//      如果tab的序号为0，则关闭上下文菜单的使用          
//        MenuItemIfFunction enableCondition = new MenuItemIfFunction() {
//            public boolean execute(Canvas target, Menu menu, MenuItem item) {
//                int selectedTab = mainTabSet.getSelectedTabNumber();
//                return selectedTab != 0;
//            }
//        };

        MenuItem closeItem = new MenuItem("<u>C</u>关闭");
//        closeItem.setEnableIfCondition(enableCondition);
        closeItem.setKeyTitle("Alt+C");
        KeyIdentifier closeKey = new KeyIdentifier();
        closeKey.setAltKey(true);
        closeKey.setKeyName("C");
        closeItem.setKeys(closeKey);
        closeItem.addClickHandler(new ClickHandler() {
            public void onClick(MenuItemClickEvent event) {
                int selectedTab = mainTabSet.getSelectedTabNumber();
                if (selectedTab != 0) {
                mainTabSet.removeTab(selectedTab);
                mainTabSet.selectTab(selectedTab - 1);
                }
            }
        });

        MenuItem closeAllButCurrent = new MenuItem("只保留当前");
//        closeAllButCurrent.setEnableIfCondition(enableCondition);
        closeAllButCurrent.addClickHandler(new ClickHandler() {
            public void onClick(MenuItemClickEvent event) {
                int selected = mainTabSet.getSelectedTabNumber();
                Tab[] tabs = mainTabSet.getTabs();
                int[] tabsToRemove = new int[tabs.length - 2];
                int cnt = 0;
                for (int i = 1; i < tabs.length; i++) {
                    if (i != selected) {
                        tabsToRemove[cnt] = i;
                        cnt++;
                    }
                }
                mainTabSet.removeTabs(tabsToRemove);
            }
        });

        MenuItem closeAll = new MenuItem("关闭所有");
//        closeAll.setEnableIfCondition(enableCondition);
        closeAll.addClickHandler(new ClickHandler() {
            public void onClick(MenuItemClickEvent event) {
                Tab[] tabs = mainTabSet.getTabs();
                int[] tabsToRemove = new int[tabs.length - 1];

                for (int i = 1; i < tabs.length; i++) {
                    tabsToRemove[i - 1] = i;
                }
                mainTabSet.removeTabs(tabsToRemove);
                mainTabSet.selectTab(0);
            }
        });

        menu.setItems(closeItem, closeAllButCurrent, closeAll);
        return menu;
    }
}
