package com.vaadin.flow.component.applayout;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.Router;
import com.vaadin.flow.server.RouteRegistry;
import com.vaadin.flow.server.VaadinService;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.rmi.registry.Registry;
import java.util.Optional;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ UI.class, VaadinService.class})
public abstract class PowerMockTest {

    protected RouteRegistry registry;

    @Before
    public final void setupRoutingMocks() {
        // Isolate Flow's UI and routing mechanism for testing
        PowerMockito.mockStatic(UI.class);
        PowerMockito.mockStatic(VaadinService.class);

        UI ui = Mockito.mock(UI.class);
        VaadinService vaadinService = Mockito.mock(VaadinService.class);
        registry = Mockito.mock(RouteRegistry.class);
        Router router = Mockito.spy(new Router(registry));

        BDDMockito.given(UI.getCurrent()).willReturn(ui);
        BDDMockito.given(VaadinService.getCurrent()).willReturn(vaadinService);

        Mockito.when(ui.getRouter()).thenReturn(router);
        Mockito.when(vaadinService.getRouter()).thenReturn(router);

        // Let Flow resolve route URLs
        Mockito.doAnswer(invocationOnMock -> {
            Class routeClass = (Class) invocationOnMock.getArguments()[0];
            Route route = (Route) routeClass.getDeclaredAnnotation(Route.class);
            return route.value();
        }).when(router).getUrl(Mockito.any(Class.class));
        // Let Flow resolve route URLs
        Mockito.doAnswer(invocationOnMock -> {
            Class routeClass = (Class) invocationOnMock.getArguments()[0];
            Route route = (Route) routeClass.getDeclaredAnnotation(Route.class);
            return Optional.ofNullable(route).map(Route::value);
        }).when(registry).getTargetUrl(Mockito.any(Class.class));
    }

}