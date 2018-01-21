package com.akzholus.easydiet.fragments;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.akzholus.easydiet.R;
import com.akzholus.easydiet.common.User;
import com.akzholus.easydiet.listeners.InputValidation;
import com.akzholus.easydiet.valuetypes.WeightInVT;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

public class WeightInDialogFragment extends AppCompatDialogFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.weight_in_not_end_of_week, container, false);
        final TextInputLayout inputLayoutCurrentWeight = (TextInputLayout) view.findViewById(R.id.todays_weight_input_layout);
        final EditText inputCurrentWeight = (EditText) view.findViewById(R.id.todays_weight_input);
        inputLayoutCurrentWeight.setHint(String.format(
                view.getContext().getString(R.string.what_is_your_weight),
                "LB"));

        FloatingActionButton weightInSubmitButton = (FloatingActionButton) view.findViewById(R.id.fab_submit_todays_weight_button);
        weightInSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float weightFloatValue = InputValidation.validateAndReturnWeigthInput(
                        inputLayoutCurrentWeight,
                        inputCurrentWeight,
                        view.getContext());
                if (weightFloatValue <= 0) {
                    return;
                }
                WeightInVT weightInVT = new WeightInVT(new Date(), weightFloatValue);
                FirebaseDatabase.getInstance().getReference().child(User.getCurrentUser().getUid()).child("weights").child(weightInVT.getId()).setValue(weightInVT);
                Toast.makeText(view.getContext(), "Your weight is recorded.", Toast.LENGTH_SHORT).show();
                dismiss();
            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
    }
}