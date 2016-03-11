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
package com.olia.processflyer.shared.bpmn.instance;

import java.util.Set;

import com.gargoylesoftware.htmlunit.javascript.host.Node;
import com.olia.processflyer.shared.bpmn.template.Identifiable;
import com.olia.processflyer.shared.bpmn.template.NodeType;
import com.olia.processflyer.shared.bpmn.template.ProcessTemplate;

/**
 * A template's process instance.
 *
 * @author Philipp Kanne
 */
public interface ProcessInstance extends Identifiable
{
    /**
     * The process template.
     * 
     * @return
     */
    ProcessTemplate getProcessTemplate();

    /**
     * The state.
     * 
     * @return
     */
    InstanceStatus getState();

    /**
     * Retrieves a node instance for a template node.
     * 
     * @param node
     * @return the instance or null when not instanciated
     */
    NodeInstance getInstanceFor(Node<? extends NodeType> node);

    /**
     * Set of all instantiated nodes.
     * 
     * @return A copy of the internal set to avoid changes.
     */
    Set<NodeInstance> getInstances();
}
