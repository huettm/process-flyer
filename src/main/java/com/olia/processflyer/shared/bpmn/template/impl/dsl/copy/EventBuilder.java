package com.olia.processflyer.shared.bpmn.template.impl.dsl.copy;

import com.olia.processflyer.shared.bpmn.template.element.Event;
import com.olia.processflyer.shared.bpmn.template.impl.EventImpl;

public class EventBuilder extends AbstractNodeBuilder<EventImpl, Event, EventBuilder> implements NodeBuilder<Event>
{
    private EventBuilder()
    {
    }

    public static EventBuilder Event()
    {
        return new EventBuilder();
    }

    @Override
    protected EventImpl createNewNode()
    {
        return new EventImpl();
    }
}
