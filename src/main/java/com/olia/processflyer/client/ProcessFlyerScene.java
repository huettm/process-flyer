/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olia.processflyer.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

	private ProcessInstanceImpl[] result = null;

	private static Logger LOG = Logger.getLogger("Scene");

	AsyncCallback<ProcessInstanceImpl[]> callback = new AsyncCallback<ProcessInstanceImpl[]>() {
		public void onFailure(Throwable caught) {
			LOG.log(Level.SEVERE, "Error: " + caught.getMessage());
		}

		public void onSuccess(ProcessInstanceImpl[] pResult) {
			result = pResult;
			LOG.log(Level.INFO,
					"ProcessBox scene update: " + (result != null ? result.length : "null") + " process instances");
		}
	};

	Mesh meshLines;
	Mesh meshTris;
	Mesh meshMixed;

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
		sceneUpdater.getProcessInstances(callback);

		camera.getPosition().setZ(800);

		double size = 150;

		BoxGeometry geometryLines = new BoxGeometry(size, size, size);

		getScene().add(new AmbientLight(0x404040));

		DirectionalLight light = new DirectionalLight(0xffffff);
		light.getPosition().set(0, 1, 0);
		getScene().add(light);

		MeshLambertMaterial material = new MeshLambertMaterial();
		material.setAmbient(new Color(0xbbbbbb));
		material.setSide(Material.SIDE.DOUBLE);

		MeshBasicMaterial materialLines = new MeshBasicMaterial();
		materialLines.setWireframe(true);

		meshLines = new Mesh(geometryLines, materialLines);
		getScene().add(meshLines);

		processSceneUpdate(result);
	}

	private void processSceneUpdate(ProcessInstanceImpl[] result2) {
		// TODO Auto-generated method stub
		
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
		// Called when the animation should be updated.
		getRenderer().render(getScene(), camera);
		// this.controls.update(1000);
	}

}
