package com.vaadin.flow.component.applayout.examples;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.applayout.NavigationMenu;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.BodySize;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

@BodySize
@Theme(Lumo.class)
public class HorizontalMenuLayout extends AppLayout {

    {
        final NavigationMenu menu = new NavigationMenu();
        menu.createNavigationTab(VaadinIcon.ARROW_RIGHT,"Page 1", HorizontalPage1.class);
        menu.createNavigationTab(VaadinIcon.ARROW_LEFT,"Page 2", HorizontalPage2.class);
        addToNavbar(menu);
    }

    @Route(layout = HorizontalMenuLayout.class)
    public static final class HorizontalPage1 extends Div {}
    @Route(layout = HorizontalMenuLayout.class)
    public static final class HorizontalPage2 extends Div {}
}
