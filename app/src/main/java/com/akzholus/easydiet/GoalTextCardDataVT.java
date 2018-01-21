package com.akzholus.easydiet;

import com.akzholus.easydiet.GoalVT;
import com.akzholus.easydiet.common.Formatters;

import org.joda.time.DateTime;

import java.util.Date;
import java.util.List;

public final class GoalTextCardDataVT {
    private final Date startDate;
    private final Date endDate;
    private final int remainingDurationInDay;
    private final int goalDurationInWeeks;

    private final float goalPoundsPerWeek;
    private final float lostWeightSinceStartInPounds;
    private String refereeEmailAddress;
    private float pledgeAmount;

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

    public static GoalTextCardDataVT fromGoal(GoalVT goal) {
        return new GoalTextCardDataVT(goal.getStartDate(), goal.getEndDate(), 24, goal.getGoalPoundsPerWeek(), 6, 0.4f,
                goal.getRefereeEmailAddress(), goal.getPledgeAmount());
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