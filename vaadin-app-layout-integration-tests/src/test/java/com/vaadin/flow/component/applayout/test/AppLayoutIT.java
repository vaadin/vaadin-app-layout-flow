package com.vaadin.flow.component.applayout.test;

import com.vaadin.flow.component.applayout.testbench.AppLayoutElement;
import com.vaadin.flow.component.orderedlayout.testbench.HorizontalLayoutElement;
import com.vaadin.testbench.TestBenchElement;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AppLayoutIT extends AbstractParallelTest {

    @Before
    public void init() {
        getDriver().get(getBaseURL());
    }

    @Test
    public void branding() {
        Assert.assertEquals("Vaadin",
                $(AppLayoutElement.class).waitForFirst().getBranding().getText());
    }

    @Test
    public void content() {
        Assert.assertEquals("Welcome home",
                $(AppLayoutElement.class).waitForFirst().getContent().getText());
    }

    @Test
    public void routingMenuItems() {

        Assert.assertEquals("Welcome home",
                $(AppLayoutElement.class).waitForFirst().getContent().getText());

        $(AppLayoutElement.class).waitForFirst().getMenu(HorizontalLayoutElement.class)
                .$("a[href=\"Page2\"]").waitForFirst().click();

        Assert.assertEquals("This is Page 2",
                $(AppLayoutElement.class).waitForFirst().getContent().getText());

    }

    @Test
    public void navigateToNotFound() {
        getDriver().get(getBaseURL() + "/nonexistingpage");
        Assert.assertTrue($(AppLayoutElement.class).waitForFirst().getContent()
                .getText().contains("Could not navigate to"));

    }
}
