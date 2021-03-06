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

import com.olia.processflyer.shared.bpmn.template.element.Label;

/**
 * Attaches a label to a node.
 *
 * @author Philipp Kanne
 */
public interface Markable
{
    /**
     * The label.
     * 
     * @return
     */
    Label getLabel();
}
