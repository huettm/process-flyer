package com.olia.processflyer.shared.bpmn.template.impl.dsl;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.olia.processflyer.shared.bpmn.template.element.Task;
import com.olia.processflyer.shared.bpmn.template.impl.TaskImpl;

public class TaskBuilder extends AbstractNodeBuilder<TaskImpl, Task, TaskBuilder> implements NodeBuilder<Task>, IsSerializable
{
    private TaskBuilder()
    {
        super();
    }

    public static TaskBuilder Task()
    {
        return new TaskBuilder();
    }

    @Override
    protected TaskImpl createNewNode()
    {
        return new TaskImpl();
    }

    public TaskBuilder named(String name)
    {
        ((TaskImpl) node()).setName(name);
        return this;
    }

}
