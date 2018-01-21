package com.akzholus.easydiet.valuetypes;
import com.akzholus.easydiet.common.Formatters;

//import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

import java.util.UUID;

public final class GoalVT {
    private String goalId;
    private float actualWeight;
    private Date startDate;
    private Date endDate;
    private float goalPoundsPerWeek;
    private int goalDurationInWeeks;
    private float pledgeAmount;
    private String refereeEmailAddress;

    public GoalVT() {
        goalId = UUID.randomUUID().toString();
    }

    @Override
    public String toString() {
        return "Your weight is:" + this.getActualWeights() + " Your weekly goal is to loose: " + this.getGoalPoundsPerWeek() + "You will start: " + this.getFormattedStartDate() + " Goal ends: " + this.getFormattedEndDate();
    }

    public String getGoalId() {
        return goalId;
    }

    public float getActualWeights() {

        return actualWeight;
    }

    public void setActualWeights(float actualWeight) {
        this.actualWeight = actualWeight;
    }

    public Date getStartDate() {
        return startDate;
    }

    public String getFormattedStartDate() {
        return Formatters.formatGoalDateTime(startDate);
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getFormattedEndDate() {
        return Formatters.formatGoalDateTime(endDate);
    }

    public void setEndDate(Date endDate) {
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

    public void setPledgeAmount(float pledgeAmount) {
        this.pledgeAmount = pledgeAmount;
    }

    public String getRefereeEmailAddress() {
        return refereeEmailAddress;
    }

    public void setRefereeEmailAddress(String refereeEmailAddress) {
        this.refereeEmailAddress = refereeEmailAddress;
    }
}
