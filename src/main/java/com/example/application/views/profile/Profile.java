package com.example.application.views.profile;

import com.example.application.models.user.User;
import com.example.application.views.findmatch.SwipeSection;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;

import static com.example.application.Application.currentUser;

@CssImport("./styles/views/profile/profile.css")
public class Profile extends VerticalLayout {

    private User user;
    Tabs preferences;

    public Profile() {
        setId("profile");
        Label name = new Label(currentUser.getName().get("firstName") + " " + currentUser.getName().get("lastName"));
        name.addClassName("ProfileNameLabel");
        Label age = new Label(currentUser.getAge());
        Label location = new Label(currentUser.getLocation().get("city") + ", " + currentUser.getLocation().get("state"));
        Label biography = new Label(currentUser.getBiography());
        biography.setId("biographyLabel");
        Image profilePic = new Image(currentUser.getProfilePic(), "");
        profilePic.setId("profilePic");
        profilePic.setHeight("150px");
        profilePic.setWidth("150px");
        Tab travellerTab = new Tab("I want to Travel");
        Tab hostTab = new Tab("I want to Host");
        preferences = new Tabs(travellerTab, hostTab);
        preferences.setSelectedIndex(currentUser.getPreference());

        HorizontalLayout hL = new HorizontalLayout();
        VerticalLayout userDesc = new VerticalLayout();

        HorizontalLayout images = new HorizontalLayout();
        images.setId("profileImages");
        Image pic1 = new Image("images/profileImages/IMG_0642.JPG", "Profile Pic");
        Image pic2 = new Image("images/profileImages/IMG_3365.jpg", "Profile Pic");
        Image pic3 = new Image("images/profileImages/IMG_3745.JPG", "Profile Pic");
        pic1.addClassName("images");
        pic3.addClassNames("images", "image2");
        pic2.addClassName("images");
        images.setVerticalComponentAlignment(Alignment.CENTER, pic1, pic3, pic2);
        images.setAlignItems(Alignment.AUTO);
        images.add(pic1, pic3, pic2);
        images.setJustifyContentMode(JustifyContentMode.CENTER);

        Anchor logout = new Anchor("logout", "Log out");

        setMaxWidth("40%");
        setMinHeight("100%");
        hL.add(age, location);
        userDesc.add(hL, biography);
        userDesc.setHorizontalComponentAlignment(Alignment.CENTER, hL, biography);
        setHorizontalComponentAlignment(Alignment.CENTER, name, userDesc, images, preferences, profilePic, logout);
        setJustifyContentMode(JustifyContentMode.CENTER);



        add(name, profilePic, userDesc, preferences, images, logout);
    }

    public Profile(SwipeSection sW) {
        this();
        preferences.setSelectedIndex(currentUser.getPreference());
        preferences.addSelectedChangeListener(e -> {
            currentUser.switchPreference();
            sW.changeTitleLabel(preferences.getSelectedIndex());
        });
    }
}
