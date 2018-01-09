package com.akzholus.easydiet.wizard;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.akzholus.easydiet.R;
import com.akzholus.easydiet.common.Utils;


public class WizardStep3Fragment extends WizardBaseFragment {

    public static WizardStep3Fragment newInstance(ViewPager viewPager) {
        WizardStep3Fragment fragment = new WizardStep3Fragment();
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
        View view = inflater.inflate(R.layout.wizard_step3, container, false);

        setNextButtonLabelAndListener(view, "Create goal", new CreateGoalOnClickListener(getActivity(), view));
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
            viewPager.setCurrentItem(2);
        }
    }

    static class CreateGoalOnClickListener implements View.OnClickListener {
        private FragmentActivity activity;
        private TextInputLayout inputLayoutEmail;
        private EditText inputEmail;

        public CreateGoalOnClickListener(FragmentActivity activity, View view) {
            this.activity = activity;
            inputLayoutEmail = (TextInputLayout) view.findViewById(R.id.input_layout_referee_email);
            inputEmail = (EditText) view.findViewById(R.id.input_referee_email);
        }

        @Override
        public void onClick(View view) {
            if (validateEmail()) {
                Toast.makeText(view.getContext(), "Goal is created", Toast.LENGTH_LONG).show();
                activity.finish();
            }
        }


        private boolean validateEmail() {
            String email = inputEmail.getText().toString().trim();
            if (email.isEmpty() || !Utils.isValidEmail(email)) {
                inputLayoutEmail.setError(activity.getString(R.string.err_msg_email));
                return false;
            } else {
                inputLayoutEmail.setErrorEnabled(false);
            }
            return true;
        }
    }
}