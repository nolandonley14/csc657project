package com.example.application.models.user;

import com.example.application.models.firebase.HasKey;
import com.example.application.models.matches.Match;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;

public class User implements HasKey {

    private String id;
    private Map<String, String> name;
    private LocalDate birthday;
    private String age;
    private Map<String, String> location;
    private String biography;
    private String password;
    private String profilePic;
    private Boolean traveller;
    private ArrayList<Match> matches;

    public User() {
        this.name = null;
        this.location = null;
        this.age = null;
        this.biography = null;
        this.password = null;
        this.profilePic = null;
        this.traveller = null;
        this.matches = new ArrayList<>();
    }

    public User(Map<String, String> name, Map<String, String> location, String age, String biography, String password, String profilePic, Boolean traveller) {
        this.name = name;
        this.location = location;
        this.age = age;
        this.biography = biography;
        this.password = password;
        this.profilePic = profilePic;
        this.traveller = traveller;
        this.matches = new ArrayList<>();
    }

    public ArrayList<Match> getMatches() {
        return matches;
    }

    public void addMatch(User u) {
        matches.add(new Match(u));
    }

    public String getId() { return this.id; }

    public Map<String, String> getName() {
        return this.name;
    }

    public String getAge() {
        return this.age;
    }

    public String getBiography() {
        return this.biography;
    }

    public Map<String, String> getLocation() {
        return this.location;
    }

    public String getProfilePic() { return this.profilePic; }

    public ArrayList<String> getMatchedPics() {
        ArrayList<String> pics = new ArrayList<String>();
        for (Match m : matches) {
            pics.add(m.getUser().getProfilePic());
        }
        return pics;
    }

    public int getPreference() {
        if (traveller) {
            return 0;
        } else {
            return 1;
        }
    }

    public void switchPreference() {
        this.traveller = !this.traveller;
    }

    @Override
    public String getKey() {
        return this.id;
    }

    @Override
    public void setKey(String key) {
        this.id = key;
    }
}
