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
package com.olia.processflyer.shared.bpmn.template.impl.dsl.copy;

import javafx.geometry.Point3D;

import com.olia.processflyer.shared.bpmn.template.RenderingInformation;
import com.olia.processflyer.shared.bpmn.template.impl.RenderingInformationImpl;

/**
 * DOCME
 *
 * @author Philipp Kanne
 */
public class RenderInformationBuilder
{
    private RenderingInformationImpl data;

    private RenderInformationBuilder()
    {
        data = new RenderingInformationImpl();
    }

    public static RenderInformationBuilder Render()
    {
        return new RenderInformationBuilder();
    }

    public RenderInformationBuilder addWayPoint(double x, double y, double z)
    {
        data.getWayPoints().add(new Point3D(x, y, z));
        return this;
    }

    public RenderInformationBuilder startPosition(double x, double y, double z)
    {
        data.setStartPosition(new Point3D(x, y, z));
        return this;
    }

    public RenderInformationBuilder endPosition(double x, double y, double z)
    {
        data.setEndPosition(new Point3D(x, y, z));

        Point3D subtracted = data.getEndPosition().subtract(data.getStartPosition());

        data.setWidth(subtracted.getX());
        data.setHeight(subtracted.getY());
        data.setDepth(subtracted.getZ());

        return this;
    }

    public RenderInformationBuilder width(double width)
    {
        data.setEndPosition(getEndPositionOrCreateFromStartPosition().add(new Point3D(width, 0, 0)));
        data.setWidth(width);

        return this;
    }

    public RenderInformationBuilder height(double height)
    {
        data.setEndPosition(getEndPositionOrCreateFromStartPosition().add(new Point3D(0, height, 0)));
        data.setHeight(height);

        return this;
    }

    public RenderInformationBuilder depth(double depth)
    {
        data.setEndPosition(getEndPositionOrCreateFromStartPosition().add(new Point3D(0, 0, depth)));
        data.setDepth(depth);

        return this;
    }

    private Point3D getEndPositionOrCreateFromStartPosition()
    {
        Point3D endPosition = data.getEndPosition();
        if (endPosition == null)
        {
            Point3D start = data.getStartPosition();
            data.setEndPosition(new Point3D(start.getX(), start.getY(), start.getZ()));
            endPosition = data.getEndPosition();
        }
        return endPosition;
    }

    public RenderingInformation getData()
    {
        return data;
    }

}
