/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olia.processflyer.client;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Image;
import thothbot.parallax.core.client.AnimatedScene;
import thothbot.parallax.core.client.controls.FirstPersonControls;
import thothbot.parallax.core.client.textures.Texture;
import thothbot.parallax.core.shared.cameras.PerspectiveCamera;
import thothbot.parallax.core.shared.core.Geometry;
import thothbot.parallax.core.shared.core.Object3D;
import thothbot.parallax.core.shared.geometries.BoxGeometry;
import thothbot.parallax.core.shared.geometries.CircleGeometry;
import thothbot.parallax.core.shared.geometries.OctahedronGeometry;
import thothbot.parallax.core.shared.geometries.PlaneGeometry;
import thothbot.parallax.core.shared.geometries.SphereGeometry;
import thothbot.parallax.core.shared.lights.AmbientLight;
import thothbot.parallax.core.shared.lights.DirectionalLight;
import thothbot.parallax.core.shared.lights.HemisphereLight;
import thothbot.parallax.core.shared.lights.PointLight;
import thothbot.parallax.core.shared.materials.Material;
import thothbot.parallax.core.shared.materials.MeshBasicMaterial;
import thothbot.parallax.core.shared.materials.MeshLambertMaterial;
import thothbot.parallax.core.shared.materials.MeshNormalMaterial;
import thothbot.parallax.core.shared.materials.MeshPhongMaterial;
import thothbot.parallax.core.shared.math.Color;
import thothbot.parallax.core.shared.math.Vector2;
import thothbot.parallax.core.shared.math.Vector3;
import thothbot.parallax.core.shared.objects.Mesh;
import com.olia.processflyer.shared.SceneUpdaterService;
import com.olia.processflyer.shared.SceneUpdaterServiceAsync;


/**
 *
 * @author HUETTM
 */
public class MyScene extends AnimatedScene {

    PerspectiveCamera camera;

    private String imageAsString;

    private boolean moveup = true;

    private static final String imageLocation = "Tulips.jpg";

    private SceneUpdaterServiceAsync sceneUpdater = GWT.create(TextRendererService.class);

    FirstPersonControls controls;
    
    @Override
    protected void onStart() {
        // Loads default camera for the Animation
        camera = new PerspectiveCamera(
                100, // field of view
                getRenderer().getAbsoluteAspectRation(), // aspect ratio 
                1, // near
                1000 // far 
        );
        
        if(sceneUpdater==null) {
    		sceneUpdater=GWT.create(SceneUpdaterService.class);
    	}
       
                
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
        process.loadProcessDefinition();
        process.getPosition().addX(300);
        
        for(Geometry object: process.processObjects) {
            getScene().add(new Mesh(object, material));          
        }
        
        for(int i = 0; i < getScene().getChildren().size(); i++) {
            int newX = 0;
            if(i != 0) {
                newX = i * process.getDistance() + Double.valueOf(process.getPosition().getX()).intValue();
            }
            getScene().getChildren().get(i).getPosition().addX(-1 * newX);
        }

//        SphereGeometry startPoint = new SphereGeometry(50, 100, 100);        
//
//        Mesh mesh1 = new Mesh(startPoint, material);
//
//        BoxGeometry processStep1 = new BoxGeometry(100, 100, 100);
//       
//        Mesh mesh2 = new Mesh(processStep1, material);
//
////        DirectionalLight dirLight = new DirectionalLight(0xffffff, 1.0);
////        dirLight.getPosition().set(0, 100, 0);
////        getScene().add(dirLight);
        //getScene().add(mesh1);
//        getScene().add(mesh2);
//        Vector3 spherePosition = mesh1.getPosition();
//        spherePosition.addX(200.0);       
//
////        AmbientLight ambLight = new AmbientLight(0xffffff);
////        getScene().add(ambLight);
//        OctahedronGeometry gateway = new OctahedronGeometry(
//                50, // Sets the radius of octahedron 
//                0 // Sets the frequency of decomposition
//        );
//
//        Mesh gateWayMesh = new Mesh(gateway, material);
//
//        getScene().add(gateWayMesh);
//
//        gateWayMesh.getPosition().addX(-200);
    }

    @Override
    protected void onUpdate(double duration) {
        // Called when the animation should be updated.        
        getRenderer().render(getScene(), camera);
        //this.controls.update(1);
    }

}
