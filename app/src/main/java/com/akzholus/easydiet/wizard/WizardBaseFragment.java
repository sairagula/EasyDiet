package com.akzholus.easydiet.wizard;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

import com.akzholus.easydiet.R;

public abstract class WizardBaseFragment extends Fragment {
    protected ViewPager viewPager;

    void setViewPager(ViewPager viewPager) {
        this.viewPager = viewPager;
    }

    void setNextButtonLabelAndListener(View view, String label, Button.OnClickListener onClickListener) {
        Button next = (Button) view.findViewById(R.id.next_button);
        next.setOnClickListener(onClickListener);
        next.setText(label);
    }

    void setPrevButtonLabelAndListener(View view, String label, Button.OnClickListener onClickListener) {
        Button prev = (Button) view.findViewById(R.id.prev_button);
        prev.setOnClickListener(onClickListener);
        prev.setText(label);
    }
}
