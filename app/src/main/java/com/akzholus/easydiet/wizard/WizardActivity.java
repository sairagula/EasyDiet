package com.akzholus.easydiet.wizard;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.akzholus.easydiet.R;

public class WizardActivity extends FragmentActivity {

    FragmentPagerAdapter adapterViewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wizard);
        ViewPager vpPager = (ViewPager) findViewById(R.id.wizardViewPager);
        adapterViewPager = new WizardPageAdapter(getSupportFragmentManager(), vpPager);
        vpPager.setAdapter(adapterViewPager);
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder =
                new AlertDialog.Builder(this, R.style.AppCompatAlertDialogStyle);
        builder.setTitle("Discard all changes?");
        builder.setMessage("This app is built around your active goal. Do you really want to stop creating a goal and discard all your changes?");
        builder.setPositiveButton("Discard", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }

        });
        builder.setNegativeButton("Cancel", null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}