package com.olia.processfly.bpmn.template.impl.dsl;

import com.olia.processfly.bpmn.template.element.Gateway;
import com.olia.processfly.bpmn.template.impl.GatewayImpl;

public class GatewayBuilder extends AbstractNodeBuilder<GatewayImpl, Gateway, GatewayBuilder> implements
        NodeBuilder<Gateway>
{
    private GatewayBuilder()
    {
        super();
    }

    public static GatewayBuilder Gateway()
    {
        return new GatewayBuilder();
    }

    @Override
    protected GatewayImpl createNewNode()
    {
        return new GatewayImpl();
    }

}
