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

import java.util.Properties;

import com.gargoylesoftware.htmlunit.javascript.host.Node;
import com.olia.processflyer.shared.bpmn.template.Identifiable;
import com.olia.processflyer.shared.bpmn.template.NodeType;

/**
 * A node's instance.
 *
 * @author Philipp Kanne
 */
public interface NodeInstance extends Identifiable
{
    /**
     * The referenced node.
     */
    Node<? extends NodeType> getNode();

    /**
     * Check if node was already executed.
     */
    boolean isExecuted();

    /**
     * The result of the current execution.
     * 
     * @return
     */
    InstanceStatus getStatus();

    /**
     * Instance properties.
     * 
     * @return
     */
    Properties getProperties();
}
