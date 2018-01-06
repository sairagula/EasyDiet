package com.akzholus.easydiet.wizard;


import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.akzholus.easydiet.R;


public class WizardStep1Fragment extends WizardBaseFragment {

    public static WizardStep1Fragment newInstance(ViewPager viewPager) {
        WizardStep1Fragment fragment = new WizardStep1Fragment();
        fragment.setViewPager(viewPager);
        return fragment;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.wizard_step1, container, false);

        setNextButtonLabelAndListener(view, "Next >", new NextOnClickListener(getActivity(), viewPager, view));
        setPrevButtonLabelAndListener(view, "< Back", new BackOnClickListener(viewPager));


        return view;
    }

    static class BackOnClickListener implements View.OnClickListener {
        private ViewPager viewPager;

        public BackOnClickListener(ViewPager viewPager) {
            this.viewPager = viewPager;
        }

        @Override
        public void onClick(View view) {
            viewPager.setCurrentItem(0);
        }
    }

    static class NextOnClickListener implements View.OnClickListener {
        private FragmentActivity activity;
        private ViewPager viewPager;
        private TextInputLayout inputLayoutCurrentWeight;
        private EditText inputCurrentWeight;
        private TextInputLayout inputLayoutGoalWeight;
        private EditText inputGoalWeight;


        public NextOnClickListener(FragmentActivity activity, ViewPager viewPager, View view) {
            this.activity = activity;
            this.viewPager = viewPager;
        }

        @Override
        public void onClick(View view) {
                viewPager.setCurrentItem(2);
        }
    }
}