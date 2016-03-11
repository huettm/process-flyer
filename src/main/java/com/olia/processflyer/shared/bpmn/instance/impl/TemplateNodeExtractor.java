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

import static com.olia.processflyer.shared.bpmn.template.impl.InOrderNodeVisitorStrategy.anInOrderVisitorStrategyFor;

import java.util.List;

import com.olia.processflyer.shared.bpmn.template.Node;
import com.olia.processflyer.shared.bpmn.template.ProcessTemplate;
import com.olia.processflyer.shared.bpmn.template.impl.NodeVisitorStrategy;

/**
 * Extracts all nodes from the template using a visitor. Uses an in-order strategy to iterate the template.
 * 
 * @author Philipp Kanne
 */
public class TemplateNodeExtractor
{
    private CollectAllNodesProcessTemplateVisitor visitor = new CollectAllNodesProcessTemplateVisitor();

    public List<Node<?>> extractNodes(ProcessTemplate template)
    {
        NodeVisitorStrategy visitorStrategy = anInOrderVisitorStrategyFor(template.getRootNode());
        visitorStrategy.apply(visitor);

        return visitor.getNodes();
    }

}
