package com.vaadin.flow.component.applayout;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.dom.Element;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

public class AppLayoutTest {

    private AppLayout systemUnderTest;

    @Before
    public void setUp() {
        systemUnderTest = new AppLayout();
    }

    @Test
    public void setContent() {
        Div content = new Div();
        systemUnderTest.setContent(content);

        List<Element> children = systemUnderTest.getElement().getChildren()
            .collect(Collectors.toList());
        Assert.assertTrue(children.contains(content.getElement()));
    }

    @Test
    public void removeContent() {
        systemUnderTest.removeContent(); // No NPE.

        Div content = new Div();
        systemUnderTest.setContent(content);

        systemUnderTest.removeContent();

        List<Element> children = systemUnderTest.getElement().getChildren()
            .collect(Collectors.toList());
        Assert.assertFalse(children.contains(content));
        Assert.assertNull(systemUnderTest.getContent());
    }

}
