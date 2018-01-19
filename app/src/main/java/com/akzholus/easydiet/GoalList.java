package com.akzholus.easydiet;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class GoalList  extends ArrayAdapter <GoalVT>{

    private Activity context;
    private List<GoalVT> goalList;

    public GoalList (Activity context,List<GoalVT> goalList ){
        super(context, R.layout.goals,goalList);
        this.context = context;
        this.goalList = goalList;


    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        GoalVT goal = goalList.get(position);

        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.goals,null,true);

        TextView textView = (TextView) listViewItem.findViewById(R.id.textView);



//        textView.setText(goal.getValue());

        return listViewItem;


    }
}
