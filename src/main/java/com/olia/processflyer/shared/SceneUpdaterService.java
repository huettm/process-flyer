package com.olia.processflyer.shared;

import java.util.Collection;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.olia.processflyer.shared.bpmn.instance.impl.ProcessInstanceImpl;

@RemoteServiceRelativePath("sceneUpdater")
public interface SceneUpdaterService extends RemoteService {
	Collection<ProcessInstanceImpl> getProcessInstances();
}
