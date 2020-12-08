package com.example.application.views.findmatch;

import com.example.application.models.user.*;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

@CssImport("./styles/views/profile/profile.css")
public class PotentialMatchUserDescription extends VerticalLayout {

    public PotentialMatchUserDescription(User user) {
        setId("userDescription");
        HorizontalLayout hLayout = new HorizontalLayout();
        Label pMName = new Label(user.getName().get("firstName") + " " + user.getName().get("lastName"));
        Label pMLocation = new Label(user.getLocation().get("city") + ", " + user.getLocation().get("state"));
        Label pMAge = new Label(user.getAge());
        Label pMBio = new Label(user.getBiography());
        pMName.addClassName("PMNameLabel");
        hLayout.add(pMName, pMAge, pMLocation);
        add(hLayout, pMBio);
        setHorizontalComponentAlignment(Alignment.CENTER, hLayout, pMBio);
    }
}
