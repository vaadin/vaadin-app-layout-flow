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

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.tabs.Tabs;

/**
 * Tabs for AppLayout.
 */
class AppLayoutMenu extends Tabs {

    private AppLayoutMenuItem selectedMenuItem;

    /**
     * Initializes a new app layout with a default menu.
     */
    AppLayoutMenu() {
        getElement().setAttribute("slot", "menu");
        getElement().setAttribute("theme", "minimal");

        addSelectedChangeListener(event -> {
            final AppLayoutMenuItem selectedTab = (AppLayoutMenuItem) getSelectedTab();

            if (selectedTab.getRoute() == null) {
                // If there is no route associated, set previous tab as selected
                if (selectedMenuItem != null) {
                    setSelectedTab(selectedMenuItem);
                }
            } else {
                // Update selected tab if it is associated with a route.
                selectedMenuItem = selectedTab;
            }
            selectedTab.fireMenuItemClickEvent();
        });
    }

    /**
     * Selects a menu item.
     */
    void selectMenuItem(AppLayoutMenuItem menuItem) {
        setSelectedTab(menuItem);
        selectedMenuItem = menuItem;
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        try {
            final AppLayoutMenuItem selectedTab = (AppLayoutMenuItem) getSelectedTab();
            if (selectedTab.getRoute() != null) {
                selectedMenuItem = (AppLayoutMenuItem) getSelectedTab();
            }
        } catch (IllegalArgumentException noMenuItemPresent) {
        }
    }

    AppLayoutMenuItem getSelectedMenuItem() {
        return selectedMenuItem;
    }
}
