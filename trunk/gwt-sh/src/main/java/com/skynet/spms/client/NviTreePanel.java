package com.skynet.spms.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.skynet.spms.client.entity.ViewModuleTree;
import com.skynet.spms.client.event.RefreshModuleDetailEvent;
import com.skynet.spms.client.event.SeleRootModuleEvent;
import com.skynet.spms.client.event.SeleRootModuleEventHandler;
import com.skynet.spms.client.service.ModuleInfoService;
import com.skynet.spms.client.service.ModuleInfoServiceAsync;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tree.Tree;
import com.smartgwt.client.widgets.tree.TreeGrid;
import com.smartgwt.client.widgets.tree.TreeNode;
import com.smartgwt.client.widgets.tree.events.LeafClickEvent;
import com.smartgwt.client.widgets.tree.events.LeafClickHandler;

/*
   左侧导航来树状目录控制类
*/
public class NviTreePanel {
    /*GWT延迟绑定模块信息，通过servlet处理映射绑定后台
	com.skynet.spms.web.control.ModuleInfoAction
	*/
	private ModuleInfoServiceAsync featureAction = GWT
			.create(ModuleInfoService.class);

	private TreeGrid treeGrid;
	
	VLayout sideNavLayout = new VLayout();

    //构建导航栏树状目录面板
	public NviTreePanel(final HandlerManager eventBus) {
		treeGrid = new TreeGrid();
		treeGrid.setHeight100();
		treeGrid.setShowRoot(true);
		treeGrid.setCanSort(false);
				
		//添加目录树最终节点的鼠标点击事件处理器
		treeGrid.addLeafClickHandler(new LeafClickHandler() {
			@Override
			//鼠标点击目录树最终节点触发事件
			public void onLeafClick(LeafClickEvent event) {
				TreeNode node = event.getLeaf();
				String name = node.getName();
				TreeNode[] parentArray=treeGrid.getTree().getParents(node);
				RefreshModuleDetailEvent modEvent = new RefreshModuleDetailEvent(
						name,node.getAttribute("desc"));
				modEvent.setPathArray(parentArray);
				eventBus.fireEvent(modEvent);

			}
		});
        
		/*构建顶层工具栏菜单按钮选择的事件捕捉处理，当顶层工具栏菜单子菜单选择按钮被Click时，
		本函数被调用,将实现树状目录的刷新操作。
		*/
		eventBus.addHandler(SeleRootModuleEvent.TYPE,
				new SeleRootModuleEventHandler() {

					@Override
					public void onSeleRootModule(SeleRootModuleEvent event) {

						final String modName = event.getSeleModName();
						final String subModName = event.getSubItem();
						
						sideNavLayout.setWidth("20%");
//						treeGrid.setFields(new TreeGridField("desc", desc));

						featureAction.getModuleTree(modName,
								new AsyncCallback<ViewModuleTree>() {

									@Override
									public void onFailure(Throwable caught) {

									}

									@Override
									public void onSuccess(ViewModuleTree result) {
										Tree tree = result.generTree();

										treeGrid.setData(tree);
										TreeNode record = tree.find("name",
												subModName);
										if (tree.isFolder(record)) {
											tree.openFolder(record);
										}
										treeGrid.selectSingleRecord(record);
									}

								});
					}

				});

		
	}

	public VLayout generTreeLayout() {
		sideNavLayout.setHeight100();
		sideNavLayout.setShowResizeBar(true);

		// 树显示页面

		sideNavLayout.addMember(treeGrid);
		sideNavLayout.setWidth("20%");
		return sideNavLayout;
	}

	

}
