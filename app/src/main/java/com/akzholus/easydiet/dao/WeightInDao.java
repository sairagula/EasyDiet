package com.akzholus.easydiet.dao;

import com.akzholus.easydiet.valuetypes.WeightInVT;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sairagul on 1/23/18.
 */

public class WeightInDao {
    private static List<WeightInVT> weightInVTList = new ArrayList<>();

    public static List<WeightInVT> getWeightInVTList() {
        return weightInVTList;
    }

    public static void setWeightInVTList(List<WeightInVT> input) {
        weightInVTList.clear();
        weightInVTList.addAll(input);
    }

    public static WeightInVT getLatestWeightIn() {
        WeightInVT latest = null;
        for (WeightInVT weightInVT : weightInVTList) {
            if (latest == null) {
                latest = weightInVT;
            } else {
                if (weightInVT.getDate().after(latest.getDate())) {
                    latest = weightInVT;
                }

            }
        }
        return latest;
    }
}
