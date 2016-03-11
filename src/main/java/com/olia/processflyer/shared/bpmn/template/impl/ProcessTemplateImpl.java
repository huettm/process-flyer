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
package com.olia.processflyer.shared.bpmn.template.impl;

import java.util.List;

import com.olia.processflyer.shared.bpmn.instance.impl.CollectAllNodesProcessTemplateVisitor;
import com.olia.processflyer.shared.bpmn.template.Node;
import com.olia.processflyer.shared.bpmn.template.NodeType;
import com.olia.processflyer.shared.bpmn.template.ProcessTemplate;

/**
 * DOCME
 *
 * @author Philipp Kanne
 */
public class ProcessTemplateImpl implements ProcessTemplate {
	private final Node<? extends NodeType> root;

	private final String name;

	public ProcessTemplateImpl(Node<? extends NodeType> root, String name) {
		super();
		this.root = root;
		this.name = name;
	}

	@Override
	public Node<? extends NodeType> getRootNode() {
		return root;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		ProcessTemplateImpl other = (ProcessTemplateImpl) obj;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}

	public List<Node<?>> getAllNodes() {
		CollectAllNodesProcessTemplateVisitor visitor = new CollectAllNodesProcessTemplateVisitor();
		InOrderNodeVisitorStrategy.anInOrderVisitorStrategyFor(root).apply(visitor);
		
		return visitor.getNodes();
	}

	
}
