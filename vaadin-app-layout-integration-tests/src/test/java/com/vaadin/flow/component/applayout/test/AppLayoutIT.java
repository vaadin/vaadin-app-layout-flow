package com.vaadin.flow.component.applayout.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.vaadin.flow.component.applayout.testbench.AppLayoutElement;

public class AppLayoutIT extends AbstractParallelTest {

    @Before
    public void init() {
        getDriver().get(getBaseURL());
    }

    @Test
    public void content() {
        final AppLayoutElement layout = $(AppLayoutElement.class)
            .waitForFirst();
        Assert
            .assertEquals("Welcome home", layout.getContent().get(1).getText());

        Assert.assertNotNull(layout.getDrawerToggle());

        layout.$("a").attribute("href", "Page1").first().click();
        Assert.assertEquals("This is Page 1",
            $(AppLayoutElement.class).waitForFirst().getContent().get(1)
                .getText());

        layout.$("a").attribute("href", "Page2").first().click();
        Assert.assertEquals("This is Page 2",
            $(AppLayoutElement.class).waitForFirst().getContent().get(1)
                .getText());
    }

    @Test
    public void properties() {
        final AppLayoutElement layout = $(AppLayoutElement.class)
            .waitForFirst();
        Assert.assertEquals("vertical", layout.getOrientation());
        Assert.assertEquals(true, layout.isDrawerOpened());
        Assert.assertEquals(false, layout.isDrawerFirst());
        Assert.assertEquals(false, layout.isOverlay());
    }

    @Test
    public void navigateToNotFound() {
        getDriver().get(getBaseURL() + "/nonexistingpage");
        Assert.assertTrue(
            $(AppLayoutElement.class).waitForFirst().getContent().get(1)
                .getText().contains("Could not navigate to"));

    }
}
