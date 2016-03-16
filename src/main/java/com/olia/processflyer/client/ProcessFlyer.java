package com.olia.processflyer.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.TouchMoveEvent;
import com.google.gwt.event.dom.client.TouchMoveHandler;
import com.google.gwt.event.dom.client.TouchStartEvent;
import com.google.gwt.event.dom.client.TouchStartHandler;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.RootLayoutPanel;

import thothbot.parallax.core.client.AnimatedScene;
import thothbot.parallax.core.client.RenderingPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class ProcessFlyer implements EntryPoint {

 
    /**
     * This is the entry point method.
     */
    @Override
    public void onModuleLoad() {
        
        RenderingPanel renderingPanel = new RenderingPanel();
        
        //final MyScene myScene = new MyScene();
        renderingPanel.sinkEvents(Event.ONMOUSEMOVE);
        final ProcessFlyerScene myScene = new ProcessFlyerScene();
        
        renderingPanel.addHandler(new MouseMoveHandler() {

			@Override
			public void onMouseMove(MouseMoveEvent arg0) {
				GWT.log("ProcFlyer mouse:"+arg0);
				myScene.onMouseMove(arg0);
			}
			
        }, MouseMoveEvent.getType());
        
        renderingPanel.setBackground(0x111111);
        renderingPanel.setAnimatedScene(myScene);
        
        
        RootLayoutPanel.get().add(renderingPanel);
    }    
}
