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
public class AppLayout extends Component implements RouterLayout {
    private static final PropertyDescriptor<Boolean, Boolean> drawerFirstProperty = PropertyDescriptors
        .propertyWithDefault("drawerFirst", false);
    private static final PropertyDescriptor<Boolean, Boolean> drawerOpenedProperty = PropertyDescriptors
        .propertyWithDefault("drawerOpened", true);
    private static final PropertyDescriptor<Boolean, Boolean> overlayProperty = PropertyDescriptors
        .propertyWithDefault("overlay", false);

    private Component content;

    /**
     * Defines how the navbar and the drawer will interact with each other on desktop view when the drawer is opened.
     * <ul>
     * <li>By default, the navbar takes the full available width and moves the drawer down.</li>
     * <li>If drawer-first is set, then the drawer will move the navbar, taking the full available height.</li>
     * </ul>
     */
    public boolean isDrawerFirst() {
        return drawerFirstProperty.get(this);
    }

    /**
     * @see #isDrawerFirst
     * @param drawerFirst new value for the drawerFirst property.
     */
    public void setDrawerFirst(boolean drawerFirst) {
        drawerFirstProperty.set(this, drawerFirst);
    }

    /**
     * Controls whether the drawer is opened (visible) or not.
     * Its default value depends on the viewport:
     * <ul>
     * <li>{@code true} for desktop size views</li>
     * <li>{@code false} for mobile size views</li>
     * </ul>
     */
    public boolean isDrawerOpened() {
        return drawerOpenedProperty.get(this);
    }

    /**
     * @see #isDrawerOpened
     * @param drawerOpened new value for the drawerOpened property.
     */
    public void setDrawerOpened(boolean drawerOpened) {
        drawerOpenedProperty.set(this, drawerOpened);
    }

    public boolean isOverlay() {
        return overlayProperty.get(this);
    }

    /**
     * Returns the displayed content
     */
    public Component getContent() {
        return content;
    }

    /**
     * Sets the displayed content.
     *
     * @param content {@link Component} to display in the content area
     */
    public void setContent(Component content) {

        removeContent();

        if (content != null) {
            this.content = content;
            content.getElement().removeAttribute("slot");
            add(content);
        }
    }

    /**
     * Adds the components to the <em>drawer</em> slot of this AppLayout.
     *
     * @param components Components to add to the drawer slot.
     * @throws NullPointerException if any of the components is null or if the components array is null.
     */
    public void addToDrawer(Component... components) {
        addToSlot("drawer", components);
    }

    /**
     * Adds the components to the <em>navbar</em> slot of this AppLayout.
     *
     * @param components Components to add to the navbar slot.
     * @throws NullPointerException if any of the components is null or if the components array is null.
     */
    public void addToNavbar(Component... components) {
        addToSlot("navbar", components);
    }

    /**
     * Removes the child components from the parent. Components can be in any slot or be the main content.
     *
     * @param components Components to remove.
     */
    public void remove(Component... components) {
        for (Component component : components) {
            if(this.content != null && this.content.equals(component)) {
                this.content = null;
            }
            remove(component);
        }
    }

    private void addToSlot(String slot, Component... components) {
        for (Component component : components) {
            setSlot(component, slot);
            add(component);
        }
    }

    private void add(Component component) {
        getElement().appendChild(component.getElement());
    }

    private static void setSlot(Component component, String slot) {
        component.getElement().setAttribute("slot", slot);
    }

    /**
     * Removes the displayed content.
     */
    private void removeContent() {
        remove(this.content);
        this.content = null;
    }

    private void remove(Component component) {
        if (component != null) {
            component.getElement().removeFromParent();
        }
    }

    /**
     * {@inheritDoc}
     *
     * @throws IllegalArgumentException if content is not a {@link Component}
     */
    @Override
    public void showRouterLayoutContent(HasElement content) {
        final Component target = content.getElement().getComponent()
            .orElseThrow(() -> new IllegalArgumentException(
                "AppLayout content must be a Component"));

        setContent(target);
    }
}
