package com.skynet.spms.client.gui.base;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.skynet.spms.client.entity.FeatureInfo;
import com.skynet.spms.client.entity.ModuleDetail;
import com.skynet.spms.client.service.ModuleInfoService;
import com.skynet.spms.client.service.ModuleInfoServiceAsync;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.toolbar.ToolStrip;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

public abstract class BaseButtonToolStript extends ToolStrip {
	protected Window useWindow;

	public HandlerManager handlerManager = new HandlerManager(null);

	public BaseButtonToolStript() {

	}

	protected BaseButtonToolStript(String moduleName) {
		setWidth100();
		setHeight(28);

		ModuleInfoServiceAsync service = GWT.create(ModuleInfoService.class);
		service.getModuleDetail(moduleName, new AsyncCallback<ModuleDetail>() {

			@Override
			public void onSuccess(ModuleDetail detail) {
				removeMembers(getMembers());

				for (FeatureInfo info : detail.getFeatureList()) {

					final FeatureInfo param = info;

					final String name = param.getName();

					// log.log(Level.INFO,name);
					final ToolStripButton btn = info.getButton();
					// 设置各个按钮的icon
					setButtonIncon(name, btn);
					btn.addClickHandler(new ClickHandler() {

						@Override
						public void onClick(ClickEvent event) {
							// 根据按钮的名称设置打开的窗体
							showWindow(name, btn);

							// handlerManager.fireEvent(new
							// ClickFeatureEvent(name,modName));
						}
					});
					addButton(btn);
					addSeparator();
				}
				handlerManager.fireEvent(new FeatureLoadCompleteEvent());

			}

			@Override
			public void onFailure(Throwable arg0) {
				// TODO Auto-generated method stub

			}
		});

	}

	protected BaseButtonToolStript(String moduleName, final int startPos,
			final int endPos) {
		setWidth100();
		setHeight(30);
		ModuleInfoServiceAsync service = GWT.create(ModuleInfoService.class);
		service.getModuleDetail(moduleName, new AsyncCallback<ModuleDetail>() {

			@Override
			public void onSuccess(ModuleDetail detail) {
				// TODO Auto-generated method stub
				removeMembers(getMembers());
				List<FeatureInfo> infoList = detail.getFeatureList();
				if (startPos > endPos || startPos >= infoList.size() - 1
						|| endPos >= infoList.size() - 1) {
					for (FeatureInfo info : infoList) {

						final FeatureInfo param = info;

						final String name = param.getName();
						final ToolStripButton btn = info.getButton();
						// 设置各个按钮的icon
						setButtonIncon(name, btn);
						btn.addClickHandler(new ClickHandler() {

							@Override
							public void onClick(ClickEvent event) {
								// 根据按钮的名称设置打开的窗体
								showWindow(name, btn);

								// handlerManager.fireEvent(new
								// ClickFeatureEvent(name,modName));
							}
						});
						addButton(btn);
						addSeparator();
					}
					handlerManager.fireEvent(new FeatureLoadCompleteEvent());
				} else {

					for (int i = startPos; i <= endPos; i++) {

						final String name = ((FeatureInfo) infoList.get(i))
								.getName();
						final ToolStripButton btn = ((FeatureInfo) infoList
								.get(i)).getButton();
						// 设置各个按钮的icon
						setButtonIncon(name, btn);
						btn.addClickHandler(new ClickHandler() {

							@Override
							public void onClick(ClickEvent event) {
								// 根据按钮的名称设置打开的窗体
								showWindow(name, btn);

								// handlerManager.fireEvent(new
								// ClickFeatureEvent(name,modName));
							}
						});
						addButton(btn);
						addSeparator();

					}
					handlerManager.fireEvent(new FeatureLoadCompleteEvent());
				}

			}

			@Override
			public void onFailure(Throwable arg0) {
				// TODO Auto-generated method stub

			}
		});

	}

	protected BaseButtonToolStript(String moduleName, final String[] btnNames) {
		final List list = new ArrayList();
		for (String button : btnNames)
			list.add(button);
		setWidth100();
		setHeight(30);
		ModuleInfoServiceAsync service = GWT.create(ModuleInfoService.class);
		service.getModuleDetail(moduleName, new AsyncCallback<ModuleDetail>() {

			@Override
			public void onSuccess(ModuleDetail detail) {
				// TODO Auto-generated method stub
				removeMembers(getMembers());
				List<FeatureInfo> infoList = detail.getFeatureList();
				if (btnNames == null || "".equals(btnNames)) {
					for (FeatureInfo info : infoList) {

						final FeatureInfo param = info;

						final String name = param.getName();
						final ToolStripButton btn = info.getButton();
						// 设置各个按钮的icon
						setButtonIncon(name, btn);
						bindClickListener(name, btn);
						addButton(btn);
						addSeparator();
						//

					}
					handlerManager.fireEvent(new FeatureLoadCompleteEvent());
				} else {
					for (FeatureInfo info : infoList) {

						final FeatureInfo param = info;

						final String name = param.getName();
						if (list.contains(name)) {
							final ToolStripButton btn = info.getButton();
							// 设置各个按钮的icon
							setButtonIncon(name, btn);
							btn.addClickHandler(new ClickHandler() {

								@Override
								public void onClick(ClickEvent event) {
									// 根据按钮的名称设置打开的窗体
									showWindow(name, btn);

								}
							});
							addButton(btn);
							addSeparator();

						}

					}
					handlerManager.fireEvent(new FeatureLoadCompleteEvent());
				}

			}

			@Override
			public void onFailure(Throwable arg0) {
				// TODO Auto-generated method stub

			}
		});

	}

	protected void bindClickListener(final String name,
			final ToolStripButton btn) {
		btn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// 根据按钮的名称设置打开的窗体
				showWindow(name, btn);

			}
		});
	}

	private void setButtonIncon(String buttonName, ToolStripButton button) {
		button.setIcon("icons/" + buttonName.toLowerCase() + ".png");
	}

	abstract protected void showWindow(String buttonName, ToolStripButton button);
}
