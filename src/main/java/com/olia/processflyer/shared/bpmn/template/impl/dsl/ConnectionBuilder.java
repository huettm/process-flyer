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
package com.olia.processflyer.shared.bpmn.template.impl.dsl;

import com.olia.processflyer.shared.bpmn.template.Node;
import com.olia.processflyer.shared.bpmn.template.NodeType;
import com.olia.processflyer.shared.bpmn.template.RenderingInformation;
import com.olia.processflyer.shared.bpmn.template.impl.NodeConnectorImpl;
import com.olia.processflyer.shared.bpmn.template.impl.RenderingInformationImpl;

/**
 * DOCME
 *
 * @author Philipp Kanne
 */
public class ConnectionBuilder
{
    private NodeConnectorImpl connection;

    private ConnectionBuilder()
    {
        connection = new NodeConnectorImpl();
    }

    public ConnectionBuilder identifiedBy(String identifier)
    {
        connection.setUniqueIdentifier(identifier);
        return this;
    }

    public static ConnectionBuilder to(Node<? extends NodeType> node)
    {
        ConnectionBuilder connection = new ConnectionBuilder();
        connection.getConnection().setEndNode(node);

        return connection;
    }

    public static ConnectionBuilder to(NodeBuilder<? extends Node<? extends NodeType>> node)
    {
        return to(node.node());
    }

    public ConnectionBuilder from(Node<? extends NodeType> from)
    {
        getConnection().setStartNode(from);
        return this;
    }

    public ConnectionBuilder render(RenderInformationBuilder rendering)
    {
        RenderingInformation data = rendering.getData();

        connection.setRenderingData(data);
        ((RenderingInformationImpl) data).setElement(connection);

        return this;
    }

    public NodeConnectorImpl getConnection()
    {
        return connection;
    }
}
