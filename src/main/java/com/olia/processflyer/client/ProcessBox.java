/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olia.processflyer.client;

import com.olia.processflyer.shared.bpmn.template.Node;
import com.olia.processflyer.shared.bpmn.template.ProcessTemplate;
import thothbot.parallax.core.shared.math.Vector3;

import java.util.ArrayList;
import java.util.List;

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
            Vector3 nodePosition = new Vector3(
                    processNode.getRenderingData().getCenterPosition().getX(),
                    processNode.getRenderingData().getCenterPosition().getY(),
                    processNode.getRenderingData().getCenterPosition().getZ());
            processObjects.add(new VisualProcessObject(processNode.getElementType(), nodePosition));
        }            
    }
    
    
}
