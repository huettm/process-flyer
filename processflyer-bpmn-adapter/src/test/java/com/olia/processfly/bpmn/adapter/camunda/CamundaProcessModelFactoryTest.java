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

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.xml.instance.ModelElementInstance;
import org.junit.Ignore;
import org.junit.Test;

import com.olia.processflyer.shared.bpmn.template.Node;
import com.olia.processflyer.shared.bpmn.template.NodeElementType;
import com.olia.processflyer.shared.bpmn.template.ProcessTemplate;

public class CamundaProcessModelFactoryTest {

	private CamundaProcessModelReader reader = new CamundaProcessModelReader();

	@Test
	public void shouldReadAModel() {
		BpmnModelInstance model = reader.readFromStream("Hackathon_Process.bpmn");

		Set<ModelElementInstance> nodes = new CamundaProcessAdapter(model).getAllNodes();
		assertThat(nodes).isNotEmpty();
	}

	@Test
	@Ignore
	public void shouldCreateTheHackathonProcess() throws Exception {
		BpmnModelInstance model = reader.readFromStream("Hackathon_Process.bpmn");
		CamundaProcessFactory adapter = new CamundaProcessFactory();

		ProcessTemplate theModel = adapter.create(model);
		assertThat(theModel).isNotNull();
	}

	@Test
	public void shouldParseTheHackathonProcess() throws Exception {
		BpmnModelInstance model = reader.readFromStream("Hackathon_Process.bpmn");
		CamundaProcessFactory factory = new CamundaProcessFactory();

		ProcessTemplate theModel = factory.create(model);
		List<Node<?>> allNodes = theModel.getAllNodes();
		
		assertThat(filter(NodeElementType.Task, allNodes)).hasSize(5);
		assertThat(filter(NodeElementType.Gateway, allNodes)).hasSize(2);
		assertThat(filter(NodeElementType.Event, allNodes)).hasSize(2);
	}

	private List<Node<?>> filter(NodeElementType type, List<Node<?>> allNodes) {

		List<Node<?>> filtered = new ArrayList<Node<?>>();
		for (Node<?> node : allNodes) {
			if (node.getElementType() == type){
				filtered.add(node);
			}
		}
		return filtered;
	}

}
