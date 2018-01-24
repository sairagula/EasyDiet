package com.akzholus.easydiet;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.akzholus.easydiet.common.Constants;
import com.akzholus.easydiet.common.User;
import com.akzholus.easydiet.dao.WeightInDao;
import com.akzholus.easydiet.valuetypes.GoalVT;
import com.akzholus.easydiet.valuetypes.WeightInVT;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Goals extends Fragment {
    private static final String TAG = "GoalFragment";

    private List<GoalVT> goals = new ArrayList<>();



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.goals, container, false);

        RecyclerView recList = (RecyclerView) rootView.findViewById(R.id.my_gaol_recycler_view);
        recList.setLayoutManager(new LinearLayoutManager(getActivity()));
        final GoalsAdapter goalsAdapter = new GoalsAdapter(goals);
        recList.setAdapter(goalsAdapter);

        if (User.isLoggedIn()) {
            FirebaseDatabase.getInstance().getReference().child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot allDataForUser) {
                    goals.clear();
                    Log.d(TAG, "DATA SNAPSHOT: " + allDataForUser.getKey() + " ," + allDataForUser.toString());
                    for (DataSnapshot child : allDataForUser.getChildren()) {
                        if (child.getKey().equals("goals")) {
                            for (DataSnapshot goalData : child.getChildren()) {
                                Log.d(TAG, "GOAL-ID SNAPSHOT: " + goalData.getKey() + " ," + goalData.getValue());
                                GoalVT goal = goalData.getValue(GoalVT.class);
//                            Log.d(TAG, "DATA: " + goal.toPrettyString());
                                goals.add(goal);

                            }
                        }
                    }
                    goalsAdapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


            FirebaseDatabase.getInstance().getReference().child(User.getCurrentUser().getUid()).child("weights").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot allWeights) {
                    List<WeightInVT> weightInVTList = new ArrayList<>();
                    for (DataSnapshot weightReport : allWeights.getChildren()) {
//                        Log.d(Constants.TAG, "onDataChange: HERE we got in the callback wheeee");
//                        Log.d(Constants.TAG, "onDataChange: Weight from " + weightReport.child("date") + " is " + weightReport.child("weight").getValue());
                        WeightInVT weightInVT = weightReport.getValue(WeightInVT.class);
                        weightInVTList.add(weightInVT);
//                        Log.d(Constants.TAG, "onDataChange: LASTWEIGHT:" + weightInVTList);
                    }
                    WeightInDao.setWeightInVTList(weightInVTList);
                    goalsAdapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }
        return rootView;
    }

}
