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

import org.camunda.bpm.model.bpmn.impl.instance.di.LabelImpl;
import org.camunda.bpm.model.bpmn.instance.dc.Bounds;
import org.camunda.bpm.model.xml.instance.ModelElementInstance;

import com.olia.processflyer.shared.bpmn.template.element.Label;
import com.olia.processflyer.shared.bpmn.template.impl.dsl.LabelBuilder;

/**
 * @author Philipp Kanne
 *
 */
public class CamundaLabelAdapter implements CamundaNodeAdapter<org.camunda.bpm.model.bpmn.instance.di.Label, Label> {

	private CamundaLabelAdapter() {

	}

	public static CamundaLabelAdapter aLabelAdapter() {
		return new CamundaLabelAdapter();
	}

	public Label adapt(org.camunda.bpm.model.bpmn.instance.di.Label node) {

		LabelImpl event = (LabelImpl) node;

		Bounds bounds = event.getBounds();
		return LabelBuilder.Label().identifiedBy(event.getId()) //
				.render(aBoundsAdapter().adapt(bounds)).node();

	}

	public boolean canAdapt(ModelElementInstance node) {

		return org.camunda.bpm.model.bpmn.instance.di.Label.class.isAssignableFrom(node.getClass());
	}

}
