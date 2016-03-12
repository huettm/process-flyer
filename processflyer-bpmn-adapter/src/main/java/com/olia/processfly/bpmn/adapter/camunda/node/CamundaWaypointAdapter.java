/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.olia.processfly.bpmn.adapter.camunda.node;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.camunda.bpm.model.bpmn.instance.bpmndi.BpmnEdge;
import org.camunda.bpm.model.bpmn.instance.di.Waypoint;
import org.camunda.bpm.model.xml.instance.ModelElementInstance;

import com.olia.processflyer.shared.bpmn.template.Point;

/**
 * @author Philipp Kanne
 *
 */
public class CamundaWaypointAdapter implements CamundaNodeAdapter<BpmnEdge, List<Point>> {

	private CamundaWaypointAdapter() {

	}

	public static final CamundaWaypointAdapter aWaypointAdapter() {
		return new CamundaWaypointAdapter();
	}

	public List<Point> adapt(BpmnEdge edge) {

		List<Point> points = new ArrayList<Point>();
		Collection<Waypoint> waypoints = edge.getWaypoints();
		for (Waypoint waypoint : waypoints) {
			points.add(new Point(waypoint.getX(), waypoint.getY(), 0d));
		}
		
		return points;
	}

	public boolean canAdapt(ModelElementInstance node) {
		return BpmnEdge.class.isAssignableFrom(node.getClass());
	}

}
