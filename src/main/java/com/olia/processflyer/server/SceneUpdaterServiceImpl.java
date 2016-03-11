package com.olia.processflyer.server;

import java.util.Collection;

import com.olia.processflyer.client.SceneUpdaterService;
import com.olia.processflyer.shared.bpmn.instance.ProcessInstance;

public class SceneUpdaterServiceImpl implements SceneUpdaterService {

	@Override
	public Collection<ProcessInstance> getProcessInstances() {
		return new HackathonDungleMock().createDungle(1);
	}

}
