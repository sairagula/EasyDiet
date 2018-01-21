package com.akzholus.easydiet.common;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class User {
    public static boolean isLoggedIn() {
        return getCurrentUser() != null;
    }

    public static void logUserOut() {
        FirebaseAuth.getInstance().signOut();
    }

    public static String getUserName() {
        if (isLoggedIn()) {
            return getCurrentUser().getDisplayName();
        }
        return "";
    }

    public static FirebaseUser getCurrentUser() {
        return FirebaseAuth.getInstance().getCurrentUser();
    }
}