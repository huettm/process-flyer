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
package com.olia.processflyer.shared.bpmn.template;

import java.util.List;

import com.olia.processflyer.shared.bpmn.template.element.NodeConnector;

/**
 * A BPMN node.
 *
 * @author Philipp Kanne
 */
public interface Node<NODE_TYPE extends NodeType> extends Identifiable, Markable, Renderable, TemplateElement,
        Visitable
{
    /**
     * All incoming connections.
     * 
     * @return
     */
    List<NodeConnector> getIncoming();

    /**
     * All outgoing connections.
     * 
     * @return
     */
    List<NodeConnector> getOutgoing();

    /**
     * The node type.
     * 
     * @return
     */
    NODE_TYPE getType();
    
    NodeElementType getElementType();

}
