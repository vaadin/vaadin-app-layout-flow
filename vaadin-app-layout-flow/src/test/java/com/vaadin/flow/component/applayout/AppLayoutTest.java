package com.vaadin.flow.component.applayout;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.dom.Element;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

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
        assertTrue(children.contains(content.getElement()));
    }

    @Test
    public void setMainContentNull() {
        systemUnderTest.setMainContent(null); // No NPE.

        Div content = new Div();
        systemUnderTest.setMainContent(content);

        systemUnderTest.setMainContent(null);

        List<Element> children = systemUnderTest.getElement().getChildren()
            .collect(Collectors.toList());
        assertFalse(children.contains(content.getElement()));
        assertNull(systemUnderTest.getMainContent());
    }

    @Test
    public void addToDrawer() {
        final Component component = new Div();
        systemUnderTest.addToDrawer(component);
        assertEquals("drawer",
            component.getElement().getAttribute("slot"));
        assertEquals(systemUnderTest, component.getParent().orElse(null));
    }

    @Test
    public void addToNavbar() {
        final Component component = new Div();
        systemUnderTest.addToNavbar(component);
        assertEquals("navbar",
            component.getElement().getAttribute("slot"));
        assertEquals(systemUnderTest, component.getParent().orElse(null));
    }

}
