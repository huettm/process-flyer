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
package com.olia.processflyer.shared.bpmn.instance;

import static com.olia.processflyer.shared.bpmn.template.impl.dsl.ConnectionBuilder.to;
import static com.olia.processflyer.shared.bpmn.template.impl.dsl.EventBuilder.Event;
import static com.olia.processflyer.shared.bpmn.template.impl.dsl.GatewayBuilder.Gateway;
import static com.olia.processflyer.shared.bpmn.template.impl.dsl.LabelBuilder.Label;
import static com.olia.processflyer.shared.bpmn.template.impl.dsl.RenderInformationBuilder.Render;
import static com.olia.processflyer.shared.bpmn.template.impl.dsl.TaskBuilder.Task;

import com.olia.processflyer.shared.bpmn.template.ProcessTemplate;
import com.olia.processflyer.shared.bpmn.template.impl.ProcessTemplateImpl;
import com.olia.processflyer.shared.bpmn.template.impl.dsl.EventBuilder;
import com.olia.processflyer.shared.bpmn.template.impl.dsl.GatewayBuilder;
import com.olia.processflyer.shared.bpmn.template.impl.dsl.TaskBuilder;

/**
 * DOCME
 *
 * @author Philipp Kanne
 */
public class HackathonProcessMock
{
    public static ProcessTemplate createTemplate()
    {
        EventBuilder StartEvent_1 = Event()
                .identifiedBy("StartEvent_1")
                .render(Render().startPosition(245d, 102d, 0d).width(36d).height(36d))
                .connected(to(Label().identifiedBy("StartEvent_1_label").withText("Start")
                                   .render(Render().startPosition(218d, 138d, 0d).width(90d).height(20d))));
        TaskBuilder Task_1tn7rt3 = Task().identifiedBy("Task_1tn7rt3").named("Perform Hackathon - Project")
                .render(Render().startPosition(345d, 80d, 0d).width(100d).height(80d));

        StartEvent_1.connected(to(Task_1tn7rt3).identifiedBy("SequenceFlow_0smcf6h")
                .render(Render().startPosition(281d, 120d, 0d).endPosition(345d, 120d, 0d).addWayPoint(281d, 120d, 0d)
                                .addWayPoint(345d, 120d, 0d)));

        TaskBuilder Task_0mozaz3 = Task().identifiedBy("Task_0mozaz3").named("Present results")
                .render(Render().startPosition(497d, 80d, 0d).width(100d).height(80d));

        Task_1tn7rt3.connected(to(Task_0mozaz3).identifiedBy("SequenceFlow_1qh3kef")
                .render(Render().startPosition(445d, 120d, 0d).endPosition(497d, 120d, 0d)//
                        .addWayPoint(445d, 120d, 0d) //
                        .addWayPoint(472d, 120d, 0d) //
                        .addWayPoint(472d, 120d, 0d) //
                        .addWayPoint(497d, 120d, 0d)));

        GatewayBuilder ExclusiveGateway_0opqb7m = Gateway()
                .identifiedBy("ExclusiveGateway_0opqb7m")
                .render(Render().startPosition(667d, 95d, 0d).width(50d).height(50d))
                .connected(to(Label().identifiedBy("ExclusiveGateway_0opqb7m_label").withText("Results outstanding?")
                                   .render(Render().startPosition(610d, 72d, 0d).width(90d).height(20d))));

        Task_0mozaz3.connected(to(ExclusiveGateway_0opqb7m).identifiedBy("SequenceFlow_0uhxihd")
                .render(Render().startPosition(597d, 120d, 0d).endPosition(667d, 120d, 0d)//
                        .addWayPoint(597d, 120d, 0d) //
                        .addWayPoint(633d, 120d, 0d) //
                        .addWayPoint(633d, 120d, 0d) //
                        .addWayPoint(667d, 120d, 0d)));

        TaskBuilder Task_07r46fp = Task().identifiedBy("Task_07r46fp").named("Take money and buy beer for team")
                .render(Render().startPosition(808d, 25d, 0d).width(100d).height(80d));
        TaskBuilder Task_1k5wexy = Task().identifiedBy("Task_1k5wexy").named("Lend money and buy beer for team")
                .render(Render().startPosition(808d, 145d, 0d).width(100d).height(80d));

        // TODO Connection can have connections to labels!
        ExclusiveGateway_0opqb7m.connected(to(Task_07r46fp).identifiedBy("SequenceFlow_1pto0fq")
                                                   .render(Render().startPosition(692d, 95d, 0d)
                                                                   .endPosition(808d, 65d, 0d)//
                                                                   .addWayPoint(692d, 95d, 0d) //
                                                                   .addWayPoint(692d, 65d, 0d) //
                                                                   .addWayPoint(808d, 95d, 0d)))
                .connected(to(Task_1k5wexy).identifiedBy("SequenceFlow_09gzo45")
                                   .render(Render().startPosition(692d, 145d, 0d).endPosition(808d, 185d, 0d)//
                                           .addWayPoint(692d, 145d, 0d) //
                                           .addWayPoint(692d, 185d, 0d) //
                                           .addWayPoint(808d, 185d, 0d)));

        GatewayBuilder ExclusiveGateway_1o1oogl = Gateway().identifiedBy("ExclusiveGateway_1o1oogl")
                .render(Render().startPosition(995d, 95d, 0d).width(50d).height(50d));

        Task_07r46fp.connected(to(ExclusiveGateway_1o1oogl).identifiedBy("SequenceFlow_1r58419")
                .render(Render().startPosition(908d, 65d, 0d).endPosition(1020d, 95d, 0d)//
                        .addWayPoint(908d, 65d, 0d) //
                        .addWayPoint(1020d, 65d, 0d) //
                        .addWayPoint(1020d, 95d, 0d)));
        Task_1k5wexy.connected(to(ExclusiveGateway_1o1oogl).identifiedBy("SequenceFlow_0q0hk3a"))
                .render(Render().startPosition(908d, 185d, 0d).endPosition(1020d, 145d, 0d)//
                        .addWayPoint(908d, 185d, 0d) //
                        .addWayPoint(1020d, 185d, 0d) //
                        .addWayPoint(1020d, 145d, 0d));

        TaskBuilder Task_1k0dzw7 = Task().identifiedBy("Task_1k0dzw7").named("Drink beer and enjoy the fun you had")
                .render(Render().startPosition(1109d, 80d, 0d).width(100d).height(80d));

        ExclusiveGateway_1o1oogl.connected(to(Task_1k0dzw7).identifiedBy("SequenceFlow_1kpkyim")
                .render(Render().startPosition(1045d, 120d, 0d).endPosition(1109d, 120d, 0d)//
                        .addWayPoint(1045d, 120d, 0d) //
                        .addWayPoint(1109d, 120d, 0d)));

        EventBuilder EndEvent_1e9sihl = Event().identifiedBy("EndEvent_1e9sihl")
                .render(Render().startPosition(1299d, 102d, 0d).width(36d).height(36d));

        Task_1k0dzw7.connected(to(EndEvent_1e9sihl).identifiedBy("SequenceFlow_1oqpc9v"))
                .render(Render().startPosition(1209d, 120d, 0d).endPosition(1299d, 120d, 0d)//
                        .addWayPoint(1209d, 120d, 0d) //
                        .addWayPoint(1299d, 120d, 0d));

        return new ProcessTemplateImpl(StartEvent_1.node(), "Hackaton_Process");
    }

}
