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

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;

/**
 * Server-side component for the {@code <vaadin-drawer-toggle>} element.
 * It is a button that allows the user to open and close the drawer.
 * To use it, add it to the {@link AppLayout}, typically in the navbar slot.
 * <code>
 *     AppLayout layout = new AppLayout();
 *     layout.addToNavbar(new DrawerToggle());
 * </code>
 */
@Tag("vaadin-drawer-toggle")
@HtmlImport("frontend://bower_components/vaadin-app-layout/src/vaadin-drawer-toggle.html")
@JsModule("@vaadin/vaadin-app-layout/vaadin-drawer-toggle.js")
public class DrawerToggle extends Button {
}
