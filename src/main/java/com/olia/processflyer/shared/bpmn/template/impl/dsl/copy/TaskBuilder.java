package com.olia.processfly.bpmn.template.impl.dsl.copy;

import com.olia.processfly.bpmn.template.element.Task;
import com.olia.processfly.bpmn.template.impl.TaskImpl;

public class TaskBuilder extends AbstractNodeBuilder<TaskImpl, Task, TaskBuilder> implements NodeBuilder<Task>
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
