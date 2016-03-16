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
import static com.olia.processflyer.shared.bpmn.template.impl.dsl.GatewayBuilder.Gateway;
import static com.olia.processflyer.shared.bpmn.template.impl.dsl.LabelBuilder.Label;

import java.util.Collection;

import org.camunda.bpm.model.bpmn.impl.instance.GatewayImpl;
import org.camunda.bpm.model.bpmn.instance.dc.Bounds;
import org.camunda.bpm.model.bpmn.instance.di.Label;
import org.camunda.bpm.model.xml.instance.ModelElementInstance;

import com.olia.processflyer.shared.bpmn.template.element.Gateway;
import com.olia.processflyer.shared.bpmn.template.element.GatewayType;
import com.olia.processflyer.shared.bpmn.template.impl.dsl.ConnectionBuilder;
import com.olia.processflyer.shared.bpmn.template.impl.dsl.GatewayBuilder;
import com.olia.processflyer.shared.bpmn.template.impl.dsl.LabelBuilder;

/**
 * @author Philipp Kanne
 *
 */
public class CamundaGatewayAdapter implements CamundaNodeAdapter<org.camunda.bpm.model.bpmn.instance.Gateway, Gateway> {

	private CamundaGatewayAdapter() {

	}

	public static CamundaGatewayAdapter aGatewayAdapter() {
		return new CamundaGatewayAdapter();
	}

	public Gateway adapt(org.camunda.bpm.model.bpmn.instance.Gateway node) {

		GatewayImpl gateway = (GatewayImpl) node;
		Bounds bounds = node.getDiagramElement().getBounds();

		GatewayBuilder gatewayBuilder = Gateway().identifiedBy(gateway.getId()) //
				.render(aBoundsAdapter().adapt(bounds)) //
				.withType(resolveGatewayType(gateway));

		// Add each label
		Collection<Label> eventLabel = gateway.getDiagramElement().getChildElementsByType(Label.class);
		if (eventLabel != null) {
			for (Label label : eventLabel) {
				LabelBuilder labelBuilder = Label().withText(gateway.getName()) //
						.render(aBoundsAdapter().adapt(label.getBounds())).identifiedBy(label.getId());
				gatewayBuilder.connected(ConnectionBuilder.to(labelBuilder));
			}
		}

		Gateway result = gatewayBuilder.node();
		
		return result;

	}

	private GatewayType resolveGatewayType(GatewayImpl gateway) {
		String typeName = gateway.getElementType().getTypeName();
		if ("exclusiveGateway".equalsIgnoreCase(typeName)) {
			return GatewayType.Exklusive;
		}
		
		return GatewayType.Unknown;
	}

	public boolean canAdapt(ModelElementInstance node) {

		return org.camunda.bpm.model.bpmn.instance.Gateway.class.isAssignableFrom(node.getClass());
	}

}
