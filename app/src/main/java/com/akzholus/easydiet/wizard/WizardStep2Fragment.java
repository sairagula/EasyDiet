package com.akzholus.easydiet.wizard;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatSpinner;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.akzholus.easydiet.R;

public class WizardStep2Fragment extends WizardBaseFragment {

    public static WizardStep2Fragment newInstance(ViewPager viewPager) {
        WizardStep2Fragment fragment = new WizardStep2Fragment();
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
        View view = inflater.inflate(R.layout.wizard_step2, container, false);

        setNextButtonLabelAndListener(view, "Agree & Continue >", new NextOnClickListener(view, viewPager));
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
            viewPager.setCurrentItem(1);
        }
    }

    static class NextOnClickListener implements View.OnClickListener {
        private AppCompatSpinner moneySpinner;
        private ViewPager viewPager;

        public NextOnClickListener(View view, ViewPager viewPager) {

            moneySpinner = (AppCompatSpinner)view.findViewById(R.id.pled_money_input);
            this.viewPager = viewPager;
        }

        @Override
        public void onClick(View view) {
            String value = moneySpinner.getSelectedItem().toString();
            Float floatValue = Float.parseFloat(value.replace("$", "").trim());
            WizardActivity.getWizardGoal().setPledgeAmount(floatValue);

            viewPager.setCurrentItem(3);
        }

    }
}