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
package com.olia.processflyer.shared.bpmn.instance.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.olia.processflyer.shared.bpmn.instance.InstanceStatus;
import com.olia.processflyer.shared.bpmn.instance.NodeInstance;
import com.olia.processflyer.shared.bpmn.instance.ProcessInstance;
import com.olia.processflyer.shared.bpmn.template.Node;
import com.olia.processflyer.shared.bpmn.template.NodeType;
import com.olia.processflyer.shared.bpmn.template.ProcessTemplate;

/**
 * DOCME
 *
 * @author Philipp Kanne
 */
public class ProcessInstanceImpl implements ProcessInstance, IsSerializable {

	private final ProcessTemplate processTemplate;

	private InstanceStatus state;

	private final String uniqueIdentifier;

	private Map<String, NodeInstance> instances = new HashMap<String, NodeInstance>();

	public ProcessInstanceImpl(ProcessTemplate processTemplate, String uniqueIdentifier) {
		super();
		this.processTemplate = processTemplate;
		this.uniqueIdentifier = uniqueIdentifier;
		this.state = InstanceStatusImpl.notExecutedState();
	}

	@Override
	public ProcessTemplate getProcessTemplate() {
		return processTemplate;
	}

	@Override
	public InstanceStatus getState() {
		return state;
	}

	@Override
	public String getUniqueIdentifier() {
		return uniqueIdentifier;
	}

	public void addNodeInstance(NodeInstance nodeInstance) {
		instances.put(nodeInstance.getUniqueIdentifier(), nodeInstance);
	}

	public NodeInstance getInstanceByUUID(String uuid) {
		return instances.get(uuid);
	}

	@Override
	public java.util.Set<NodeInstance> getInstances() {
		return new HashSet<NodeInstance>(instances.values());
	}

	@Override
	public NodeInstance getInstanceFor(Node<? extends NodeType> node) {
		for (NodeInstance instance : instances.values()) {
			if (instance.getNode().equals(node)) {
				return instance;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return "ProcessInstance [uuid=" + uniqueIdentifier + ", template=" + processTemplate + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((uniqueIdentifier == null) ? 0 : uniqueIdentifier.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		ProcessInstanceImpl other = (ProcessInstanceImpl) obj;
		if (uniqueIdentifier == null) {
			if (other.uniqueIdentifier != null) {
				return false;
			}
		} else if (!uniqueIdentifier.equals(other.uniqueIdentifier)) {
			return false;
		}
		return true;
	}


}
