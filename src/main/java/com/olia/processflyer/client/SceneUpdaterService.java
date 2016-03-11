package com.olia.processflyer.client;

import java.util.Collection;
import java.util.Vector;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.olia.processflyer.shared.bpmn.instance.ProcessInstance;

@RemoteServiceRelativePath("sceneUpdater")
public interface SceneUpdaterService {
	Collection<ProcessInstance> getProcessInstances();
}
