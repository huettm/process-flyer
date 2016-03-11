package com.olia.processfly.bpmn.instance.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.olia.processfly.bpmn.instance.ProcessInstance;
import com.olia.processfly.bpmn.template.Node;
import com.olia.processfly.bpmn.template.ProcessTemplate;
import com.olia.processfly.bpmn.template.impl.dsl.ConnectionBuilder;
import com.olia.processfly.bpmn.template.impl.dsl.EventBuilder;

public class ProcessInstanceFactoryTest
{

    private ProcessInstanceFactory sut;

    @Before
    public void before()
    {
        sut = new ProcessInstanceFactory(mock(ApplyStatusToNodeInstanceCommand.class));
    }

    @Test
    public void shouldCreateNothingIfTemplateIsEmpty() throws Exception
    {
        ProcessTemplate template = mock(ProcessTemplate.class);
        when(template.getRootNode()).thenReturn(null);

        ProcessInstance instance = sut.create(template);
        assertThat(instance.getInstances()).isEmpty();
    }

    @SuppressWarnings({ "rawtypes", "unchecked"})
    @Test
    public void shouldCreateAnInstanceForEachNode() throws Exception
    {
        Node aNode = EventBuilder.Event().connected(ConnectionBuilder.to(EventBuilder.Event()))
                .connected(ConnectionBuilder.to(EventBuilder.Event())).node();

        ProcessTemplate template = mock(ProcessTemplate.class);
        when(template.getRootNode()).thenReturn(aNode);

        ProcessInstance instance = sut.create(template);
        assertThat(instance.getInstances()).hasSize(3);
        assertThat(instance.getInstances()).doesNotHaveDuplicates();
    }

    @SuppressWarnings({ "rawtypes", "unchecked"})
    @Test
    public void anInstanceKnowsItTemplateNode() throws Exception
    {
        Node aNode = EventBuilder.Event().node();

        ProcessTemplate template = mock(ProcessTemplate.class);
        when(template.getRootNode()).thenReturn(aNode);

        ProcessInstance instance = sut.create(template);

        assertThat(instance.getInstanceFor(aNode).getNode()).isEqualTo(aNode);
    }

}
