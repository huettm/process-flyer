package com.olia.processflyer.client;

import java.util.Collection;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.olia.processflyer.shared.bpmn.instance.ProcessInstance;
import com.olia.processflyer.shared.bpmn.instance.impl.ProcessInstanceImpl;

public interface SceneUpdaterServiceAsync {
	void getProcessInstances(AsyncCallback<Collection<ProcessInstanceImpl> > pCallback);
}
