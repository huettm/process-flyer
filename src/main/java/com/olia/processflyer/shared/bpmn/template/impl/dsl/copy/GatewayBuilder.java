package com.olia.processflyer.shared.bpmn.template.impl.dsl.copy;

import com.olia.processflyer.shared.bpmn.template.element.Gateway;
import com.olia.processflyer.shared.bpmn.template.impl.GatewayImpl;

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
