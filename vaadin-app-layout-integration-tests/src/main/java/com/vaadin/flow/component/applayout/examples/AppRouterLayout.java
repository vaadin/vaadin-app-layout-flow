package com.vaadin.flow.component.applayout.examples;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.page.BodySize;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

import java.util.function.IntFunction;
import java.util.stream.IntStream;

@BodySize
@Theme(Lumo.class)
public class AppRouterLayout extends AppLayout {

    private static final int NOTIFICATION_DURATION = 10000;


    public AppRouterLayout() {
        this.setBranding(new Span("Vaadin"));
        this.setMenu(createMenu());
    }

    private static Component createMenu() {
        final HorizontalLayout layout = new HorizontalLayout();
        layout.add(new RouterLink("Page 1",Page1.class));
        layout.add(new RouterLink("Page 2",Page2.class));
        layout.add(new Button("Logout", e -> UI.getCurrent().navigate("LoggedOut")));
        return layout;
    }

    private static Tab createTab(VaadinIcon icon, String title) {
        final Tab tab = new Tab();
        tab.add(icon.create(),new Text(title));
        return tab;
    }

}
