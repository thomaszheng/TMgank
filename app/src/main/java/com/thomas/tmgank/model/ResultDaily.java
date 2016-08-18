package com.thomas.tmgank.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by thomas on 2015/10/13.
 */
public class ResultDaily implements Serializable {
    public boolean error;
    public DailyGank results;

    public ResultDaily(boolean error, DailyGank results) {
        this.error = error;
        this.results = results;
    }
}
