package com.olia.processflyer.shared.bpmn.template.impl.dsl;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.olia.processflyer.shared.bpmn.template.element.Gateway;
import com.olia.processflyer.shared.bpmn.template.element.GatewayType;
import com.olia.processflyer.shared.bpmn.template.impl.GatewayImpl;

public class GatewayBuilder extends AbstractNodeBuilder<GatewayImpl, Gateway, GatewayBuilder>
		implements NodeBuilder<Gateway>, IsSerializable {
	private GatewayBuilder() {
		super();
	}

	public static GatewayBuilder Gateway() {
		return new GatewayBuilder();
	}

	@Override
	protected GatewayImpl createNewNode() {
		return new GatewayImpl();
	}

	public GatewayBuilder withType(GatewayType type) {
		((GatewayImpl) node()).setType(type);
		return this;
	}

}
