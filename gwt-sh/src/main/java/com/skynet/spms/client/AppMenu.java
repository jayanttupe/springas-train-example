package com.skynet.spms.client;

import java.util.logging.Level;
import java.util.logging.Logger;


import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.http.client.UrlBuilder;
import com.google.gwt.i18n.client.LocaleInfo;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.Window.Location;
import com.skynet.spms.client.constants.NavigationConst;
import com.skynet.spms.client.constants.NavigationMsg;
import com.skynet.spms.client.event.UserLogoutEvent;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.FormMethod;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.HTMLPane;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.events.CloseClientEvent;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.CheckboxItem;
import com.smartgwt.client.widgets.form.fields.HiddenItem;
import com.smartgwt.client.widgets.form.fields.PasswordItem;
import com.smartgwt.client.widgets.form.fields.SubmitItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.menu.Menu;
import com.smartgwt.client.widgets.menu.MenuItem;
import com.smartgwt.client.widgets.menu.MenuItemSeparator;
import com.smartgwt.client.widgets.menu.events.ClickHandler;
import com.smartgwt.client.widgets.menu.events.ItemClickEvent;
import com.smartgwt.client.widgets.menu.events.ItemClickHandler;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;
import com.smartgwt.client.widgets.toolbar.ToolStripMenuButton;

//应用程序主菜单类
public class AppMenu {

	private Logger log = Logger.getLogger("AppMenu Panel");

	private NavigationConst navConst=GWT.create(NavigationConst.class);
	
	private NavigationMsg navMsg=GWT.create(NavigationMsg.class);

	
	private LogGrid logGrid=new LogGrid();
	
	public static AppMenu getInstance() {
		return new AppMenu();
	}

	public ToolStripMenuButton getLocalMenu() {
		final Menu localMenu = new Menu();

		String[] localeNames = LocaleInfo.getAvailableLocaleNames();
		for (String localeName : localeNames) {
			String nativeName = LocaleInfo
					.getLocaleNativeDisplayName(localeName);
			if (nativeName == null) {
				continue;
			}
			log.info("local name:" + localeName + " nativeName:" + nativeName);
			MenuItem item = new MenuItem(nativeName);
			item.setAttribute("locale", localeName);
			localMenu.addItem(item);
		}

		localMenu.addItemClickHandler(new ItemClickHandler() {

			@Override
			public void onItemClick(ItemClickEvent event) {

				MenuItem item = event.getItem();

				String localeName = item.getAttribute("locale");

				log.log(Level.INFO, "sele item:" + item.getTitle() + " local:"
						+ localeName);

				UrlBuilder builder = Location.createUrlBuilder().setParameter(
						"locale", localeName);
				Window.Location.replace(builder.buildString());

			}

		});

		ToolStripMenuButton menuButton = new ToolStripMenuButton(navConst.langSele(),
				localMenu);
		return menuButton;
	}

	public ToolStripMenuButton getLoginMenu(final HandlerManager eventBus,
			String userName) {

		final Menu loginMenu = new Menu();
        loginMenu.setShowShadow(true);
		final MenuItem relogin = new MenuItem();
		final MenuItem logout = new MenuItem();

		relogin.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(MenuItemClickEvent event) {
				final com.smartgwt.client.widgets.Window winModal = getFullScreenWin();
				winModal.addCloseClickHandler(new CloseClickHandler() {
					public void onCloseClick(CloseClientEvent event) {
						winModal.destroy();
					}
				});
				winModal.show();

			}
		});

		logout.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(MenuItemClickEvent event) {
				eventBus.fireEvent(new UserLogoutEvent());
			}

		});

		relogin.setTitle(navConst.loginWithOther());
		loginMenu.addItem(relogin);
		logout.setTitle(navMsg.getLogoutMsg(userName));
		loginMenu.addItem(logout);

		ToolStripMenuButton menuButton = new ToolStripMenuButton(navConst.loginMenu(),
				loginMenu);
		return menuButton;
	}

	String[][] themeArray = new String[][] { { "BlackOps", "Black Ops Theme" },
			{ "fleet", "Fleet Theme" }, { "SilverWave", "Silver Wave Theme" },
			{ "Graphite", "Graphite Theme" },
			{ "TreeFrog", "Tree Frog Theme" },
			{ "Simplicity", "Simplicity Theme" } };

	//
	public ToolStripMenuButton getSkinList() {
		final Menu skinMenu = new Menu();

		for (String[] info : themeArray) {
			MenuItem item = new MenuItem(info[1]);
			item.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(MenuItemClickEvent event) {
					for (MenuItem item : event.getMenu().getItems()) {
						item.setChecked(false);
					}
					MenuItem click = event.getItem();
					click.setChecked(true);

					int idx = skinMenu.getItemNum(click);
					log.log(Level.INFO, "col:" + idx);
					String theme = themeArray[idx][0];
					log.log(Level.INFO, theme);
					Cookies.setCookie("skin", theme);
					Location.reload();

				}

			});

			skinMenu.addItem(item);
		}
		//
		ToolStripMenuButton menuButton = new ToolStripMenuButton("theme",
				skinMenu);
		return menuButton;
	}

	public com.smartgwt.client.widgets.Window getFullScreenWin() {
		final com.smartgwt.client.widgets.Window winModal = new com.smartgwt.client.widgets.Window();
		winModal.setWidth(360);
		winModal.setHeight(205);
		winModal.setTitle(navConst.loginWin());
		winModal.setShowMinimizeButton(false);
		winModal.setIsModal(true);

		winModal.setShowModalMask(true);
		winModal.centerInPage();

		DynamicForm form = getLoginForm();
		winModal.addItem(form);

		return winModal;
	}
	
	
	public com.smartgwt.client.widgets.Window getAboutScreenWin() {
		final com.smartgwt.client.widgets.Window winModal = new com.smartgwt.client.widgets.Window();
		winModal.setWidth(750);
		winModal.setHeight(600);
		winModal.setTitle("关于");
		winModal.setShowMinimizeButton(false);
		winModal.setIsModal(false);
        winModal.setShowShadow(true);  
		winModal.setShowModalMask(false);
		winModal.centerInPage();

		HTMLPane aboutPanel = getAboutPanel();
		winModal.addItem(aboutPanel);

		return winModal;
	}
	
	private HTMLPane getAboutPanel(){
		HTMLPane aboutPanel = new HTMLPane();
		aboutPanel.setContentsURL(GWT.getHostPageBaseURL()+"about.html");
		return aboutPanel;
	}

	private DynamicForm getLoginForm() {
		DynamicForm form = new DynamicForm();
		form.setCanSubmit(true);
		form.setMethod(FormMethod.POST);
		form.setHeight100();
		form.setWidth100();
		form.setPadding(5);
		form.setLayoutAlign(VerticalAlignment.BOTTOM);

		TextItem userItem = new TextItem();
		userItem.setTitle("Username");
		userItem.setName("j_username");

		PasswordItem pwdItem = new PasswordItem();
		pwdItem.setTitle("Password");
		pwdItem.setName("j_password");

		CheckboxItem rememberItem = new CheckboxItem();
		rememberItem.setTitle("remember me");
		rememberItem.setName("_spring_security_remember_me");

		SubmitItem submitItem = new SubmitItem();
		submitItem.setTitle("login");

		form.setAction("./j_spring_security_check");
		
		HiddenItem  targetItem=new HiddenItem();
		targetItem.setName("target");
		targetItem.setValue("spms");
		
		HiddenItem locItem=new HiddenItem();
		locItem.setName("locale");
		locItem.setValue(LocaleInfo.getCurrentLocale().getLocaleName());
		
		form.setFields(userItem, pwdItem, rememberItem, submitItem,targetItem,locItem);

		return form;
	}

	//构建帮助弹出菜单
	public ToolStripMenuButton getHelpMenu() {
		Menu helpMenuBar = new Menu();
		helpMenuBar.setShowShadow(true);
		
		MenuItem helpMenu = new MenuItem();		
		helpMenu.setTitle("帮助");
		helpMenuBar.addItem(helpMenu);
		
		MenuItem aboutMenu = getAboutMenu();
		helpMenuBar.addItem(aboutMenu);
		//增加弹出菜单的分割符号
		MenuItemSeparator separator = new MenuItemSeparator();
		helpMenuBar.addItem(separator);
		
		MenuItem adminConsoleMenu = getAdminMenu();
		helpMenuBar.addItem(adminConsoleMenu);
		
		MenuItem logMenu=getLogMenu();
		helpMenuBar.addItem(logMenu);
		
		ToolStripMenuButton helpMenuButton = new ToolStripMenuButton("帮助",
				helpMenuBar);
		return helpMenuButton;
	}

	private MenuItem getLogMenu() {
		MenuItem logMenu = new MenuItem();
		logMenu.setTitle(navConst.logMenuName());
		
		logMenu.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(MenuItemClickEvent event) {
				logGrid.popupWin();			
			}
			
		});
		return logMenu;
	}

	private MenuItem getAdminMenu() {
		MenuItem adminConsoleMenu = new MenuItem();
			
		//增加管理员控制台的弹出窗口
		adminConsoleMenu.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(MenuItemClickEvent event) {
			   SC.showConsole();
			}
		});
		
		adminConsoleMenu.setTitle("管理控制台");

		return adminConsoleMenu;
	}

	private MenuItem getAboutMenu() {
		MenuItem aboutMenu = new MenuItem();
		aboutMenu.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(MenuItemClickEvent event) {
				// TODO Auto-generated method stub
				final com.smartgwt.client.widgets.Window winModal = getAboutScreenWin();
				
				winModal.addCloseClickHandler(new CloseClickHandler() {
					public void onCloseClick(CloseClientEvent event) {
						winModal.destroy();
					}
				});
				winModal.show();
			}
		});
		aboutMenu.setTitle("关于");
		return aboutMenu;
	}

}
