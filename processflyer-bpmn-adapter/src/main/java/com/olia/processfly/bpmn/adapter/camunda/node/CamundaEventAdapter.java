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

import static com.olia.processfly.bpmn.adapter.camunda.node.CamundaBoundsAdapter.aBoundsAdapter;
import static com.olia.processflyer.shared.bpmn.template.impl.dsl.LabelBuilder.Label;

import java.util.Collection;

import org.camunda.bpm.model.bpmn.instance.dc.Bounds;
import org.camunda.bpm.model.bpmn.instance.di.Label;
import org.camunda.bpm.model.xml.instance.ModelElementInstance;

import com.olia.processflyer.shared.bpmn.template.element.Event;
import com.olia.processflyer.shared.bpmn.template.element.EventType;
import com.olia.processflyer.shared.bpmn.template.impl.EventImpl;
import com.olia.processflyer.shared.bpmn.template.impl.dsl.ConnectionBuilder;
import com.olia.processflyer.shared.bpmn.template.impl.dsl.EventBuilder;
import com.olia.processflyer.shared.bpmn.template.impl.dsl.LabelBuilder;

/**
 * @author Philipp Kanne
 *
 */
public class CamundaEventAdapter implements CamundaNodeAdapter<org.camunda.bpm.model.bpmn.instance.Event, Event> {

	private CamundaEventAdapter() {

	}

	public static CamundaEventAdapter anEventAdapter() {
		return new CamundaEventAdapter();
	}

	public Event adapt(org.camunda.bpm.model.bpmn.instance.Event node) {

		org.camunda.bpm.model.bpmn.impl.instance.EventImpl event = (org.camunda.bpm.model.bpmn.impl.instance.EventImpl) node;
		String typeName = event.getElementType().getTypeName();

		Bounds bounds = node.getDiagramElement().getBounds();

		EventBuilder eventBuilder = EventBuilder.Event().identifiedBy(event.getId()) //
				.render(aBoundsAdapter().adapt(bounds));

		// Add each label
		Collection<Label> eventLabel = event.getDiagramElement().getChildElementsByType(Label.class);
		if (eventLabel != null) {
			for (Label label : eventLabel) {
				LabelBuilder labelBuilder = Label().withText(event.getName()) //
						.render(aBoundsAdapter().adapt(label.getBounds()))
						.identifiedBy(label.getId());

				eventBuilder.connected(ConnectionBuilder.to(labelBuilder));
			}
		}
		EventImpl result = (EventImpl) EventBuilder.Event().identifiedBy(event.getId()) //
				.render(aBoundsAdapter().adapt(bounds)) //
				.node();
		
		// Choose type of event
		result.setStartNode(typeName.equalsIgnoreCase("startEvent"));
		result.setEndNode(typeName.equalsIgnoreCase("endEvent"));
		
		// TODO how get information about event type?
		result.setType(EventType.None);

		return result;

	}

	public boolean canAdapt(ModelElementInstance node) {

		return org.camunda.bpm.model.bpmn.instance.Event.class.isAssignableFrom(node.getClass());
	}

}
