/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olia.processflyer.client;

import com.olia.processflyer.shared.bpmn.template.NodeElementType;
import com.olia.processflyer.shared.bpmn.template.NodeType;
import thothbot.parallax.core.shared.core.Geometry;
import thothbot.parallax.core.shared.geometries.BoxGeometry;
import thothbot.parallax.core.shared.geometries.OctahedronGeometry;
import thothbot.parallax.core.shared.geometries.SphereGeometry;
import thothbot.parallax.core.shared.math.Vector3;

/**
 *
 * @author HUETTM
 */
public class VisualProcessObject {
    
    private Geometry geometryUsedForDisplay;
    
    private Vector3 positionRelativToProcess;
    
    private NodeElementType nodeType;
    
    private double radius = 50; 
    
    private double height = 100;
    
    private double width = 100;
    
    private double depth = 100;
    
    public VisualProcessObject(NodeElementType nodeType, Vector3 position) {
        this.positionRelativToProcess = position;
        this.nodeType = nodeType;
    }
    public Geometry getGeometry() {
        if(geometryUsedForDisplay == null) {
            geometryUsedForDisplay = getGeometryByNodeType();
        }
        return geometryUsedForDisplay;
    }
    
    public Vector3 getPosition() {
        return positionRelativToProcess;
    }   

    private Geometry getGeometryByNodeType() {
        switch(nodeType) {
            case Event: 
                return new SphereGeometry(radius, Double.valueOf(width).intValue(), Double.valueOf(height).intValue());            
            case Task:
                return new BoxGeometry(width, height, depth);            
            case Gateway: 
                return new OctahedronGeometry(radius, 0);            
        }
        return new BoxGeometry(width, height, depth);
    }
}
