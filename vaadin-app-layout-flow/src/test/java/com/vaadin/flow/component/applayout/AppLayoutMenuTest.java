package com.vaadin.flow.component.applayout;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Component;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AppLayoutMenuTest {
    private AppLayoutMenu systemUnderTest;

    @Before
    public void setUp() {
        systemUnderTest = new AppLayoutMenu();
    }

    @Test
    public void onAttach_noMenuItem() {
        // systemUnderTest.onAttach(new AttachEvent(systemUnderTest, true));
        Assert.assertEquals("menu",
            systemUnderTest.getElement().getAttribute("slot"));
    }
}
