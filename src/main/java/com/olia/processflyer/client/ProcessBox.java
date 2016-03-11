/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olia.processflyer.client;

import java.util.ArrayList;
import java.util.List;
import thothbot.parallax.core.shared.core.Geometry;
import thothbot.parallax.core.shared.geometries.BoxGeometry;
import thothbot.parallax.core.shared.geometries.OctahedronGeometry;
import thothbot.parallax.core.shared.geometries.SphereGeometry;
import thothbot.parallax.core.shared.math.Vector3;

/**
 *
 * @author HUETTM
 */
public class ProcessBox {
    
    private Vector3 position = new Vector3();
    
    public Vector3 getPosition(){
        return position;
    }
    
    public int getDistance() {
        return distance;
    }
    
    List<Geometry> processObjects = new ArrayList<>();
    
    private double radius = 50;
    
    private int width = 100;
    
    private int height = 100;
    
    private int depth = 50;
    
    private int distance = 150;
    
    public void loadProcessDefinition() {
        processObjects.add(new SphereGeometry(radius, width, height));
        processObjects.add(new BoxGeometry(100, width, height));
        processObjects.add(new BoxGeometry(100, width, height));
        processObjects.add(new OctahedronGeometry(radius, 0));
        processObjects.add(new BoxGeometry(width, height, depth));
        processObjects.add(new BoxGeometry(width, height, depth));
        processObjects.add(new OctahedronGeometry(radius, 0));
        processObjects.add(new BoxGeometry(width, height, depth));
        processObjects.add(new SphereGeometry(radius, width, height));        
    }
    
    
}
