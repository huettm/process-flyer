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
package com.olia.processfly.bpmn.adapter.camunda;

import static com.olia.processfly.bpmn.adapter.camunda.node.CamundaBoundsAdapter.aBoundsAdapter;
import static com.olia.processfly.bpmn.adapter.camunda.node.CamundaEventAdapter.anEventAdapter;
import static com.olia.processfly.bpmn.adapter.camunda.node.CamundaGatewayAdapter.aGatewayAdapter;
import static com.olia.processfly.bpmn.adapter.camunda.node.CamundaLabelAdapter.aLabelAdapter;
import static com.olia.processfly.bpmn.adapter.camunda.node.CamundaTaskAdapter.aTaskAdapter;
import static com.olia.processfly.bpmn.adapter.camunda.node.CamundaWaypointAdapter.aWaypointAdapter;
import static com.olia.processflyer.shared.bpmn.template.impl.dsl.LabelBuilder.Label;
import static com.olia.processflyer.shared.bpmn.template.impl.dsl.RenderInformationBuilder.Render;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.instance.FlowNode;
import org.camunda.bpm.model.bpmn.instance.SequenceFlow;
import org.camunda.bpm.model.bpmn.instance.bpmndi.BpmnLabel;
import org.camunda.bpm.model.xml.instance.ModelElementInstance;

import com.olia.processfly.bpmn.adapter.camunda.node.CamundaNodeAdapter;
import com.olia.processflyer.shared.bpmn.template.Node;
import com.olia.processflyer.shared.bpmn.template.NodeElementType;
import com.olia.processflyer.shared.bpmn.template.ProcessTemplate;
import com.olia.processflyer.shared.bpmn.template.impl.NodeConnectorImpl;
import com.olia.processflyer.shared.bpmn.template.impl.ProcessTemplateImpl;
import com.olia.processflyer.shared.bpmn.template.impl.dsl.ConnectionBuilder;

/**
 * @author Philipp Kanne
 *
 */
public class CamundaProcessFactory {

	@SuppressWarnings("rawtypes")
	private static final CamundaNodeAdapter[] adapters = new CamundaNodeAdapter[] { //
			anEventAdapter(), //
			aTaskAdapter(), //
			aGatewayAdapter(), //
			aLabelAdapter() //
	};

	public CamundaProcessFactory() {
	}

	public ProcessTemplate create(BpmnModelInstance modelInstance) {

		CamundaProcessAdapter processAdapter = new CamundaProcessAdapter(modelInstance);

		Map<String, Node<?>> nodes = adaptNodes(processAdapter);
		createConnectionsBetweenNodes(nodes, processAdapter);
		Node<?> root = getRoot(nodes);

		ProcessTemplate newTemplate = new ProcessTemplateImpl(root, processAdapter.getName());

		return newTemplate;
	}

	private Node<?> getRoot(Map<String, Node<?>> nodes) {

		for (Node<?> node : nodes.values()) {
			if (node.isStartNode()) {
				return node;
			}
		}
		return null;
	}

	private void createConnectionsBetweenNodes(Map<String, Node<?>> nodes, CamundaProcessAdapter templateAdapter) {
		Set<FlowNode> flowNodes = templateAdapter.getSequenceFlowNodes();
		for (FlowNode aNode : flowNodes) {
			for (SequenceFlow out : aNode.getOutgoing()) {
				Node<?> fromNode = nodes.get(out.getSource().getId());
				Node<?> toNode = nodes.get(out.getTarget().getId());

				if (fromNode != null && toNode != null) {
					NodeConnectorImpl connection = ConnectionBuilder.to(toNode).from(fromNode).identifiedBy(out.getId())
							.getConnection();
					connection.setRenderingData(
							Render().addWayPoints(aWaypointAdapter().adapt(out.getDiagramElement())).getData());

					BpmnLabel bpmnLabel = out.getDiagramElement().getBpmnLabel();
					connection.setLabel(
							Label().withText(out.getName()).render(aBoundsAdapter().adapt(bpmnLabel.getBounds()))
									.identifiedBy(connection.getUniqueIdentifier() + "_label").node());

					fromNode.getOutgoing().add(connection);
				}
			}
			for (SequenceFlow in : aNode.getIncoming()) {
				Node<?> fromNode = nodes.get(in.getSource().getId());
				Node<?> toNode = nodes.get(in.getTarget().getId());
				if (fromNode != null && toNode != null) {
					NodeConnectorImpl connection = ConnectionBuilder.to(toNode).from(fromNode).identifiedBy(in.getId())
							.getConnection();
					connection.setRenderingData(
							Render().addWayPoints(aWaypointAdapter().adapt(in.getDiagramElement())).getData());
					BpmnLabel bpmnLabel = in.getDiagramElement().getBpmnLabel();
					connection.setLabel(
							Label().withText(in.getName()).render(aBoundsAdapter().adapt(bpmnLabel.getBounds()))
									.identifiedBy(connection.getUniqueIdentifier() + "_label").node());

					toNode.getIncoming().add(connection);
				}
			}
		}

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Map<String, Node<?>> adaptNodes(CamundaProcessAdapter templateAdapter) {
		Map<String, Node<?>> nodes = new HashMap<String, Node<?>>();

		Set<ModelElementInstance> allNodes = templateAdapter.getAllNodes();
		for (ModelElementInstance aNode : allNodes) {

			for (CamundaNodeAdapter adapter : adapters) {
				if (adapter.canAdapt(aNode)) {
					Node<?> node = (Node<?>) adapter.adapt(aNode);
					nodes.put(node.getUniqueIdentifier(), node);
				}
			}
		}

		return nodes;
	}

}
