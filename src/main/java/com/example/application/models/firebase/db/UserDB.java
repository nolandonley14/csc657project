package com.example.application.models.firebase.db;

import com.example.application.models.user.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;

import java.util.HashMap;
import java.util.logging.Logger;

public class UserDB {

    private static final String DATABASE_NAMESPACE = "Users";

    public static DatabaseReference getUsersDb() {
        return Firebase.getDb().child(DATABASE_NAMESPACE);
    }

    public static void maybeCreateInitialData(DataSnapshot snapshot) {
        if (snapshot.hasChild(DATABASE_NAMESPACE)) {
            return;
        }

        add(new User());
        add(new User());
    }

    public static void add(User item) {
        getUsersDb().push().setValueAsync(item);
    }

    protected static Logger getLogger() {
        return Logger.getLogger("UserDB");
    }

    public static void update(String key, User item) {
        getLogger().info("Set user " + key + " to " + item);
        HashMap<String, Object> toUpdate = new HashMap<>();
        toUpdate.put(key, item);
        getUsersDb().updateChildrenAsync(toUpdate);
    }

    public static void delete(User item) {
        getLogger().info("Delete user " + item);
        HashMap<String, Object> toUpdate = new HashMap<>();
        toUpdate.put(item.getKey(), null);
        getUsersDb().updateChildrenAsync(toUpdate);

    }
}
