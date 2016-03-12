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
package com.olia.processflyer.shared.bpmn.template.impl;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.olia.processflyer.shared.bpmn.template.Node;
import com.olia.processflyer.shared.bpmn.template.NodeType;
import com.olia.processflyer.shared.bpmn.template.NodeVisitor;
import com.olia.processflyer.shared.bpmn.template.element.NodeConnector;

/**
 * Iterates in-order through the process template. Creates a new path for each connection.
 *
 * @author Philipp Kanne
 */
public class InOrderNodeVisitorStrategy implements NodeVisitorStrategy, IsSerializable
{
    private final Node<? extends NodeType> theRoot;

    private InOrderNodeVisitorStrategy(Node<? extends NodeType> theRoot)
    {
        this.theRoot = theRoot;
    }

    public static NodeVisitorStrategy anInOrderVisitorStrategyFor(Node<? extends NodeType> theRoot)
    {
        return new InOrderNodeVisitorStrategy(theRoot);
    }

    @Override
    public void apply(NodeVisitor visitor)
    {
        apply(theRoot, visitor);
    }

    /**
     * Applies the visitor on each node. Starts recursion for each outgoing connection.
     * 
     * @param node
     * @param visitor
     */
    private void apply(Node<? extends NodeType> node, NodeVisitor visitor)
    {
        if (node == null)
        {
            return;
        }

        node.accept(visitor);
        for (NodeConnector aConnector : node.getOutgoing())
        {
            aConnector.accept(visitor);
            apply(aConnector.getEndNode(), visitor);
        }
    }

}
