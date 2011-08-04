package com.skynet.spms.client.tools;

import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.widgets.AnimationCallback;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Window;


public class ShowWindowTools {

	public static void showWondow(Rectangle srcRect,final Window targerWindow,int openSeconds){
		if(openSeconds==-1)
			openSeconds=500;
		final Canvas animateOutline = new Canvas();
        animateOutline.setBorder("2px solid black");
        animateOutline.setTop(srcRect.getTop());
        animateOutline.setLeft(srcRect.getLeft());
        animateOutline.setWidth(srcRect.getLeft());
        animateOutline.setHeight(srcRect.getHeight());
        final Integer left = targerWindow.getLeft(),top=targerWindow.getTop(),width = targerWindow.getWidth(),height=targerWindow.getHeight();
        
        animateOutline.show();
        animateOutline.animateRect(left, top, width, height, new AnimationCallback() {
            public void execute(boolean earlyFinish) {
                animateOutline.hide();
                targerWindow.show();
            }
        }, openSeconds
        );	
	}
	
	public static void showCloseWindow(Rectangle srcRect,final Window targerWindow,int closeSeconds){
		if(closeSeconds==-1)
			closeSeconds=500;
		final Canvas animateOutline = new Canvas();
        animateOutline.setBorder("2px solid black");
        animateOutline.setTop(srcRect.getTop());
        animateOutline.setLeft(srcRect.getLeft());
        animateOutline.setWidth(srcRect.getLeft());
        animateOutline.setHeight(srcRect.getHeight());
        final Integer rectLeft = srcRect.getLeft(),rectTop=srcRect.getTop(),rectWidth =srcRect.getWidth(),rectHeight=srcRect.getHeight();
        final Integer left = targerWindow.getLeft(),top=targerWindow.getTop(),width = targerWindow.getWidth(),height=targerWindow.getHeight();
        animateOutline.setRect(left, top, width, height);
        animateOutline.show();
        targerWindow.hide();
        animateOutline.animateRect(rectLeft, rectTop, rectWidth, rectHeight
        		, new AnimationCallback() {
                    public void execute(boolean earlyFinish) {
                        animateOutline.hide();
                    }
                }, closeSeconds);

    }
	public static void showWindow(Rectangle srcRect,final Window targerWindow,int openSeconds){
		showWondow(srcRect, targerWindow, openSeconds);
	}
}

