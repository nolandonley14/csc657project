package com.example.application.models.firebase.db;


import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.*;

import java.io.FileInputStream;
import java.io.IOException;

public class Firebase {
    private static FirebaseApp app;

    public static void setup() throws IOException {
        if (app != null) {
            return;
        }

        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(new FileInputStream("/Users/nolandonley/Desktop/serviceAccount.json")))
                .setDatabaseUrl("https://quest-52776.firebaseio.com/")
                .build();

        FirebaseApp.initializeApp();


        maybeGenerateData();

    }

    /**
     * Creates some initial data if the database is empty
     */
    private static void maybeGenerateData() {
        getDb().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                // Wait for initial data before deciding to create or not
                UserDB.maybeCreateInitialData(snapshot);
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
    }

    public static DatabaseReference getDb() {
        return FirebaseDatabase.getInstance(app).getReference();
    }

}
