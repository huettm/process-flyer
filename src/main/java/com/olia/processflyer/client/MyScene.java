/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olia.processflyer.client;

import thothbot.parallax.core.client.AnimatedScene;
import thothbot.parallax.core.shared.cameras.PerspectiveCamera;
import thothbot.parallax.core.shared.geometries.BoxGeometry;
import thothbot.parallax.core.shared.geometries.SphereGeometry;
import thothbot.parallax.core.shared.lights.AmbientLight;
import thothbot.parallax.core.shared.lights.DirectionalLight;
import thothbot.parallax.core.shared.lights.HemisphereLight;
import thothbot.parallax.core.shared.lights.PointLight;
import thothbot.parallax.core.shared.materials.MeshBasicMaterial;
import thothbot.parallax.core.shared.materials.MeshPhongMaterial;
import thothbot.parallax.core.shared.math.Color;
import thothbot.parallax.core.shared.math.Vector2;
import thothbot.parallax.core.shared.math.Vector3;
import thothbot.parallax.core.shared.objects.Mesh;

/**
 *
 * @author HUETTM
 */
public class MyScene extends AnimatedScene {

    PerspectiveCamera camera;

    private Mesh mesh1;
    
    private Mesh mesh2;
    

    @Override
    protected void onStart() {
        // Loads default camera for the Animation
        camera = new PerspectiveCamera(
                100, // field of view
                getRenderer().getAbsoluteAspectRation(), // aspect ratio 
                1, // near
                1000 // far 
        );

        camera.getPosition().setZ(400);

        BoxGeometry geometry = new BoxGeometry(200, 200, 200);       

        MeshPhongMaterial material = new MeshPhongMaterial();
        material.setColor(new Color(0xFF0000));
        //material.setWireframe(true);

        this.mesh1 = new Mesh(geometry, material);
        
        SphereGeometry sphere = new SphereGeometry(30, 30, 30, 0, Math.PI * 2, 0, Math.PI * 2);
        MeshPhongMaterial sphereMaterial = new MeshPhongMaterial();;
        sphereMaterial.setColor(new Color(0x0066ff));
        
        this.mesh2 = new Mesh(sphere, sphereMaterial);
         

//        PointLight light = new PointLight( 0xffffff); 
//        light.getPosition().set( 30, 300, 0 ); 
//        getScene().add( light );
        
        DirectionalLight dirLight = new DirectionalLight( 0xffffff, 1.0 ); 
        dirLight.getPosition().set( 0, 100, 0 ); 
        getScene().add( dirLight );
        
        getScene().add(mesh1);
        getScene().add(mesh2);
        Vector3 spherePosition = mesh2.getPosition();
        spherePosition.addX(300.0);
        spherePosition.addZ(-100.0);
        //AmbientLight ambLight = new AmbientLight( 0xffffff );
        //getScene().add(ambLight);
              
        
    }

    @Override
    protected void onUpdate(double duration) {
        // Called when the animation should be updated.
        this.mesh1.getRotation().addX(0.005);
        this.mesh1.getRotation().addY(0.01);
                        
        getRenderer().render(getScene(), camera);
    }

}
