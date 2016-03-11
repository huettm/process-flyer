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
package com.olia.processfly.bpmn.model.impl.dsl;

import static com.olia.processfly.bpmn.template.impl.dsl.EventBuilder.Event;
import static com.olia.processfly.bpmn.template.impl.dsl.TaskBuilder.Task;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.olia.processfly.bpmn.template.element.Event;
import com.olia.processfly.bpmn.template.element.NodeConnector;
import com.olia.processfly.bpmn.template.element.Task;
import com.olia.processfly.bpmn.template.impl.dsl.ConnectionBuilder;

public class ConnectionBuilderTest
{

    @Test
    public void shouldLinkIncomingAndOutgoingNodes() throws Exception
    {
        Task taskNode = aTaskNode();
        Event startNode = theStartingNodeConnectedTo(taskNode);

        NodeConnector outgoing = startNode.getOutgoing().get(0);
        assertThat(outgoing.getStartNode().getUniqueIdentifier()).isEqualTo(startNode.getUniqueIdentifier());
        assertThat(outgoing.getEndNode().getUniqueIdentifier()).isEqualTo(taskNode.getUniqueIdentifier());
    }

    @Test
    public void shouldCreateBiDirectionalLink() throws Exception
    {
        Task taskNode = aTaskNode();
        Event startNode = theStartingNodeConnectedTo(taskNode);

        NodeConnector incoming = taskNode.getIncoming().get(0);
        assertThat(incoming.getEndNode().getUniqueIdentifier()).isEqualTo(startNode.getUniqueIdentifier());

        NodeConnector outgoing = startNode.getOutgoing().get(0);
        assertThat(outgoing.getEndNode().getUniqueIdentifier()).isEqualTo(taskNode.getUniqueIdentifier());

    }

    private Event theStartingNodeConnectedTo(Task taskNode)
    {
        return Event().identifiedBy("StartEvent_1").connected(ConnectionBuilder.to(taskNode)) //
                .node();
    }

    private Task aTaskNode()
    {
        return Task().identifiedBy("Task_1tn7rt3").node();
    }
}
