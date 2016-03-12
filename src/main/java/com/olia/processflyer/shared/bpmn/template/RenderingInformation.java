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

import java.util.List;

/**
 * Information for rendering a node.
 *
 * @author Philipp Kanne
 */
public interface RenderingInformation
{
    /**
     * The referenced template element.
     * 
     * @return
     */
    TemplateElement getElement();

    /**
     * The upper left corner of a node.
     * 
     * @return
     */
    Point getStartPosition();

    /**
     * The center of a node.
     *
     * @return
     */
    Point getCenterPosition();

    /**
     * The lower right corner of node.
     * 
     * @return
     */
    Point getEndPosition();

    /**
     * All steps from start to end. This list will contain at least the start and end point. I.e a connector can have
     * more than one position from start to end.
     * 
     * @return List of postions, contains at least two elements.
     */
    List<Point> getWayPoints();

    /**
     * The width.
     * 
     * @return
     */
    double getWidth();

    /**
     * The height.
     * 
     * @return
     */
    double getHeight();

    /**
     * The depth.
     * 
     * @return
     */
    double getDepth();

}
