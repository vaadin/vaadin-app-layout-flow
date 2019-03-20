package com.vaadin.flow.component.applayout.examples;

import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.AppLayoutMenu;
import com.vaadin.flow.component.applayout.AppLayoutMenuItem;
import com.vaadin.flow.component.applayout.MenuItemClickEvent;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.page.BodySize;
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
        AppLayoutMenu appLayoutMenu = createMenu();
        this.setBranding(new Span("Vaadin"));

        ComponentEventListener<MenuItemClickEvent> listener = e -> Notification
            .show(e.getSource().getTitle() + " executed!",
                NOTIFICATION_DURATION, Notification.Position.BOTTOM_START);

        appLayoutMenu.addMenuItems(
            new AppLayoutMenuItem(VaadinIcon.SAFE_LOCK.create(), "Action 1",
                listener),
            new AppLayoutMenuItem(VaadinIcon.SAFE_LOCK.create(), "Action 2",
                listener),
            new AppLayoutMenuItem(VaadinIcon.LOCATION_ARROW.create(), "Page 1",
                Page1.class),
            new AppLayoutMenuItem(VaadinIcon.LOCATION_ARROW.create(), "Page 2",
                Page2.class),
            new AppLayoutMenuItem(VaadinIcon.HOME.create(), "Home", Home.class),
            new AppLayoutMenuItem(VaadinIcon.USER.create(), "Logout",
                e -> UI.getCurrent().navigate("LoggedOut")));
    }
}
