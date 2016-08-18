package com.thomas.tmgank.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by thomas on 2015/10/13.
 */
public class DailyGank implements Serializable{
    public List<String> types;
    public List<List<Gank>> ganks;

    public int size() {
        return types.size();
    }
    public String getType(int index) {
        return types.get(index);
    }

    public List<Gank> getGanhuo(int index) {
        return ganks.get(index);
    }

    public DailyGank(List<String> types, List<List<Gank>> ganks) {
        this.types = types;
        this.ganks = ganks;
    }
}
