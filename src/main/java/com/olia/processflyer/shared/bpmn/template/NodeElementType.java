/**
 * 
 */
package com.olia.processflyer.shared.bpmn.template;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * @author philipp
 *
 */
public enum NodeElementType implements IsSerializable{

	Task, Gateway, Label, Event;
}
