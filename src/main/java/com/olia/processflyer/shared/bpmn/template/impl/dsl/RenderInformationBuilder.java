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
package com.olia.processflyer.shared.bpmn.template.impl.dsl;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.olia.processflyer.shared.bpmn.template.Point;
import com.olia.processflyer.shared.bpmn.template.RenderingInformation;
import com.olia.processflyer.shared.bpmn.template.impl.RenderingInformationImpl;

import java.util.List;

/**
 * DOCME
 *
 * @author Philipp Kanne
 */
public class RenderInformationBuilder implements IsSerializable {
	private RenderingInformationImpl data;

	public RenderInformationBuilder() {
		data = new RenderingInformationImpl();
	}

	public static RenderInformationBuilder Render() {
		return new RenderInformationBuilder();
	}

	public RenderInformationBuilder addWayPoint(double x, double y, double z) {
		data.getWayPoints().add(new Point(x, y, z));
		return this;
	}

	public RenderInformationBuilder addWayPoints(List<Point> points) {
		data.getWayPoints().addAll(points);
		return this;
	}

	public RenderInformationBuilder startPosition(double x, double y, double z) {
		data.setStartPosition(new Point(x, y, z));
		return this;
	}

	public RenderInformationBuilder endPosition(double x, double y, double z) {
		data.setEndPosition(new Point(x, y, z));
		return this;
	}

	public RenderInformationBuilder width(double width) {
		data.setEndPosition(getEndPositionOrCreateFromStartPosition().add(new Point(width, 0, 0)));

		return this;
	}

	public RenderInformationBuilder height(double height) {
		data.setEndPosition(getEndPositionOrCreateFromStartPosition().add(new Point(0, height, 0)));

		return this;
	}

	public RenderInformationBuilder depth(double depth) {
		data.setEndPosition(getEndPositionOrCreateFromStartPosition().add(new Point(0, 0, depth)));

		return this;
	}

	private Point getEndPositionOrCreateFromStartPosition() {
		Point endPosition = data.getEndPosition();
		if (endPosition == null) {
			Point start = data.getStartPosition();
			data.setEndPosition(new Point(start.getX(), start.getY(), start.getZ()));
			endPosition = data.getEndPosition();
		}
		return endPosition;
	}

	public RenderingInformation getData() {
		data.calculateStartAndEndFromWaypoints();

		return data;
	}

}
