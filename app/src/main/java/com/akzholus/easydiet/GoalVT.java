package com.akzholus.easydiet;

import android.support.v4.util.Preconditions;


/**
 * Created by sairagul on 1/8/18.
 */

import com.akzholus.easydiet.common.Formatters;

import org.joda.time.DateTime;

public final class GoalVT {
    private float actualWeight;
    private DateTime startDate;
    private DateTime endDate;
    private float goalPoundsPerWeek;
    private int goalDurationInWeeks;
    private int pledgeAmount;
    private String refereeEmailAddress;

    @Override
    public String toString() {
        return "Your weight is:" + this.getActualWeights() + "Your weekly goal is to loose:" + this.getGoalDurationInWeeks() + "You will start: " + this.getFormattedStartDate();
    }

    public float getActualWeights() {
        return actualWeight;
    }

    public void setActualWeights(float actualWeight) {
        this.actualWeight = actualWeight;
    }


    public String getFormattedStartDate() {
        return Formatters.formatGoalDateTime(startDate);
    }

    public void setFormattedStartDate(DateTime startDate) {
        this.startDate = startDate;
    }

    public String getFormattedEndDate() {
        return Formatters.formatGoalDateTime(endDate);
    }

    public void setFormattedEndDate(DateTime endDate) {
        this.endDate = endDate;
    }

    public int getGoalDurationInWeeks() {
        return goalDurationInWeeks;
    }

    public void setGoalDurationInWeeks(int goalDurationInWeeks) {
        this.goalDurationInWeeks = goalDurationInWeeks;
    }

    public float getGoalPoundsPerWeek() {
        return goalPoundsPerWeek;
    }

    public void setGoalPoundsPerWeek(float goalPoundsPerWeek) {
        this.goalPoundsPerWeek = goalPoundsPerWeek;
    }

    public float getPledgeAmount() {
        return pledgeAmount;
    }

    public void setPledgeAmount(int pledgeAmount){
        this.pledgeAmount = pledgeAmount;
    }

    public String getRefereeEmailAddress() {
        return refereeEmailAddress;
    }

    public void setRefereeEmailAddress(String refereeEmailAddress){
        this.refereeEmailAddress = refereeEmailAddress;
    }
}
