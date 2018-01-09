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

import com.akzholus.easydiet.common.Constants;
import com.akzholus.easydiet.listeners.InputValidation;

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
            inputLayoutCurrentWeight = (TextInputLayout) view.findViewById(R.id.input_layout_current_weight);
            inputCurrentWeight = (EditText) view.findViewById(R.id.input_current_weight);
            inputLayoutGoalWeight = (TextInputLayout) view.findViewById(R.id.input_layout_goal_weight);
            inputGoalWeight = (EditText) view.findViewById(R.id.input_goal_weight);
        }

        @Override
        public void onClick(View view) {
            if (validateInput()) {
                viewPager.setCurrentItem(2);
            }
        }

        private boolean validateInput() {
            float currentWeightNum = InputValidation.validateAndReturnWeigthInput(
                    inputLayoutCurrentWeight,
                    inputCurrentWeight,
                    activity);
            if (currentWeightNum < 0) {
                return false;
            }

            String goalWeight = inputGoalWeight.getText().toString().trim();
            float goalWeightNum;
            if (goalWeight.isEmpty()) {
                inputLayoutGoalWeight.setError(activity.getString(R.string.err_msg_goal_weight));
                return false;
            } else {
                goalWeightNum = Float.parseFloat(goalWeight);
                if (goalWeightNum < 0) {
                    inputLayoutGoalWeight.setError(activity.getString(R.string.err_msg_invalid_goal_weight));
                    return false;
                }

                // Allow maximum of 1 percent
                double maxAllowedGoal = currentWeightNum / 100;
                if (goalWeightNum > maxAllowedGoal) {
                    // Due to rounding error show -0.1 in error message. So if weight is 156,
                    // max would be displayed as 1.5, not 1.6
                    inputLayoutGoalWeight.setError(
                            String.format(activity.getString(R.string.err_msg_more_than_max), maxAllowedGoal - 0.1));
                    return false;
                }

                inputLayoutGoalWeight.setErrorEnabled(false);
            }

            return true;
        }
    }
}