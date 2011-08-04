package com.skynet.spms.client.gui.base;

import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * 自定义面板。用于结构为 上为Title，下为内容的布局显示
 * @author Tony FANG
 *
 */
public class CustomPanel extends VLayout {
	private Label titleLabel= new Label();//标题控件
	private String titleLabelContents;//标题内容
	private Canvas contentCanvas= new Label();//内容控件

	public CustomPanel() {
		this.setBorder("1px solid #a6abb4");
		this.titleLabel.setBorder("1px solid #a6abb4");
		this.titleLabel.setHeight(24);
		this.titleLabel.setWidth100();
		
		contentCanvas.setWidth100();
		contentCanvas.setMargin(5);
		this.addMember(titleLabel);
		this.addMember(contentCanvas);
	}

	public Label getTitleLabel() {
		return titleLabel;
	}


	public Canvas getContentCanvas() {
		return contentCanvas;
	}

	public String getTitleLabelContents() {
		return titleLabelContents;
	}

	public void setTitleLabelContents(String titleLabelContents) {
		this.titleLabel.setContents(titleLabelContents);
		this.titleLabelContents = titleLabelContents;
	}

}
