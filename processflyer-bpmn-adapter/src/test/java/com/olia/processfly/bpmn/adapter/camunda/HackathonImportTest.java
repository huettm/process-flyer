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

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;

import org.camunda.bpm.model.bpmn.Bpmn;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.impl.instance.TaskImpl;
import org.camunda.bpm.model.bpmn.instance.BaseElement;
import org.camunda.bpm.model.bpmn.instance.DataInputAssociation;
import org.camunda.bpm.model.bpmn.instance.DataOutputAssociation;
import org.camunda.bpm.model.bpmn.instance.Event;
import org.camunda.bpm.model.bpmn.instance.Gateway;
import org.camunda.bpm.model.bpmn.instance.Property;
import org.camunda.bpm.model.bpmn.instance.SequenceFlow;
import org.camunda.bpm.model.bpmn.instance.Task;
import org.camunda.bpm.model.bpmn.instance.bpmndi.BpmnLabel;
import org.camunda.bpm.model.bpmn.instance.bpmndi.BpmnShape;
import org.camunda.bpm.model.bpmn.instance.dc.Bounds;
import org.camunda.bpm.model.bpmn.instance.di.Label;
import org.camunda.bpm.model.xml.Model;
import org.camunda.bpm.model.xml.instance.ModelElementInstance;
import org.camunda.bpm.model.xml.type.ModelElementType;
import org.junit.Before;
import org.junit.Test;

/**
 * @author philipp
 *
 */
public class HackathonImportTest {

	private BpmnModelInstance sut;

	@Before
	public void before() {
		sut = importProcessHelper("Hackathon_Process.bpmn");
	}

	@Test
	public void shouldLoadHackathonProcess() {
		assertThat(sut).isNotNull();
	}

	@Test
	public void shouldExtractAllTasks() throws Exception {

		Model model = sut.getModel();
		String modelName = model.getModelName();
		ModelElementType type = sut.getModel().getType(Task.class);
		Collection<ModelElementInstance> elements = sut.getModelElementsByType(type);
		;
		for (ModelElementInstance elem : elements) {

			TaskImpl t = (TaskImpl) elem;
			Collection<DataInputAssociation> dataInputAssociations = t.getDataInputAssociations();
			Collection<DataOutputAssociation> dataOutputAssociations = t.getDataOutputAssociations();
			BpmnShape diagramElement = t.getDiagramElement();
			Bounds bounds = diagramElement.getBounds();
			BaseElement bpmnElement = diagramElement.getBpmnElement();
			BpmnLabel bpmnLabel = diagramElement.getBpmnLabel();
			String name2 = t.getName();
			Collection<SequenceFlow> incoming = t.getIncoming();
			for (SequenceFlow sequenceFlow : incoming) {
				String id = sequenceFlow.getId();
				
				int y=1;
			}

			Collection<SequenceFlow> outgoing = t.getOutgoing();
			Collection<Property> properties = t.getProperties();
			String id = t.getId();

			int i = 1;
		}

		// Collection<ModelElementInstance> elements =
		// sut.getModelElementsByType(type);
		// ModelElementInstance modelElementById =
		// sut.getModelElementById("Process_Hackathon");
		// modelElementById.getParentElement()
		// // modelElementById.getModelInstance().get
		//
		// ModelElementInstance one = elements.iterator().next();
		// // one.getChildElementsByType(Bounds.class)
		// assertThat(elements.size()).isEqualTo(5);
	}

	@Test
	public void shouldExtractAllEvents() throws Exception {

		ModelElementType type = sut.getModel().getType(Event.class);
		Collection<ModelElementInstance> elements = sut.getModelElementsByType(type);

		assertThat(elements.size()).isEqualTo(2);
	}

	@Test
	public void shouldExtractAllGateways() throws Exception {

		ModelElementType type = sut.getModel().getType(Gateway.class);
		Collection<ModelElementInstance> elements = sut.getModelElementsByType(type);

		assertThat(elements.size()).isEqualTo(2);
	}

	@Test
	public void shouldExtractAllLabels() throws Exception {

		ModelElementType type = sut.getModel().getType(Label.class);
		Collection<ModelElementInstance> elements = sut.getModelElementsByType(type);

		assertThat(elements.size()).isEqualTo(13);
	}

	private BpmnModelInstance importProcessHelper(String resourceName) {
		BpmnModelInstance modelInstance = Bpmn
				.readModelFromStream(HackathonImportTest.class.getResourceAsStream(resourceName));
		return modelInstance;

	}

}
