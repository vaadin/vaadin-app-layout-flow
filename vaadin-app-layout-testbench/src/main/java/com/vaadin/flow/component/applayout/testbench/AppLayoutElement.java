package com.vaadin.flow.component.applayout.testbench;

/*
 * #%L
 * Vaadin App Layout Testbench API
 * %%
 * Copyright (C) 2018 Vaadin Ltd
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

import com.vaadin.testbench.TestBenchElement;
import com.vaadin.testbench.elementsbase.Element;

import java.util.List;

@Element("vaadin-app-layout")
public class AppLayoutElement extends TestBenchElement {

    public TestBenchElement getBranding() {
        return $(TestBenchElement.class).attribute("slot", "branding").first();
    }

    public TestBenchElement getContent() {
        TestBenchElement contentPlaceholder = $(TestBenchElement.class).attribute("part", "content").first();

        return (TestBenchElement) executeScript("return arguments[0].firstElementChild.assignedNodes()[0];",
                contentPlaceholder);
    }

    public <T extends TestBenchElement> T getMenu(Class<T> clazz) {
        return $(clazz).attribute("slot", "menu").waitForFirst();
    }

    public AppLayoutMenuElement getAppLayoutMenuElement() {
        return getMenu(AppLayoutMenuElement.class);
    }

}
