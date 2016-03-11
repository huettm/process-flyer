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
package com.olia.processfly.bpmn.template.impl;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Point3D;

import com.olia.processfly.bpmn.template.RenderingInformation;
import com.olia.processfly.bpmn.template.TemplateElement;

/**
 * DOCME
 *
 * @author Philipp Kanne
 */
public class RenderingInformationImpl implements RenderingInformation
{
    private TemplateElement element;

    private List<Point3D> waypoints = new ArrayList<Point3D>();

    private Point3D startPosition = new Point3D(0, 0, 0);

    private Point3D endPosition = new Point3D(0, 0, 0);

    private double width;

    private double height;

    private double depth;

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
    public Point3D getStartPosition()
    {
        return startPosition;
    }

    @Override
    public Point3D getEndPosition()
    {
        return endPosition;
    }

    @Override
    public List<Point3D> getWayPoints()
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

    public boolean addWaypoint(Point3D waypoint)
    {
        if (waypoint == null)
        {
            return false;
        }
        return waypoints.add(waypoint);
    }

    public void setStartPosition(Point3D startPosition)
    {
        this.startPosition = startPosition;
    }

    public void setEndPosition(Point3D endPosition)
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
