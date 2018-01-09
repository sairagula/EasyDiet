package com.akzholus.easydiet.listeners;

import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.widget.EditText;

import com.akzholus.easydiet.R;
import com.akzholus.easydiet.common.Constants;

public final class InputValidation {

    /**
     * Gets weight input from the weight-input. Sets error message and return -1 if input is invalid.
     * Otherwise returns validated input.
     */
    public static float validateAndReturnWeigthInput(TextInputLayout inputLayoutCurrentWeight,
                                                     EditText inputCurrentWeight, Context context) {

        String currentWeight = inputCurrentWeight.getText().toString().trim();
        float currentWeightNum;
        if (currentWeight.isEmpty()) {
            inputLayoutCurrentWeight.setError(context.getString(R.string.err_msg_current_weight));
            return Constants.MISSING_WEIGHT;
        } else {
            currentWeightNum = Float.parseFloat(currentWeight);
            if (currentWeightNum <= 0) {
                inputLayoutCurrentWeight.setError(context.getString(R.string.err_msg_invalid_current_weight));
                return Constants.MISSING_WEIGHT;
            } else {
                inputLayoutCurrentWeight.setErrorEnabled(false);
                inputLayoutCurrentWeight.setError(null);
            }
        }
         return currentWeightNum;
    }
}
