package com.vaadin.flow.component.applayout;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.tabs.Tabs;

class AppLayoutMenu extends Tabs {

    private MenuItem selectedMenuItem;

    /**
     * Initializes a new app layout with a default menu.
     */
    AppLayoutMenu() {
        getElement().setAttribute("slot", "menu");
        getElement().setAttribute("theme", "minimal");

        addSelectedChangeListener(event -> {
            final MenuItem selectedTab = (MenuItem) getSelectedTab();

            if (selectedTab instanceof ActionMenuItem) {
                // Do not set actions (such as logout) as selected.
                getChildren().map(MenuItem.class::cast)
                    .filter(e -> e == selectedMenuItem).findFirst()
                    .ifPresent(this::selectMenuItem);
            } else {
                selectedMenuItem = selectedTab;
            }

            selectedTab.getListener().onComponentEvent(
                new MenuItemClickEvent(selectedTab, event.isFromClient()));
        });
    }

    /**
     * Selects a menu item.
     */
    void selectMenuItem(MenuItem menuItem) {
        setSelectedTab(menuItem);
        selectedMenuItem = menuItem;
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        try {
            selectedMenuItem = (MenuItem) getSelectedTab();
        } catch (IllegalArgumentException noMenuItemPresent) { }
    }

}
