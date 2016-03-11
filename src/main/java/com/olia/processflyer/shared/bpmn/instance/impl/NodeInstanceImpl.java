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
package com.olia.processflyer.shared.bpmn.instance.impl;

import static com.olia.processflyer.shared.bpmn.instance.impl.InstanceStatusImpl.notExecutedState;

import java.util.Properties;

import com.olia.processflyer.shared.bpmn.instance.InstanceStatus;
import com.olia.processflyer.shared.bpmn.instance.InstanceStatusType;
import com.olia.processflyer.shared.bpmn.instance.NodeInstance;
import com.olia.processflyer.shared.bpmn.template.Node;
import com.olia.processflyer.shared.bpmn.template.NodeType;

/**
 * DOCME
 *
 * @author Philipp Kanne
 */
public class NodeInstanceImpl implements NodeInstance
{

    private final Node<? extends NodeType> node;

    private InstanceStatus status;

    private Properties properties;

    private String uniqueIdentifier;

    public NodeInstanceImpl(Node<? extends NodeType> node, String uniqueIdentifier)
    {
        this.node = node;
        this.uniqueIdentifier = uniqueIdentifier;
        this.status = notExecutedState();
        properties = new Properties();
    }

    @Override
    public Node<? extends NodeType> getNode()
    {
        return node;
    }

    @Override
    public boolean isExecuted()
    {
        return status.getStatus() != InstanceStatusType.None;
    }

    @Override
    public InstanceStatus getStatus()
    {
        return status;
    }

    @Override
    public String getUniqueIdentifier()
    {
        return uniqueIdentifier;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((uniqueIdentifier == null) ? 0 : uniqueIdentifier.hashCode());
        return result;
    }

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
        NodeInstanceImpl other = (NodeInstanceImpl) obj;
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

    public void addProperty(String key, Object value)
    {
        properties.put(key, value);
    }

    public void setStatus(InstanceStatus status)
    {
        this.status = status;
    }

}
