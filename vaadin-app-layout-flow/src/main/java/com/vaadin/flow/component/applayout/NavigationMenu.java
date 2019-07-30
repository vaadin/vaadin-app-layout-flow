package com.vaadin.flow.component.applayout;

/*
 * #%L
 * Vaadin App Layout
 * %%
 * Copyright (C) 2018 - 2019 Vaadin Ltd
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.TabVariant;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.RouterLink;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;

/**
 * Implements a navigation menu based on {@link Tabs}.
 */
public class NavigationMenu extends Composite<Tabs>
    implements AfterNavigationObserver {

    private DisplayStrategy menuDisplayStrategy;

    /**
     * Default constructor using {@link HorizontalMenuStrategy}
     */
    public NavigationMenu() {
        this(new HorizontalMenuStrategy());
    }

    /**
     *
     * @param menuDisplayStrategy {@link DisplayStrategy}
     */
    public NavigationMenu(DisplayStrategy menuDisplayStrategy) {
        this.menuDisplayStrategy = Objects.requireNonNull(menuDisplayStrategy);
        menuDisplayStrategy.configure(this);
    }

    /**
     * Selects the tab corresponding to the navigation event.
     * @param event {@link AfterNavigationEvent}
     */
    @Override
    public void afterNavigation(AfterNavigationEvent event) {

        final String target = event.getLocation().getPath();
        final Optional<Tab> tabToSelect = getContent().getChildren()
            .map(Tab.class::cast).filter(tab -> tab.getChildren().anyMatch(
                child -> child instanceof RouterLink && ((RouterLink) child)
                    .getHref().equals(target))).findFirst();
        tabToSelect.ifPresent(tab -> getContent().setSelectedTab(tab));
    }

    /**
     * Creates a tab for navigation.
     * @param icon {@link VaadinIcon}
     * @param title text on the tab.
     * @param viewClass {@link com.vaadin.flow.router.Route} Annotated view class.
     * @return created {@link Tab}
     */
    public Tab createNavigationTab(VaadinIcon icon, String title,
        Class<? extends Component> viewClass) {
        return createTab(
            populateLink(new RouterLink(null, viewClass), icon, title));
    }

    /**
     * Creates a tab with an anchor for external navigation.
     * @param icon {@link VaadinIcon}
     * @param title text on the tab.
     * @param href href for the link.
     * @return created {@link Tab}
     */
    public Tab createExternalLinkTab(VaadinIcon icon, String title,
        String href) {
        final Anchor a = populateLink(new Anchor(), icon, title);
        a.setHref(href);
        return createTab(a);
    }

    /**
     *
     * @param content content to add to the tab
     * @return created {@link Tab}
     */
    public Tab createTab(Component... content) {
        final Tab tab = menuDisplayStrategy.createTab(content);
        getContent().add(tab);
        return tab;
    }

    private static <T extends HasComponents> T populateLink(T a,
        VaadinIcon icon, String title) {
        if(icon != null) {
            a.add(icon.create());
        }

        if(title != null) {
            a.add(title);
        }
        return a;
    }

    /**
     * Configures menu and creates individual tabs.
     */
    public interface DisplayStrategy extends Serializable {

        /**
         *
         * @param menu {@link NavigationMenu}
         */
        void configure(NavigationMenu menu);

        /**
         * @param content content of the tab
         * @return created tab.
         */
        Tab createTab(Component... content);
    }

    /**
     * {@link DisplayStrategy} for horizontal menus with icons on top placed in the navbar.
     */
    public static class HorizontalMenuStrategy implements DisplayStrategy {

        @Override
        public void configure(NavigationMenu menu) {
            menu.getContent().setOrientation(Tabs.Orientation.HORIZONTAL);
        }

        @Override
        public Tab createTab(Component... content) {
            final Tab tab = new Tab();
            tab.addThemeVariants(TabVariant.LUMO_ICON_ON_TOP);
            tab.add(content);
            return tab;
        }
    }
}
