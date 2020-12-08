package com.example.application;

import com.example.application.models.user.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.vaadin.artur.helpers.LaunchUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * The entry point of the Spring Boot application.
 */
@SpringBootApplication(exclude = ErrorMvcAutoConfiguration.class)
public class Application extends SpringBootServletInitializer {

    public static User currentUser;

    public static void main(String[] args) {
        Map<String, String> name = new HashMap<String, String>();
        Map<String, String> location = new HashMap<String, String>();
        name.put("firstName", "Nolan");
        name.put("lastName", "Donley");
        location.put("city", "Macon");
        location.put("state", "GA");
        currentUser = new User(name, location, "21", "My name is Nolan." +
                " This is my biography! This is a really long biography that hopefully takes two lines.",
                "password", "images/profile.jpg", true);
        LaunchUtil.launchBrowserInDevelopmentMode(SpringApplication.run(Application.class, args));
    }

}
