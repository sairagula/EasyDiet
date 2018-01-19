package com.akzholus.easydiet.wizard;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.akzholus.easydiet.GoalVT;
import com.akzholus.easydiet.R;

import com.akzholus.easydiet.common.Constants;
import com.akzholus.easydiet.common.GoalProcessPolicy;
import com.akzholus.easydiet.listeners.InputValidation;

//import org.joda.time.DateTime;

import static com.akzholus.easydiet.common.Formatters.formatGoalDateTime;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.DateFormat;

public class WizardStep1Fragment extends WizardBaseFragment {
    private static final String TAG = "";
//    private DatabaseReference mDatabase;

    Calendar cal = Calendar.getInstance();   // GregorianCalendar
    Date date = cal.getTime();
    DateFormat formatter;
    Calendar calendar_date;



    public static WizardStep1Fragment newInstance(ViewPager viewPager) {
        WizardStep1Fragment fragment = new WizardStep1Fragment();
        fragment.setViewPager(viewPager);
        return fragment;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        mDatabase = FirebaseDatabase.getInstance().getReference("goals");

    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.wizard_step1, container, false);

        setNextButtonLabelAndListener(view, "Next >", new NextOnClickListener(getActivity(), viewPager, view));
        setPrevButtonLabelAndListener(view, "< Back", new BackOnClickListener(viewPager));

        initalizeProgressBar(view);

        return view;
    }

    private void initalizeProgressBar(View view) {
        Calendar calendar_date = (Calendar) cal.clone();
        calendar_date.roll(Calendar.WEEK_OF_YEAR, 6 );

        AppCompatTextView goalEndBubble = (AppCompatTextView) view.findViewById(R.id.goalEndBubbleId);
//        String goalEnd = String.format("goal end: %s", formatGoalDateTime(
//                DateTime.now().plus(GoalProcessPolicy.getDurationBetweenCheckins().multipliedBy(Constants.GOAL_LENGTH_IN_WEEKS))));
        String goalEnd = String.format("goal ends on: " + formatGoalDateTime(calendar_date.getTime()));
        goalEndBubble.setText(goalEnd);

        Log.d(TAG, "DATE: "+ goalEnd );
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
            GoalVT goalVT = WizardActivity.getWizardGoal();
            goalVT.setActualWeights(currentWeightNum);
            goalVT.setGoalPoundsPerWeek(goalWeightNum);
            goalVT.setGoalDurationInWeeks(Constants.GOAL_LENGTH_IN_WEEKS);
            goalVT.setStartDate(new Date());

            Calendar cal = Calendar.getInstance();   // GregorianCalendar
            Date date = cal.getTime();
            Calendar calendar_date = (Calendar) cal.clone();
            calendar_date.roll(Calendar.WEEK_OF_YEAR, 6 );
            goalVT.setEndDate(calendar_date.getTime());
            //goalVT.setEndDate(DateTime.now().plus(GoalProcessPolicy.getDurationBetweenCheckins().multipliedBy(Constants.GOAL_LENGTH_IN_WEEKS)));
            return true;
        }
    }
}