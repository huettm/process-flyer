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

import java.io.File;

import org.camunda.bpm.model.bpmn.Bpmn;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;

/**
 * @author Philipp Kanne
 *
 */
public class CamundaProcessModelReader {

	public BpmnModelInstance readFromStream(String processTemplate) {
		BpmnModelInstance modelInstance = Bpmn
				.readModelFromStream(CamundaProcessModelReader.class.getResourceAsStream(processTemplate));
		return modelInstance;
	}

	public BpmnModelInstance read(File file) {
		BpmnModelInstance modelInstance = Bpmn.readModelFromFile(file);
		return modelInstance;
	}
	
	public BpmnModelInstance read(String fileName) {
		return read(new File(fileName));
	}


}
