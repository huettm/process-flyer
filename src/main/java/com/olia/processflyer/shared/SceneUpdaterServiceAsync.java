package com.olia.processflyer.shared;

import java.util.Collection;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.olia.processflyer.shared.bpmn.instance.impl.ProcessInstanceImpl;

/**
 * Manuall async interface
 * @author grobap
 *
 */
public interface SceneUpdaterServiceAsync {
	void getProcessInstances(AsyncCallback<ProcessInstanceImpl[] > pCallback);
}
