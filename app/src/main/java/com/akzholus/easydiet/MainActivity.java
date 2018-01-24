package com.akzholus.easydiet;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.akzholus.easydiet.common.Constants;
import com.akzholus.easydiet.common.User;
import com.akzholus.easydiet.dao.WeightInDao;
import com.akzholus.easydiet.listeners.FabOnClickListener;
import com.akzholus.easydiet.listeners.NavigationItemSelectedListener;
import com.akzholus.easydiet.valuetypes.GoalVT;
import com.akzholus.easydiet.valuetypes.WeightInVT;
import com.akzholus.easydiet.wizard.WizardActivity;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<WeightInVT> weights = new ArrayList<>();
    private static final int RC_SIGN_IN = 123;

    private TextView userName;
    private TextView email;
    private MenuItem logout;
    private TabLayoutAdapter pageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Check if user is signed in (non-null) and update UI accordingly.
        if (!User.isLoggedIn()) {
            startLoginProcess();
        }

        // Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        overridePendingTransition(0, 0);

        // Get the ViewPager and set it's PagerAdapter so that it can display
        // items
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        pageAdapter = new TabLayoutAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pageAdapter);

        // Setup tab layout
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setupWithViewPager(viewPager);

        // DrawerLayout
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.app_name, R.string.app_name);
        drawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        // NavigationView
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigationId);
        navigationView.setNavigationItemSelectedListener(
                new NavigationItemSelectedListener(drawerLayout, this));
        View headerLayout = navigationView.getHeaderView(0); // 0-index header
        userName = (TextView) headerLayout.findViewById(R.id.navigation_username);
        email = (TextView) headerLayout.findViewById(R.id.navigation_email);
        logout = navigationView.getMenu().findItem(R.id.action_logout);
        updateNavigationViewContent();


    }

    private void updateNavigationViewContent() {
        if (User.isLoggedIn()) {
            userName.setText(User.getUserName());
            email.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());
        }
    }

    private void refresh() {
        pageAdapter.notifyDataSetChanged();
        updateNavigationViewContent();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            if (resultCode == RESULT_OK) {
                refresh();
                // Start wizard.
                Intent forwardIntent = new Intent(this, WizardActivity.class);
                startActivity(forwardIntent);
            } else {
                startLoginProcess();
            }
        }
    }


    // Used by FAB
    public void weightInFabClicked(View view) {
        new FabOnClickListener().weightInFabClicked(view, getSupportFragmentManager());
    }

    public void startLoginProcess() {
        startActivityForResult(
                // Get an instance of AuthUI based on the default app
                AuthUI.getInstance().createSignInIntentBuilder()
                        .setIsSmartLockEnabled(false)
                        .setAvailableProviders(
                                Arrays.asList(new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build(),
                                        new AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build())).build(),
                RC_SIGN_IN);
    }

}