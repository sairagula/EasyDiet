package com.akzholus.easydiet;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Goals extends Fragment {
    private static final String TAG = "GoalFragment";

    private ListView mListView;
    private List<GoalVT> goals = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.goals, container, false);

        mListView = (ListView) rootView.findViewById(R.id.listview);
        final ArrayAdapter<GoalVT> adapter = new ArrayAdapter<GoalVT>(rootView.getContext(), android.R.layout.simple_dropdown_item_1line, goals);

        mListView.setAdapter(adapter);
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
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return rootView;
    }
}
