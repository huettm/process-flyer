/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olia.processflyer.client;

import com.olia.processflyer.shared.bpmn.template.Node;
import com.olia.processflyer.shared.bpmn.template.NodeElementType;
import com.olia.processflyer.shared.bpmn.template.ProcessTemplate;
import com.olia.processflyer.shared.bpmn.template.impl.ProcessTemplateImpl;
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
    
    List<VisualProcessObject> processObjects = new ArrayList<>();
    
    private double radius = 50;
    
    private int width = 100;
    
    private int height = 100;
    
    private int depth = 50;
    
    private int distance = 150;
    
    
    public void loadProcessDefinition(ProcessTemplate processTemplate) {
        List<Node<?>> processNodeList = processTemplate.getAllNodes();
        for(Node processNode: processNodeList) {            
            Vector3 nodePosition = new Vector3(processNode.getRenderingData().getStartPosition().getX(), processNode.getRenderingData().getStartPosition().getY(), processNode.getRenderingData().getStartPosition().getZ());
            processObjects.add(new VisualProcessObject(processNode.getElementType(), nodePosition));
        }            
    }
    
    
}
