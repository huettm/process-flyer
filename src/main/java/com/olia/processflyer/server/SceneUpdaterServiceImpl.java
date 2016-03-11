package com.olia.processflyer.server;

import java.util.Collection;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.olia.processflyer.shared.SceneUpdaterService;
import com.olia.processflyer.shared.bpmn.instance.impl.ProcessInstanceImpl;

public class SceneUpdaterServiceImpl extends RemoteServiceServlet  implements SceneUpdaterService  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2232537390715617168L;

	@Override
	public Collection<ProcessInstanceImpl> getProcessInstances() {
		return new HackathonDungleMock().createDungle(1);
	}

}
