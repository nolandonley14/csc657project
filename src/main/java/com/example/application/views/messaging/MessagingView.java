package com.example.application.views.messaging;

import com.example.application.views.main.MainView;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "messaging", layout = MainView.class)
@PageTitle("Messaging")
@CssImport("./styles/views/about/about-view.css")
public class MessagingView extends HorizontalLayout {

    public MessagingView() {
        setId("about-view");
        setId("profile-view");
        MessagingWidget m = new MessagingWidget();
        m.title.setText("");
        m.setMaxWidth("100%");
        setSizeFull();
        add(m);
    }

}
