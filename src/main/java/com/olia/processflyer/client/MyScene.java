/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olia.processflyer.client;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

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
import thothbot.parallax.core.shared.math.Vector3;
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

	private ProcessInstanceImpl[] result = null;

	private static Logger LOG = Logger.getLogger("Scene");

	AsyncCallback<ProcessInstanceImpl[]> callback = new AsyncCallback<ProcessInstanceImpl[]>() {
		public void onFailure(Throwable caught) {
			LOG.log(Level.SEVERE, "Error: " + caught.getMessage());
		}

		public void onSuccess(ProcessInstanceImpl[] pResult) {
			LOG.log(Level.INFO, "Received new scene update: " + result.length + " process instances");
			result = pResult;
		}
	};

	@Override
	protected void onStart() {

		camera = new PerspectiveCamera(100, // field of view
				getRenderer().getAbsoluteAspectRation(), // aspect ratio
				1, // near
				1000 // far
		);

		if (sceneUpdater == null) {
			sceneUpdater = GWT.create(SceneUpdaterService.class);
		}
		sceneUpdater.getProcessInstances(callback);

		camera.getPosition().setZ(600);
		// this.controls = new FirstPersonControls( camera, getCanvas() );
		// this.controls.setMovementSpeed(1.0);
		// this.controls.setLookSpeed(1.0);

		getScene().add(new AmbientLight(0x404040));

		DirectionalLight light = new DirectionalLight(0xffffff);
		light.getPosition().set(0, 1, 0);
		getScene().add(light);

		MeshLambertMaterial material = new MeshLambertMaterial();
		material.setAmbient(new Color(0xbbbbbb));
		material.setSide(Material.SIDE.DOUBLE);

		Map<String, List<ProcessBox>> processesMap = new HashMap<>();

		// if(result != null) {
		// for(ProcessInstanceImpl processInstance: result) {
		// ProcessBox process = new ProcessBox();
		// process.loadProcessDefinition(processInstance.getProcessTemplate());
		// if(processesMap.containsKey(processInstance.getProcessTemplate().getName()))
		// {
		// processesMap.get(processInstance.getProcessTemplate().getName()).add(process);
		// } else {
		// List<ProcessBox> processesByName = new ArrayList<>();
		// processesByName.add(process);
		// processesMap.put(processInstance.getProcessTemplate().getName(),
		// processesByName);
		// }
		// }
		// }
		ProcessBox process = new ProcessBox();
		process.loadProcessDefinition(HackathonProcessMock.createTemplate());
		List<ProcessBox> p = new ArrayList<>();
		p.add(process);
		processesMap.put("Hackaton_Process", p);
		ProcessBox process2 = new ProcessBox();
		process2.loadProcessDefinition(HackathonProcessMock.createTemplate());
        processesMap.get("Hackaton_Process").add(process2);

		// Processlanes starten links von der Mitte und werden nach rechts
		// erweitert
		int processLaneXPosition = -1200;
		for (Entry<String, List<ProcessBox>> entry : processesMap.entrySet()) {
			GWT.log("Displaying all processes for template: " + entry.getKey());
            int depth = 1000;
			for (ProcessBox processBox : entry.getValue()) {
				GWT.log("ProcessBox at depth " + depth);
				processBox.getPosition().add(new Vector3(processLaneXPosition, 0, depth));
                depth = depth + 100;
				for (VisualProcessObject processElement : processBox.processObjects) {
					Mesh currentMesh = new Mesh(processElement.getGeometry(), material);
					currentMesh.setPosition(processElement.getPosition().add(processBox.getPosition()));
					getScene().add(currentMesh);
				}
			}
            processLaneXPosition = processLaneXPosition + 200;
		}

	}

	@Override
	protected void onUpdate(double duration) {
		// Called when the animation should be updated.
		getRenderer().render(getScene(), camera);
		// this.controls.update(1000);
	}

}
