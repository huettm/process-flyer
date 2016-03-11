
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
package com.olia.processflyer.server;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.olia.processflyer.shared.bpmn.instance.InstanceStatus;
import com.olia.processflyer.shared.bpmn.instance.NodeInstance;
import com.olia.processflyer.shared.bpmn.instance.ProcessInstance;
import com.olia.processflyer.shared.bpmn.instance.impl.ApplyStatusToNodeInstanceCommand;
import com.olia.processflyer.shared.bpmn.instance.impl.ProcessInstanceFactory;
import com.olia.processflyer.shared.bpmn.instance.impl.ProcessInstanceImpl;
import com.olia.processflyer.shared.bpmn.instance.impl.ProcessInstanceStatusResolver;
import com.olia.processflyer.shared.bpmn.template.ProcessTemplate;

/**
 * DOCME
 *
 * @author Philipp Kanne
 */
public class HackathonDungleMock {
	private HackathonProcessMock hackatonTemplate = new HackathonProcessMock();

	private ProcessInstanceFactory instanceFactory;

	public HackathonDungleMock() {
		this.instanceFactory = new ProcessInstanceFactory(
				new ApplyStatusToNodeInstanceCommand(new NullStatusResolver()));
	}

	public Collection<ProcessInstanceImpl> createDungle(int amount) {
		Set<ProcessInstanceImpl> result = new HashSet<ProcessInstanceImpl>();

		ProcessTemplate template = hackatonTemplate.createTemplate();
		for (int i = 0; i < amount; i++) {
			result.add(instanceFactory.create(template));
		}

		return result;
	}

	public class NullStatusResolver implements ProcessInstanceStatusResolver {

		@Override
		public InstanceStatus resolve(NodeInstance instance) {
			return null;
		}
	}

}
