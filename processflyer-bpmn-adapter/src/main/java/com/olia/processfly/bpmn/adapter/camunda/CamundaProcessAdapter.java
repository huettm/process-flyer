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

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.instance.Artifact;
import org.camunda.bpm.model.bpmn.instance.Event;
import org.camunda.bpm.model.bpmn.instance.FlowNode;
import org.camunda.bpm.model.bpmn.instance.Gateway;
import org.camunda.bpm.model.bpmn.instance.Process;
import org.camunda.bpm.model.bpmn.instance.Task;
import org.camunda.bpm.model.bpmn.instance.di.Label;
import org.camunda.bpm.model.xml.instance.ModelElementInstance;
import org.camunda.bpm.model.xml.type.ModelElementType;

/**
 * @author Philipp Kanne
 *
 */
public class CamundaProcessAdapter {

	private final BpmnModelInstance modelInstance;

	private final Class<?>[] nodeTypes = new Class[] { Task.class, Event.class, Gateway.class, Label.class };

	public CamundaProcessAdapter(BpmnModelInstance modelInstance) {
		super();
		this.modelInstance = modelInstance;
	}

	@SuppressWarnings({ "unchecked" })
	public Set<ModelElementInstance> getAllNodes() {

		Set<ModelElementInstance> nodes = new HashSet<ModelElementInstance>();
		for (Class<?> nodeType : nodeTypes) {
			ModelElementType type = modelInstance.getModel().getType((Class<? extends ModelElementInstance>) nodeType);
			Collection<ModelElementInstance> elements = modelInstance.getModelElementsByType(type);
			nodes.addAll(elements);
		}

		return nodes;
	}

	public String getName() {
		ModelElementType processType = modelInstance.getModel().getType(Process.class);
		Collection<ModelElementInstance> elements = modelInstance.getModelElementsByType(processType);
		Process process = (Process) elements.iterator().next();
		String processName = process.getName();
		
		return processName;
	}

	public Set<FlowNode> getSequenceFlowNodes() {
		Set<FlowNode> result = new HashSet<FlowNode>();

		for (ModelElementInstance aNode : getAllNodes()) {
			if (aNode instanceof FlowNode) {
				result.add((FlowNode) aNode);
			}
		}
		return result;
	}

}
