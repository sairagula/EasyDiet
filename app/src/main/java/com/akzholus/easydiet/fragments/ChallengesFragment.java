package com.akzholus.easydiet.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.akzholus.easydiet.R;
import com.akzholus.easydiet.adapters.ChallengesRecycleViewAdapter;
import com.akzholus.easydiet.dao.ChallengesDao;
import com.akzholus.easydiet.valuetypes.ChallengeVT;

/**
 * Created by sairagul on 1/4/18.
 */

public class ChallengesFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.challenge, container, false);

        RecyclerView mRecyclerView = (RecyclerView) rootView.findViewById(R.id.challenges_recycler_view_id);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(new ChallengesRecycleViewAdapter(ChallengesDao.getCurrentChallenge()));

        return rootView;
    }
}
