package com.vaadin.flow.component.applayout;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.page.Viewport;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.demo.DemoView;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.PWA;

@Route("vaadin-app-layout")
public class AppLayoutView extends DemoView {

    @Override
    protected void initView() {
        addCard("App Layout usage in a demo application", new Div(new Label(
                "Try out the demo which is using the `vaadin-app-layout-flow` component. "),
                new Anchor("https://bakery-flow.demo.vaadin.com/login",
                        "Open demo.")));
        addCard("App layout with nabvar menu");
        addCard("App layout with drawer menu");
        addCard("App layout in mobile");
        addCard("App layout as main view for PWA");
        addCard("Routing example", "App layout with RouterLink");
    }

    // @formatter:off
    // begin-source-example
    // source-example-heading: App layout with nabvar menu
    public class AppLayoutWithNavbarMenu extends AppLayout {
        public AppLayoutWithNavbarMenu() {
            Image img = new Image("https://i.imgur.com/GPpnszs.png", "Vaadin Logo");
            Tabs tabs = new Tabs(new Tab("Home"), new Tab("About"));
            img.setHeight("44px");
            addToNavbar(img, tabs);
        }
    }
    // end-source-example
    // @formatter:on

    // @formatter:off
    // begin-source-example
    // source-example-heading: App layout with drawer menu
    public class AppLayoutWithDrawerMenu extends AppLayout {
        public AppLayoutWithDrawerMenu() {
            setPrimarySection(AppLayout.Section.DRAWER);
            Image img = new Image("https://i.imgur.com/GPpnszs.png", "Vaadin Logo");
            img.setHeight("44px");
            addToNavbar(new DrawerToggle(), img);
            Tabs tabs = new Tabs(new Tab("Home"), new Tab("About"));
            tabs.setOrientation(Tabs.Orientation.VERTICAL);
            addToDrawer(tabs);
        }
    }
    // end-source-example
    // @formatter:on

    // @formatter:off
    // begin-source-example
    // source-example-heading: App layout in mobile
    public class AppLayoutInMobile extends AppLayout {
        public AppLayoutInMobile() {
            Image img = new Image("https://i.imgur.com/GPpnszs.png", "Vaadin Logo");
            img.setHeight("44px");
            final boolean touchOptimized = true;
            addToNavbar(touchOptimized, new DrawerToggle(), img);
            Tabs tabs = new Tabs(new Tab("Home"), new Tab("About"));
            tabs.setOrientation(Tabs.Orientation.VERTICAL);
            addToDrawer(tabs);
        }
    }
    // end-source-example
    // @formatter:on

    // @formatter:off
    // begin-source-example
    // source-example-heading: App layout as main view for PWA
    @Viewport("width=device-width, minimum-scale=1, initial-scale=1, user-scalable=yes, viewport-fit=cover")
    @PWA(name = "My Application", shortName = "My App")
    class MainAppView extends AppLayout {

        public MainAppView() {
            Image img = new Image("https://i.imgur.com/GPpnszs.png", "Vaadin Logo");
            img.setHeight("44px");
            addToNavbar(new DrawerToggle(), img);
            Tabs tabs = new Tabs(new Tab("Home"), new Tab("About"));
            tabs.setOrientation(Tabs.Orientation.VERTICAL);
            addToDrawer(tabs);
        }
    }
    // end-source-example
    // @formatter:on

    // @formatter:off
    // begin-source-example
    // source-example-heading: App layout with RouterLink
    public class MainView extends AppLayout implements AfterNavigationObserver {

        private Tabs tabs = new Tabs();
        // List will contain all the hrefs in the same order as they were added to tabs
        // ["", "admin", "dashboard"]
        // index is used to setSelectedIndex on afterNavigation
        private List<String> hrefs = new ArrayList<>();

        public MainView() {
            addMenuTab("Main", DefaultView.class);
            addMenuTab("Admin", AdminView.class);
            addMenuTab("Dashboard", DashboardView.class);
            tabs.setOrientation(Tabs.Orientation.VERTICAL);
            addToDrawer(tabs);
            addToNavbar(new DrawerToggle());
        }

        private void addMenuTab(String text, Class<? extends Component> navigationTarget) {
            //Each tab will contain a RouterLink that navigates to the navigationTarget
            RouterLink link = new RouterLink(text, navigationTarget);
            hrefs.add(link.getHref());
            tabs.add(new Tab(link));
        }

        @Override
        public void afterNavigation(AfterNavigationEvent event) {
            tabs.setSelectedIndex(hrefs.indexOf(event.getLocation().getFirstSegment()));
        }
    }

    @Route(value = "", layout = MainView.class)
    public class DefaultView extends Div {
        public DefaultView() {
            add(new Span("Default view content"));
        }
    }

    @Route(value = "admin", layout = MainView.class)
    public class AdminView extends Div {
        public AdminView() {
            add(new Span("Admin view content"));
        }
    }

    @Route(value = "dashboard", layout = MainView.class)
    public class DashboardView extends Div {
        public DashboardView() {
            add(new Span("Dashboard view content"));
        }
    }
    // end-source-example
    // @formatter:on
}
