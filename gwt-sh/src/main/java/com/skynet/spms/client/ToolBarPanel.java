package com.skynet.spms.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.skynet.spms.client.constants.NavigationConst;
import com.skynet.spms.client.constants.NavigationMsg;
import com.skynet.spms.client.entity.ModuleItem;
import com.skynet.spms.client.entity.RootModulesEntity;
import com.skynet.spms.client.event.SeleRootModuleEvent;
import com.skynet.spms.client.event.SeleRootModuleEventHandler;
import com.skynet.spms.client.service.ModuleInfoService;
import com.skynet.spms.client.service.ModuleInfoServiceAsync;
import com.skynet.spms.client.tools.UserTools;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.menu.Menu;
import com.smartgwt.client.widgets.menu.MenuItem;
import com.smartgwt.client.widgets.menu.events.ClickHandler;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;
import com.smartgwt.client.widgets.toolbar.ToolStrip;
import com.smartgwt.client.widgets.toolbar.ToolStripMenuButton;

public class ToolBarPanel {
	
	/*GWT技术 延迟绑定 ModuleInfoService.class, 并将根据 @RemoteServiceRelativePath的value，提交
	servlet进行请求处理，在本地实例化一个异步回调的接口实例ModuleInfoServiceAsync。
	*/
	private ModuleInfoServiceAsync featureAction = GWT
	.create(ModuleInfoService.class);

	//构建应用程序工具条主菜单
	private AppMenu appMenuTool = AppMenu.getInstance();
	
	private ToolStrip modBar = new ToolStrip();

	private NavigationMsg navMsg=GWT.create(NavigationMsg.class);

	public Canvas getToolStrip(){
		
		return modBar;
	}

	//构建顶层工具栏菜单面板
	public ToolBarPanel(final HandlerManager eventBus) {

		modBar.setWidth("100%");
		
		//定义企业标志LOGO图片
		Img logoImg = new Img("logo.png");
		logoImg.setWidth("210px");
		logoImg.setHeight("25px");
		logoImg.setAutoFit(false);
	
		modBar.addMember(logoImg);

		featureAction.getRootModuleList(new AsyncCallback<RootModulesEntity>() {

			//建议派生一个AsyncCallback的子类，可以叫AsyncCallbackEx，其中重写onFailure的方法
			//并进行错误的日志记录，onSuccess可以抽象继承 曹宏炜 
			public void onFailure(Throwable caught) {

			}
			public void onSuccess(RootModulesEntity result) {
                //循环取出当前用户的模块并进行顶层工具栏菜单的装载
				for (ModuleItem item : result.getModulesList()) {
					ToolStripMenuButton btn = generButton(eventBus, item);
					btn.setIcon(item.getIcon());
					modBar.addMenuButton(btn);
//     				modBar.addSpacer(4);
					modBar.addSeparator();
				}
				//addFill()方法执行后，确保控件占位的稳定
				modBar.addFill();
				
				//定义用户名，并显示在顶层工具栏菜单内
//				Label labName=new Label();
//				labName.setContents("welcome: "+ result.getUserName() + " ");
//				labName.setAutoWidth();
				UserTools.setCurrentUserName( result.getUserName() );
				UserTools.setUserVo(result.getUserName());
				modBar.addMember(result.getUserInfoWidget(navMsg));
				modBar.addSeparator();
				//在顶层工具栏菜单中定义登陆管理菜单
				modBar.addMenuButton(appMenuTool.getLoginMenu(eventBus, result.getUserName()));
				//定义帮助管理菜单
				modBar.addMenuButton(appMenuTool.getHelpMenu());
			}

		}
		);
	}

    /*工具条菜单按钮生成
	子模块应该考虑菜单模块可以递归生成，曹宏炜，请开发小组予以考虑设计 
	*/
	private ToolStripMenuButton generButton(final HandlerManager eventBus,
			ModuleItem item) {

		final ToolStripMenuButton modBtn = new ToolStripMenuButton(item.getDesc());
		final String rootName = item.getName();
		modBtn.setIcon(item.getIcon());

		//构建弹出菜单
		final Menu menu = new Menu();
		//设置菜单的阴影效果
		menu.setShowShadow(true);
		menu.setShadowDepth(5);
		menu.setTitle(item.getDesc());
		for (final ModuleItem subItem : item.getSubModuleList()) {
			MenuItem menuItem = new MenuItem(subItem.getName());
			menuItem.setTitle(subItem.getDesc());
			menuItem.setIcon(subItem.getIcon());
			menuItem.addClickHandler(new ClickHandler() {

				public void onClick(MenuItemClickEvent event) {
					//构建顶层工具栏菜单选择事件
					SeleRootModuleEvent seleEvent = new SeleRootModuleEvent(
							rootName, subItem.getName());
					//通过事件总线触发按钮选择事件，该事件将被NviTreePanel中的eventBus.addHandler
					//(SeleRootModuleEvent.TYPE,new SeleRootModuleEventHandler())捕获
					eventBus.fireEvent(seleEvent);
				}
			});
			menu.addItem(menuItem);
		}

		modBtn.setMenu(menu);
		return modBtn;
	}
}
