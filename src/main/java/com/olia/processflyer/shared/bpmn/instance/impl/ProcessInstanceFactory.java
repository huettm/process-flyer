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

import java.util.List;
import java.util.UUID;

import com.olia.processflyer.shared.bpmn.instance.NodeInstance;
import com.olia.processflyer.shared.bpmn.instance.ProcessInstance;
import com.olia.processflyer.shared.bpmn.template.Node;
import com.olia.processflyer.shared.bpmn.template.NodeType;
import com.olia.processflyer.shared.bpmn.template.ProcessTemplate;

/**
 * DOCME
 *
 * @author Philipp Kanne
 */
public class ProcessInstanceFactory
{
    private TemplateNodeExtractor nodeExtractor = new TemplateNodeExtractor();

    private ApplyStatusToNodeInstanceCommand applyStatusCommand;

    public ProcessInstanceFactory(ApplyStatusToNodeInstanceCommand applyStatusCommand)
    {
        super();
        this.applyStatusCommand = applyStatusCommand;
    }

    public ProcessInstanceImpl create(ProcessTemplate template)
    {
        ProcessInstanceImpl instance = new ProcessInstanceImpl(template, UUID.randomUUID().toString());
        List<Node<? extends NodeType>> nodes = nodeExtractor.extractNodes(template);
        for (Node<? extends NodeType> node : nodes)
        {
            NodeInstance newInstance = new NodeInstanceImpl(node, UUID.randomUUID().toString());
            applyStatusCommand.run(newInstance);

            instance.addNodeInstance(newInstance);
        }

        return instance;
    }
}
