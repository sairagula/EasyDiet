package com.akzholus.easydiet.listeners;

import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;

import com.akzholus.easydiet.MainActivity;
import com.akzholus.easydiet.R;
import com.akzholus.easydiet.common.User;

public class NavigationItemSelectedListener
        implements NavigationView.OnNavigationItemSelectedListener {
    static int random_count = 0;
    private MainActivity activity;
    private DrawerLayout drawerLayout;

    public NavigationItemSelectedListener(DrawerLayout drawerLayout, MainActivity activity) {
        this.activity = activity;
        this.drawerLayout = drawerLayout;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        int id = menuItem.getItemId();
        menuItem.setChecked(true);
        drawerLayout.closeDrawers();
        if (id == R.id.action_logout && User.isLoggedIn()) {
            User.logUserOut();
            activity.startLoginProcess();
        } else if (id == R.id.action_login && !User.isLoggedIn()) {
            activity.startLoginProcess();
        } else if (id == R.id.action_send_feedback) {
            sendEmail("EasyHabit - Questions/Feedback", "Email your questions/feedback");
        }
        return true;
    }


    public void sendEmail(String subject, String prompt) {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto", "akzholus@gmail.com", null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        activity.startActivity(Intent.createChooser(emailIntent, prompt));
    }

}