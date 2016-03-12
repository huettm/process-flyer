package com.olia.processflyer.shared.bpmn.template.impl.dsl;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.olia.processflyer.shared.bpmn.template.element.Event;
import com.olia.processflyer.shared.bpmn.template.impl.EventImpl;

public class EventBuilder extends AbstractNodeBuilder<EventImpl, Event, EventBuilder> implements NodeBuilder<Event>, IsSerializable
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
