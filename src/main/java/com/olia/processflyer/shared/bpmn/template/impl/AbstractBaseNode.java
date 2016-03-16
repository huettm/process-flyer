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

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.olia.processflyer.shared.bpmn.template.Node;
import com.olia.processflyer.shared.bpmn.template.NodeType;
import com.olia.processflyer.shared.bpmn.template.NodeVisitor;
import com.olia.processflyer.shared.bpmn.template.RenderingInformation;
import com.olia.processflyer.shared.bpmn.template.element.Label;
import com.olia.processflyer.shared.bpmn.template.element.NodeConnector;

/**
 * DOCME
 *
 * @author Philipp Kanne
 */
public abstract class AbstractBaseNode<NODE_TYPE extends NodeType> implements Node<NODE_TYPE>, IsSerializable
{
    private String uniqueIdentifier;

    private List<NodeConnector> incoming = new ArrayList<NodeConnector>();

    private List<NodeConnector> outgoing = new ArrayList<NodeConnector>();

    private Label label = null;

    private RenderingInformation renderingData = null;

    private NODE_TYPE type;

    public AbstractBaseNode()
    {
        super();
    }

    @Override
    public String getUniqueIdentifier()
    {
        return uniqueIdentifier;
    }

    public void setUniqueIdentifier(String uniqueIdentifier)
    {
        this.uniqueIdentifier = uniqueIdentifier;
    }

    @Override
    public List<NodeConnector> getIncoming()
    {
        return incoming;
    }

    public void setIncoming(List<NodeConnector> incoming)
    {
        this.incoming = incoming;
    }

    @Override
    public List<NodeConnector> getOutgoing()
    {
        return outgoing;
    }

    public void setOutgoing(List<NodeConnector> outgoing)
    {
        this.outgoing = outgoing;
    }

    public boolean addOutgoing(NodeConnector newConnection)
    {
        if (newConnection != null)
        {
            return outgoing.add(newConnection);
        }
        return false;
    }

    public boolean addIncoming(NodeConnector newConnection)
    {
        if (newConnection != null)
        {
            return incoming.add(newConnection);
        }
        return false;
    }

    @Override
    public Label getLabel()
    {
        return label;
    }

    public void setLabel(Label label)
    {
        this.label = label;
    }

    public boolean hasLabel()
    {
        return label != null;
    }

    @Override
    public void accept(NodeVisitor visitor)
    {
        visitor.visit(this);
    }

    @Override
    public RenderingInformation getRenderingData()
    {
        return renderingData;
    }

    public void setRenderingData(RenderingInformation renderingData)
    {
        this.renderingData = renderingData;
    }

    @Override
    public NODE_TYPE getType()
    {
        return type;
    }

    public void setType(NODE_TYPE type)
    {
        this.type = type;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((uniqueIdentifier == null) ? 0 : uniqueIdentifier.hashCode());
        return result;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        AbstractBaseNode other = (AbstractBaseNode) obj;
        if (uniqueIdentifier == null)
        {
            if (other.uniqueIdentifier != null)
            {
                return false;
            }
        }
        else if (!uniqueIdentifier.equals(other.uniqueIdentifier))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "Node [uniqueIdentifier=" + uniqueIdentifier + ", type=" + getType() + "]";
    }

    @Override
    public boolean isNode()
    {
        return true;
    }

    @Override
    public boolean isConnector()
    {
        return false;
    }

    @Override
    public boolean isStartNode()
    {
        return false;
    }

    @Override
    public boolean isEndNode()
    {
        return false;
    }

}
