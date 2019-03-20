package com.vaadin.flow.component.applayout;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.Router;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.RouteRegistry;
import com.vaadin.flow.server.VaadinService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AppLayoutRouterTest extends PowerMockTest {

    public class TestAppRouterLayout extends AppLayout {

        @Override
        protected void beforeNavigate(Component content) {
            events.add("Before nav to " + content.getClass().getSimpleName());
        }

        @Override
        protected void afterNavigate(Component content) {
            events.add("After nav to " + content.getClass().getSimpleName());
        }
    }

    @Route("route1")
    private static class Route1 extends Div {
    }

    @Route("route2")
    private static class Route2 extends Div {
    }

    @Route("dummy")
    private static class Dummy extends Div {
    }

    private final List<String> events = new ArrayList<>();

    private AppLayout systemUnderTest;

    @Before
    public void setup() {
        systemUnderTest = new TestAppRouterLayout();
    }

    @Test
    public void showRouterLayoutContent() {
        setupFlowRouting();

        new RouterLink(null,Route1.class);
        AppLayoutMenuItem route1MenuItem = new AppLayoutMenuItem("Route 1",
            Route1.class);
        AppLayoutMenu menu = new AppLayoutMenu();
        menu.addMenuItems(route1MenuItem,new AppLayoutMenuItem("Dummy", Dummy.class));
        systemUnderTest.setMenu(menu);
        Route1 route1 = new Route1();

        // Simulate navigation to Route1 (which has a matching menu item)
        systemUnderTest.showRouterLayoutContent(route1);

        // Ensure beforeNavigate() hook gets called
        Assert.assertEquals("Before nav to Route1", events.get(0));

        // Ensure afterNavigate() hook gets called
        Assert.assertEquals("After nav to Route1", events.get(1));

        // Ensure the matching menu item is selected
        Assert.assertEquals(route1MenuItem,
            menu.getSelectedMenuItem());
        Assert.assertEquals(route1,
            systemUnderTest.getContent());

        // Simulate navigation to Route2 (which has no matching menu item)
        systemUnderTest.showRouterLayoutContent(new Route2());

        // Ensure selected menu item remains unchanged
        Assert.assertEquals(route1MenuItem,
            menu.getSelectedMenuItem());
    }

    private void setupFlowRouting() {

        Mockito.when(registry.getNavigationTarget("route1")).thenReturn(Optional.of(Route1.class));
        Mockito.when(registry.getNavigationTarget("route2")).thenReturn(Optional.of(Route2.class));
        Mockito.when(registry.getNavigationTarget("dummy")).thenReturn(Optional.empty());
    }
}
