package com.vaadin.flow.component.applayout;

/*
 * #%L
 * Vaadin App Layout for Vaadin 10
 * %%
 * Copyright (C) 2017 - 2018 Vaadin Ltd
 * %%
 * This program is available under Commercial Vaadin Add-On License 3.0
 * (CVALv3).
 * 
 * See the file license.html distributed with this software for more
 * information about licensing.
 * 
 * You should have received a copy of the CVALv3 along with this program.
 * If not, see <http://vaadin.com/license/cval-3>.
 * #L%
 */

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;

import java.util.Objects;

/**
 * A menu item for navigation.
 * Navigation happens only when selection was fired from a client side.
 */
public class RoutingMenuItem extends MenuItem {

    private String route;

    /**
     * Constructs a new object with the given title and route.
     *
     * @param title
     *            the title to display
     * @param route
     *            the route to navigate on click
     */
    public RoutingMenuItem(String title, String route) {
        this(null, title, route);
    }

    /**
     * Constructs a new object with the given icon and route.
     *
     * @param icon
     *            the icon to display
     * @param route
     *            the route to navigate on click
     */
    public RoutingMenuItem(Component icon, String route) {
        this(icon, null, route);
    }

    /**
     * Constructs a new object with the given icon, title and route.
     *
     * @param icon
     *            the icon to display
     * @param title
     *            the title to display
     * @param route
     *            the route to navigate on the tab click
     */
    public RoutingMenuItem(Component icon, String title, String route) {
        super(icon, title);

        setRoute(route);
        setListener(event -> {
            if (!event.isFromClient()) {
                return;
            }
            UI.getCurrent().navigate(this.route);
        });
    }

    /**
     * Gets the route that would be navigated to if this menu item is selected.
     */
    public String getRoute() {
        return route;
    }

    /**
     * Sets the route to be navigated to when this menu item is selected.
     *
     * @param route
     */
    public void setRoute(String route) {
        Objects.requireNonNull(route, "Route required");
        this.route = route;
    }
}
