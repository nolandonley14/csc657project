package com.example.application.views.dashboard;

import com.example.application.models.firebase.FirebaseDataProvider;
import com.example.application.models.user.User;
import com.example.application.views.findmatch.SwipeSection;
import com.example.application.views.main.MainView;
import com.example.application.views.messaging.MessagingWidget;
import com.example.application.views.profile.Profile;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;


@Route(value = "dashboard", layout = MainView.class)
@PageTitle("Dashboard")
@CssImport("./styles/views/dashboard/dashboard.css")
@RouteAlias(value = "", layout = MainView.class)
public class Dashboard extends HorizontalLayout {

    private SwipeSection ss;
    private Profile ps;
    private MessagingWidget ms;
    private FirebaseDataProvider<User> dataProvider;

    public Dashboard() {
        setId("dashboard");
//        dataProvider = new FirebaseDataProvider<>(User.class,
//                UserDB.getUsersDb());
//        System.out.println(dataProvider);
//        Map<String, String> name = new HashMap<String, String>();
//        Map<String, String> location = new HashMap<String, String>();
//        name.put("firstName", "Nolan");
//        name.put("lastName", "Donley");
//        location.put("city", "Macon");
//        location.put("state", "GA");
//        User currentUser = new User(name, location, "21", "My name is Nolan." +
//                " This is my biography! This is a really long biography that hopefully takes two lines.",
//                "password", "images/profile.jpg", true);
        ms = new MessagingWidget();
        ss = new SwipeSection(ms);
        ps = new Profile(ss);

        SplitLayout leftSide = new SplitLayout();
        leftSide.setId("leftSide");
        leftSide.setOrientation(SplitLayout.Orientation.VERTICAL);
        leftSide.addToPrimary(ss);
        leftSide.addToSecondary(ms);
        leftSide.setSplitterPosition(70);

        add(leftSide, ps);
        setVerticalComponentAlignment(Alignment.CENTER, ps);
    }

}
