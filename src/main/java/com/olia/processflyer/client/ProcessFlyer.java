package com.olia.processflyer.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootLayoutPanel;

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
        
        //RootLayoutPanel.get().add(new Label("Its worked"));
        
        RenderingPanel renderingPanel = new RenderingPanel();
        
        renderingPanel.setBackground(0x111111);
        //renderingPanel.setAnimatedScene(new MyScene());
        renderingPanel.setAnimatedScene(new ProcessFlyerScene());
        
        
        RootLayoutPanel.get().add(renderingPanel);
    }    
}
