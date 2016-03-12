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

import com.google.gwt.user.client.rpc.IsSerializable;
import com.olia.processflyer.shared.bpmn.template.Node;
import com.olia.processflyer.shared.bpmn.template.NodeType;
import com.olia.processflyer.shared.bpmn.template.NodeVisitor;
import com.olia.processflyer.shared.bpmn.template.RenderingInformation;
import com.olia.processflyer.shared.bpmn.template.element.Label;
import com.olia.processflyer.shared.bpmn.template.element.NodeConnector;
import com.olia.processflyer.shared.bpmn.template.element.NodeConnectorType;

/**
 * DOCME
 *
 * @author Philipp Kanne
 */
public class NodeConnectorImpl implements NodeConnector, IsSerializable
{
	private String uniqueIdentifier;

	private Node<? extends NodeType> endNode;

	private Node<? extends NodeType> startNode;

	private Label label;

	private RenderingInformation renderingData;

	public NodeConnectorImpl() {
		super();
	}

	@Override
	public String getUniqueIdentifier() {
		return uniqueIdentifier;
	}

	public void setUniqueIdentifier(String uniqueIdentifier) {
		this.uniqueIdentifier = uniqueIdentifier;
	}

	@Override
	public Label getLabel() {
		return label;
	}

	public void setLabel(Label label) {
		this.label = label;
	}

	@Override
	public RenderingInformation getRenderingData() {
		return renderingData;
	}

	public void setRenderingData(RenderingInformation renderingData) {
		this.renderingData = renderingData;
	}

	@Override
	public Node<? extends NodeType> getStartNode() {
		return startNode;
	}

	@Override
	public Node<? extends NodeType> getEndNode() {
		return endNode;
	}

	@Override
	public NodeConnectorType getType() {
		return NodeConnectorType.Default;
	}

	public void setEndNode(Node<? extends NodeType> endNode) {
		this.endNode = endNode;
	}

	public void setStartNode(Node<? extends NodeType> startNode) {
		this.startNode = startNode;
	}

	@Override
	public void accept(NodeVisitor visitor) {
		visitor.visit(this);
		if (label != null) {
			label.accept(visitor);
		}
	}

	@Override
	public String toString() {
		return "Connector [uniqueIdentifier=" + uniqueIdentifier + ", startNode=" + startNode + ", endNode=" + endNode
				+ "]";
	}

	@Override
	public boolean isNode() {
		return false;
	}

	@Override
	public boolean isConnector() {
		return true;
	}

	@Override
	public boolean isStartNode() {
		return false;
	}

	@Override
	public boolean isEndNode() {
		return false;
	}

}
