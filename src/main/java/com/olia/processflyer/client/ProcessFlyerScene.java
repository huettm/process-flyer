/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olia.processflyer.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle.Source;
import com.google.gwt.resources.client.TextResource;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.olia.processflyer.shared.SceneUpdaterService;
import com.olia.processflyer.shared.SceneUpdaterServiceAsync;
import com.olia.processflyer.shared.bpmn.instance.HackathonProcessMock;
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
import thothbot.parallax.core.shared.materials.ShaderMaterial;
import thothbot.parallax.core.shared.math.Color;
import thothbot.parallax.core.shared.math.Vector3;
import thothbot.parallax.core.shared.objects.Mesh;

/**
 *
 * @author HUETTM
 */
public class ProcessFlyerScene extends AnimatedScene {

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
		BoxGeometry geometryTris = new BoxGeometry(size, size, size);

		// this.controls = new FirstPersonControls(camera, getCanvas());
		// this.controls.setMovementSpeed(1.0);
		// this.controls.setLookSpeed(1.0);

//		getScene().add(new AmbientLight(0x404040));
//
//		DirectionalLight light = new DirectionalLight(0xffffff);
//		light.getPosition().set(0, 1, 0);
//		getScene().add(light);
//
		MeshLambertMaterial material = new MeshLambertMaterial();
		material.setAmbient(new Color(0xbbbbbb));
		material.setSide(Material.SIDE.DOUBLE);
//
//		Map<String, List<ProcessBox>> processesMap = new HashMap<>();

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

		// ProcessBox process = new ProcessBox();
		// process.loadProcessDefinition(HackathonProcessMock.createTemplate());
		// List<ProcessBox> p = new ArrayList<>();
		// p.add(process);
		// processesMap.put("Hackaton_Process", p);
		// ProcessBox process2 = new ProcessBox();
		// process2.loadProcessDefinition(HackathonProcessMock.createTemplate());
		// processesMap.get("Hackaton_Process").add(process2);
		//
		// // Processlanes starten links von der Mitte und werden nach rechts
		// // erweitert
		// int processLaneXPosition = -1200;
		// for (Entry<String, List<ProcessBox>> entry : processesMap.entrySet())
		// {
		// GWT.log("Displaying all processes for template: " + entry.getKey());
		// int depth = 1000;
		// for (ProcessBox processBox : entry.getValue()) {
		// GWT.log("ProcessBox at depth " + depth);
		// processBox.getPosition().add(new Vector3(processLaneXPosition, 0,
		// depth));
		// depth = depth + 100;
		// for (VisualProcessObject processElement : processBox.processObjects)
		// {
		// Mesh currentMesh = new Mesh(processElement.getGeometry(), material);
		// currentMesh.setPosition(processElement.getPosition().add(processBox.getPosition()));
		// LOG.log(Level.FINER,"ProcessBox pos " + currentMesh.getPosition());
		// getScene().add(currentMesh);
		// }
		// }
		// processLaneXPosition = processLaneXPosition + 200;
		// }

	     MeshBasicMaterial materialLines = new MeshBasicMaterial();
	      materialLines.setWireframe(true);

	      meshLines = new Mesh( geometryLines, materialLines );
	      meshLines.getPosition().setX(-150);
	      getScene().add( meshLines );

	      // wireframe using gl.TRIANGLES (interpreted as triangles)

	      Attribute attributesTris = new Attribute(Attribute.TYPE.V3, setupAttributes( geometryTris ));
	      attributesTris.setBoundTo( Attribute.BOUND_TO.FACE_VERTICES );
	      
//	      ShaderMaterial materialTris = new ShaderMaterial( Resources.INSTANCE );
//	      materialTris.getShader().addAttributes("center", attributesTris);

	      meshTris = new Mesh( geometryTris, material );
	      meshTris.getPosition().setX(150);
	      getScene().add( meshTris );

	      // wireframe using gl.TRIANGLES (mixed triangles and quads)

	      SphereGeometry mixedGeometry = new SphereGeometry( size / 2.0, 32, 16 );

	      Attribute attributesMixed = new Attribute(Attribute.TYPE.V3, setupAttributes( mixedGeometry ));
	      attributesMixed.setBoundTo( Attribute.BOUND_TO.FACE_VERTICES );

//	      ShaderMaterial materialMixed = new ShaderMaterial( Resources.INSTANCE );
//	      materialMixed.getShader().addAttributes("center", attributesMixed);

	      meshMixed = new Mesh( mixedGeometry, material );
	      meshMixed.getPosition().setX(-150);
	      getScene().add( meshMixed );

	}

	private List<List<Vector3>> setupAttributes( Geometry geometry) 
	   {
	      List<List<Vector3>> values = new ArrayList<List<Vector3>>();
	      
	      for( int f = 0; f < geometry.getFaces().size(); f ++ ) 
	      {
	         Face3 face = geometry.getFaces().get( f );
	         values.add(f, Arrays.asList(
	               new Vector3( 1, 0, 0 ), 
	               new Vector3( 0, 1, 0 ), 
	               new Vector3( 0, 0, 1 ) ));
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
