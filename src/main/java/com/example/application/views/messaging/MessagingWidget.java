package com.example.application.views.messaging;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.util.ArrayList;

import static com.example.application.Application.currentUser;

@CssImport("./styles/views/dashboard/Messaging.css")
public class MessagingWidget extends VerticalLayout {

    protected Label title;

    public MessagingWidget() {
        setId("messaging");
        title = new Label("Messaging");
        add(title);
        showNewImage();
    }

    public void showNewImage() {
        this.removeAll();
        Label title = new Label("Messaging");
        add(title);
        ArrayList<String> imageStrings = currentUser.getMatchedPics();
        ArrayList<Image> images = new ArrayList<Image>();
        if (imageStrings != null) {
            for (String s : imageStrings) {
                Image i = new Image(s, "");
                i.setHeight("75px");
                i.setWidth("75px");
                i.addClassName("MessagingImage");
                images.add(i);
            }
            HorizontalLayout matches = new HorizontalLayout();
            for (Image i : images) {
                matches.add(i);
            }
            this.add(matches);
            this.setHorizontalComponentAlignment(Alignment.START, matches);
        }
    }
}
