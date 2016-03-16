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
import com.olia.processflyer.shared.bpmn.template.NodeElementType;
import com.olia.processflyer.shared.bpmn.template.element.Event;
import com.olia.processflyer.shared.bpmn.template.element.EventType;

/**
 * DOCME
 *
 * @author Philipp Kanne
 */
public class EventImpl extends AbstractBaseNode<EventType> implements Event, IsSerializable {

	private boolean startNode = false;
	private boolean endNode = false;

	public EventImpl() {
		super();
	}

	@Override
	public boolean isStartNode() {
		return startNode;
	}

	@Override
	public boolean isEndNode() {
		return endNode;
	}

	public void setStartNode(boolean startNode) {
		this.startNode = startNode;
	}

	@Override
	public NodeElementType getElementType() {

		return NodeElementType.Event;
	}

	public void setEndNode(boolean endNode) {
		this.endNode = endNode;
	}

}
