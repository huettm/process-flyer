package com.olia.processflyer.bpmn.instance.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.olia.processflyer.server.HackathonProcessMock;
import com.olia.processflyer.shared.bpmn.instance.impl.TemplateNodeExtractor;
import com.olia.processflyer.shared.bpmn.template.Node;
import com.olia.processflyer.shared.bpmn.template.ProcessTemplate;

public class TemplateNodeExtractorTest
{

    private ProcessTemplate template;

    private TemplateNodeExtractor sut = new TemplateNodeExtractor();

    @Before
    public void before()
    {
        template = new HackathonProcessMock().createTemplate();
    }

    @Test
    public void shouldExtractNodes()
    {
        List<Node<?>> extracted = sut.extractNodes(template);
        assertThat(extracted).isNotEmpty();
    }

}
