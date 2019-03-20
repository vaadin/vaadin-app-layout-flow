package com.vaadin.flow.component.applayout;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.VaadinService;
import com.vaadin.flow.shared.Registration;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.mockito.Mockito.times;

public class AppLayoutMenuItemTest extends PowerMockTest {

    private AppLayoutMenuItem systemUnderTest;

    @Before
    public void setUp() {
        systemUnderTest = new AppLayoutMenuItem("Home");
        Mockito.when(registry.getNavigationTarget("page")).thenReturn(Optional.of(
            Page.class));

    }

    @Test
    public void setRoute_null() {
        systemUnderTest.setRoute(null);
    }

    @Test
    public void setIcon() {
        Icon icon = new Icon();
        systemUnderTest.setIcon(icon);
        Assert.assertEquals("img", icon.getElement().getAttribute("role"));

        List<Component> children = systemUnderTest.getChildren()
            .collect(Collectors.toList());

        Assert.assertEquals(2, children.size());
        Assert.assertTrue(children.get(0) instanceof Icon);
        Assert.assertEquals("Home", ((Span) children.get(1)).getText());
    }

    @Test
    public void setIcon_null() {
        systemUnderTest.setIcon(new Icon());
        systemUnderTest.setIcon(null);

        List<Component> children = systemUnderTest.getChildren()
            .collect(Collectors.toList());

        Assert.assertEquals(1, children.size());
        Assert.assertEquals("Home", ((Span) children.get(0)).getText());
    }

    @Test
    public void setTitle() {
        systemUnderTest.setTitle("Logout");

        Assert.assertEquals("Logout",
            systemUnderTest.getElement().getAttribute("title"));

        List<Component> children = systemUnderTest.getChildren()
            .collect(Collectors.toList());

        Assert.assertEquals(1, children.size());
        Assert.assertEquals("Logout", ((Span) children.get(0)).getText());
    }

    @Test
    public void setTitle_null() {
        systemUnderTest.setTitle(null);

        Assert.assertFalse(systemUnderTest.getElement().hasAttribute("title"));
    }

    @Test
    public void addMenuItemClickListener() {
        final ComponentEventListener<MenuItemClickEvent> listener = Mockito
            .mock(ComponentEventListener.class);
        final Registration registration = systemUnderTest
            .addMenuItemClickListener(listener);
        click(systemUnderTest);
        Mockito.verify(listener).onComponentEvent(Mockito.any());
        Mockito.reset(listener);
        registration.remove();
        click(systemUnderTest);
        Mockito.verify(listener, Mockito.never())
            .onComponentEvent(Mockito.any());
    }

    @Test
    public void testConstructor() {
        final String TITLE = "Page";
        final AppLayoutMenuItem menuItem = new AppLayoutMenuItem(
            VaadinIcon.LOCATION_ARROW.create(), TITLE, Page.class);
        Assert.assertTrue(menuItem.getIcon() instanceof Icon);
        Assert.assertEquals(TITLE, menuItem.getTitle());
        Assert.assertEquals(TITLE, menuItem.getElement().getAttribute("title"));
    }

    private void click(AppLayoutMenuItem menuItem) {
        menuItem.fireMenuItemClickEvent();
    }

    @Route("page")
    static class Page extends Div {}
}
