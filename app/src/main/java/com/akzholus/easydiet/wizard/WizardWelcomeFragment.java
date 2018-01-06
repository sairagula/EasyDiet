package com.akzholus.easydiet.wizard;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.akzholus.easydiet.R;

public class WizardWelcomeFragment extends WizardBaseFragment {

    public static WizardWelcomeFragment newInstance(ViewPager viewPager) {
        WizardWelcomeFragment fragment = new WizardWelcomeFragment();
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
        View view = inflater.inflate(R.layout.wizard_welcome, container, false);

        setNextButtonLabelAndListener(view, "Get started >", new WelcomeOnClickListener(viewPager));
        setPrevButtonLabelAndListener(view, "Maybe Later", new MaybeLaterOnClickListener(getActivity()));

        return view;
    }

    // Return to the previous activity when MaybeLater is clicked.
    static class MaybeLaterOnClickListener implements View.OnClickListener {
        private FragmentActivity activity;

        public MaybeLaterOnClickListener(FragmentActivity activity) {
            this.activity = activity;
        }

        @Override
        public void onClick(View view) {
            // Sometimes activity can be already destroyed, no need to do anything then.
            if (activity != null) {
                activity.finish();
            }
        }
    }

    // Start the wizard.
    static class WelcomeOnClickListener implements View.OnClickListener {
        private ViewPager viewPager;

        public WelcomeOnClickListener(ViewPager viewPager) {
            this.viewPager = viewPager;
        }

        @Override
        public void onClick(View view) {
            viewPager.setCurrentItem(1);
        }
    }
}