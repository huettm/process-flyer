/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olia.processflyer.client;

import java.util.Collection;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.olia.processflyer.shared.SceneUpdaterService;
import com.olia.processflyer.shared.SceneUpdaterServiceAsync;
import com.olia.processflyer.shared.bpmn.instance.HackathonProcessMock;
import com.olia.processflyer.shared.bpmn.instance.impl.ProcessInstanceImpl;

import thothbot.parallax.core.client.AnimatedScene;
import thothbot.parallax.core.client.controls.FirstPersonControls;
import thothbot.parallax.core.shared.cameras.PerspectiveCamera;
import thothbot.parallax.core.shared.core.Geometry;
import thothbot.parallax.core.shared.lights.AmbientLight;
import thothbot.parallax.core.shared.lights.DirectionalLight;
import thothbot.parallax.core.shared.materials.Material;
import thothbot.parallax.core.shared.materials.MeshLambertMaterial;
import thothbot.parallax.core.shared.math.Color;
import thothbot.parallax.core.shared.objects.Mesh;
/**
 *
 * @author HUETTM
 */
public class MyScene extends AnimatedScene {
    
    PerspectiveCamera camera;

    private String imageAsString;

    private boolean moveup = true;

    private static final String imageLocation = "Tulips.jpg";

    FirstPersonControls controls;
    
    private SceneUpdaterServiceAsync sceneUpdater;
    
    Collection<ProcessInstanceImpl> result = null;

    AsyncCallback<Collection<ProcessInstanceImpl>> callback = new AsyncCallback<Collection<ProcessInstanceImpl>>() {
        public void onFailure(Throwable caught) {
          GWT.log("Error: "+caught.getMessage());
        }

        public void onSuccess(Collection<ProcessInstanceImpl> result) {
          GWT.log("Received new scene update: "+result.size()+" process instances");
        }
      };
      
    @Override
    protected void onStart() {

    	if(sceneUpdater==null) {
    		sceneUpdater=GWT.create(SceneUpdaterService.class);
    	}
    	sceneUpdater.getProcessInstances(callback);
    	
        camera.getPosition().setZ(600);
        //this.controls = new FirstPersonControls( camera, getCanvas() );
        
        getScene().add(new AmbientLight(0x404040));

        DirectionalLight light = new DirectionalLight(0xffffff);
        light.getPosition().set(0, 1, 0);
        getScene().add(light);
        
        MeshLambertMaterial material = new MeshLambertMaterial();      
        material.setAmbient( new Color(0xbbbbbb) );
        material.setSide(Material.SIDE.DOUBLE);
        
        ProcessBox process = new ProcessBox();
        
        process.loadProcessDefinition(HackathonProcessMock.createTemplate());
               
        for(VisualProcessObject obj: process.processObjects) {
            Mesh currentMesh = new Mesh(obj.getGeometry(), material);
            currentMesh.setPosition(obj.getPosition().add(process.getPosition()));
            getScene().add(currentMesh);
        }
    }

    @Override
    protected void onUpdate(double duration) {
        // Called when the animation should be updated.        
        getRenderer().render(getScene(), camera);
        //this.controls.update(1);
    }

}
