package com.example.application.views.findmatch;

import com.example.application.data.DatabaseAccess;
import com.example.application.models.user.User;
import com.example.application.views.messaging.MessagingWidget;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.net.MalformedURLException;
import java.util.Random;

import static com.example.application.Application.currentUser;

@CssImport("./styles/views/dashboard/Swipe-Section.css")
public class SwipeSection extends VerticalLayout {

    private Button like;
    private Button dislike;
    private User potentialMatch;
    private Image placeholder;
    private PotentialMatchUserDescription pMUserDescription;
    private int index = 0;
    private Label title;
    private VerticalLayout content;

    public SwipeSection(MessagingWidget mW) {
        Random rand = new Random();
        setId("swipe-section-view");
        title = new Label();
        content = new VerticalLayout();
        like = new Button(new Icon(VaadinIcon.CHECK));
        dislike = new Button(new Icon(VaadinIcon.CLOSE));
        Span match = new Span("CONGRATS! It's a MATCH!");
        Span noMatch = new Span("Oh no! Not a match. :(");
        Notification matchNotif = new Notification(match);
        matchNotif.setDuration(2000);
        matchNotif.setPosition(Notification.Position.MIDDLE);
        Notification noMatchNotif = new Notification(noMatch);
        noMatchNotif.setDuration(2000);
        noMatchNotif.setPosition(Notification.Position.MIDDLE);
        add(title);
        changeTitleLabel(currentUser.getPreference());
        showNext();
        setHorizontalComponentAlignment(Alignment.CENTER, title);
        like.addClickListener( e-> {
            if (rand.nextInt(10) > 4) {
                matchNotif.open();
                currentUser.addMatch(potentialMatch);
                mW.showNewImage();
            }else {
                noMatchNotif.open();
            }
            if (index < 20) {
                index++;
                showNext();
            } else {
                showNext();
            }
        });
        dislike.addClickListener(e -> {
            index++;
            if (index < 20) {
                showNext();
            } else {
                showNext();
            }
        });
    }

    public void changeTitleLabel(int pref) {
        removeAll();
        if (pref == 1) {
            title.setText("Match with users looking to travel to your city!");
        } else {
            title.setText("Match with users from cities you're interested in!");
        }
        add(title);
        showNext();
    }

    private User getNewPotentialUser() throws MalformedURLException {
        DatabaseAccess db = new DatabaseAccess();
        return db.getNewUser();
    }

    private void showNext() {
        content.removeAll();
        if (index != 20) {
            try {
                potentialMatch = getNewPotentialUser();
                placeholder = new Image(potentialMatch.getProfilePic(), "Profile Pic");
                placeholder.setId("matchPicture");
                pMUserDescription = new PotentialMatchUserDescription(potentialMatch);
                pMUserDescription.setId("swipe-user-description");
                placeholder.setHeight("400px");
                placeholder.setWidth("400px");
                HorizontalLayout hL = new HorizontalLayout();
                hL.add(dislike, placeholder, like);
                hL.setVerticalComponentAlignment(Alignment.CENTER, placeholder, dislike, like);
                content.add(hL, pMUserDescription);
                content.setHorizontalComponentAlignment(Alignment.CENTER, pMUserDescription, hL);
                content.setJustifyContentMode(JustifyContentMode.START);
                add(content);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        } else {
            title.setText("");
            Label emptyList = new Label("Sorry, there are no more new potential matches for right now. Please check " +
                    "back later. :)");
            add(emptyList);
            setHorizontalComponentAlignment(Alignment.CENTER, emptyList);
            setJustifyContentMode(JustifyContentMode.CENTER);
        }
    }

}

