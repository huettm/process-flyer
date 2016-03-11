package com.olia.processfly.bpmn.instance.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.olia.processfly.bpmn.model.hackathon.HackathonProcessMock;
import com.olia.processfly.bpmn.template.Node;
import com.olia.processfly.bpmn.template.ProcessTemplate;

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
