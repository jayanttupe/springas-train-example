package com.skynet.spms.client.gui.portal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import com.skynet.spms.client.gui.portal.LayoutManager.MemberFactory;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.DragAppearance;
import com.smartgwt.client.util.EventHandler;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.DropEvent;
import com.smartgwt.client.widgets.events.DropHandler;
import com.smartgwt.client.widgets.events.DropOutEvent;
import com.smartgwt.client.widgets.events.DropOutHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.layout.VStack;

public class PortalConfig {

	private static final Logger log = Logger.getLogger("Portal config");

	private HLayout demoLayout;

	private MemberList memberStack = new MemberList();

//	private Set<String> memberArray;
	
	private Set<String> filterSet;
//	
	public PortalConfig(Set<String> filterSet){
		this.filterSet=filterSet;
	}


	public void displayWin(final LayoutManager layoutMang,final ReDrawPortal handler) {
		log.info("portal config");
		
		demoLayout = layoutMang.getLayout(new ConfigFactory());

		log.info("member num:" + filterSet);

		for (String name : filterSet) {
			memberStack.addMember(new MemberLab(name));
		}

		final Window win = new Window();
		win.setWidth100();
		win.setHeight100();
		win.setShowCloseButton(true);

		HLayout mainLayout = new HLayout();

		mainLayout.addMember(demoLayout);

		VLayout ctrlLayout = new VLayout();
		memberStack.setHeight("50%");
		ctrlLayout.addMember(memberStack);

		IButton btn = new IButton();
		btn.setTitle("save layout");
		btn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				List<List<String>> layoutList = new ArrayList<List<String>>();

				for (Canvas member : demoLayout.getMembers()) {
					VLayout lineLayout = (VLayout) member;

					layoutList.add(getLine(lineLayout));
				}
				layoutMang.setLayoutInfoList(layoutList);
				win.destroy();
				handler.doReDraw(layoutMang);
			}

		});
		IButton close = new IButton();
		close.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				win.destroy();
			}

		});
		close.setTitle("reset");
		ctrlLayout.addMember(close);
		ctrlLayout.addMember(btn);
		mainLayout.addMember(ctrlLayout);

		win.addItem(mainLayout);
		win.show();
	}

	private List<String> getLine(VLayout layout) {
		List<String> subList = new ArrayList<String>();

		for (int i = 0; i < layout.getMembers().length; i++) {
			Canvas member = layout.getMember(i);
			LayoutWin win = (LayoutWin) member;
			String memName = win.getMemberName();
			log.info("read member name:" + memName);
			subList.add(memName);
		}
		return subList;
	}

	public class ConfigFactory implements MemberFactory {

		@Override
		public Canvas getMember(String name) {
			LayoutWin win = new LayoutWin(name);

			return win;
		}

		@Override
		public String getDescription(String name) {
			return name;
		}

		@Override
		public HLayout getHLayout() {
			HLayout layout = new HLayout();
			return layout;
		}

		@Override
		public VLayout getVLayout() {
			VLayout layout = new VLayout();

			return layout;
		}

	}

	private static class MemberList extends VStack {
		public MemberList() {
			setLayoutMargin(10);
			setCanAcceptDrop(true);
			setShowDragPlaceHolder(true);

			addDropOutHandler(new DropOutHandler() {
				public void onDropOut(DropOutEvent event) {
					MemberLab lab = (MemberLab) EventHandler.getDragTarget();
					log.info("remove member " + lab.getName());

					MemberLab oldLab = (MemberLab) getMember(lab.getID());
					oldLab.setBackgroundColor("#20a020");
				}
			});
		}

	}

	private static class MemberLab extends Label {

		private String name;

		public MemberLab(String name) {
			setBackgroundColor("#40c040");
			setCanDragReposition(true);
			setCanDrop(true);
			setDragAppearance(DragAppearance.TRACKER);
			setWidth(40);
			setHeight(40);
			setBorder("1px");
			this.name = name;
			this.setContents("member:" + name);
		}

		public String getName() {
			return name;
		}

	}

	private static class LayoutWin extends Label {

		String memberName = null;

		public LayoutWin(String name) {
			memberName = name;

			setContents("<h1>" + name + "</h1>");
			setWidth100();
			setMargin(1);
			this.setBorder("2px solid blue");
			setCanAcceptDrop(true);
			setAlign(Alignment.CENTER);

			addDropHandler(new DropHandler() {
				public void onDrop(DropEvent event) {

					MemberLab member = (MemberLab) EventHandler.getDragTarget();

					memberName = member.getName();
					log.info("add member " + member.getName());
					setContents("<h1>" + memberName + "</h1>");

				}
			});

		}

		public String getMemberName() {
			return memberName;
		}
	}

	public interface ReDrawPortal {
		void doReDraw(LayoutManager layoutMang);
	}

}
