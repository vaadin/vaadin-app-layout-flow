package com.vaadin.flow.component.applayout;

/*
 * #%L
 * Vaadin App Layout
 * %%
 * Copyright (C) 2017 - 2018 Vaadin Ltd
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
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.PropertyDescriptor;
import com.vaadin.flow.component.PropertyDescriptors;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.router.RouterLayout;

/**
 * Server-side component for the {@code <vaadin-app-layout>} element.
 * Provides a quick and easy way to get a common application layout.
 */
@Tag("vaadin-app-layout")
@HtmlImport("frontend://bower_components/vaadin-app-layout/src/vaadin-app-layout.html")
public class AppLayout extends Component
    implements RouterLayout, HasComponents {
    private static final PropertyDescriptor<Boolean, Boolean> drawerFirstProperty = PropertyDescriptors
        .propertyWithDefault("drawerFirst", false);
    private static final PropertyDescriptor<Boolean, Boolean> drawerOpenedProperty = PropertyDescriptors
        .propertyWithDefault("drawerOpened", true);
    private static final PropertyDescriptor<Boolean, Boolean> overlayProperty = PropertyDescriptors
        .propertyWithDefault("overlay", false);

    private Component mainContent;

    public boolean isDrawerFirst() {
        return drawerFirstProperty.get(this);
    }

    public void setDrawerFirst(boolean drawerFirst) {
        drawerFirstProperty.set(this, drawerFirst);
    }

    public boolean isDrawerOpened() {
        return drawerOpenedProperty.get(this);
    }

    public void setDrawerOpened(boolean drawerOpened) {
        drawerOpenedProperty.set(this, drawerOpened);
    }

    public boolean isOverlay() {
        return overlayProperty.get(this);
    }

    public void setOverlay(boolean overlay) {
        overlayProperty.set(this, overlay);
    }

    /**
     * Returns the displayed content
     */
    public Component getMainContent() {
        return mainContent;
    }

    /**
     * Sets the displayed content.
     *
     * @param content {@link Component} to display in the content area
     */
    public void setMainContent(Component content) {

        removeMainContent();

        if(content != null) {
            this.mainContent = content;
            content.getElement().removeAttribute("slot");
            getElement().appendChild(content.getElement());
        }
    }

    /**
     * Removes the displayed content.
     */
    public void removeMainContent() {
        remove(this.mainContent);
        this.mainContent = null;
    }

    private void remove(Component component) {
        if (component != null) {
            component.getElement().removeFromParent();
        }
    }

    @Override
    public void remove(Component... components) {
        HasComponents.super.remove();
        for (Component component : components) {
            if (component.equals(this.mainContent)) {
                removeMainContent();
            }
        }
    }

    @Override
    public void removeAll() {
        HasComponents.super.removeAll();
        this.mainContent = null;
    }

    @Override
    public void showRouterLayoutContent(HasElement content) {
        final Component target = content.getElement().getComponent()
            .orElseThrow(() -> new IllegalArgumentException(
                "AppLayout content must be a Component"));

        beforeNavigate(target);
        setMainContent(target);
        afterNavigate(target);
    }

    /**
     * This hook is called before a navigation is being made into a route
     * which has this router layout as its parent layout.
     *
     * @param content {@link HasElement} the content component being added
     */
    protected void beforeNavigate(Component content) {
    }

    /**
     * This hook is called after a navigation is made into a route
     * which has this router layout as its parent layout.
     *
     * @param content {@link HasElement} the content component added
     */
    protected void afterNavigate(Component content) {
    }
}
