package com.vaadin.flow.component.applayout;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.dom.Element;

public class AppLayoutTest {

    private AppLayout systemUnderTest;

    @Before
    public void setUp() {
        systemUnderTest = new AppLayout();
    }

    @Test
    public void setMainContent() {
        Div content = new Div();
        systemUnderTest.setMainContent(content);

        List<Element> children = systemUnderTest.getElement().getChildren()
            .collect(Collectors.toList());
        Assert.assertTrue(children.contains(content.getElement()));
    }

    @Test
    public void removeMainContent() {
        systemUnderTest.removeMainContent(); // No NPE.

        Div content = new Div();
        systemUnderTest.setMainContent(content);

        systemUnderTest.removeMainContent();

        List<Element> children = systemUnderTest.getElement().getChildren()
            .collect(Collectors.toList());
        Assert.assertFalse(children.contains(content.getElement()));
        Assert.assertNull(systemUnderTest.getMainContent());
    }

}
