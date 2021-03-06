package com.akzholus.easydiet.valuetypes;

import android.util.Log;

import com.akzholus.easydiet.common.Constants;
import com.akzholus.easydiet.common.Formatters;
import com.akzholus.easydiet.common.GoalProcessPolicy;
import com.akzholus.easydiet.dao.WeightInDao;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.joda.time.DateTime;
import org.joda.time.Days;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public final class GoalTextCardDataVT {
    private static final String TAG = "";
    private final Date startDate;
    private final Date endDate;
    private final int remainingDurationInDay;
    private final int goalDurationInWeeks;

    private final float goalPoundsPerWeek;
    private final float lostWeightSinceStartInPounds;
    private String refereeEmailAddress;
    private float pledgeAmount;
    //    private List<WeightInVT> weights = new ArrayList<>();
    private static String uid;
    private float latestweight;


    public GoalTextCardDataVT(Date startDate, Date endDate, int remainingDurationInDay,
                              float losingGoalPerWeekInPounds, int goalDurationInWeeks,
                              float lostWeightSinceStartInPounds, String emailAddress, float pledgeAmount) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.remainingDurationInDay = remainingDurationInDay;
        this.goalPoundsPerWeek = losingGoalPerWeekInPounds;
        this.goalDurationInWeeks = goalDurationInWeeks;
        this.lostWeightSinceStartInPounds = lostWeightSinceStartInPounds;
        refereeEmailAddress = emailAddress;
        this.pledgeAmount = pledgeAmount;
    }


    public static GoalTextCardDataVT fromGoal(GoalVT goal) throws ParseException {
        WeightInVT latest = WeightInDao.getLatestWeightIn();
        float latestWeight = goal.getActualWeights();
        if (latest != null) {
            latestWeight = latest.getWeight();
        }

        DateTime endDate = new DateTime(goal.getEndDate());
        int remainingDurationInDays = Days.daysBetween(DateTime.now().toLocalDate(), endDate.toLocalDate()).getDays() + 1;
        if (remainingDurationInDays <0) {
            remainingDurationInDays = 0;
        }

        Log.d(Constants.TAG, "Conversion: " + goal.getGoalId() + " " + remainingDurationInDays + " => " + endDate.toString() + " " + goal.getEndDate() + " " + DateTime.now().toString());
        Log.d(Constants.TAG, goal.getActualWeights() + " => LOST " + latestWeight);

        return new GoalTextCardDataVT(goal.getStartDate(), goal.getEndDate(),
                remainingDurationInDays,
                goal.getGoalPoundsPerWeek(),
                Constants.GOAL_LENGTH_IN_WEEKS,
                goal.getActualWeights() - latestWeight,
                goal.getRefereeEmailAddress(),
                goal.getPledgeAmount());


    }

    public DateTime getStartDate() {
        return new DateTime((startDate));
    }

    public DateTime getEndDate() {
        return getStartDate().plus(GoalProcessPolicy.getDurationBetweenCheckins().multipliedBy(getGoalDurationInWeeks()));
    }

    public String getFormattedStartDate() {
        return Formatters.formatGoalDateTime(startDate);
    }

    public String getFormattedEndDate() {
        return Formatters.formatGoalDateTime(endDate);
    }

    public int getRemainingDurationInDays() {
        return remainingDurationInDay;
    }

    public int getGoalDurationInWeeks() {
        return goalDurationInWeeks;
    }

    public int getGoalDurationInDays() {
        return goalDurationInWeeks * 7;
    }

    public float getGoalPoundsPerWeek() {
        return goalPoundsPerWeek;
    }

    public float getTotalTargetInPounds() {
        return goalDurationInWeeks * goalPoundsPerWeek;
    }

    public float getLostWeightSinceStartInPounds() {
        return lostWeightSinceStartInPounds < 0 ? 0f : lostWeightSinceStartInPounds;
    }

    public boolean madeMoreThanHalfProgress() {
        return getLostWeightSinceStartInPounds() > getTotalTargetInPounds() / 2;
    }


    public String getRefereeEmailAddress() {
        return refereeEmailAddress;
    }

    public float getPledgeAmount() {
        return pledgeAmount;
    }

}