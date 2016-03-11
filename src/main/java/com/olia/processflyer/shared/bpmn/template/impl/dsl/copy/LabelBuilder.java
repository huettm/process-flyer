package com.olia.processflyer.shared.bpmn.template.impl.dsl.copy;

import com.olia.processflyer.shared.bpmn.template.element.Label;
import com.olia.processflyer.shared.bpmn.template.impl.LabelImpl;

public class LabelBuilder extends AbstractNodeBuilder<LabelImpl, Label, LabelBuilder> implements NodeBuilder<Label>
{
    private LabelBuilder()
    {
        super();
    }

    public static LabelBuilder Label()
    {
        return new LabelBuilder();
    }

    @Override
    protected LabelImpl createNewNode()
    {
        return new LabelImpl();
    }

    public LabelBuilder withText(String text)
    {
        ((LabelImpl) node()).setText(text);
        return this;
    }
}
