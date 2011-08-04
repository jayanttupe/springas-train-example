package com.skynet.spms.client.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.smartgwt.client.types.TreeModelType;
import com.smartgwt.client.widgets.tree.Tree;
import com.smartgwt.client.widgets.tree.TreeNode;

public class ViewModuleTree  implements IsSerializable {
		
	private static Logger log = Logger.getLogger("View Module Tree");

	private List<ViewModuleTree> subModuleList=new ArrayList<ViewModuleTree>();
	
	private ModuleItem item;

		
	public ViewModuleTree(){
		
	}
	
	public ViewModuleTree(ModuleItem item){
		this.item=item;
	}
	
	public void addSubModule(ViewModuleTree subTree){
		subModuleList.add(subTree);		
	}
	
	public Tree generTree(){
		 Tree moduleTree = new Tree();
		 moduleTree.setModelType(TreeModelType.CHILDREN);
		 moduleTree.setShowRoot(true);

		 moduleTree.setNameProperty("desc");
		 moduleTree.setAutoOpenRoot(true);
		 
		 moduleTree.setChildrenProperty("subModule");
		 TreeNode treeNode=new ViewNode(this);
		 
		 moduleTree.setRoot(treeNode);
		 
		 return moduleTree;
	}
	
	public static class ViewNode extends TreeNode{
				
		public ViewNode(ViewModuleTree tree){
			log.log(Level.INFO,"tree node:"+tree.item.getName());
			setAttribute("name", tree.item.getName());  
			setAttribute("desc", tree.item.getDesc());			
					
			if(!tree.subModuleList.isEmpty()){
				setIcon("silk/resultset_next.png");	
				ViewNode[] subList=new ViewNode[tree.subModuleList.size()];
				int idx=0;
				for(ViewModuleTree sub:tree.subModuleList){
					ViewNode node=new ViewNode(sub);
					
					subList[idx]=node;					
					idx++;
				}			
				
				setAttribute("subModule",subList);
			}else{
				setIcon(tree.item.getIcon());
			}
		}
		
	}
	
}
