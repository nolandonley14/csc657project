package com.example.application.views.profile;

import com.example.application.views.main.MainView;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "profile", layout = MainView.class)
@PageTitle("Profile")
public class ProfileView extends HorizontalLayout {

    public ProfileView() {
        setId("profile-view");
        Profile p = new Profile();
        p.setMaxWidth("100%");
        Anchor edit = new Anchor("edit", "Edit Profile");
        p.add(edit);
        p.setHorizontalComponentAlignment(Alignment.CENTER, edit);
        setSizeFull();
        add(p);
    }
}
