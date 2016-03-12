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

import static com.olia.processflyer.shared.bpmn.template.impl.dsl.RenderInformationBuilder.Render;

import org.camunda.bpm.model.bpmn.instance.dc.Bounds;
import org.camunda.bpm.model.xml.instance.ModelElementInstance;

import com.olia.processflyer.shared.bpmn.template.RenderingInformation;
import com.olia.processflyer.shared.bpmn.template.impl.dsl.RenderInformationBuilder;

/**
 * @author Philipp Kanne
 *
 */
public class CamundaBoundsAdapter implements CamundaNodeAdapter<Bounds, RenderingInformation> {

	private CamundaBoundsAdapter() {

	}

	public static final CamundaBoundsAdapter aBoundsAdapter() {
		return new CamundaBoundsAdapter();
	}

	public RenderingInformation adapt(Bounds bounds) {

		RenderInformationBuilder renderBuilder = Render() //
				.startPosition(bounds.getX(), bounds.getY(), 0d) //
				.depth(0d) //
				.width(bounds.getWidth()) //
				.height(bounds.getHeight());
		
		return renderBuilder.getData();
	}

	public boolean canAdapt(ModelElementInstance node) {
		return Bounds.class.isAssignableFrom(node.getClass());
	}

}
