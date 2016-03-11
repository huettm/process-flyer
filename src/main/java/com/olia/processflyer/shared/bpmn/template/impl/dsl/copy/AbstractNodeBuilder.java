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
package com.olia.processflyer.shared.bpmn.template.impl.dsl.copy;

import com.olia.processflyer.shared.bpmn.template.Node;
import com.olia.processflyer.shared.bpmn.template.NodeType;
import com.olia.processflyer.shared.bpmn.template.RenderingInformation;
import com.olia.processflyer.shared.bpmn.template.impl.AbstractBaseNode;
import com.olia.processflyer.shared.bpmn.template.impl.NodeConnectorImpl;
import com.olia.processflyer.shared.bpmn.template.impl.RenderingInformationImpl;

/**
 * DOCME
 *
 * @author Philipp Kanne
 */
public abstract class AbstractNodeBuilder<NODE extends AbstractBaseNode<? extends NodeType>, NODE_INTERFACE extends Node<? extends NodeType>, THAT extends NodeBuilder<NODE_INTERFACE>>
{

    private NODE_INTERFACE theNode;

    public NODE_INTERFACE node()
    {
        return theNode;
    }

    public AbstractNodeBuilder()
    {
        theNode = createNewNode();
    }

    public THAT identifiedBy(String identifier)
    {
        theNode().setUniqueIdentifier(identifier);
        return that();
    }

    @SuppressWarnings("unchecked")
    private THAT that()
    {
        return (THAT) this;
    }

    @SuppressWarnings("unchecked")
    private NODE theNode()
    {
        return (NODE) theNode;
    }

    public THAT connected(ConnectionBuilder connection)
    {
        connection.from(theNode);
        theNode().addOutgoing(connection.getConnection());

        // Create backlink
        Node<? extends NodeType> end = connection.getConnection().getEndNode();
        NodeConnectorImpl incoming = ConnectionBuilder.to(theNode).from(end).getConnection();
        ((AbstractBaseNode<? extends NodeType>) end).addIncoming(incoming);

        return that();
    }

    public THAT render(RenderInformationBuilder rendering)
    {
        RenderingInformation data = rendering.getData();

        theNode().setRenderingData(data);
        ((RenderingInformationImpl) data).setElement(theNode());

        return that();
    }

    protected abstract NODE_INTERFACE createNewNode();

}
