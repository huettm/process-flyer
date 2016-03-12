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
import com.olia.processflyer.shared.bpmn.template.Point;
import com.olia.processflyer.shared.bpmn.template.RenderingInformation;
import com.olia.processflyer.shared.bpmn.template.TemplateElement;

/**
 * DOCME
 *
 * @author Philipp Kanne
 */
public class RenderingInformationImpl implements RenderingInformation, IsSerializable
{
    private TemplateElement element;

    private List<Point> waypoints = new ArrayList<Point>();

    private Point startPosition = new Point(0d, 0d, 0d);

    private Point endPosition = new Point(0d, 0d, 0d);

    private double width;

    private double height;

    private double depth;

    public RenderingInformationImpl(){}
    
    @Override
    public TemplateElement getElement()
    {
        return element;
    }

    public void setElement(TemplateElement element)
    {
        this.element = element;
    }

    @Override
    public Point getStartPosition()
    {
        return startPosition;
    }

    @Override
    public Point getEndPosition()
    {
        return endPosition;
    }

    @Override
    public List<Point> getWayPoints()
    {
        return waypoints;
    }

    @Override
    public double getWidth()
    {
        return width;
    }

    @Override
    public double getHeight()
    {
        return height;
    }

    @Override
    public double getDepth()
    {
        return depth;
    }

    public boolean addWaypoint(Point waypoint)
    {
        if (waypoint == null)
        {
            return false;
        }
        return waypoints.add(waypoint);
    }

    public void setStartPosition(Point startPosition)
    {
        this.startPosition = startPosition;
    }

    public void setEndPosition(Point endPosition)
    {
        this.endPosition = endPosition;
    }

    public void setWidth(double width)
    {
        this.width = width;
    }

    public void setHeight(double height)
    {
        this.height = height;
    }

    public void setDepth(double depth)
    {
        this.depth = depth;
    }

    @Override
    public String toString()
    {
        return "Rendering [start="
                + startPosition
                + ", end="
                + endPosition
                + ", width="
                + width
                + ", height="
                + height
                + ", depth="
                + depth
                + "]";
    }

}
