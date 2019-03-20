package com.vaadin.flow.component.applayout;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.demo.DemoView;
import com.vaadin.flow.router.Route;

@Route("vaadin-app-layout")
public class AppLayoutView extends DemoView {

    @Override
    protected void initView() {
        addCard("App Layout usage in a demo application",
                new Div(new Label("Try out the demo which is using the `vaadin-app-layout-flow` component. "),
                        new Anchor("https://bakery-flow.demo.vaadin.com/login", "Open demo.")));

        appLayoutWithBrandingLogo();
        appLayoutWithBrandingText();
    }

    private void appLayoutWithBrandingText() {
        // @formatter:off
        // begin-source-example
        // source-example-heading: Basic App Layout with text branding
        AppLayout appLayout = new AppLayout();
        appLayout.setBranding(new H3("App Company"));

        // end-source-example
        // @formatter:on

        addCard("Basic App Layout with text branding");
    }

    private void appLayoutWithBrandingLogo() {
        try {
            // @formatter:off
            // begin-source-example
            // source-example-heading: Simple App Layout with brand logo
            AppLayout appLayout = new AppLayout();
            Tabs menu = new Tabs();
            appLayout.setMenu(menu);
            Image img = new Image("https://i.imgur.com/GPpnszs.png", "Vaadin Logo");
            img.setHeight("44px");
            appLayout.setBranding(img);

            menu.add(new Tab("Page 1"),
                    new Tab("Page 2"),
                    new Tab("Page 3"),
                    new Tab("Page 4"));

            Component content = new Span(new H3("Page title"),
                    new Span("Page content"));
            appLayout.setContent(content);
            // end-source-example
            // @formatter:on
        } catch (IllegalArgumentException e) {
            // Workaround is needed if the vaadin tabs version < 1.0.5 is used
        }

        addCard("Simple App Layout with brand logo");
    }

}
