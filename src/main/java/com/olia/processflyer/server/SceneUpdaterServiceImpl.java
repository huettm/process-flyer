package com.olia.processflyer.server;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.olia.processflyer.client.SceneUpdaterService;
import com.olia.processflyer.shared.bpmn.instance.HackathonProcessMock;
import com.olia.processflyer.shared.bpmn.instance.InstanceStatus;
import com.olia.processflyer.shared.bpmn.instance.NodeInstance;
import com.olia.processflyer.shared.bpmn.instance.ProcessInstance;
import com.olia.processflyer.shared.bpmn.instance.impl.ApplyStatusToNodeInstanceCommand;
import com.olia.processflyer.shared.bpmn.instance.impl.ProcessInstanceFactory;
import com.olia.processflyer.shared.bpmn.instance.impl.ProcessInstanceStatusResolver;
import com.olia.processflyer.shared.bpmn.template.ProcessTemplate;

public class SceneUpdaterServiceImpl extends com.google.gwt.user.server.rpc.RemoteServiceServlet implements SceneUpdaterService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7859593133747401123L;

	private HackathonProcessMock hackatonTemplate = new HackathonProcessMock();
	
	private ProcessInstanceFactory instanceFactory;

	public SceneUpdaterServiceImpl() {
		this.instanceFactory = new ProcessInstanceFactory(
				new ApplyStatusToNodeInstanceCommand(new NullStatusResolver()));
	}

	public Collection<ProcessInstance> createDungle(int amount) {
		Set<ProcessInstance> result = new HashSet<ProcessInstance>();

		ProcessTemplate template = hackatonTemplate.createTemplate();

		for (int i = 0; i < amount; i++) {
			result.add(instanceFactory.create(template));
		}

		return result;
	}

	public static void main(String[] args) {
		new SceneUpdaterServiceImpl().createDungle(10);
	}

	public class NullStatusResolver implements ProcessInstanceStatusResolver {

		@Override
		public InstanceStatus resolve(NodeInstance instance) {
			return null;
		}
	}

	@Override
	public Collection<ProcessInstance> getProcessInstances() {
		// TODO Auto-generated method stub
		return createDungle(1);
	}

}
