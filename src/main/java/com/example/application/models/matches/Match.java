package com.example.application.models.matches;

import com.example.application.models.messages.Message;
import com.example.application.models.user.User;

import java.util.List;

public class Match {

    private List<Message> messages;
    private User matchedUser;

    public Match(User mU) {
        messages = null;
        matchedUser = mU;
    }

    public User getUser() { return matchedUser; }

}
