package com.akzholus.easydiet.listeners;

import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.Toast;

import com.akzholus.easydiet.fragments.WeightInDialogFragment;

public class FabOnClickListener {

    public void weightInFabClicked(View view, FragmentManager supportFragmentManager) {
        new WeightInDialogFragment().show(supportFragmentManager, "Tag");
    }
}
