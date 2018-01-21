package com.akzholus.easydiet.valuetypes;

import java.util.Date;
import java.util.UUID;

/**
 * Created by sairagul on 1/20/18.
 */

public class WeightInVT {
    private String id;
    private Date date;
    private  float weight;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public WeightInVT(Date date, float weight) {
        id = UUID.randomUUID().toString();

        this.date = date;
        this.weight = weight;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public Date getDate() {

        return date;
    }

    public float getWeight() {
        return weight;
    }
}
