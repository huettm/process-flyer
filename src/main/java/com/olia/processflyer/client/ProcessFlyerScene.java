/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olia.processflyer.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.olia.processflyer.shared.SceneUpdaterService;
import com.olia.processflyer.shared.SceneUpdaterServiceAsync;
import com.olia.processflyer.shared.bpmn.instance.impl.ProcessInstanceImpl;

import thothbot.parallax.core.client.AnimatedScene;
import thothbot.parallax.core.client.controls.FirstPersonControls;
import thothbot.parallax.core.client.shaders.Attribute;
import thothbot.parallax.core.shared.cameras.PerspectiveCamera;
import thothbot.parallax.core.shared.core.Face3;
import thothbot.parallax.core.shared.core.Geometry;
import thothbot.parallax.core.shared.geometries.BoxGeometry;
import thothbot.parallax.core.shared.geometries.SphereGeometry;
import thothbot.parallax.core.shared.lights.AmbientLight;
import thothbot.parallax.core.shared.lights.DirectionalLight;
import thothbot.parallax.core.shared.materials.Material;
import thothbot.parallax.core.shared.materials.MeshBasicMaterial;
import thothbot.parallax.core.shared.materials.MeshLambertMaterial;
import thothbot.parallax.core.shared.math.Color;
import thothbot.parallax.core.shared.math.Vector3;
import thothbot.parallax.core.shared.objects.Mesh;

/**
 *
 * @author HUETTM
 */
public class ProcessFlyerScene extends AnimatedScene {

	PerspectiveCamera camera;

	FirstPersonControls controls;

	private SceneUpdaterServiceAsync sceneUpdater;

	private ProcessInstanceImpl[] m_currentProcesses = null;

	private Vector<Mesh> m_currentGeometries = new Vector<Mesh>();
	
	private static Logger LOG = Logger.getLogger("Scene");

	AsyncCallback<ProcessInstanceImpl[]> callback = new AsyncCallback<ProcessInstanceImpl[]>() {
		public void onFailure(Throwable caught) {
			LOG.log(Level.SEVERE, "Error: " + caught.getMessage());
		}

		public void onSuccess(ProcessInstanceImpl[] pResult) {
			m_currentProcesses = pResult;
			LOG.log(Level.INFO,
					"ProcFlyer scene update: " + (m_currentProcesses != null ? m_currentProcesses.length : "null") + " process instances");
		}
	};

	Mesh meshLines;

	private MeshLambertMaterial m_defaultMaterial;

	@Override
	protected void onStart() {

		camera = new PerspectiveCamera(//
				40, // field of view
				getRenderer().getAbsoluteAspectRation(), // aspect ratio
				1, // near
				2000 // far
		);

		if (sceneUpdater == null) {
			sceneUpdater = GWT.create(SceneUpdaterService.class);
		}
		LOG.log(Level.FINE, "ProcFlyer calling service updater");
		sceneUpdater.getProcessInstances(callback);

		camera.getPosition().setZ(800);

		double size = 150;

		BoxGeometry geometryLines = new BoxGeometry(size, size, size);

		getScene().add(new AmbientLight(0x404040));

		DirectionalLight light = new DirectionalLight(0xffffff);
		light.getPosition().set(0, 1, 0);
		getScene().add(light);

		m_defaultMaterial = new MeshLambertMaterial();
		m_defaultMaterial.setAmbient(new Color(0xbbbbbb));
		m_defaultMaterial.setSide(Material.SIDE.DOUBLE);

		MeshBasicMaterial materialLines = new MeshBasicMaterial();
		materialLines.setWireframe(true);

		meshLines = new Mesh(geometryLines, materialLines);
		getScene().add(meshLines);

	}

	private void processSceneUpdate() {
		if(m_currentProcesses==null) {
			return;
		}
		LOG.log(Level.FINE,"ProcFlyer processing " );
		Map<String, List<ProcessBox>> processesMap = new HashMap<>();
		for (ProcessInstanceImpl myProcInstance : m_currentProcesses) {
			ProcessBox process = new ProcessBox();
			process.loadProcessDefinition(myProcInstance.getProcessTemplate());
			if (processesMap.containsKey(myProcInstance.getProcessTemplate().getName())) {
				processesMap.get(myProcInstance.getProcessTemplate().getName()).add(process);
			} else {
				List<ProcessBox> processesByName = new ArrayList<>();
				processesByName.add(process);
				processesMap.put(myProcInstance.getProcessTemplate().getName(), processesByName);
			}
		}
		// Processlanes starten links von der Mitte und werden nach rechts
		// erweitert
		m_currentGeometries.clear();
		int processLaneXPosition = -1200;
		for (Entry<String, List<ProcessBox>> entry : processesMap.entrySet()) {
			GWT.log("Displaying all processes for template: " + entry.getKey());
			int depth = 1000;
			for (ProcessBox processBox : entry.getValue()) {
				GWT.log("ProcFlyer at depth " + depth);
				processBox.getPosition().add(new Vector3(processLaneXPosition, 0, depth));
				depth = depth + 100;
				for (VisualProcessObject processElement : processBox.processObjects) {
					Mesh myNewMesh = new Mesh(processElement.getGeometry(), m_defaultMaterial);
					myNewMesh.setPosition(processElement.getPosition().add(processBox.getPosition()));
					LOG.log(Level.FINER,"ProcFlyer pos " + myNewMesh.getPosition());
//					getScene().add(currentMesh);
					m_currentGeometries.add(myNewMesh);
				}
			}
			processLaneXPosition = processLaneXPosition + 200;
		}
		LOG.log(Level.INFO, "ProcFlyer Consume scene");
		m_currentProcesses=null;
	}

	private List<List<Vector3>> setupAttributes(Geometry geometry) {
		List<List<Vector3>> values = new ArrayList<List<Vector3>>();

		for (int f = 0; f < geometry.getFaces().size(); f++) {
			Face3 face = geometry.getFaces().get(f);
			values.add(f, Arrays.asList(new Vector3(1, 0, 0), new Vector3(0, 1, 0), new Vector3(0, 0, 1)));
		}

		return values;
	}

	@Override
	protected void onUpdate(double duration) {
		processSceneUpdate();
		// Called when the animation should be updated.
		getRenderer().render(getScene(), camera);
		// this.controls.update(1000);
	}

}
