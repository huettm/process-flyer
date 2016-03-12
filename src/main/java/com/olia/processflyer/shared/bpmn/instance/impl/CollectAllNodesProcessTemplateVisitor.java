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

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.olia.processflyer.shared.bpmn.template.Node;
import com.olia.processflyer.shared.bpmn.template.NodeVisitor;
import com.olia.processflyer.shared.bpmn.template.TemplateElement;

/**
 * DOCME
 *
 * @author Philipp Kanne
 */
public class CollectAllNodesProcessTemplateVisitor implements NodeVisitor, IsSerializable
{
    private List<Node<?>> allNodes = new ArrayList<Node<?>>();

    public CollectAllNodesProcessTemplateVisitor(){}
    
    @Override
    public void visit(TemplateElement theElement)
    {
        if (theElement.isNode())
        {
            allNodes.add((Node<?>) theElement);
        }
    }

    public List<Node<?>> getNodes()
    {
        return allNodes;
    }

}
