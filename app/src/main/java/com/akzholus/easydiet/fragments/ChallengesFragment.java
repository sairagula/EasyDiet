package com.akzholus.easydiet.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.akzholus.easydiet.R;

/**
 * Created by sairagul on 1/4/18.
 */

public class ChallengesFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.challenge, container, false);
        return rootView;
    }
}
