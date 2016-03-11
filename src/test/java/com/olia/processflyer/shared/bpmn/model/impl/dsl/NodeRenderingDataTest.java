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
package com.olia.processflyer.shared.bpmn.model.impl.dsl;

import static com.olia.processflyer.shared.bpmn.template.impl.dsl.RenderInformationBuilder.Render;
import static com.olia.processflyer.shared.bpmn.template.impl.dsl.TaskBuilder.Task;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Ignore;
import org.junit.Test;

import com.olia.processflyer.shared.bpmn.template.Point;
import com.olia.processflyer.shared.bpmn.template.element.Task;
import com.olia.processflyer.shared.bpmn.template.impl.dsl.TaskBuilder;

@Ignore
public class NodeRenderingDataTest
{

    @Test
    public void shouldAddStartingPoint()
    {
        Task node = Task().render(Render().startPosition(0d, 0d, 0d)) //
                .node();

        assertThat(node.getRenderingData().getStartPosition()).isEqualTo(new Point(0d, 0d, 0d));
    }

    @Test
    public void shouldAddEndPoint()
    {
        Task node = Task().render(Render().endPosition(10d, 10d, 10d)) //
                .node();

        assertThat(node.getRenderingData().getEndPosition()).isEqualTo(new Point(10d, 10d, 10d));
    }

    @Test
    public void shouldAddWayPoint()
    {
        Task node = Task().render(Render().addWayPoint(0d, 1d, 1d)) //
                .node();

        Point waypoint = node.getRenderingData().getWayPoints().get(0);
        assertThat(waypoint).isEqualTo(new Point(0d, 1d, 1d));
    }

    @Test
    public void shouldCalcWidthFromStartPoint() throws Exception
    {
        Task node = Task().render(Render() //
                .startPosition(0d, 0d, 0d).width(10d)).node();

        assertThat(node.getRenderingData().getEndPosition()).isEqualTo(new Point(10d, 0d, 0d));
    }

    @Test
    public void shouldCalcHeightFromStartPoint() throws Exception
    {
        Task node = TaskBuilder.Task().render(Render() //
                .startPosition(0d, 0d, 0d).height(10d)).node();

        assertThat(node.getRenderingData().getEndPosition()).isEqualTo(new Point(0d, 10d, 0d));
    }

    @Test
    public void shouldCalcDepthFromStartPoint() throws Exception
    {
        Task node = Task().render(Render() //
                .startPosition(0d, 0d, 0d).depth(10d)).node();

        assertThat(node.getRenderingData().getEndPosition()).isEqualTo(new Point(0d, 0d, 10d));
    }
}
