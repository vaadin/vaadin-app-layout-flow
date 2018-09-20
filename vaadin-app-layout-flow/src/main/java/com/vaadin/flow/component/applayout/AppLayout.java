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

import com.helger.commons.annotation.VisibleForTesting;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.dom.Element;

import java.util.Objects;
import java.util.Optional;

/**
 * Server-side component for the {@code <vaadin-app-layout>} element.
 * Provides a quick and easy way to get a common application layout.
 */
@Tag("vaadin-app-layout")
@HtmlImport("frontend://bower_components/vaadin-app-layout/src/vaadin-app-layout.html")
public class AppLayout extends Component {

    private Element branding;
    private Element content;

    private final AppLayoutMenu menuTabs = new AppLayoutMenu();

    /**
     * Initializes a new app layout with a default menu.
     */
    public AppLayout() {
        getElement().appendChild(menuTabs.getElement());
    }

    /**
     * Sets the element into branding area
     *
     * @param branding
     *            the element to set into branding area
     */
    public void setBranding(Element branding) {
        Objects.requireNonNull(branding, "Branding cannot be null");

        removeBranding();

        this.branding = branding;
        branding.setAttribute("slot", "branding");

        getElement().appendChild(branding);
    }

    /**
     * Clears the branding area
     */
    public void removeBranding() {
        if (this.branding == null) {
            return;
        }

        getElement().removeChild(this.branding);

        this.branding = null;
    }

    /**
     * Clears existing menu items and sets the new the arguments.
     * @param menuItems
     */
    public void setMenuItems(MenuItem... menuItems) {
        menuTabs.removeAll();
        menuTabs.add(menuItems);
    }

    /**
     * Adds menu item to the menu
     *
     * @param menuItem
     *              Menu Item to add
     */
    public void addMenuItem(MenuItem menuItem) {
        menuTabs.add(menuItem);
    }

    /**
     * Removes menu item from the menu
     */
    public void removeMenuItem(MenuItem menuItem) {
        menuTabs.remove(menuItem);
    }

    /**
     * Gets the first {@link RoutingMenuItem} targeting a route.
     */
    Optional<MenuItem> getMenuItemTargetingRoute(String route) {
        return menuTabs.getChildren()
                .map(e -> (MenuItem) e)
                .filter(e -> e instanceof RoutingMenuItem)
                .filter(e -> ((RoutingMenuItem) e).getRoute().equals(route))
                .findFirst();
    }

    /**
     * Gets the currently selected menu item.
     */
    public MenuItem getSelectedMenuItem() {
        return (MenuItem) menuTabs.getSelectedTab();
    }

    /**
     * Returns a content
     */
    public Element getContent() {
        return content;
    }

    /**
     * Sets the displayed content.
     *
     * @param content
     *              Element to display in the content area
     */
    public void setContent(Element content) {
        Objects.requireNonNull(content, "Content cannot be null");

        removeContent();

        this.content = content;
        getElement().appendChild(content);
    }

    /**
     * Removes the displayed content.
     */
    public void removeContent() {
        if (this.content != null) {
            this.content.removeFromParent();
        }

        this.content = null;
    }

    @VisibleForTesting
    Component getMenu() {
        return menuTabs;
    }
}
